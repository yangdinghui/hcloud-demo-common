package hcloud.demo.niodemo.client;

import hcloud.demo.utils.StringUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * @author HCoding
 * @description 描述
 * @date 2020/4/4 0004
 */
public class NioClient {

    public void connect(String nickName) {

        try {
            //连接服务器端
            SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8000));

            //接收服务器端响应
            //新开线程,专门负责接收服务端的响应数据
            //selector, socketChannel, 注册
            Selector selector = Selector.open();
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);
            new Thread(new NioClientHandler(selector)).start();
            //向服务器端发送信息
            System.out.println("请求键入消息给服务端:");
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                String nextLine = scanner.nextLine();
                if (!StringUtil.isEmpty(nextLine)) {
                    socketChannel.write(Charset.forName("utf-8").encode(nickName + ": " + nextLine));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        new NioClient().connect("A");
    }
}
