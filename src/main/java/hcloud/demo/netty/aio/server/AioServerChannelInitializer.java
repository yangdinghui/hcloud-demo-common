package hcloud.demo.netty.aio.server;

import hcloud.demo.netty.aio.ChannelInitializer;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

/**
 * description 服务端初始化
 * 创建时间 2020-03-30
 *
 * @author 杨丁辉
 */
public class AioServerChannelInitializer extends ChannelInitializer {
    @Override
    protected void initChannel(AsynchronousSocketChannel channel) throws Exception {
        channel.read(ByteBuffer.allocate(1024), 10, TimeUnit.SECONDS, null, new AioServerHandler(channel, Charset.forName("GBK")));
    }
}
