package cn.leecode.DayOne;

/**
 * 描述:
 * 斐波那契数
 *
 * @author HeGaoJian
 * @version 1.0
 * @create 2019-07-16 13:57
 */
public class FibonNum {
    /**
     * 0 1 1 2 3 5 8 13 ...
     * 下面的数是前面两个数的相加
     * 0+1 = 1   1+2 =3  3+2 =5
     * 求第N个斐波那契数的算法
     */
    //思路 1 递归
    private static int fib(int n) {
        if (n <= 1) {
            return n;
        }
        return fib(n - 1) + fib(n - 2);

    }

    //思路二,赋值
    private static int fib2(int n) {
        //思路 1 递归
        if (n <= 1) {
            return n;
        }
        int first = 0;
        int second = 1;
        for (int i = 0; i < n - 1; i++) {
            int sum = first + second;
            first = second;
            second = sum;
        }
        return second;
    }

    public static void main(String[] args) {
//        for (int i = 0; i < 10; i++) {
//            System.out.println(i+"result="+fib(i));
//        }
        //死 循环
        System.out.println(fib2(8));
        int n = 15;
        Times.test("fib2",new Times.Task(){
            @Override
            public void execute() {
                System.out.println(fib2(n));
            }
        });
        int num = 10 ;
        System.out.println(num>>1);
    }
}
