package hcloud.demo.test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * description xxxxxxxxxxxxxxxxxxxxx
 *
 * @author y
 * @date 2019-10-08
 */
public class Test {


    public static void demo1() {
        Map<String,String> map = new HashMap(1);
        map.put("1","1");
        Set set = map.entrySet();
        Iterator iterator = set.iterator();


    }

    public static void demo2(){
        String str = "1 23 ";
        System.out.println("   ".length());
        System.out.println(Character.isWhitespace(str.charAt(0)));
        System.out.println("  1 2 3  ".trim());
    }

    public static void main(String[] args) {
        Test.demo2();
    }
}
