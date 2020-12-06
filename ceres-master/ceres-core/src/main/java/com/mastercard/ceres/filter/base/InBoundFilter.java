package com.mastercard.ceres.filter.base;

import com.mastercard.ceres.filter.CeresFilter;
import com.mastercard.ceres.filter.FilterType;

/**
 * @className InBoundFilter
 * @description
 * @author liuliu
 * @version 1.0
 * @email liuliu.zhao@mastercard.com
 */
public abstract class InBoundFilter implements CeresFilter {
    public FilterType filterType() {
        return FilterType.INBOUND;
    }
}
