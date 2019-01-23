package com.SelfServiceBarWeb.server;


import org.springframework.stereotype.Component;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;


/**
 * Created by Muki on 2019/1/15
 */

@Component
public class SocketServer {
    //解码buffer
    private Charset cs = Charset.forName("UTF-8");
    //接受数据缓冲区
    private static ByteBuffer sBuffer = ByteBuffer.allocate(1024);
    //发送数据缓冲区
    private static ByteBuffer rBuffer = ByteBuffer.allocate(1024);
    //选择器（叫监听器更准确些吧应该）
    private static Selector selector;
    private Socket clientSocket;
    private ServerSocket serverSocket;

    public Socket getClientSocket() {
        return clientSocket;
    }

    public SocketServer() {
        try {
            System.out.println("socketServer start");
            serverSocket = new ServerSocket(8188);
//                clientSocket=serverSocket.accept();




                /*ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
                //设置为非阻塞
//                serverSocketChannel.configureBlocking(false);
                //获取套接字
                serverSocket = serverSocketChannel.socket();
                //
                serverSocket.setReuseAddress(true);
                //绑定端口号
                serverSocket.bind(new InetSocketAddress(8188));*/
            //打开监听器
//                selector = Selector.open();
//                //将通信信道注册到监听器
//                serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

                /*//打开监听器
                selector = Selector.open();
                //将通信信道注册到监听器
                serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);*/
        } catch (Exception e) {
            e.printStackTrace();
        }
        //}
    }

    /*public static  String bytesToHexString(byte[] bArray) {
        StringBuffer sb = new StringBuffer(bArray.length);
        String sTemp;
        for (int i = 0; i < bArray.length; i++) {
            sTemp =Integer.toHexString(0xFF & bArray[i]);
            if (sTemp.length() < 2)
                sb.append(0);
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
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

//            System.out.println(clientSocket.isOutputShutdown());
//            System.out.println(clientSocket.isInputShutdown());
            InputStream in = clientSocket.getInputStream();
            while (in.available() != 0) {
                BufferedReader buf = new BufferedReader(new InputStreamReader(in));
                System.out.println("server said:" + buf.readLine());
            }


            /*while (true){
                InputStream in=clientSocket.getInputStream();
                byte[] buffer = new byte[1024];
                int len = -1;
                while ((len = in.read(buffer)) != -1) {
                    //输出获取到所有字节  16进制
                    System.out.println(bytesToHexString(buffer));
                }

            }*/

            /*while (true) {
                selector.select();//select方法会一直阻塞直到有相关事件发生或超时
                Set<SelectionKey> selectionKeys = selector.selectedKeys();//监听到的事件
                for (SelectionKey key : selectionKeys) {
                    handle(key);
                }
                selectionKeys.clear();//清除处理过的事件
            }*/
//
//            BufferedReader buf = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//            System.out.println("server said:" + buf.readLine());

            /*InputStream in=clientSocket.getInputStream();
            byte[] headerBuf = new byte[4];
            in.read(headerBuf);
            int bodyLength = TypeUtil.bytesToInt(headerBuf, 0);
            byte[] bodyBuf = new byte[bodyLength];

            in.read(bodyBuf); // 输出

            String result = getMessage(new String(bodyBuf));
            System.out.println("server said:" + result);*/


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

            responseBodyBuf[2] = 0x00;
            responseBodyBuf[3] = 0x31;
            responseBodyBuf[4] = 0x02;
            responseBodyBuf[5] = 0x03;
            responseBodyBuf[6] = 0x00;
            responseBodyBuf[7] = 0x01;

            responseBodyBuf[18] = 0x10;
            responseBodyBuf[19] = 0x03;
            responseBodyBuf[20] = 0x36;

            socketOut.write(responseBodyBuf);
            /*out.write(responseBodyBuf);
            out.flush();*/

            while ((len = in.read(buf)) != -1) {
                System.out.println("len:" + len);
                for (int i = 0; i < len; i++) {
                    System.out.println(buf[i]);
                }
                String data = new String(buf, 0, len);
                System.out.println(data);
            }

            /*while (in.available()!=0){
                BufferedReader buf = new BufferedReader(new InputStreamReader(in));
                System.out.println("server said:" + buf.readLine());
            }*/
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

    public byte[] sendCommandToChair(byte[] command) throws IOException {
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
            socketOut.write(command);
            socketOut.flush();
            if ((len = in.read(buf)) != -1) {
                System.out.println("len:" + len);
                for (int i = 0; i < len; i++) {
                    System.out.println(buf[i]);
                }
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
        return buf;
    }

    /*private String getMessage(String mes) {
        StringBuilder newM;
        String[] a = mes.split("}");
        int len = a.length;
        newM = new StringBuilder(a[0]);
        for (int i = 1; i < len - 1; i++) {
            newM.append("}").append(a[i]);
        }
        return newM.toString();
    }*/

    /**
     * 启动socket服务，开启监听
     *
     * @param port 服务器端口
     * @throws IOException
     */
    public ServerSocket startSocketServer(int port) {
        ServerSocket serverSocket = null;
        try {
            /*if(ss==null){
                ss=new Socket("10.108.121.110",8080);
                System.out.println("服务器连接客户端...");
                PrintStream out = new PrintStream(ss.getOutputStream());
                BufferedReader buf = new BufferedReader(new InputStreamReader(ss.getInputStream()));
                byte[] responseBodyBuf = new byte[4];
                responseBodyBuf[0] = 0x00;
                responseBodyBuf[1] = 0x01;
                responseBodyBuf[2] = 0x01;
                responseBodyBuf[3] = 0x01;
                out.write(responseBodyBuf);
                out.flush();
                String str =  buf.readLine();
                System.out.println(str);
            }*/
            /*//打开通信信道
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            //设置为非阻塞
            serverSocketChannel.configureBlocking(false);
            //获取套接字
            serverSocket = serverSocketChannel.socket();
            //
            serverSocket.setReuseAddress(true);
            //绑定端口号
            serverSocket.bind(new InetSocketAddress(port));
            //打开监听器
            selector = Selector.open();
            //将通信信道注册到监听器
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);*/


            serverSocket = new ServerSocket(port);
            OutputStream out = null;
            clientSocket = serverSocket.accept();
//            clientSocket.setSoTimeout(1000 * 10);




            /*while (true){
                out = clientSocket.getOutputStream();
                byte[] responseBodyBuf = new byte[4];
                responseBodyBuf[0] = 0x00;
                responseBodyBuf[1] = 0x01;
                responseBodyBuf[2] = 0x01;
                responseBodyBuf[3] = 0x01;
                out.write(responseBodyBuf);
                out.flush();

                BufferedReader buf = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                System.out.println("server said:" + buf.readLine());
            }*/

            //监听器会一直监听，如果客户端有请求就会进入相应的事件处理
            /*while (true) {
                selector.select();//select方法会一直阻塞直到有相关事件发生或超时
                Set<SelectionKey> selectionKeys = selector.selectedKeys();//监听到的事件
                for (SelectionKey key : selectionKeys) {
                    handle(key);
                }
                selectionKeys.clear();//清除处理过的事件
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }
        return serverSocket;
    }

    /**
     * 处理不同的事件
     *
     * @param selectionKey 处理客户端消息
     * @throws IOException
     */
    private void handle(SelectionKey selectionKey) throws IOException {
        ServerSocketChannel serverSocketChannel = null;
        SocketChannel socketChannel = null;
        String requestMsg = "";
        int count = 0;
        if (selectionKey.isAcceptable()) {
            //每有客户端连接，即注册通信信道为可读
            serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
            socketChannel = serverSocketChannel.accept();
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);
        } else if (selectionKey.isReadable()) {
            socketChannel = (SocketChannel) selectionKey.channel();
            rBuffer.clear();
            count = socketChannel.read(rBuffer);
            //读取数据
            if (count > 0) {
                rBuffer.flip();
                requestMsg = rBuffer.toString();
//                requestMsg = String.valueOf(cs.decode(rBuffer).array());
            }
            String responseMsg = "已收到客户端的消息:" + requestMsg;
            System.out.println(responseMsg);
            //返回数据
            /*sBuffer = ByteBuffer.allocate(responseMsg.getBytes("UTF-8").length);
            sBuffer.put(responseMsg.getBytes("UTF-8"));
            sBuffer.flip();
            socketChannel.write(sBuffer);
            socketChannel.close();*/
        }
    }

}
