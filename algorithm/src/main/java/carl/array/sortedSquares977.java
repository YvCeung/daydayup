package carl.array;

public class sortedSquares977 {
    public static void main(String[] args) {
        sortedSquares977 s = new sortedSquares977();
        int[] nums = {-4,-1,0,3,10};
    }



    /**
     * 相向而行的双指针
     * @param nums
     * @return
     */
    public int[] sortedSquares1(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        //创建一个新数组，接受返回结果
        int [] res = new int[nums.length];
        //因为最后结果要数组为非递减排序，所以从最后一个位置插入最大值，逐渐j指针向前移动
        int j = nums.length - 1;
        //left 要小于等于 right，少了等于就漏掉值了
        while (left <= right) {
            if (nums[left] * nums[left] > nums[right] * nums[right]) {
                res[j--] = nums[left] * nums[left++];
            }else {
                res[j--] = nums[right] * nums[right--];
            }
        }
        return res;
    }










    public int[] sortedSquares0(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        int[] res = new int[nums.length];
        int j = nums.length - 1;
        while(l <= r){
            if(nums[l] * nums[l] > nums[r] * nums[r]){
                res[j--] = nums[l] * nums[l++];
            }else{
                res[j--] = nums[r] * nums[r--];
            }
        }
        return res;
    }
}
