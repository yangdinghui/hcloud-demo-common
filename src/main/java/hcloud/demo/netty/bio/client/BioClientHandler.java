package hcloud.demo.netty.bio.client;

import hcloud.demo.netty.bio.ChannelAdapter;
import hcloud.demo.netty.bio.ChannelHandler;

import java.net.Socket;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * description 描述这个类的主要功能、用途
 * 创建时间 2020-03-31
 *
 * @author 杨丁辉
 */
public class BioClientHandler extends ChannelAdapter {


    public BioClientHandler(Socket socket, Charset charset) {
        super(socket, charset);
    }


    @Override
    public void channelActive(ChannelHandler ctx) {
        System.out.println("链接报告LocalAddress:" + ctx.socket().getLocalAddress());
        ctx.writeAndFlush("hi! 我是 BioClient to msg for you \r\n");
    }

    @Override
    public void channelRead(ChannelHandler ctx, Object msg) {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " 接收到消息：" + msg);
        ctx.writeAndFlush("hi 我已经收到你的消息Success！\r\n");
    }
}
