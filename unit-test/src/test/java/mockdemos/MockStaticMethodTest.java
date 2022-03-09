package mockdemos;

import com.codigger.samples.StaticMethodDemo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * 静态方法测试
 *
 * @author suzailong
 * @date 2022/3/9-2:16 下午
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({
        StaticMethodDemo.class
})
public class MockStaticMethodTest {
    @Test
    public void staticTest() {
        PowerMockito.mockStatic(StaticMethodDemo.class);
        PowerMockito
                .when(StaticMethodDemo.isTrue_4())
                .thenReturn(false);

        Assert.assertFalse(StaticMethodDemo.isTrue_4());
    }
}
