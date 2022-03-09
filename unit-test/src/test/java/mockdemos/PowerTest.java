package mockdemos;

import com.codigger.samples.MockPublicDemo;
import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;

import static org.junit.Assert.*;


/**
 * power mock test
 *
 * @author suzailong
 * @date 2022/3/9-1:11 下午
 */
public class PowerTest {


    /**
     * mock simple public method, the same way as mocking public final method.
     */
    @Test
    public void mockPublicMethod() {
        MockPublicDemo demo = PowerMockito.mock(MockPublicDemo.class);
        PowerMockito
                .when(demo.methodReturnTrue())
                .thenReturn(null);

        boolean mockResult = demo.methodReturnTrue();
        assertFalse(mockResult);
    }


}
