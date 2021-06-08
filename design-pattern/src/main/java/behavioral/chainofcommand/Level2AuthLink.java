package behavioral.chainofcommand;

import java.text.ParseException;
import java.util.Date;

/**
 * <p></p>
 *
 * @author suzailong
 * @date 2021/6/4 1:28 下午
 */
public class Level2AuthLink extends AuthLink {
    private final Date beginDate = f.parse("2020-06-11 00:00:00");
    private final Date endDate = f.parse("2020-06-20 23:59:59");

    public Level2AuthLink(String levelUserId, String levelUserName) throws ParseException {
        super(levelUserId, levelUserName);
    }

    @Override
    public AuthInfo doAuth(String uId, String orderId, Date authDate) {
        Date date = AuthService.queryAuthInfo(levelUserId, orderId);
        if (null == date) {
            return new AuthInfo("0001", "单号：", orderId, " 状态：待⼆级审批 负责⼈ ", levelUserName);
        }
        AuthLink next = super.next();
        if (null == next) {
            return new AuthInfo("0000", "单号：", orderId, " 状态：⼆级审批完 成负责⼈", " 时间：", f.format(date), " 审批⼈：", levelUserName);
        }
        if (authDate.before(beginDate) || authDate.after(endDate)) {
            return new AuthInfo("0000", "单号：", orderId, " 状态：⼆级审批完 成负责⼈", " 时间：", f.format(date), " 审批⼈：", levelUserName);

        }

        return next.doAuth(uId, orderId, authDate);
    }
}