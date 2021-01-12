package com.liuliu.ceres.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 
 * @author liuliu
 *
 */
@ConfigurationProperties(prefix = CeresProperties.PREFIX)
public class CeresProperties {

    /**
     * Properties prefix.
     */
    public static final String PREFIX = "ceres.gateway";

    private String loader;

    private String schedulerType;

    private String workThreads;

    public String getLoader() {
        return loader;
    }

    public void setLoader(String loader) {
        this.loader = loader;
    }

    public String getSchedulerType() {
        return schedulerType;
    }

    public void setSchedulerType(String schedulerType) {
        this.schedulerType = schedulerType;
    }

    public String getWorkThreads() {
        return workThreads;
    }

    public void setWorkThreads(String workThreads) {
        this.workThreads = workThreads;
    }
}
