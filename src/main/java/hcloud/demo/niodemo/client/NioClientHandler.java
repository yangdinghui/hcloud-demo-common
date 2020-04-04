package hcloud.demo.niodemo.client;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * @author HCoding
 * @description 客户端线程类, 处理客户端接收自服务端的消息
 * @date 2020/4/4 0004
 */
public class NioClientHandler implements Runnable {
    private Selector selector;

    public NioClientHandler(Selector selector) {
        this.selector = selector;
    }

    @Override
    public void run() {
        try {
            for (; ; ) {
                int readyChannels = selector.select();
                if (readyChannels == 0) continue;
                //获取可用channel的集合
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    //SelectionKey实例
                    SelectionKey key = iterator.next();
                    /**
                     * 7.根据就绪状态，调用对应方法处理业务逻辑
                     */
                    //如果是可读事件
                    if (key.isReadable()) {
                        readHandler(key, selector);
                    }
                    //移除set中的当前key
                    iterator.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 可读事件处理器
     *
     * @param selectionKey
     * @param selector
     */
    private void readHandler(SelectionKey selectionKey, Selector selector) {
        try {

            /**
             * 要从SelectionKey中，获取到已经就绪的Channel
             */
            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
            /**
             * 创建Buffer
             */
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            /**
             * 循环读取服务器端响应信息
             */
            StringBuffer response = new StringBuffer("");
            while (socketChannel.read(byteBuffer) > 0) {
                //切换buffer为读模式
                byteBuffer.flip();
                //读取buffer中的内容
                response.append(Charset.forName("utf-8").decode(byteBuffer));
            }
            /**
             * 将Channel再次注册到Selector上，监听他的可读事件
             */
            socketChannel.register(selector, SelectionKey.OP_READ);
            /**
             * 将服务器端响应信息打印到本地控制台
             */
            if (response.length() > 0) {
                System.out.println(" :: " + response.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
