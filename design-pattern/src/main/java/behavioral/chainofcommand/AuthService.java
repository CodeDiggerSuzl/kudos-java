package behavioral.chainofcommand;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p></p>
 *
 * @author suzailong
 * @date 2021/6/4 1:26 下午
 */
public class AuthService {

    private static final Map<String, Date> authMap = new ConcurrentHashMap<>();

    public static Date queryAuthInfo(String uId, String orderId) {
        return authMap.get(uId.concat(orderId));
    }

    public static void auth(String uId, String orderId) {
        authMap.put(uId.concat(orderId), new Date());
    }
}
