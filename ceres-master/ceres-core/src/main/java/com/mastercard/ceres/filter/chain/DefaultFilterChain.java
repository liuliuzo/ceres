package com.mastercard.ceres.filter.chain;

import java.util.List;

import com.mastercard.ceres.filter.CeresFilter;

public class DefaultFilterChain implements CeresFilterChain {

    private int index;

    private final List<CeresFilter> filters;

    public DefaultFilterChain(final List<CeresFilter> filters) {
        this.filters = filters;
    }

    @Override
    public void execute() {
        // TODO Auto-generated method stub
        
    }
}
