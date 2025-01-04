package carl;

/**
 * @Description 九九乘法表
 * @Author zy
 * @Date 2024/12/24 16:02
 **/
public class NineNineMultiTablePrint {
    public static void main(String[] args) {
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print( j + "*" + i + "=" + j * i + "\t");
            }


            System.out.println();
        }
    }
}
