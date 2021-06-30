package behavioral.chainofcommand;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>认证责任链</p>
 * <p>
 * 这部分是责任链，链接起来的核⼼部分。
 * AuthChain next ，重点在于可以通过 next ⽅式获取下 ⼀个链路需要处理的节点。<br>
 * levelUserId 、 levelUserName ，是责任链中的公⽤信息，标记每⼀个审核节点的⼈员信息。
 * <br>抽象类中定义了⼀个抽象⽅法， abstract AuthInfo doAuth ，这是每⼀个实现者必须实现的
 * <p>
 * 类，不同的审核级别处理不同的业务。
 *
 * @author suzailong
 * @date 2021/6/3 9:33 下午
 */
@Slf4j
public abstract class AuthLink {
    protected SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 时间格式化

    protected String levelUserId;

    protected String levelUserName;

    private AuthLink next;

    public AuthLink(String levelUserId, String levelUserName) {
        log.info("AuthChain constructor.....");
        this.levelUserId = levelUserId;
        this.levelUserName = levelUserName;
    }

    public AuthLink next() {
        return next;
    }

    public AuthLink appendNext(AuthLink next) {
        this.next = next;
        return this;
    }

    public abstract AuthInfo doAuth(String uId, String orderId, Date authDate);
}
