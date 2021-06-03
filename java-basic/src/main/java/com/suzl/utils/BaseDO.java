package com.suzl.utils;

import java.util.Date;

/**
 * <p></p>
 *
 * @author suzailong
 * @date 2021/5/29 6:04 下午
 */
public class BaseDO {

    /**
     * 主键id
     */
    private Long id;
    /**
     * 租户id
     */
    private Long tenantId;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 修改时间
     */
    private Date gmtModified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

}
