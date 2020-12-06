package com.mastercard.ceres.plugin.httpclient.webClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mastercard.ceres.core.CeresContext;
import com.mastercard.ceres.filter.base.OutBoundFilter;

/**
 * @className NettyHttpClientFilter
 * @description
 * @author liuliu
 * @email liuliu.zhao@mastercard.com
 * @date 2019-03-15 10:45
 **/
public class WebClientOutBoundFilter extends OutBoundFilter {

    private static final Logger log = LoggerFactory.getLogger(WebClientOutBoundFilter.class);

    @Override
    public String filterName() {
        return "WebClientOutBoundFilter";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean skipFilter() {
        return false;
    }

    @Override
    public void doFilter(CeresContext context) {
    }

    @Override
    public boolean stopFilterProcessing() {
        return false;
    }
}
