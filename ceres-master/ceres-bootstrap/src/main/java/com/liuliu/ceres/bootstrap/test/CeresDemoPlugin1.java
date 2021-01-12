package com.liuliu.ceres.bootstrap.test;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.codec.HttpMessageReader;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.HandlerStrategies;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ServerWebExchange;

import com.mastercard.ceres.core.CeresContext;
import com.mastercard.ceres.plugin.base.InBoundPlugin;
import com.mastercard.ceres.plugin.chain.CeresPluginChain;

import reactor.core.publisher.Mono;

//@Component
public class CeresDemoPlugin1 extends InBoundPlugin {

    private static final Logger log = LoggerFactory.getLogger(CeresDemoPlugin1.class);

    private final List<HttpMessageReader<?>> messageReaders;
    
    public CeresDemoPlugin1() {
        this.messageReaders = HandlerStrategies.withDefaults().messageReaders();
    }
    
    @Override
    public int pluginOrder() {
        return 0;
    }

    @Override
    public String pluginName() {
        return "CeresDemoPlugin1";
    }

    @Override
    public boolean skipPlugin() {
        return false;
    }

    @Override
    public Mono<Void> doPlugin(CeresContext context,CeresPluginChain chain) {
        log.info("doPlugin !");
        ServerWebExchange exchange =(ServerWebExchange) context.getCeresRequst();
        ServerHttpRequest request = exchange.getRequest();
        ServerRequest serverRequest = ServerRequest.create(exchange, messageReaders);
        MediaType mediaType = request.getHeaders().getContentType();
        if (MediaType.APPLICATION_JSON.isCompatibleWith(mediaType)) {
            return body(context, serverRequest, chain);
        }
        return chain.execute(context);
    }
    
    
    private Mono<Void> body(final CeresContext context, final ServerRequest serverRequest, final CeresPluginChain chain) {
        ServerWebExchange exchange =(ServerWebExchange) context.getCeresRequst();
        return serverRequest.bodyToMono(String.class)
                .switchIfEmpty(Mono.defer(() -> Mono.just("")))
                .flatMap(body -> {
                    exchange.getAttributes().put("BODY", body);
                    return chain.execute(context);
                });
    }
}
