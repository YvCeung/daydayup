package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description 两数之和
 * @Author zy
 * @Date 2024/7/29 23:26
 **/
public class TwoNumSum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            //判断当前元素的另一半是否存在
            if(map.containsKey(target - nums[i])){
                return new int[]{map.get(target - nums[i]),i};
            //不存在的话应该放入当前元素，他可能是下一个元素的另一半
            }else {
                map.put(nums[i],i);
            }
        }
        return new int[0];
    }
}
