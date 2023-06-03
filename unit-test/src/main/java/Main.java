/**
 * @author suzailong
 * @date 2022/3/9-3:22 下午
 */
public class Main {
    public static void main(String[] args) {
        String s1  = ",";
        String[] split = s1.split(",",2);
        System.out.println(split.length);


        String s2 = ",m";

        String[] split1 = s2.split(",");
        System.out.println(split1.length);
    }
}
