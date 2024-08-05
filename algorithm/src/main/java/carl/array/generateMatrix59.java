package carl.array;

public class generateMatrix59 {
    public static void main(String[] args) {
        generateMatrix59 g = new generateMatrix59();
        int [][] nums = g.generateMatrix1(1);
        System.out.println(11111);
    }



    public int[][] generateMatrix1(int n) {
        int loop =  0; //控制循环次数
        int[][] res = new int[n][n];
        int start = 0;  //每次循环的开始点
        int count = 1; //定义填充数字

        int i, j;

        while (loop++ < n/2) {
            //模拟上侧从左到右(左闭右开)
            for (j = start; j < n - loop; j++) {
                res[start][j] = count++;
            }
            //模拟右侧从上到下
            for (i = start; i < n - loop; i++) {
                res[i][j] = count++;
            }
            //模拟下侧从右到左
            for (; j > start; j--) {
                res[i][j] = count++;
            }
            //模拟左侧从下到上
            for (; i > start; i--) {
                res[i][j] = count++;
            }
            start++;
        }

        //如果n是奇数，要单独为中间的数赋值
        if (n % 2 == 1) {
            res[start][start] = count;
        }

        return res;

    }
























































    public int[][] generateMatrix0(int n) {
        int loop = 0;  // 控制循环次数
        int[][] res = new int[n][n];
        int start = 0;  // 每次循环的开始点(start, start)
        int count = 1;  // 定义填充数字
        int i, j;

        while (loop++ < n / 2) { // 判断边界后，loop从1开始
            // 模拟上侧从左到右
            for (j = start; j < n - loop; j++) {
                res[start][j] = count++;
            }

            // 模拟右侧从上到下
            for (i = start; i < n - loop; i++) {
                res[i][j] = count++;
            }

            // 模拟下侧从右到左
            for (; j >= loop; j--) {
                res[i][j] = count++;
            }

            // 模拟左侧从下到上
            for (; i >= loop; i--) {
                res[i][j] = count++;
            }
            start++;
        }

        if (n % 2 == 1) {
            res[start][start] = count;
        }

        return res;
    }
}
