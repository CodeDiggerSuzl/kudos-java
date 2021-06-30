package behavioral.chainofcommand;

import java.text.ParseException;
import java.util.Date;

/**
 * <p></p>
 *
 * @author suzailong
 * @date 2021/6/4 1:32 下午
 */
public class Level3AuthLink extends AuthLink {
    private final Date beginDate = f.parse("2020-06-01 00:00:00");
    private final Date endDate = f.parse("2020-06-25 23:59:59");

    public Level3AuthLink(String levelUserId, String levelUserName) throws ParseException {
        super(levelUserId, levelUserName);
    }


    @Override
    public AuthInfo doAuth(String uId, String orderId, Date authDate) {
        Date date = AuthService.queryAuthInfo(levelUserId, orderId);
        if (null == date) {
            return new AuthInfo("0001", "单号：", orderId, " 状态：待三级审批 负责⼈ ", levelUserName);
        }
        AuthLink next = super.next();
        if (null == next) {
            return new AuthInfo("0000", "单号：", orderId, " 状态：三级审批负 责⼈完成", " 时间：", f.format(date), " 审批⼈：", levelUserName);
        }

        if (authDate.before(beginDate) || authDate.after(endDate)) {
            return new AuthInfo("0000", "单号：", orderId, " 状态：三级审批负 责⼈完成", " 时间：", f.format(date), " 审批⼈：", levelUserName);

        }

        return next.doAuth(uId, orderId, authDate);

    }
}
