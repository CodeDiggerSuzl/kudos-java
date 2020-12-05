package springboot.service;

/**
 * @author Suz1
 * @date 2020/10/10 12:25 上午
 */

public interface TestService {
    /**
     * test spring cache
     *
     * @param str  str
     * @param code code
     * @return str + code
     */
    String testCache(String str, int code);
}
