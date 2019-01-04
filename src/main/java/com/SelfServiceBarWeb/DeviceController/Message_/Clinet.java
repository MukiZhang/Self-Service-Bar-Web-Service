package com.SelfServiceBarWeb.DeviceController.Message_;

import com.SelfServiceBarWeb.DeviceController.Message_.light.TypeUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Title: Client.java
 * Description: this class is used to act as an client of the gateWay to send control and require message
 *
 * @author Jie Ji
 * @version 1.0
 */
public class Clinet {
    static private int MAX = 1024;

    public Clinet() {
    }

    /**
     * this method is used to broad cast message to the server in UDP protocol
     *
     * @param host    the server ip
     * @param port    the port of the server
     * @param message the message used to send to the gateway
     * @return the respounce message
     * @throws IOException
     */
    public String BroadCast(String host, int port, String message) throws IOException {
        /*
         * 向服务器端发送数据
         */
        String reply = null;
        InetAddress address = InetAddress.getByName(host);
        // 1.定义服务器的地址、端口号、数据

        //定义端口类型
        while (true) {
            //通过循环不同的向客户端发送和接受数据

            String send = message;//nextLine方式接受字符串
            byte[] data = send.getBytes();//将接收到的数据变成字节数组
            DatagramPacket packet = new DatagramPacket(data, data.length, address, port);//2.创建数据报，包含发送的数据信息
            DatagramSocket socket = new DatagramSocket(); // 3.创建DatagramSocket对象
            socket.send(packet);// 4.向服务器端发送数据报
            /*
             * 接收服务器端响应的数据
             */
            byte[] data2 = new byte[1024];
            //创建字节数组
            DatagramPacket packet2 = new DatagramPacket(data2, data2.length);// 1.创建数据报，用于接收服务器端响应的数据
            socket.receive(packet2);// 2.接收服务器响应的数据
            // 3.读取数据
            reply = new String(data2, 0, packet2.getLength());
            //创建字符串对象

            //输出提示信息
            socket.close();//4.关闭资源
            break;
        }
        return reply;
    }

    /**
     * this method is used to unicast message to the server in TCP protocol
     *
     * @param ip      the server ip
     * @param port    the port of the server
     * @param message he respounce message
     * @return
     */
    public String unicast(String ip, int port, String message) {
        InputStream in = null;
        OutputStream out = null;
        Socket socket = null;
        String result = null;
        try {
            socket = new Socket(ip, port);
            out = socket.getOutputStream();

            byte[] responseHeaderBuf = TypeUtil.int2Bytes(message.getBytes().length + 2);
            byte[] buf = message.getBytes();
            byte[] responseBodyBuf = new byte[message.getBytes().length + 2];
            int count = 1;
            responseBodyBuf[0] = 0x02;
            for (byte b : buf) {
                responseBodyBuf[count] = b;
                count++;
            }
            responseBodyBuf[count] = 0x03;

            out.write(responseHeaderBuf);
            out.write(responseBodyBuf);
            out.flush();
            in = socket.getInputStream(); // 读头信息，即Body长度
            byte[] headerBuf = new byte[4];
            in.read(headerBuf);
            int bodyLength = TypeUtil.bytesToInt(headerBuf, 0);
            byte[] bodyBuf = new byte[bodyLength];

            in.read(bodyBuf); // 输出

            result = getMessage(new String(bodyBuf));
            System.out.println("server said:" + result);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return result;
    }

    /**
     * this method is used to get the useful message from the respounce
     *
     * @param mes the respounce from server
     * @return
     */
    String getMessage(String mes) {
        String newM = null;
        String[] a = mes.split("}");
        int len = a.length;
        newM = a[0];
        for (int i = 1; i < len - 1; i++) {
            newM = newM + "}" + a[i];
        }
        return newM;
    }
}
