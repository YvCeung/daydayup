package carl.array;

public class minSubArrayLen209 {
    public static void main(String[] args) {
        System.out.println(22222);

    }


    /**
     *     ”滑动窗口“
     * @param s
     * @param nums
     * @return
     */
    public int minSubArraylen1(int s, int[] nums) {
        int left = 0;
        int sum = 0;
        int result = Integer.MAX_VALUE;
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            while (sum >= s) {
                //不断更新result值
                result = Math.min(result, right - left + 1);
                //因为sum表示的就是滑动窗口中集合元素的值，所有起始位置的指针向前移动就需要把之前起始指针所指元素减去
                sum -= nums[left++];
            }
        }
        //这里最后判断result，如果还是Integer.MAX_VALUE就说明没有找到符合条件的集合，返回result为0
        return result == Integer.MAX_VALUE ? 0 : result;
    }






















    // 滑动窗口
    public int minSubArrayLen0(int s, int[] nums) {
        int left = 0;
        int sum = 0;
        int result = Integer.MAX_VALUE;
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            while (sum >= s) {
                result = Math.min(result, right - left + 1);
                sum -= nums[left++];
            }
        }
        return result == Integer.MAX_VALUE ? 0 : result;
    }
}
