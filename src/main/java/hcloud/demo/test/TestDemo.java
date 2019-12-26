package hcloud.demo.test;

import java.util.ArrayList;
import java.util.List;

/**
 * description 功能描述
 *
 * @author 杨丁辉
 * @date 2019-12-26
 */
public class TestDemo {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (String s : list) {
            System.out.println("for");
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println("fori");
        }
        list = null;

        //集合为null时,增强for循环会报异常java.lang.NullPointerException
//        for (String s : list) {
//            System.out.println("for");
//        }
//
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println("fori");
//        }

    }
}
