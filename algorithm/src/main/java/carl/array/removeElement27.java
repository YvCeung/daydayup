package carl.array;

public class removeElement27 {
    public static void main(String[] args) {
        removeElement27 r = new removeElement27();
        int[] nums = {-4,-1,0,3,10};
        r.removeElement3(nums,3);
        r.print(nums);
    }


    public void print(int[] nums){
        for (int i = 0; i < nums.length; i++){
            System.out.println(nums[i]);
        }
    }

    /**
     * 单指针：暴力解法
     * @param nums
     * @param val
     * @return
     */
    public int removeElement3(int[] nums, int val){
        int len = nums.length;
        for (int i = 0; i < nums.length; i++){
            if (nums[i] == val){
                for (int j = i+1; j < nums.length; j++){
                    nums[i] = nums[j];
                    len--;
                }
            }
        }
        return len;
    }

    //快指针：寻找新数组的元素 ，新数组就是不含有目标元素的数组
    //慢指针：指向更新 新数组下标的位置  !!!!
    //无论fast所指的值是否与val 的值相等，fast都会加1（即都会向前走）快指针最多与慢指针持平
    public int removeElement2(int[] nums, int val) {
        //快慢指针
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] == val) {

                //如果fast指针指向的值与val相等，slow指针慢一步
            }else {
                //如果fast指针指向的值与val不等，把fast所指的值赋给slow，slow指针加一
                nums[slow] = nums[fast];
                slow++;
            }
        }

        //返回新数组的长度为slow所指
        return slow;
    }


    public int removeElement1(int[] nums,int val) {
        //快慢指针
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast];
                slow++;
            }
        }
        return slow;
    }






























    public int removeElement0(int[] nums, int val) {
        // 快慢指针
        int slowIndex = 0;
        for (int fastIndex = 0; fastIndex < nums.length; fastIndex++) {
            if (nums[fastIndex] != val) {
                nums[slowIndex] = nums[fastIndex];
                slowIndex++;
            }
        }
        return slowIndex;
    }

}
