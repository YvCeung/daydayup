package carl.array;

public class binarySearch {
    public static void main(String[] args) {

    }

    public int search1(int[] nums,int target){
        //避免 target 小于num[0],nums[nums.length -1]时多次循环
        if(target < nums[0] || target > nums[nums.length - 1]) {
            return -1;
        }
        int left = 0,right = nums.length-1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] == target) {
                return mid;
            }else if (nums[mid] > target) {
                right = mid - 1;
            }else if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        return -1;
    }



















































    //左闭右闭
    public int search0(int[] nums, int target) {
        // 避免当 target 小于nums[0] nums[nums.length - 1]时多次循环运算
        if (target < nums[0] || target > nums[nums.length - 1]) {
            return -1;
        }
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] < target)
                left = mid + 1;
            else if (nums[mid] > target)
                right = mid - 1;
        }
        return -1;
    }
}
