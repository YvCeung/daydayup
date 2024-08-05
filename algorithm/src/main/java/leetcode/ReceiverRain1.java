package leetcode;

/**
 * @Description
 * @Author zy
 * @Date 2024/8/6 01:15
 **/
public class ReceiverRain1 {
    public int maxArea(int[] height) {
        int initArea = 0;
        int left = 0,right = height.length - 1;
        int area = 0;
        while (left < right){
            //为什么取最小的值呢？因为木桶原理
            area = (right - left) * Math.min(height[left],height[right]);
            initArea = Math.max(initArea,area);

            //哪边比较矮，就往反方向移动
            if(height[left] < height[right]){
                left++;
            }else {
                right--;
            }
        }
        return initArea;
    }
}
