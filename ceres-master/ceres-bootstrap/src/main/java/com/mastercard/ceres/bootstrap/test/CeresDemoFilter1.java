package com.mastercard.ceres.bootstrap.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.mastercard.ceres.bootstrap.test.CeresDemoFilter1;
import com.mastercard.ceres.core.CeresContext;
import com.mastercard.ceres.filter.base.InBoundFilter;
import com.mastercard.ceres.filter.loader.StaticFilterLoader;

@Component
public class CeresDemoFilter1 extends InBoundFilter {

    private static final Logger log = LoggerFactory.getLogger(StaticFilterLoader.class);

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public String filterName() {
        return "CeresDemoFilter1";
    }

    @Override
    public boolean stopFilterProcessing() {
        return true;
    }

    @Override
    public boolean skipFilter() {
        return false;
    }

    @Override
    public void doFilter(CeresContext context) {
        log.info("doFilter {}!", this);
    }
}
