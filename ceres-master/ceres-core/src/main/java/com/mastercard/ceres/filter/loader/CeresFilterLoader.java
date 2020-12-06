package com.mastercard.ceres.filter.loader;

import java.util.Comparator;
import java.util.SortedSet;

import com.mastercard.ceres.filter.CeresFilter;
import com.mastercard.ceres.filter.FilterType;

/**
 * @className FilterLoader
 * @description
 * @author liuliu
 * @version 1.0
 * @email liuliu.zhao@mastercard.com
 */
public interface CeresFilterLoader {

    SortedSet<CeresFilter> getFiltersByType(FilterType filterType);

    CeresFilter getFilterByNameAndType(String filterName,FilterType filterType);

    Comparator<CeresFilter> FILTER_COMPARATOR = Comparator.<CeresFilter>comparingInt(CeresFilter::filterOrder)
            .thenComparing(CeresFilter::filterName);
}
