package hcloud.demo.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * description 功能描述
 *
 * @author 杨丁辉
 * @date 2019-12-25
 */
public class NettyServer {

    public static void main(String[] args) throws Exception {
//        EventLoopGroup mainGroup = new NioEventLoopGroup();
//        EventLoopGroup subGroup = new NioEventLoopGroup();
//
//        ServerBootstrap serverBootstrap = new ServerBootstrap();
//        serverBootstrap.group(mainGroup, subGroup)
//                        .childHandler(null);

        new NettyServer().fileNio();
    }

    public void fileNio() throws Exception {
        RandomAccessFile fromFile = new RandomAccessFile("fromFile.txt", "rw");
        FileChannel fromChannel = fromFile.getChannel();

        RandomAccessFile toFile = new RandomAccessFile("toFile.txt", "rw");
        FileChannel toChannel = toFile.getChannel();

        long position = 0;
        long count = fromChannel.size();

        long transferFrom = toChannel.transferFrom(fromChannel, position, count);
        System.out.println(transferFrom);
    }
}
