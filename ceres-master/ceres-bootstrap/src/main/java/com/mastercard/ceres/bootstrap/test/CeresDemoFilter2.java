package com.mastercard.ceres.bootstrap.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.mastercard.ceres.core.CeresContext;
import com.mastercard.ceres.filter.base.InBoundFilter;
import com.mastercard.ceres.filter.loader.StaticFilterLoader;

@Component
public class CeresDemoFilter2 extends InBoundFilter {

    private static final Logger log = LoggerFactory.getLogger(StaticFilterLoader.class);
    
    @Override
    public String filterName() {
        return "CeresDemoFilter2";
    }

    @Override
    public int filterOrder() {
        return 2;
    }

    @Override
    public boolean stopFilterProcessing() {
        return false;
    }

    @Override
    public boolean skipFilter() {
        return true;
    }

    @Override
    public void doFilter(CeresContext context) {
        log.info("doFilter {}!", this);
    }
}
