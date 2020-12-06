package com.mastercard.ceres.core;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.mastercard.ceres.filter.CeresFilter;
import com.mastercard.ceres.filter.FilterType;
import com.mastercard.ceres.filter.loader.CeresFilterLoader;

import reactor.core.publisher.Mono;

/**
 * @className CeresHandler
 * @description
 * @author liuliu
 * @email liuliu.zhao@mastercard.com
 * @date 2019-03-15 10:45
 **/
public class CeresHandler {

    private static final Logger log = LoggerFactory.getLogger(CeresFilterLoader.class);

    private CeresFilterLoader ceresFilterLoader;

    public CeresHandler(CeresFilterLoader ceresFilterLoader) {
        this.ceresFilterLoader = ceresFilterLoader;
    }

    public CeresFilterLoader getCeresFilterLoader() {
        return ceresFilterLoader;
    }

    public void setCeresFilterLoader(CeresFilterLoader ceresFilterLoader) {
        this.ceresFilterLoader = ceresFilterLoader;
    }

    @SuppressWarnings("unchecked")
    public Mono<ServerResponse> execute(ServerRequest request) {
        CeresContext ceresContext = new CeresContext();
        ceresContext.setCeresRequst(request);
        this.run(ceresContext);
        Mono<ServerResponse> serverRespons = (Mono<ServerResponse>) ceresContext.getCeresResponse();
        return serverRespons;
    }

    private void run(CeresContext ceresContext) {
        Set<CeresFilter> inboundFilters = ceresFilterLoader.getFiltersByType(FilterType.INBOUND);
        Set<CeresFilter> endpointFilters = ceresFilterLoader.getFiltersByType(FilterType.ENDPOINT);
        Set<CeresFilter> outboundFilters = ceresFilterLoader.getFiltersByType(FilterType.OUTBOUND);
        Set<CeresFilter> errorFilters = ceresFilterLoader.getFiltersByType(FilterType.ERROR);
        try {
            processFilters(inboundFilters, ceresContext);
            processFilters(endpointFilters, ceresContext);
            processFilters(outboundFilters, ceresContext);
        } catch (Exception e) {
            log.error("error occurred",e);
            processFilters(errorFilters, ceresContext);
        }
    }

    private void processFilters(Set<CeresFilter> ceresFilters, CeresContext ceresContext) {
        for (CeresFilter ceresFilter : ceresFilters) {
            if (!ceresFilter.skipFilter()) {
                log.info("process filterType:{},filterName:{}", ceresFilter.filterType(), ceresFilter.filterName());
                ceresFilter.doFilter(ceresContext);
            } else {
                log.info("filterName:{} skip not pr!", ceresFilter.filterName());
            }
        }
    }
}
