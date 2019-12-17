package hcloud.demo.test;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@AllArgsConstructor
@Data
public class Person {

    private String firstName;
    private String lastName;
    private String age;

    public static void main(String[] args) {
        LocalDate date = LocalDate.now(); // 获取当前时间
        int month = date.getMonthValue();   // 获取月份
        int day = date.getDayOfMonth();     // 日
        date = date.minusDays(day - 1);     // 生成当前日期之前几天的日期
        int value = date.getDayOfWeek().getValue();
        System.out.println("一  二  三  四  五  六  日");
        for (int i = 0; i < value; i++) {
            System.out.print("  ");
        }
        while (date.getMonthValue() == month) {
            System.out.printf("%3d", date.getDayOfMonth());
            if (date.getDayOfMonth() == day) {
                System.out.print("*");
            } else {
                System.out.print(" ");
            }
            date = date.plusDays(1);       // 生成当前日期之后几天的日期
            if (date.getDayOfWeek().getValue() == 1)    // 获取当前时间星期几
            {
                System.out.println();
            }
        }
        if (date.getDayOfWeek().getValue() != 1) {
            System.out.println();
        }

    }

}
