package faqs.real.coin;

/**
 * 写一个可以**上线**的代码, 求一个数的阶乘</p>
 * 需要上线的代码:
 * <p>1. 线程安全</p>
 * <p>2. 整数溢出的情况</p>
 * <p>3. 使用递归需要考虑栈溢出的情况</p>
 * <p>4. 是用循环需要考虑时间复杂度的问题(有没有可能缓存起来什么值)</p>
 */
public class GetFactorialOfANumber {

    /**
     * 使用递归的方式</br>
     * 需要考虑
     * 1. 栈溢出
     * 2. 输入大数整数溢出(可以考虑BigDecimal)
     */
    static int factorial(int n) {
        if (n == 0) return 1;
        return n * factorial(n - 1);
    }

    /**
     * 使用循环来实现
     */
    static int getFactorialByForLoop(int n) {
        int res = 1, i;
        for (i = 2; i <= n; i++)
            res *= i;
        return res;

    }

    public static void main(String[] args) {
        int num = 11110;
        System.out.println("Factorial of " + num + " is " + factorial(num));
    }


}
