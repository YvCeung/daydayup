package leetcode;

import java.util.HashSet;

/**
 * @Description 最长连续序列
 * @Author zy
 * @Date 2024/7/30 00:02
 **/
public class MaxLongLianxuXulie {
    public int longestConsecutive(int[] nums) {
        //先进行去重
        HashSet<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }
        //对去重之后的集合进行便利判断
        int longest = 0;
        for (Integer num : numSet) {
            //对每一个元素进行判断，判断num - 1在集合中是否存在，如果不存在则说明当前元素可作为开头的值
            if(!numSet.contains(num - 1)){
                //记录值和长度
                int currentNum = num;
                int currentLong = 1;

                //循环判断当前元素的下一个元素是否存在集合中
                while (numSet.contains(currentNum + 1)){
                    currentNum += 1;
                    currentLong += 1;
                }

                //退出循环之后更新最长长度
                longest = Math.max(longest,currentLong);
            }
        }
        return longest;
    }
}
