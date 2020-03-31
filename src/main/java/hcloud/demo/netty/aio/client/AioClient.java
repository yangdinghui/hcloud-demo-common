package hcloud.demo.netty.aio.client;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;
import java.util.concurrent.Future;

/**
 * description 客户端
 * 创建时间 2020-03-30
 *
 * @author 杨丁辉
 */
public class AioClient {

    public static void main(String[] args) throws Exception {
        AsynchronousSocketChannel socketChannel = AsynchronousSocketChannel.open();

        Future<Void> future = socketChannel.connect(new InetSocketAddress("192.168.33.104", 7397));
        System.out.println("AioClient Connecting!!!");
        future.get();
        socketChannel.read(ByteBuffer.allocate(1024), null, new AioClientHandler(socketChannel, Charset.forName("GBK")));
        Thread.sleep(100000);
    }
}
