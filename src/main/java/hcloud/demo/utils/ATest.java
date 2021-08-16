package hcloud.demo.utils;

import java.util.HashSet;
import java.util.Set;

/**
 * @author dinghui
 * @Descrition 类描述信息
 * @date 2021/8/16 11:29
 */
public class ATest {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        IdWorker idWorker = new IdWorker();
        for (int i = 0; i < 1000; i++) {
            String id = idWorker.getId().toString();
            System.out.println(id);
            set.add(id);
        }
        System.out.println(set.size());
    }

}
