package com.mastercard.ceres.bootstrap.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.mastercard.ceres.core.CeresContext;
import com.mastercard.ceres.plugin.base.OutBoundPlugin;
import com.mastercard.ceres.plugin.chain.CeresPluginChain;
import com.mastercard.ceres.utils.WebFluxResultUtils;

import reactor.core.publisher.Mono;

/**
 * @className CeresDemoPlugin2
 * @description
 * @author liuliu
 * @version 1.0
 * @email liuliu.zhao@mastercard.com
 */
@Component
public class CeresDemoPlugin3 extends OutBoundPlugin {

    private static final Logger log = LoggerFactory.getLogger(CeresDemoPlugin3.class);

    @Override
    public int pluginOrder() {
        return 3;
    }

    @Override
    public String pluginName() {
        return "CeresDemoPlugin3";
    }

    @Override
    public boolean skipPlugin() {
        return false;
    }

    @Override
    public Mono<Void> doPlugin(CeresContext context, CeresPluginChain chain) {
        log.info("doPlugin !");
        ServerWebExchange exchange = (ServerWebExchange) context.getCeresRequst();
        // exchange.getResponse().setStatusCode(HttpStatus.TOO_MANY_REQUESTS);

//        if(!this.skipPlugin()) {
//			return WebFluxResultUtils.result(exchange, "{}");
//        } 

        return exchange.getResponse().setComplete();
    }

}
