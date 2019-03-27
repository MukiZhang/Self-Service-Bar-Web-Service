package com.SelfServiceBarWeb.server;


import com.SelfServiceBarWeb.constant.ChairCommand;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by Muki on 2019/1/15
 */
//todo 增加一个线程来循环获取客户端和主机的socket，将socket对象、ip地址、端口号、序号等信息写入类成员变量中，可用map存放
@Component
public class SocketServer {
    private static final char[] HEX_CHAR = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private Socket clientSocket;
    private ServerSocket serverSocket;

    public SocketServer() {
        try {
            System.out.println("socketServer start");
            serverSocket = new ServerSocket(8188);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private byte[] phraseCommand(byte[] commandContent, Byte[] addition) {
        List<Byte> command = new ArrayList<>();
        byte sum = (byte) 0xFF;
        //头部
        command.add(ChairCommand.DLE);
        command.add(ChairCommand.STX);
        //命令
        for (byte b : commandContent) {
            command.add(b);
            sum += b;
        }
        //附加数据
        if (addition != null) {
            for (byte b : addition) {
                command.add(b);
                sum += b;
            }
        }
        //尾部
        command.add(ChairCommand.DLE);
        command.add(ChairCommand.ETX);
        command.add(sum);
        //转换为array
        int i = 0;
        byte[] commandArray = new byte[command.size()];
        for (byte b : command)
            commandArray[i++] = b;
        System.out.println(bytesToHex(commandArray));
        return commandArray;
    }

    public Boolean getConnectionStatus() throws IOException {
        byte[] command = ChairCommand.GET_CONNECTION_STATUS;
        byte[] res = sendCommandToChair(phraseCommand(command, null));
        System.out.println(bytesToHex(res));
        return true;
    }

    /*public SeatStateEnum getSeatState(String ip,int pos)throws IOException{
        byte[] command=ChairCommand.GET_ALL_ITEMS_STATE;
        byte[] res=sendCommandToChair(phraseCommand(command,null));
        System.out.println(bytesToHex(res));

    }*/

    public void test() {
        try {
            if (clientSocket == null)
                clientSocket = serverSocket.accept();
            System.out.println(clientSocket.getPort());
            System.out.println(clientSocket.getInetAddress());
            OutputStream out = clientSocket.getOutputStream();
            byte[] responseBodyBuf = new byte[21];
            //dle
            responseBodyBuf[0] = 0x10;
            //stx
            responseBodyBuf[1] = 0x02;

            responseBodyBuf[2] = 0x00;
            responseBodyBuf[3] = 0x01;
            responseBodyBuf[4] = 0x01;
            responseBodyBuf[5] = 0x01;

            responseBodyBuf[18] = 0x10;
            responseBodyBuf[19] = 0x03;
            responseBodyBuf[20] = 0x02;

            out.write(responseBodyBuf);
            out.flush();
            System.out.println("size:" + clientSocket.getReceiveBufferSize());
            InputStream in = clientSocket.getInputStream();
            while (in.available() != 0) {
                BufferedReader buf = new BufferedReader(new InputStreamReader(in));
                System.out.println("server said:" + buf.readLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void open() throws IOException {
        InputStream in = null;
        OutputStream out = null;
        byte[] buf = new byte[1024];
        int len;
        try {
            if (clientSocket == null)
                clientSocket = serverSocket.accept();
            System.out.println(clientSocket.getPort());
            System.out.println(clientSocket.getInetAddress());
            out = clientSocket.getOutputStream();
            in = clientSocket.getInputStream();
            DataOutputStream socketOut = new DataOutputStream(out);
            byte[] responseBodyBuf = new byte[21];
            //dle
            responseBodyBuf[0] = 0x10;
            //stx
            responseBodyBuf[1] = 0x02;

            //序号
            responseBodyBuf[2] = 0x00;
            responseBodyBuf[3] = 0x31;
            //命令代码--打开客人模式
            responseBodyBuf[4] = 0x02;
            responseBodyBuf[5] = 0x03;
            //椅子序号
            responseBodyBuf[6] = 0x00;
            responseBodyBuf[7] = 0x01;

            responseBodyBuf[18] = 0x10;
            responseBodyBuf[19] = 0x03;
            responseBodyBuf[20] = 0x36;

            socketOut.write(responseBodyBuf);
            while ((len = in.read(buf)) != -1) {
                System.out.println("len:" + len);
                for (int i = 0; i < len; i++) {
                    System.out.println(buf[i]);
                }
                String data = new String(buf, 0, len);
//                System.out.println(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (out != null) {
                out.close();
            }
            if (in != null) {
                in.close();
            }
        }
    }

    public void close() throws IOException {
        InputStream in = null;
        OutputStream out = null;
        byte[] buf = new byte[1024];
        int len;
        try {
            if (clientSocket == null)
                clientSocket = serverSocket.accept();
            System.out.println(clientSocket.getPort());
            System.out.println(clientSocket.getInetAddress());
            out = clientSocket.getOutputStream();
            in = clientSocket.getInputStream();
            DataOutputStream socketOut = new DataOutputStream(out);
            byte[] responseBodyBuf = new byte[21];
            //dle
            responseBodyBuf[0] = 0x10;
            //stx
            responseBodyBuf[1] = 0x02;

            //序号
            responseBodyBuf[2] = 0x00;
            responseBodyBuf[3] = 0x31;
            //命令代码--关闭客人模式
            responseBodyBuf[4] = 0x02;
            responseBodyBuf[5] = 0x04;
            //椅子序号
            responseBodyBuf[6] = 0x00;
            responseBodyBuf[7] = 0x01;

            responseBodyBuf[18] = 0x10;
            responseBodyBuf[19] = 0x03;
            responseBodyBuf[20] = 0x37;

            socketOut.write(responseBodyBuf);
            if ((len = in.read(buf)) != -1) {
                System.out.println("len:" + len);
                for (int i = 0; i < len; i++) {
                    System.out.println(buf[i]);
                }
                String data = new String(buf, 0, len);
//                System.out.println(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (out != null) {
                out.close();
            }
            if (in != null) {
                in.close();
            }
        }
    }

    public void openById(int id) throws IOException {
        InputStream in = null;
        OutputStream out = null;
        byte[] buf = new byte[1024];
        int len;
        try {
            if (clientSocket == null)
                clientSocket = serverSocket.accept();
            System.out.println(clientSocket.getPort());
            System.out.println(clientSocket.getInetAddress());
            out = clientSocket.getOutputStream();
            in = clientSocket.getInputStream();
            DataOutputStream socketOut = new DataOutputStream(out);
            byte[] responseBodyBuf = new byte[21];
            //dle
            responseBodyBuf[0] = 0x10;
            //stx
            responseBodyBuf[1] = 0x02;

            //序号
            responseBodyBuf[2] = 0x00;
            responseBodyBuf[3] = 0x31;
            //命令代码--打开客人模式
            responseBodyBuf[4] = 0x02;
            responseBodyBuf[5] = 0x03;
            //椅子序号
            responseBodyBuf[6] = 0x00;
            responseBodyBuf[7] = 0x00;
            if (id % 2 == 1)
                responseBodyBuf[7] = 0x01;
            if (id / 2 == 1 && id % 2 == 0)
                responseBodyBuf[7] = 0x10;

            responseBodyBuf[18] = 0x10;
            responseBodyBuf[19] = 0x03;
            responseBodyBuf[20] = 0x36;

            socketOut.write(responseBodyBuf);
            while ((len = in.read(buf)) != -1) {
                System.out.println("len:" + len);
                for (int i = 0; i < len; i++) {
                    System.out.println(buf[i]);
                }
                String data = new String(buf, 0, len);
                System.out.println(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (out != null) {
                out.close();
            }
            if (in != null) {
                in.close();
            }
        }
    }

    private byte[] sendCommandToChair(byte[] command) throws IOException {
        InputStream in = null;
        OutputStream out = null;
        byte[] buf = new byte[1024];
        int len = 0;
        try {
            if (clientSocket == null)
                clientSocket = serverSocket.accept();
            System.out.println(clientSocket.getPort());
            System.out.println(clientSocket.getInetAddress());
            out = clientSocket.getOutputStream();
            in = clientSocket.getInputStream();
            DataOutputStream socketOut = new DataOutputStream(out);
            socketOut.write(command);
            socketOut.flush();
            if ((len = in.read(buf)) != -1) {
                System.out.println("receive length:" + len);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (out != null) {
                out.close();
            }
            if (in != null) {
                in.close();
            }
        }
        return Arrays.copyOfRange(buf, 0, len);
    }

    private static String bytesToHex(byte[] bytes) {
        char[] buf = new char[bytes.length * 2];
        int index = 0;
        for (byte b : bytes) { // 利用位运算进行转换
            buf[index++] = HEX_CHAR[b >>> 4 & 0xf];
            buf[index++] = HEX_CHAR[b & 0xf];
        }
        return new String(buf);
    }

}
