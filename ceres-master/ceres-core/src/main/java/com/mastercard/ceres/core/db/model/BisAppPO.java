package com.mastercard.ceres.core.db.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "bis_app", schema = "shared_bis", catalog = "")
public class BisAppPO {
    private long id;
    private String appId;
    private String name;
    private String description;
    private String createBy;
    private Timestamp createTime;
    private String updateBy;
    private Timestamp updateTime;
    private Byte deleteFlag;
    private Integer version;
    private String reserve1;
    private String reserve2;
    private String reserve3;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "app_id")
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "create_by")
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Basic
    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "update_by")
    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    @Basic
    @Column(name = "update_time")
    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Basic
    @Column(name = "delete_flag")
    public Byte getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Byte deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    @Basic
    @Column(name = "version")
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Basic
    @Column(name = "reserve1")
    public String getReserve1() {
        return reserve1;
    }

    public void setReserve1(String reserve1) {
        this.reserve1 = reserve1;
    }

    @Basic
    @Column(name = "reserve2")
    public String getReserve2() {
        return reserve2;
    }

    public void setReserve2(String reserve2) {
        this.reserve2 = reserve2;
    }

    @Basic
    @Column(name = "reserve3")
    public String getReserve3() {
        return reserve3;
    }

    public void setReserve3(String reserve3) {
        this.reserve3 = reserve3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BisAppPO bisAppPO = (BisAppPO) o;
        return id == bisAppPO.id &&
                Objects.equals(appId, bisAppPO.appId) &&
                Objects.equals(name, bisAppPO.name) &&
                Objects.equals(description, bisAppPO.description) &&
                Objects.equals(createBy, bisAppPO.createBy) &&
                Objects.equals(createTime, bisAppPO.createTime) &&
                Objects.equals(updateBy, bisAppPO.updateBy) &&
                Objects.equals(updateTime, bisAppPO.updateTime) &&
                Objects.equals(deleteFlag, bisAppPO.deleteFlag) &&
                Objects.equals(version, bisAppPO.version) &&
                Objects.equals(reserve1, bisAppPO.reserve1) &&
                Objects.equals(reserve2, bisAppPO.reserve2) &&
                Objects.equals(reserve3, bisAppPO.reserve3);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, appId, name, description, createBy, createTime, updateBy, updateTime, deleteFlag, version, reserve1, reserve2, reserve3);
    }
}
