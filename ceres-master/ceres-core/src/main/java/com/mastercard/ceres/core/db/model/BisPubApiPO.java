package com.mastercard.ceres.core.db.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "bis_pub_api", schema = "shared_bis_update", catalog = "")
public class BisPubApiPO {
    private long id;
    private String pubAppCode;
    private String apiCode;
    private String name;
    private String description;
    private String pluginChainCode;
    private String env;
    private String status;
    private String target;
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
    @Column(name = "pub_app_code")
    public String getPubAppCode() {
        return pubAppCode;
    }

    public void setPubAppCode(String pubAppCode) {
        this.pubAppCode = pubAppCode;
    }

    @Basic
    @Column(name = "api_code")
    public String getApiCode() {
        return apiCode;
    }

    public void setApiCode(String apiCode) {
        this.apiCode = apiCode;
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
    @Column(name = "plugin_chain_code")
    public String getPluginChainCode() {
        return pluginChainCode;
    }

    public void setPluginChainCode(String pluginChainCode) {
        this.pluginChainCode = pluginChainCode;
    }

    @Basic
    @Column(name = "env")
    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    @Basic
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "target")
    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
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

        BisPubApiPO that = (BisPubApiPO) o;

        if (id != that.id) return false;
        if (pubAppCode != null ? !pubAppCode.equals(that.pubAppCode) : that.pubAppCode != null) return false;
        if (apiCode != null ? !apiCode.equals(that.apiCode) : that.apiCode != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (pluginChainCode != null ? !pluginChainCode.equals(that.pluginChainCode) : that.pluginChainCode != null)
            return false;
        if (env != null ? !env.equals(that.env) : that.env != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (target != null ? !target.equals(that.target) : that.target != null) return false;
        if (createBy != null ? !createBy.equals(that.createBy) : that.createBy != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (updateBy != null ? !updateBy.equals(that.updateBy) : that.updateBy != null) return false;
        if (updateTime != null ? !updateTime.equals(that.updateTime) : that.updateTime != null) return false;
        if (deleteFlag != null ? !deleteFlag.equals(that.deleteFlag) : that.deleteFlag != null) return false;
        if (version != null ? !version.equals(that.version) : that.version != null) return false;
        if (reserve1 != null ? !reserve1.equals(that.reserve1) : that.reserve1 != null) return false;
        if (reserve2 != null ? !reserve2.equals(that.reserve2) : that.reserve2 != null) return false;
        if (reserve3 != null ? !reserve3.equals(that.reserve3) : that.reserve3 != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (pubAppCode != null ? pubAppCode.hashCode() : 0);
        result = 31 * result + (apiCode != null ? apiCode.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (pluginChainCode != null ? pluginChainCode.hashCode() : 0);
        result = 31 * result + (env != null ? env.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (target != null ? target.hashCode() : 0);
        result = 31 * result + (createBy != null ? createBy.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (updateBy != null ? updateBy.hashCode() : 0);
        result = 31 * result + (updateTime != null ? updateTime.hashCode() : 0);
        result = 31 * result + (deleteFlag != null ? deleteFlag.hashCode() : 0);
        result = 31 * result + (version != null ? version.hashCode() : 0);
        result = 31 * result + (reserve1 != null ? reserve1.hashCode() : 0);
        result = 31 * result + (reserve2 != null ? reserve2.hashCode() : 0);
        result = 31 * result + (reserve3 != null ? reserve3.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BisPubApiPO [id=" + id + ", pubAppCode=" + pubAppCode + ", apiCode=" + apiCode + ", name=" + name
                + ", description=" + description + ", pluginChainCode=" + pluginChainCode + ", env=" + env + ", status="
                + status + ", target=" + target + ", createBy=" + createBy + ", createTime=" + createTime
                + ", updateBy=" + updateBy + ", updateTime=" + updateTime + ", deleteFlag=" + deleteFlag + ", version="
                + version + ", reserve1=" + reserve1 + ", reserve2=" + reserve2 + ", reserve3=" + reserve3 + "]";
    }
}
