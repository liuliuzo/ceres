package com.mastercard.ceres.plugin.httpclient.netty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mastercard.ceres.core.CeresContext;
import com.mastercard.ceres.filter.base.OutBoundFilter;


/**
 * @className NettyClientOutBoundFilter
 * @description
 * @author liuliu
 * @email liuliu.zhao@mastercard.com
 * @date 2019-03-15 10:45
 **/
public class NettyClientOutBoundFilter extends OutBoundFilter {
    private static final Logger log = LoggerFactory.getLogger(NettyClientOutBoundFilter.class);

    @Override
    public String filterName() {
        return "NettyClientOutBoundFilter";
    }

    @Override
    public int filterOrder() {
        return 4;
    }

    @Override
    public boolean skipFilter() {
        return false;
    }

    @Override
    public void doFilter(CeresContext context) {
        log.info("doFilter {} !",this);
    }

    @Override
    public boolean stopFilterProcessing() {
        return false;
    }
}
