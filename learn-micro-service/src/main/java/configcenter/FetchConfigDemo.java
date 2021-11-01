package configcenter;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * @author suzailong
 * @date 2021/9/28 9:42 下午 周二
 */
public class FetchConfigDemo {
    public static void main(String[] args) throws NacosException, IOException {
        String serverAddr = "localhost";
        String dateId = "RateLimiterCfg";
        String group = "DEFAULT_GROUP";
        Properties prop = new Properties();
        prop.put("serverAddr", serverAddr);
        ConfigService configService = NacosFactory.createConfigService(prop);
        String config = configService.getConfig(dateId, group, 5000);
        System.out.println("Get config: " + config);
        configService.addListener(dateId, group, new Listener() {
            @Override
            public Executor getExecutor() {
                return null;
            }

            @Override
            public void receiveConfigInfo(String incomingCfg) {
                System.out.println("currTs:" + new Date().getTime() + " get config: ==" + incomingCfg);
            }
        });

        int read = System.in.read();
    }
}
