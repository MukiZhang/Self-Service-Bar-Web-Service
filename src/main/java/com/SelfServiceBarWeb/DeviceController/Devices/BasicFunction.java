package com.SelfServiceBarWeb.DeviceController.Devices;

import cn.yuhi.dto.MimeMessageDTO;
import cn.yuhi.util.MailUtil;
import com.SelfServiceBarWeb.DeviceController.Files.Response;
import com.SelfServiceBarWeb.DeviceController.Files.gateWay;
import com.SelfServiceBarWeb.DeviceController.Files.ip_id_type;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Title: BasicFunction.java
 * Description: this class is the basic function that use to process the .txt file.
 * @author Jie Ji
 * @version 1.0
 */
public class BasicFunction {
    ///home/jijie/IdeaProjects/
    protected String gateWay_file = "../Self-Service-Bar-Web-Service/src/main/java/com/SelfServiceBarWeb/DeviceController/Files/gateWay.txt";
    protected String ip_id_t_file = "../Self-Service-Bar-Web-Service/src/main/java/com/SelfServiceBarWeb/DeviceController/Files/ip_id_type.txt";
    protected String response_file = "../Self-Service-Bar-Web-Service/src/main/java/com/SelfServiceBarWeb/DeviceController/Files/response.txt";
    static protected int Error = 0;

    public BasicFunction() {
    }

    /**
     *
     * @param line
     * @param fileName
     */
    public void WriteFiles_append(String line, String fileName) {

        FileWriter fw = null; //
        try {
            File file = new File(fileName);
            fw = new FileWriter(file, true);//
            fw.write(line + "\r\n");
            fw.flush();//

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //
            try {
                if (fw != null)
                    fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * @param id
     * @param type
     * @return
     */
    public Map<String, String> id_ip(String id, String type) {
        Map<String, String> map = new HashMap<>();
        ArrayList<String> lines = readFiles(ip_id_t_file);
        ArrayList<ip_id_type> id_ip = StrToip_id(lines);
        for (ip_id_type dp : id_ip) {
            if (dp.getDeviceNo() == Integer.valueOf(id) && dp.getType().contains(type)) {
                map.put("Ip", dp.getIP());
                map.put("DeviceId", dp.getDeviceID());
                map.put("port", String.valueOf(dp.getPort()));

            }
        }
        return map;
    }

    /**
     *
     * @param fileName
     * @return
     */
    public ArrayList<String> readFiles(String fileName) {
        ArrayList<String> lines = new ArrayList<>();
        File file = new File(fileName);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String tempString;

            while ((tempString = reader.readLine()) != null) {
                lines.add(tempString);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    /**
     *
     * @param lines
     * @param fileName
     */
    public void WriteFiles(ArrayList<String> lines, String fileName) {

        FileWriter fw = null; //
        try {
            File file = new File(fileName);
            fw = new FileWriter(file);//

            for (String line : lines) {
                fw.write(line + "\r\n");
                fw.flush();//
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                if (fw != null)
                    fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * @param ID
     * @param type1
     * @param type_d
     */
    public void Delete(String ID, int type1, String type_d) {
        int i = 0;
        ArrayList<String> lines = new ArrayList<>();
        String fileName = "";
        switch (type1) {
            case 1: {
                fileName = ip_id_t_file;
                lines = readFiles(fileName);
                for (String line : lines) {
                    if (line.contains(ID))
                        lines.remove(i);
                    i++;
                }
                break;
            }
            case 2: {
                fileName = response_file;
                lines = readFiles(fileName);

                String Did = id_ip(ID, type_d).get("DeviceId");

                if (!Did.equals("")) {
                    for (String line : lines) {
                        if (line.contains(Did))
                            lines.remove(i);
                        i++;
                    }
                }

                break;
            }
        }

        WriteFiles(lines, fileName);

    }

    /**
     *
     * @param strs
     * @return
     */
    public ArrayList<ip_id_type> StrToip_id(ArrayList<String> strs) {
        ArrayList<ip_id_type> arrayList = new ArrayList<>();

        for (String str : strs) {
            String arr[] = str.split(" ");
            String info[] = new String[5];
            int i = 0;
            for (String a : arr) {
                if (a.length() != 0) {
                    info[i] = a;
                    i++;
                }
            }
            ip_id_type pdt = new ip_id_type(Integer.valueOf(info[0]), info[1], Integer.valueOf(info[2])
                    , info[3], info[4]);
            arrayList.add(pdt);
        }
        return arrayList;
    }

    /**
     *
     * @param strs
     * @return
     */
    public ArrayList<gateWay> StrToGateWay(ArrayList<String> strs) {
        ArrayList<gateWay> arrayList = new ArrayList<>();

        for (String str : strs) {
            String arr[] = str.split(" ");
            String info[] = new String[5];
            int i = 0;
            for (String a : arr) {
                if (a.length() != 0) {
                    info[i] = a;
                    i++;
                }
            }
            gateWay pdt = new gateWay(info[0], info[1], Integer.valueOf(info[2])
                    , info[3]);
            arrayList.add(pdt);
        }
        return arrayList;
    }

    public void sendMail(Mail mail) {


        MimeMessageDTO mimeDTO = new MimeMessageDTO();
        mimeDTO.setSentDate(new Date());
        mimeDTO.setSubject(mail.subject);
        switch (mail.type) {
            case 1: {//send message

                mimeDTO.setText(mail.info);

                if (MailUtil.sendEmail(mail.senderAdd, mail.passWord, mail.receiverAdd, mimeDTO)) {
                    System.out.println("邮件发送成功！");
                } else {
                    System.out.println("邮件发送失败!!!");
                }

                break;
            }
            case 2: {
                mimeDTO.setText("Please check the attachment.\n请查收附件。");
                ArrayList<String> filepath = new ArrayList<>();
                filepath.add(mail.info);
                //todo
                filepath.add("/home/jijie/IdeaProjects/first/src/Files/ip_id_type.txt");
                if (MailUtil.sendEmailByFile(mail.senderAdd, mail.passWord, mail.receiverAdd, mimeDTO, filepath)) {
                    System.out.println("邮件发送成功！");
                } else {
                    System.out.println("邮件发送失败!!!");
                }
                //send with attachment
                break;
            }


        }

    }
    /**
     *
     * @param strs the string used to change to Response object
     * @return
     */
    public ArrayList<Response> StrToResp(ArrayList<String> strs) {
        ArrayList<Response> arrayList = new ArrayList<>();

        for (String str : strs) {
            String arr[] = str.split(" ");
            String info[] = new String[6];
            int i = 0;
            for (String a : arr) {
                if (a.length() != 0) {
                    info[i] = a;
                    i++;
                }
            }
            Response pdt = new Response(Integer.valueOf(info[0]), info[1],
                    Integer.valueOf(info[2])
                    , info[3], Integer.valueOf(info[4]), info[5]);
            arrayList.add(pdt);
        }
        return arrayList;
    }

}
