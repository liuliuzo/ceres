package com.mastercard.ceres.filter;

import com.mastercard.ceres.core.CeresContext;

/**
 * @className CeresFilter
 * @description
 * @author liuliu
 * @version 1.0
 * @email liuliu.zhao@mastercard.com
 */
public interface CeresFilter {

    /**
     * filterName
     * @return
     */
    String filterName();
    
    /**
     * filterType
     * @return
     */
    FilterType filterType();

    /**
     * priority order to execute filter
     * @return
     */
    int filterOrder();

    /**
     * whether to execute this filter
     * @return
     */
    boolean skipFilter();

    /**
     * execute filter
     * @param context
     */
    void doFilter(CeresContext context);

    /**
     * Stop Filter Processing
     * @return boolean
     */
    boolean stopFilterProcessing();
}