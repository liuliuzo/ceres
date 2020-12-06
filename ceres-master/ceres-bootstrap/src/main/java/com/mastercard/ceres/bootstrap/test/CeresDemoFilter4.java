package com.mastercard.ceres.bootstrap.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.mastercard.ceres.bootstrap.test.CeresDemoFilter4;
import com.mastercard.ceres.core.CeresContext;
import com.mastercard.ceres.filter.base.OutBoundFilter;
import com.mastercard.ceres.filter.loader.StaticFilterLoader;

import reactor.core.publisher.Mono;

@Component
public class CeresDemoFilter4 extends OutBoundFilter {

    private static final Logger log = LoggerFactory.getLogger(StaticFilterLoader.class);

    @Override
    public int filterOrder() {
        return 4;
    }

    @Override
    public String filterName() {
        return "CeresDemoFilter4";
    }

    @Override
    public boolean stopFilterProcessing() {
        return false;
    }

    @Override
    public boolean skipFilter() {
        return false;
    }

    @Override
    public void doFilter(CeresContext context) {
        log.info("doFilter {}!", this);
        Mono<ServerResponse> serverResponse = ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
                .body(BodyInserters.fromObject("Run,Chain!"));
        context.setCeresResponse(serverResponse);
    }

}
