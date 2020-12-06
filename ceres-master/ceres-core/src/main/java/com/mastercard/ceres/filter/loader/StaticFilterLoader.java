package com.mastercard.ceres.filter.loader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.google.common.collect.Maps;
import com.mastercard.ceres.filter.CeresFilter;
import com.mastercard.ceres.filter.FilterType;

/**
 * @className StaticFilterLoader
 * @description
 * @author liuliu
 * @email liuliu.zhao@mastercard.com
 * @date 2019-03-15 10:45
 **/
public class StaticFilterLoader implements CeresFilterLoader {

    private static final Logger LOGGER = LoggerFactory.getLogger(StaticFilterLoader.class);

    /**
     * Maps filters <FilterType,Filter>
     */
    private final ConcurrentMap<FilterType, SortedSet<CeresFilter>> filterMap = Maps.newConcurrentMap();
    private final ConcurrentMap<FilterType, Map<String, CeresFilter>> filtersByTypeAndName = Maps.newConcurrentMap();
    
    public StaticFilterLoader(List<CeresFilter> filters) {
        Assert.notNull(filters, "filters can not null !");
        initfilterMap(filters);
    }

    private void initfilterMap(List<CeresFilter> filters) {
        filters.forEach(filter -> {
            Assert.notNull(filter.filterType(), "filterType can not null !");
            Assert.notNull(filter.filterName(), "filterName can not null !");
            LOGGER.info("loader filter type:{},name:{},order:{}",filter.filterType(),filter.filterName(),filter.filterOrder());
            filterMap.computeIfAbsent(filter.filterType(), k -> new TreeSet<>(FILTER_COMPARATOR)).add(filter);
            filtersByTypeAndName.computeIfAbsent(filter.filterType(), k -> new HashMap<>()).put(filter.filterName(), filter);
        });
        Set<CeresFilter> inboundFilters = this.getFiltersByType(FilterType.INBOUND);
        Set<CeresFilter> endpointFilters = this.getFiltersByType(FilterType.ENDPOINT);
        Set<CeresFilter> outboundFilters = this.getFiltersByType(FilterType.OUTBOUND);
        Set<CeresFilter> errorFilters = this.getFiltersByType(FilterType.ERROR);
        LOGGER.info("INBOUND filters:{}", inboundFilters);
        LOGGER.info("ENDPOINT filters:{}", endpointFilters);
        LOGGER.info("OUTBOUND filters:{}", outboundFilters);
        LOGGER.info("ERROR filters:{}", errorFilters);
    }

    @Override
    public SortedSet<CeresFilter> getFiltersByType(FilterType filterType) {
        Assert.notNull(filterType, "filterType can not null !");
        return filterMap.get(filterType);
    }

    @Override
    public CeresFilter getFilterByNameAndType(String filterName,FilterType filterType) {
        Assert.notNull(filterName, "filterName can not null !");
        Assert.notNull(filterType, "filterType can not null !");
        Map<String, CeresFilter> filtersByName = filtersByTypeAndName.get(filterType);
        if (filtersByName == null) {
            LOGGER.warn("filtersByName is null");
            return null;
        }
        return filtersByName.get(filterName);
    }
}
