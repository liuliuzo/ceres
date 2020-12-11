package com.mastercard.ceres.core;

import java.io.Serializable;

import com.mastercard.ceres.utils.JsonUtils;

public class CeresBody implements Serializable {

    /**
     * appKey .
     */
    private String appKey;

    /**
     * path.
     */
    private String path;

    /**
     * the contextPath.
     */
    private String contextPath;

    /**
     * realUrl.
     */
    private String realUrl;

    /**
     * httpMethod now we only support "get","post" .
     */
    private String httpMethod;

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public String getRealUrl() {
        return realUrl;
    }

    public void setRealUrl(String realUrl) {
        this.realUrl = realUrl;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    @Override
    public String toString() {
        return "CeresBody [appKey=" + appKey + ", path=" + path + ", contextPath=" + contextPath + ", realUrl="
                + realUrl + ", httpMethod=" + httpMethod + "]";
    }
}
