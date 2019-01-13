package com.SelfServiceBarWeb.service;

import com.SelfServiceBarWeb.constant.ResponseMessage;
import com.SelfServiceBarWeb.mapper.AdministratorMapper;
import com.SelfServiceBarWeb.mapper.SeatMapper;
import com.SelfServiceBarWeb.mapper.TableMapper;
import com.SelfServiceBarWeb.model.*;
import com.SelfServiceBarWeb.model.request.LoginRequest;
import com.SelfServiceBarWeb.utils.CommonUtil;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.common.primitives.Ints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Muki on 2018/11/5
 */
@Service
public class AdministratorService {
    private static final String mysqlSdfPatternString = "yyyy-MM-dd HH:mm:ss";
    private static final Integer row = 15;
    private static final Integer column = 25;
    private final AdministratorMapper administratorMapper;
    private final SeatMapper seatMapper;
    private final TableMapper tableMapper;

    @Autowired
    public AdministratorService(AdministratorMapper administratorMapper, SeatMapper seatMapper, TableMapper tableMapper) {
        this.administratorMapper = administratorMapper;
        this.seatMapper = seatMapper;
        this.tableMapper = tableMapper;
    }

    public Administrator login(LoginRequest loginRequest) throws Exception {
        SimpleDateFormat mysqlSdf = new java.text.SimpleDateFormat(mysqlSdfPatternString);
        Administrator administrator = administratorMapper.getByNameAndPassword(loginRequest.getUserName(), loginRequest.getPassword());
        if (administrator == null)
            throw new SelfServiceBarWebException(403, ResponseMessage.ERROR, ResponseMessage.LOGIN_FAILED);
        else {
            //采用jwt获得token
            Date createTime = new Date();
            //每七天需要重新登录(可以直接将过期时间写入token，解析token时即可判断是否过期，而无需在代码中判断)
            Date expireTime = new Date(createTime.getTime() + 1000 * 60 * 60 * 24 * 7);
            Map<String, String> content = new HashMap<>();
            content.put("uid", administrator.getId());
            String token = CommonUtil.createJWT(content, "selfServiceWeb", createTime, expireTime);
            //更新数据库token_create_at，之后每次鉴权需要查看数据库查看自己的token是否是最新的
            administratorMapper.updateTokenCreateTimeById(mysqlSdf.format(createTime), administrator.getId());
            //将token返回作为登录凭证
            administrator.setLoginToken(token);
            administrator.setPassword(null);
            administrator.setToken_create_at(null);
            return administrator;
        }
    }

    public void logout(String token) throws Exception {
        String administratorId = getAdministratorIdFromToken(token);
        administratorMapper.updateTokenCreateTimeById(null, administratorId);
    }

    public HashMap<String, Object> getLayout(String token) throws Exception {
        getAdministratorIdFromToken(token);
        List<List<Integer>> res = new ArrayList<>();
        int[][] layoutArray = new int[row][column];
        HashMap<String, String> seatIds = new HashMap<>();

        List<Seat> seatPosition = seatMapper.getAllSeatPosition();
        for (Seat seat : seatPosition) {
            int x = Integer.valueOf(seat.getPosition_x());
            int y = Integer.valueOf(seat.getPosition_y());
            if (x >= row || y >= column)
                continue;
            layoutArray[x][y] = 1;
            seatIds.put(x + "+" + y, seat.getId());
        }

        List<Table> tablePosition = tableMapper.getAll();
        for (Table table : tablePosition) {
            int leftUpX = Integer.valueOf(table.getLeft_up_x_coordinate());
            int leftUpY = Integer.valueOf(table.getLeft_up_y_coordinate());
            int rightDownX = Math.min(Integer.valueOf(table.getRight_down_x_coordinate()), row);
            int rightDownY = Math.min(Integer.valueOf(table.getRight_down_y_coordinate()), column);
            for (int i = leftUpX; i <= rightDownX; i++) {
                for (int j = leftUpY; j <= rightDownY; j++)
                    layoutArray[i][j] = 2;
            }
        }

        for (int[] aLayoutArray : layoutArray) {
            List<Integer> listSub = new ArrayList<>(Ints.asList(aLayoutArray));
            res.add(listSub);
        }
        HashMap<String, Object> resMap = new HashMap<>();
        resMap.put("seatIds", seatIds);
        resMap.put("layout", res);
        resMap.put("row", row);
        resMap.put("column", column);
        return resMap;
    }

    public String getAdministratorIdFromToken(String token) throws Exception {
        SimpleDateFormat mysqlSdf = new java.text.SimpleDateFormat(mysqlSdfPatternString);
        if (Objects.equals(token, "noToken"))
            throw new SelfServiceBarWebException(403, ResponseMessage.ERROR, ResponseMessage.DO_NOT_LOGIN);
        Date now = new Date();
        DecodedJWT jwt = CommonUtil.phraseJWT(token, "selfServiceWeb", ResponseMessage.INVALID_USER_TOKEN);
        String userId = JSONObject.parseObject(jwt.getSubject()).getString("uid");
        String tokenCreateTime = administratorMapper.getTokenCreateTime(userId);
        //数据库中token创建时间字段为空，说明用户已经注销登陆
        if (tokenCreateTime == null)
            throw new SelfServiceBarWebException(403, ResponseMessage.ERROR, ResponseMessage.DO_NOT_LOGIN);
        if (jwt.getIssuedAt().getTime() != mysqlSdf.parse(tokenCreateTime).getTime())
            throw new SelfServiceBarWebException(403, ResponseMessage.ERROR, ResponseMessage.ALREADY_LOGIN);
        else if (jwt.getExpiresAt().getTime() < now.getTime())
            throw new SelfServiceBarWebException(403, ResponseMessage.ERROR, ResponseMessage.EXPIRED_USER_TOKEN);
        else return userId;
    }

}
