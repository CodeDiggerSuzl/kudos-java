package hutool;

import cn.hutool.core.date.DateUtil;

/**
 * hutool的时间处理 utils 类
 *
 * @author suzailong
 * @date 2022/4/11-11:02
 */
public class HutoolDateUtils {

    public static void main(String[] args) {
        int i = DateUtil.ageOfNow("1995-03-26");
        System.out.println(i);

    }

}
