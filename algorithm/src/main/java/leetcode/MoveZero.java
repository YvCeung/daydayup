package leetcode;

/**
 * @Description
 * @Author zy
 * @Date 2024/8/6 00:56
 **/
public class MoveZero {
    public void moveZeroes(int[] nums) {
        int length = nums.length;
        int right = 0,left = 0;
        while (right < length){
            //如果等于0，那么left就会停留在原地，而right一直往后找，找到下一个不为0的进行交换
            //因为left和right指针起始位置相同。不为零相当于是在原地交换

            /**
             * 如果数组没有0，那么快慢指针始终指向同一个位置，每个位置自己和自己交换；
             * 如果数组有0，快指针先走一步，此时慢指针对应的就是0，所以要交换。
             */
            if(nums[right] != 0){
                //不等于0就交换
                swap(nums,left,right);
                left++;
            }
            //右侧指针一直移动寻找0
            right++;
        }
    }
    public void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}
