package com.SelfServiceBarWeb.DeviceController.Message_;

import com.SelfServiceBarWeb.DeviceController.Message_.light.TypeUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

public class Clinet {
    public Clinet() {
    }

    public String BroadCast(String host, int port, String message) throws IOException {
        /*
         * 向服务器端发送数据
         */
        String reply = "";
        int count = 0;
        InetAddress address = InetAddress.getByName(host);
        // 1.定义服务器的地址、端口号、数据

        //定义端口类型
        while (count < 5) {
            //通过循环不同的向客户端发送和接受数据

            byte[] data = message.getBytes();//将接收到的数据变成字节数组
            DatagramPacket packet = new DatagramPacket(data, data.length, address, port);//2.创建数据报，包含发送的数据信息
            DatagramSocket socket = new DatagramSocket(); // 3.创建DatagramSocket对象

            socket.setSoTimeout(1000);

            socket.send(packet);// 4.向服务器端发送数据报
            /*
             * 接收服务器端响应的数据
             */
            byte[] data2 = new byte[1024];
            //创建字节数组
            DatagramPacket packet2 = new DatagramPacket(data2, data2.length);// 1.创建数据报，用于接收服务器端响应的数据

            try {
                socket.receive(packet2);// 2.接收服务器响应的数据
                reply = new String(data2, 0, packet2.getLength());
                //创建字符串对象

                //输出提示信息
                socket.close();//4.关闭资源
            } catch (Exception e) {
                socket.close();
                System.out.println(e.getMessage());
            }

            // 3.读取数据


            if (!reply.isEmpty()) {
                break;
            } else {
                count++;
            }

        }
        return reply;
    }

    public String unicast(String ip, int port, String message) {
        InputStream in = null;
        OutputStream out = null;
        Socket socket = null;
        String result;
        result = null;
        int con = 0;
        try {
            socket = new Socket(ip, port);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            con++;
//            e.printStackTrace();
        } finally {
            if (con == 0) {
                try {
                    out = socket.getOutputStream();
                    socket.setSoTimeout(1000 * 2);
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
                    System.out.println(e.getMessage());
//                    e.printStackTrace();
                }
            }
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

    private String getMessage(String mes) {
        StringBuilder newM;
        String[] a = mes.split("}");
        int len = a.length;
        newM = new StringBuilder(a[0]);
        for (int i = 1; i < len - 1; i++) {
            newM.append("}").append(a[i]);
        }
        return newM.toString();
    }
}
