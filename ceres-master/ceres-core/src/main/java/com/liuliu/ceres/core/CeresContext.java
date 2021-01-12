package com.liuliu.ceres.core;

import org.springframework.web.server.ServerWebExchange;

/**
 * @className CeresContext
 * @description
 * @author liuliu
 * @version 1.0
 * @email liuliu.zhao@mastercard.com
 */
public class CeresContext {

    /**
     * ceres requst object
     */
    private ServerWebExchange ceresRequst;

    /**
     * ceres response object
     */
    private Object ceresResponse;

    public ServerWebExchange getCeresRequst() {
        return ceresRequst;
    }

    public void setCeresRequst(ServerWebExchange ceresRequst) {
        this.ceresRequst = ceresRequst;
    }

    public Object getCeresResponse() {
        return ceresResponse;
    }

    public void setCeresResponse(Object ceresResponse) {
        this.ceresResponse = ceresResponse;
    }
}
