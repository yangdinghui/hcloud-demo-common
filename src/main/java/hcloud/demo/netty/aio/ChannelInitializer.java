package hcloud.demo.netty.aio;

import hcloud.demo.netty.aio.server.AioServer;

import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * description 描述这个类的主要功能、用途
 * 创建时间 2020-03-30
 *
 * @author 杨丁辉
 */
public abstract class ChannelInitializer implements CompletionHandler<AsynchronousSocketChannel, AioServer> {


    @Override
    public void completed(AsynchronousSocketChannel channel, AioServer attachment) {
        try {
            initChannel(channel);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 再此接收客户端连接
            attachment.serverSocketChannel().accept(attachment, this);
        }
    }

    @Override
    public void failed(Throwable exc, AioServer attachment) {
        exc.getStackTrace();
    }

    protected abstract void initChannel(AsynchronousSocketChannel channel) throws Exception;
}
