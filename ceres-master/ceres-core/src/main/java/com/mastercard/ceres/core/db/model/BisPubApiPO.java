package com.mastercard.ceres.core.db.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "bis_pub_api", schema = "shared_bis", catalog = "")
public class BisPubApiPO {
    private String id;
    private Long interfaceId;
    private String env;
    private String serverCode;
    private String target;
    private String mqInbound;
    private String mqOutbound;
    private String protocol;
    private String description;
    private String method;
    private String status;
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
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "interface_id")
    public Long getInterfaceId() {
        return interfaceId;
    }

    public void setInterfaceId(Long interfaceId) {
        this.interfaceId = interfaceId;
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
    @Column(name = "server_code")
    public String getServerCode() {
        return serverCode;
    }

    public void setServerCode(String serverCode) {
        this.serverCode = serverCode;
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
    @Column(name = "mq_inbound")
    public String getMqInbound() {
        return mqInbound;
    }

    public void setMqInbound(String mqInbound) {
        this.mqInbound = mqInbound;
    }

    @Basic
    @Column(name = "mq_outbound")
    public String getMqOutbound() {
        return mqOutbound;
    }

    public void setMqOutbound(String mqOutbound) {
        this.mqOutbound = mqOutbound;
    }

    @Basic
    @Column(name = "protocol")
    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
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
    @Column(name = "method")
    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
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
        return Objects.equals(id, that.id) &&
                Objects.equals(interfaceId, that.interfaceId) &&
                Objects.equals(env, that.env) &&
                Objects.equals(serverCode, that.serverCode) &&
                Objects.equals(target, that.target) &&
                Objects.equals(mqInbound, that.mqInbound) &&
                Objects.equals(mqOutbound, that.mqOutbound) &&
                Objects.equals(protocol, that.protocol) &&
                Objects.equals(description, that.description) &&
                Objects.equals(method, that.method) &&
                Objects.equals(status, that.status) &&
                Objects.equals(createBy, that.createBy) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(updateBy, that.updateBy) &&
                Objects.equals(updateTime, that.updateTime) &&
                Objects.equals(deleteFlag, that.deleteFlag) &&
                Objects.equals(version, that.version) &&
                Objects.equals(reserve1, that.reserve1) &&
                Objects.equals(reserve2, that.reserve2) &&
                Objects.equals(reserve3, that.reserve3);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, interfaceId, env, serverCode, target, mqInbound, mqOutbound, protocol, description, method, status, createBy, createTime, updateBy, updateTime, deleteFlag, version, reserve1, reserve2, reserve3);
    }
}
