package com.mastercard.ceres.core;

import java.util.Objects;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import reactor.core.publisher.Mono;
import reactor.util.annotation.Nullable;

/**
 * 
 * @author liuliu
 *
 */
public class CeresWebFilter implements WebFilter{

    private CeresPluginWebHandler ceresPluginWebHandler;
    
    private static final String FILTER_TAG = "/ceres";

    public CeresPluginWebHandler getCeresPluginWebHandler() {
        return ceresPluginWebHandler;
    }

    public void setCeresPluginWebHandler(CeresPluginWebHandler ceresPluginWebHandler) {
        this.ceresPluginWebHandler = ceresPluginWebHandler;
    }

    @Override
    public Mono<Void> filter(@Nullable final ServerWebExchange exchange, @Nullable final WebFilterChain chain) {
        ServerHttpRequest request = Objects.requireNonNull(exchange).getRequest();
        String urlPath = request.getURI().getPath();
            if (FILTER_TAG.equals(urlPath)) {
                ceresPluginWebHandler.handle(exchange);
                return Objects.requireNonNull(chain).filter(exchange);
            }
        return chain.filter(exchange);
    }
}
