package com.mastercard.ceres.core;

import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import reactor.core.publisher.Mono;
import reactor.util.annotation.Nullable;

public class CeresWebFilter implements WebFilter{
    
    private CeresPluginWebHandler ceresPluginWebHandler;
    
    private static final String FILTER_TAG = "/Ceres";

    @Override
    public Mono<Void> filter(@Nullable final ServerWebExchange exchange, @Nullable final WebFilterChain chain) {
        ceresPluginWebHandler.handle(exchange);
        return chain.filter(exchange);
    }

    public CeresPluginWebHandler getCeresPluginWebHandler() {
        return ceresPluginWebHandler;
    }

    public void setCeresPluginWebHandler(CeresPluginWebHandler ceresPluginWebHandler) {
        this.ceresPluginWebHandler = ceresPluginWebHandler;
    }
}
