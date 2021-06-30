package behavioral.chainofcommand;

import java.util.Date;

/**
 * <p>1 </p>
 *
 * @author suzailong
 * @date 2021/6/3 9:40 下午
 */
public class Level1AuthLink extends AuthLink {

    public Level1AuthLink(String levelUserId, String levelUserName) {
        super(levelUserId, levelUserName);
    }

    @Override
    public AuthInfo doAuth(String uId, String orderId, Date authDate) {
        Date date = AuthService.queryAuthInfo(levelUserId, orderId);
        if (null == date) {
            return new AuthInfo("0001", "单号：", orderId, " 状态：待⼀级审批 负责⼈ ", levelUserName);
        }
        AuthLink next = super.next();
        if (null == next) {
            return new AuthInfo("0000", "单号：", orderId, " 状态：⼀级审批完 成负责⼈", " 时间：", f.format(date), " 审批⼈：", levelUserName);
        }

        return next.doAuth(uId, orderId, authDate);
    }
}
