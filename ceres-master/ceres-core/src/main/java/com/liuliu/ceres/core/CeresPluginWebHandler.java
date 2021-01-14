package com.liuliu.ceres.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebHandler;

import com.liuliu.ceres.autoconfigure.CeresProperties;
import com.liuliu.ceres.plugin.chain.DefaultCeresPluginChain;
import com.liuliu.ceres.plugin.loader.CeresPluginLoader;

import lombok.Getter;
import lombok.Setter;
import reactor.core.publisher.Mono;

/**
 * @className CeresHandler
 * @description
 * @author liuliu
 * @email liuliu.zhao@mastercard.com
 * @date 2019-03-15 10:45
 **/
public class CeresPluginWebHandler implements WebHandler {

    private static final Logger log = LoggerFactory.getLogger(CeresPluginWebHandler.class);

    @Getter
    @Setter
    private CeresPluginLoader ceresPluginLoader;

    public CeresPluginWebHandler(CeresPluginLoader ceresPluginLoader,CeresProperties ceresProperties) {
        this.ceresPluginLoader = ceresPluginLoader;
    }

    @Override
    public Mono<Void> handle(ServerWebExchange exchange) {
        CeresContext ceresContext = new CeresContext();
        ceresContext.setCeresRequst(exchange);
        return new DefaultCeresPluginChain(ceresPluginLoader).execute(ceresContext);
    }
}
