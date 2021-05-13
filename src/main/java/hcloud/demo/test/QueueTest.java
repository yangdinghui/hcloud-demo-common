package hcloud.demo.test;

import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * description 功能描述
 *
 * @author 杨丁辉
 * @date 2019-12-04
 */
public class QueueTest {

    public static void main(String[] args) {
//        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(100,true);
//        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue(100);
//        ConcurrentLinkedQueue

        String a = "123";
        String b = new String("123");//"123";

        //true
        System.out.println(a == b);
        System.out.println(a.equals(b));


    }
}
