package hcloud.demo.test;

import java.util.HashSet;
import java.util.Set;

/**
 * description 找出快乐数
 *
 * @author 杨丁辉
 * @date 2019-11-08
 */
public class HappyNumber {

    public static boolean isHappy(int n) {
        if (n == 1) {
            return true;
        }
        Set<Integer> usedStates = new HashSet<>();
        usedStates.add(n);
        int curState = n;
        while (true) {
            curState = getSqSUM(curState);
            if (usedStates.contains(curState)) {
                return false;
            } else {
                usedStates.add(curState);
            }
            if (curState == 1) {
                return true;
            }
        }
    }

    private static int getSqSUM(int n) {
        int len = String.valueOf(n).length();
        int result = 0;
        for (int i = 0; i < len; i++) {
            result += (n % 10) * (n % 10);
            n /= 10;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(HappyNumber.isHappy(10));
    }
}
