package hcloud.demo.netty.bio.client;

import java.io.IOException;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * description 描述这个类的主要功能、用途
 * 创建时间 2020-03-30
 *
 * @author 杨丁辉
 */
public class BioClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("192.168.33.104", 7397);
            System.out.println("-------------- bio client start done --------------");
            BioClientHandler bioClientHandler = new BioClientHandler(socket, Charset.forName("utf-8"));
            bioClientHandler.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
