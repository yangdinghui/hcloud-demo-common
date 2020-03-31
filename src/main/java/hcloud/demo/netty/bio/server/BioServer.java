package hcloud.demo.netty.bio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * description 描述这个类的主要功能、用途
 * 创建时间 2020-03-31
 *
 * @author 杨丁辉
 */
public class BioServer extends Thread {


    private ServerSocket serverSocket = null;

    public static void main(String[] args) {
        new BioServer()
                .start();
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(7397));
            System.out.println("-------------  bio server start done. -------------");
            while (true) {
                Socket socket = serverSocket.accept();
                BioServerHandler handler = new BioServerHandler(socket, Charset.forName("utf-8"));
                handler.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
