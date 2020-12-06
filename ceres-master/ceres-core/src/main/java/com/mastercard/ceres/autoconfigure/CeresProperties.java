package com.mastercard.ceres.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = CeresProperties.PREFIX)
public class CeresProperties {

    /**
     * Properties prefix.
     */
    public static final String PREFIX = "ceres.gateway";

    private String enableMQ;

    private String enableHttp;

    private String enableRpc;

    public String getEnableMQ() {
        return enableMQ;
    }

    public void setEnableMQ(String enableMQ) {
        this.enableMQ = enableMQ;
    }

    public String getEnableHttp() {
        return enableHttp;
    }

    public void setEnableHttp(String enableHttp) {
        this.enableHttp = enableHttp;
    }

    public String getEnableRpc() {
        return enableRpc;
    }

    public void setEnableRpc(String enableRpc) {
        this.enableRpc = enableRpc;
    }
}
