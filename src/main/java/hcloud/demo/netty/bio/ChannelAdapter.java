package hcloud.demo.netty.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * description 描述这个类的主要功能、用途
 * 创建时间 2020-03-31
 *
 * @author 杨丁辉
 */
public abstract class ChannelAdapter extends Thread {

    private Socket socket;
    private ChannelHandler channelHandler;
    private Charset charset;


    public ChannelAdapter(Socket socket, Charset charset) {
        this.socket = socket;
        this.charset = charset;
        while (!socket.isConnected()) {
            break;
        }
        channelHandler = new ChannelHandler(socket, charset);
        channelActive(channelHandler);
    }


    @Override
    public void run() {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(this.socket.getInputStream(), charset));
            String str = null;
            while ((str = input.readLine()) != null) {
                channelRead(channelHandler, str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 链接通知抽象类
     *
     * @param ctx
     */
    public abstract void channelActive(ChannelHandler ctx);

    /**
     * 读取消息抽象类
     *
     * @param ctx
     * @param msg
     */
    public abstract void channelRead(ChannelHandler ctx, Object msg);
}
