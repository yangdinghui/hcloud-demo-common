package hcloud.demo.test;

import io.swagger.models.auth.In;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class StaticCodeBlockTest {

    public static void main(String[] args) {


        // int age = StaticDemo.getAge();
        // System.out.println(age);

        // int AGE = StaticDemo.AGE;
        // System.out.println(AGE);
//        StaticDemo.test();
//        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyyMMdd");
//        String format = LocalDate.parse(new Date().toString()).format(pattern);
//        System.out.println("CZ" + format + RandomStringUtils.randomNumeric(4));
//        System.out.println(format);


        LocalDate localDate = LocalDate.now();
        String format1 = localDate.format(DateTimeFormatter.BASIC_ISO_DATE);    //yyyyMMdd
        System.out.println(format1);
        String format2 = localDate.format(DateTimeFormatter.ISO_DATE);
        System.out.println("CZ" + format1 + RandomStringUtils.randomNumeric(4));
    }

}

class StaticDemo {
    public final static int AGE = 18;
    public static String NAME = "hello";

    static {
        System.out.println("---- static area ----");
    }

    public static String getName() {
        System.out.println("---- GetName area ----");
        return NAME;
    }

    public static int getAge() {
        System.out.println("---- GetAge area ----");
        return AGE;
    }

    public static void test() {
        Integer value = Integer.valueOf(10);
        System.out.println(value);
    }
}