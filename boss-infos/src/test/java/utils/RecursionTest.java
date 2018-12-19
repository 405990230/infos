package utils;

/**
 * Created by qxr4383 on 2018/12/11.
 */
public class RecursionTest {
    public static void main(String[] args) {
        System.out.println(mul(10));
    }

    public static int mul(int num){
        if(num==1){
            return num;
        }
        return num*mul(num-1);
    }
}
