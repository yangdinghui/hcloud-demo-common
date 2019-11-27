package hcloud.demo.test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description 两数之和
 * 假设有一组面值不等的纸币和一个目标金额，请找到两张纸币面值之和等于目标金额。纸币面值用数组给出，请返回纸币索引
 *
 * @author 杨丁辉
 * @date 2019-11-08
 */
public class DoubleNumberSum {

    public static int[] returnTwoNumIndex(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>(5);
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                result[0] = map.get(target - nums[i]);
                result[1] = i;
                return result;
            }
            map.put(nums[i], i);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] twoNum = DoubleNumberSum.returnTwoNumIndex(new int[]{1, 2, 2}, 4);
        List list = Arrays.asList(twoNum);
        System.out.println(list);
    }
}
