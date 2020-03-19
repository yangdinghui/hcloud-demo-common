package hcloud.demo.test;

import java.util.Arrays;

/**
 * description 描述这个类的主要功能、用途
 * 创建时间 2020-03-19
 *
 * @author 杨丁辉
 */
public class Sort {

    /**
     * 冒泡排序
     * @param array
     * @return
     * @throws Exception
     */
    public static int[] sort(int[] array) throws Exception {
        int[] targetArray = Arrays.copyOf(array, array.length);
        int length = targetArray.length;
        for (int i = 1; i < length; i++) {
            for (int j = 0; j < length - i; j++) {
                if (targetArray[j] > targetArray[j + 1]) {
                    int temp = targetArray[j];
                    targetArray[j] = targetArray[j + 1];
                    targetArray[j + 1] = temp;
                }
            }
        }
        return targetArray;
    }

    /**
     *
     * @param array
     * @return
     * @throws Exception
     */
    public static int[] sort1(int[] array) throws Exception {

        return new int[2];
    }

    public static void main(String[] args) throws Exception {
        int[] arr = {5, 3, 1, 7, 9};
        int[] sort = sort(arr);
        for (int i : sort) {
            System.out.println(i);
        }
    }

}
