package com.common.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户分组
 *
 * @author suzailong
 * @date 2022/4/1-10:59
 */
@Data
@Accessors(chain = true)
public class UserGroup {
    private String groupId;

    private String groupName;
}
