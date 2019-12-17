package hcloud.demo.test;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * description 功能描述
 *
 * @author 杨丁辉
 * @date 2019-12-11
 */
public class BigDecimalTestDemo {
    public static void main(String[] args) {
        BigDecimal A = new BigDecimal(7);
        BigDecimal B = new BigDecimal(29);
        //四舍五入ROUND_HALF_UP
        BigDecimal C = A.divide(B, 2, BigDecimal.ROUND_HALF_UP);
        //取整
        String res = C.multiply(new BigDecimal(100)).setScale(0).toString();
        System.out.println(new BigDecimal(-12.59).setScale(0, BigDecimal.ROUND_UP));
        System.out.println(new BigDecimal(12.50).setScale(0, BigDecimal.ROUND_HALF_EVEN));
        System.out.println(new BigDecimal(11.51).setScale(0, BigDecimal.ROUND_HALF_DOWN));
    }

}
