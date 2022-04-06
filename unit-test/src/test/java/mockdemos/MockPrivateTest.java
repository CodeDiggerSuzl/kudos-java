package mockdemos;

import com.codigger.samples.MockPrivateMethodDemo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 * mock private方法打桩时， 需要使用PowerMockito.when(mock实例，"私有方法名").thenReturn(期望返回值)的形式设置mock实例的私有方法的返回值，
 * 如果私有方法有参数，还需要在私有方法名后面添加参数占位符， 比如PowerMockito.when(mock实例，"私有方法名"，anyInt()).thenReturn(期望返回值)。
 * 上面例子中进行断言时，调用私有方法采取了调用公共方法来间接调用私有方法的形式， 单元测试代码对业务代码造成了入侵，因此如果仅仅只是为了验证一个私有方法，可以使用 Whitebox 来方便的调用私有方法:
 * </p>
 *
 * @author suzailong
 * @date 2022/3/9-1:46 下午
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({MockPrivateMethodDemo.class})
public class MockPrivateTest {

    @Test
    public void privateMethodTest() throws Exception {
        MockPrivateMethodDemo mock = PowerMockito.mock(MockPrivateMethodDemo.class);
        PowerMockito.when(mock, "privateReturnTrue").thenReturn(false);
        PowerMockito.when(mock.isTrue_3()).thenCallRealMethod(); // call the real method
        // PowerMockito.when(mock.isTrue_3()).thenReturn(false);
        boolean true_3 = mock.isTrue_3();
        Assert.assertFalse(true_3);
    }

    public static final String YYYY_MM_DD = "yyyy-MM";

    public static Date parseTimeStr(String timeStr, String formatStr) throws ParseException {
        DateFormat sdf = new SimpleDateFormat(formatStr);
        return sdf.parse(timeStr);
    }

    public static void main(String[] args) throws ParseException {
        String s = "2022-01";
        Date date = parseTimeStr(s, YYYY_MM_DD);
        System.out.println(date.getTime());

    }
}
