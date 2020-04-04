package hcloud.demo.niodemo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * @author HCoding
 * @description 描述
 * @date 2020/4/4 0004
 */
public class NioServer {

    private Selector selector;
    private ServerSocketChannel serverSocketChannel;

    private void bind(int port) {

        try {
            /**
             * 1.创建Selector
             */
            selector = Selector.open();
            /**
             * 2.创建Channel
             */
            serverSocketChannel = ServerSocketChannel.open();
            /**
             * 3.Channel绑定端口
             */
            serverSocketChannel.socket().bind(new InetSocketAddress(8000));
//            serverSocketChannel.bind(new InetSocketAddress(8000));

            /**
             * 4.Channel设置为非阻塞模式
             */
            serverSocketChannel.configureBlocking(false);
            /**
             * 5.向Selector注册Channel
             */
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("---------- 服务端已启动 ----------");
            /**
             * 6.循环等待新的连接
             */
            for (; ; ) {
                int select = selector.select();
                if (select == 0) continue;
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    /**
                     * 7.根据就绪状态，调用对应方法处理业务逻辑
                     */
                    //如果是接入事件
                    if (key.isAcceptable()) {
                        acceptHandler(serverSocketChannel, selector);
                    }
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
     * 接入事件处理器
     *
     * @param serverSocketChannel
     * @param selector
     */
    private void acceptHandler(ServerSocketChannel serverSocketChannel, Selector selector) {

        SocketChannel socketChannel = null;
        try {
            /**
             * 如果是接入事件，则创建SocketChannel
             */
            socketChannel = serverSocketChannel.accept();
            /**
             * 将SocketChannel设置为非阻塞工作模式
             */
            socketChannel.configureBlocking(false);
            /**
             * 将Channel注册到Selector上，监听事件
             */
            socketChannel.register(selector, SelectionKey.OP_READ);
            /**
             * 回复客户端消息
             */
            socketChannel.write(Charset.forName("utf-8").encode("你与其他人不是朋友关系，请注意隐私安全"));
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
            ;
            /**
             * 创建Buffer
             */
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            /**
             * 循环读取客户请求信息
             */
            StringBuffer sb = new StringBuffer("");
            while (socketChannel.read(byteBuffer) > 0) {
                //切换buffer为读模式
                byteBuffer.flip();
                //读取buffer中的内容
                sb.append(Charset.forName("utf-8").decode(byteBuffer));
            }
            /**
             * 将Channel再次注册到Selector上，监听他的可读事件
             */
            socketChannel.register(selector, SelectionKey.OP_READ);
            /**
             * 将客户端发送的请求信息 广播给其他客户端
             */
            if (sb.length() > 0) {
                System.out.println(" :: " + sb.toString());
                broadCast(selector, socketChannel, sb.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 将信息广播给其他客户端
     *
     * @param selector
     * @param sourceChannel
     * @param msg           消息
     */
    private void broadCast(Selector selector, SocketChannel sourceChannel, String msg) {
        //获取所有已接入的客户端channel
        Set<SelectionKey> selectionKeySet = selector.keys();
        selectionKeySet.forEach(selectionKey -> {
            Channel targetChannel = selectionKey.channel();
            //剔除发消息的客户端
            if (targetChannel instanceof SocketChannel && targetChannel != sourceChannel) {
                SocketChannel socketChannel = (SocketChannel) targetChannel;
                try {
                    //将信息发送到targetChannel客户端
                    socketChannel.write(Charset.forName("utf-8").encode(msg));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
        //循环向所有channel广播信息


    }

    public static void main(String[] args) {
        new NioServer().bind(8000);
    }
}
