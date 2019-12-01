package hcloud.demo.test;

import java.util.ArrayList;
import java.util.List;

public class LambdaEl {
    /**
     * java.util.Function提供了大量的函数式接口
     * Predicate 接收参数T对象，返回boolean类型结果
     * Consumer 接收参数T对象，没有返回值
     * Function 接收参数T对象，返回R对象
     * Supplier 不接受任何参数，直接通过get()获取指定类型的对象
     * UnaryOperator 接收参数T对象，执行业务处理后，返回更新后的T对象
     * BinaryOperator 接口接收两个T
     */

    static List test(LambdaA<String, List<String>> listLambdaA) {
        List<String> list = new ArrayList<>();
        list.add("1");
        return listLambdaA.replace("h", list);
    }

    public static void main(String[] args) {
        int i = test((x, y) -> {
            y.add("hello");
            String s0 = y.get(0);
            String s1 = y.get(1);
            System.out.println(s0);
            System.out.println(s1);
            return y;
        }).size();
        System.out.println(i);
    }
}

@FunctionalInterface
interface LambdaA<R, T> {
    T replace(R r, T t);
}