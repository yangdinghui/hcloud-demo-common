package hcloud.demo.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * description xxxx
 *
 * @author h
 * @date 2019-10-08
 */
public class Test {


    public static void demo1() {
        Map<String, String> map = new HashMap(1);
        map.put("1", "1");
        Set set = map.entrySet();
        Iterator iterator = set.iterator();


    }

    public static void demo2() {
        String str = "1 23 ";
        System.out.println("   ".length());
        System.out.println(Character.isWhitespace(str.charAt(0)));
        System.out.println("  1 2 3  ".trim());
    }

    public static void demo3() {

        LambdaInterface st = (s222) -> {
            s222.replace("12", "123");
            System.out.println(s222);
        };
        st.doSomething("1234");

    }

    public static void demo4(LambdaInterface lambdaInterface, String s) {
        lambdaInterface.doSomething(s);
    }

    public static void demo5(List<Person> personList, NameChecker nameChecker, Executor executor) {

        for (Person person : personList) {
            if (nameChecker.check(person)) {
                executor.execute(person);
            }
        }

    }

    public static void demo6(List<Person> personList, Predicate<Person> predicate, Consumer<Person> consumer) {
        personList.forEach(p -> {
            if (predicate.test(p)) {
                consumer.accept(p);
            }
        });
    }

    public static void demo7(List<Person> personList) {
        personList.stream()
                .filter(p -> p.getLastName().startsWith("Y"))
                .forEach(p -> System.out.println(p.getFirstName()));
    }

    public static void demo8(List<Person> personList) {
        personList.stream()
                .filter(p -> p.getLastName().startsWith("Y"))
                .forEach(System.out::println);
    }

    public static Object demo9() {
        Person p = new Person("XiaoMing", "Li", "20");
        Optional<Person> personOpt = Optional.ofNullable(p);

        personOpt.ifPresent(System.out::println);

//       return personOpt.orElse(p);

//        return personOpt.orElseGet(()->new Person("tom","Li","24"));

        return personOpt.map(p1 -> p1.getLastName())
                .map(name -> name.toUpperCase())
                .orElse(null);
    }

    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public static void demo10() {

        for (int i = 0; i < 30; i++) {
            int li = i+1;
            new Thread(() -> {
                Date date = null;
                try {
                    String curr = "2019-10-" + li;
                    synchronized (Test.class) {
                        date = format.parse(curr);
                    }
                    System.out.println(li + ":" + date.getTime());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    public static void main(String[] args) {
//        Test.demo4(s -> System.out.println(s.isEmpty()), "ABCDEFG");
//
//        List<Person> personList = Arrays.asList(
//                new Person("XiaoMing", "Li", "20"),
//                new Person("Min", "Zhao", "20"),
//                new Person("XiaoMing", "Yang", "20"));
//
//        Test.demo8(personList);
        Test.demo10();
    }

}

@FunctionalInterface
interface LambdaInterface {
    void doSomething(String s);
}

@FunctionalInterface
interface Executor {
    void execute(Person p);
}

@FunctionalInterface
interface NameChecker {
    boolean check(Person p);
}
