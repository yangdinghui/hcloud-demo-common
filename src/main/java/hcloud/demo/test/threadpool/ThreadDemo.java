package hcloud.demo.test.threadpool;

import io.netty.channel.nio.NioEventLoopGroup;

import java.util.concurrent.*;

/**
 * @author dinghui
 * @Descrition 类描述信息
 * @date 2021/5/9 18:57
 */
public class ThreadDemo {

    public static void main(String[] args) {
        //系统CPU核数： 8核
        //占用cpu的：核数+1，不占用cpu的：核数*2
        int processors = Runtime.getRuntime().availableProcessors();
        System.out.println(processors);
        final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10,
                processors * 2,
                5,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(10), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return null;
            }
        }, new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

            }
        });

        threadPoolExecutor.prestartAllCoreThreads();

        Future<?> future = threadPoolExecutor.submit(() -> {
            System.out.println(threadPoolExecutor.getActiveCount());
        });

//        Object o = future.get();

    }

}
