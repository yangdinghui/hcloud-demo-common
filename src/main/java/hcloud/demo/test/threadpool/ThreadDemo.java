package hcloud.demo.test.threadpool;

import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.NANOSECONDS;

/**
 * @author dinghui
 * @Descrition 类描述信息
 * @date 2021/5/9 18:57
 */
public class ThreadDemo {

    public void t2() {
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

    public static void main(String[] args) {
        ThreadDemo threadDemo = new ThreadDemo();
        threadDemo.t3();
    }

    public void t1() {
        //0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>()
        //可缓存线程池  线程可能无限多Integer.MAX_VALUE
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
//        execute(9, newCachedThreadPool);

        //corePoolSize, Integer.MAX_VALUE, 0, NANOSECONDS, new ScheduledThreadPoolExecutor.DelayedWorkQueue()
        //定长线程池，周期性执行任务 线程可能无限多Integer.MAX_VALUE
        ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(9);

        //nThreads, nThreads, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>()
        //定长线程池   任务队列可能无限大
        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(9);
//        execute(20, newFixedThreadPool);

        //1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>())
        //单线程池 任务队列可能无限大 FIFO LIFO
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();

    }

    public void execute(int nThread, ExecutorService executorService) {
        try {
            for (int i = 0; i < nThread; i++) {
                executorService.submit(() -> {
                    System.out.println("线程：" + Thread.currentThread().getName());
                });
            }
        } finally {
            executorService.shutdown();
        }

    }

    public void t3() {
        //核心线程数
        int corePoolSize = 3;
        //最大线程数
        int maximumPoolSize = 6;
        //超过 corePoolSize 线程数量的线程最大空闲时间
        long keepAliveTime = 2;
        //以秒为时间单位
        TimeUnit unit = TimeUnit.SECONDS;
        //创建工作队列，用于存放提交的等待执行任务
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(2);
        ThreadPoolExecutor threadPoolExecutor = null;
        try {
            //创建线程池
            threadPoolExecutor = new ThreadPoolExecutor(corePoolSize,
                    maximumPoolSize,
                    keepAliveTime,
                    unit,
                    workQueue,
                    new ThreadPoolExecutor.DiscardOldestPolicy());
            /**
             * 拒绝策略
             * AbortPolicy：超出时，直接报错java.util.concurrent.RejectedExecutionException
             * CallerRunsPolicy：超出时，将任务交由提交任务的线程执行
             * DiscardPolicy：超出时，直接抛弃新任务，不抛异常
             * DiscardOldestPolicy： 超出时，丢弃任务队列队首的任务，然后尝试重新执行任务
             */


            //循环提交任务8 9
            for (int i = 0; i < 9; i++) {
                //提交任务的索引
                final int index = (i + 1);
                threadPoolExecutor.submit(() -> {
                    //线程打印输出
                    System.out.println("大家好，我是线程：" + index + "-" + Thread.currentThread().getName());
                    try {
                        //模拟线程执行时间，10s
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
                //每个任务提交后休眠500ms再提交下一个任务，用于保证提交顺序
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            threadPoolExecutor.shutdown();
        }
    }
}
