package com.mastercard.ceres.core;

import java.util.Map;

import com.google.common.collect.Maps;

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
    private Object ceresRequst;

    /**
     * ceres response object
     */
    private Object ceresResponse;

    /**
     * business routing object
     */
    private Map<String, Object> routingRequestMap = Maps.newConcurrentMap();

    /**
     * process result of filters
     */
    private Map<String, Object> filtersMap = Maps.newConcurrentMap();

    public Object getCeresRequst() {
        return ceresRequst;
    }

    public void setCeresRequst(Object ceresRequst) {
        this.ceresRequst = ceresRequst;
    }

    public Object getCeresResponse() {
        return ceresResponse;
    }

    public void setCeresResponse(Object ceresResponse) {
        this.ceresResponse = ceresResponse;
    }

    public Map<String, Object> getRoutingRequestMap() {
        return routingRequestMap;
    }

    public void setRoutingRequestMap(Map<String, Object> routingRequestMap) {
        this.routingRequestMap = routingRequestMap;
    }

    public Map<String, Object> getFiltersMap() {
        return filtersMap;
    }

    public void setFiltersMap(Map<String, Object> filtersMap) {
        this.filtersMap = filtersMap;
    }

    @Override
    public String toString() {
        return "ceresContext [ceresRequst=" + ceresRequst + ", ceresResponse=" + ceresResponse + ", routingRequestMap="
                + routingRequestMap + ", filtersMap=" + filtersMap + "]";
    }
}
