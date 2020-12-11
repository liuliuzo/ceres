package com.mastercard.ceres.bootstrap.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.mastercard.ceres.core.CeresContext;
import com.mastercard.ceres.plugin.base.InBoundPlugin;
import com.mastercard.ceres.plugin.chain.CeresPluginChain;
import com.mastercard.ceres.utils.WebFluxResultUtils;

import reactor.core.publisher.Mono;

@Component
public class CeresDemoPlugin1 extends InBoundPlugin {

    private static final Logger log = LoggerFactory.getLogger(CeresDemoPlugin1.class);

    @Override
    public int pluginOrder() {
        return 1;
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
        exchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        if(!this.skipPlugin()) {
			return WebFluxResultUtils.result(exchange, "{}");
        }
        return chain.execute(context);
    }
}
