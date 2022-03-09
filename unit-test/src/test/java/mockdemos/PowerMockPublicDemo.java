package mockdemos;

import com.codigger.samples.MockPublicDemo;
import com.codigger.samples.MockNewDemo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertFalse;

/**
 * @author suzailong
 * @date 2022/3/9-1:28 下午
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(MockNewDemo.class)
public class PowerMockPublicDemo {

    @Test
    public void mockWhenNew() throws Exception {
        MockPublicDemo mock = PowerMockito.mock(MockPublicDemo.class);
        PowerMockito
                .when(mock.methodReturnTrue())
                .thenReturn(false);

        PowerMockito
                .whenNew(MockPublicDemo.class).withAnyArguments()
                .thenReturn(mock);
        MockNewDemo mockNewDemo = new MockNewDemo();

        assertFalse(mockNewDemo.isTrue());
    }
}
