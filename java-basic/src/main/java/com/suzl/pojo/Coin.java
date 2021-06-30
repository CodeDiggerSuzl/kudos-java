package com.suzl.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * <p>coin</p>
 *
 * @author suzailong
 * @date 2021/5/21 1:37 下午
 */
@Data
@AllArgsConstructor
public class Coin {

    private String coinName;

    private Integer worth;
}
