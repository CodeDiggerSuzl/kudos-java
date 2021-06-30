package behavioral.chainofcommand;

import lombok.Data;

/**
 * <p></p>
 *
 * @author suzailong
 * @date 2021/6/3 1:47 下午
 */
@Data
public class AuthInfo {

    private String code;
    private String info = "";

    public AuthInfo(String code, String... infos) {
        this.code = code;

        for (String str : infos) {
            this.info = this.info.concat(str);
        }

    }
}
