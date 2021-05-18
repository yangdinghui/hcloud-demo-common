package hcloud.demo.test;

import hcloud.demo.utils.DesensitizedUtil;

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
        System.out.println(DesensitizedUtil.phoneDesensitized("12302138978"));
        System.out.println(DesensitizedUtil.emailDesensitized("1004329447@163.com"));
        System.out.println(DesensitizedUtil.nameDesensitized("张三"));
        System.out.println(DesensitizedUtil.nameDesensitized("王二小"));
        System.out.println(DesensitizedUtil.nameDesensitized("上官婉儿"));

//        List<String> list = new ArrayList<>();
//        for (String s : list) {
//            System.out.println("for");
//        }
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println("fori");
//        }
//        list = null;

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
