package com.mastercard.ceres.core.start;

/**
 * @className CereDomain
 * @description
 * @author liuliu
 * @email liuliu.zhao@mastercard.com
 * @date 2019-03-15 10:45
 **/
public final class CereDomain {

    private static final CereDomain Cere_DOMAIN = new CereDomain();

    /**
     * ip:port.
     */
    private String httpPath;

    private CereDomain() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static CereDomain getInstance() {
        return Cere_DOMAIN;
    }

    public String getHttpPath() {
        return httpPath;
    }

    public void setHttpPath(String httpPath) {
        this.httpPath = httpPath;
    }
    
}
