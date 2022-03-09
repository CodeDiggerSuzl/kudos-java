package mockdemos;

import com.codigger.samples.SpyDemo;
import org.junit.Assert;
import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;

/**
 * Spy test
 *
 * @author suzailong
 * @date 2022/3/9-2:25 下午
 */
public class SpyTest {
    @Test
    public void spyTest() {

        SpyDemo spy = PowerMockito.spy(new SpyDemo());
        PowerMockito.doReturn(false).when(spy).isTrue_1();
        Assert.assertFalse(spy.isTrue_1());
        Assert.assertTrue(spy.isTrue_2());
    }
}
