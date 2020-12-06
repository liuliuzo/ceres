package com.mastercard.ceres.filter.base;

import com.mastercard.ceres.filter.CeresFilter;
import com.mastercard.ceres.filter.FilterType;

/**
 * @className OutBoundFilter
 * @description
 * @author liuliu
 * @version 1.0
 * @email liuliu.zhao@mastercard.com
 */
public abstract class OutBoundFilter implements CeresFilter {
    public FilterType filterType() {
        return FilterType.OUTBOUND;
    }
}
