package com.mastercard.ceres.bootstrap.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.server.ServerWebExchange;

import com.mastercard.ceres.bootstrap.utils.CeresUtils;
import com.mastercard.ceres.core.CeresContext;
import com.mastercard.ceres.plugin.base.OutBoundPlugin;
import com.mastercard.ceres.plugin.chain.CeresPluginChain;

import reactor.core.publisher.Mono;

//@Component
public class CeresDemoPlugin4 extends OutBoundPlugin {

    private static final Logger log = LoggerFactory.getLogger(CeresDemoPlugin4.class);

    @Override
    public int pluginOrder() {
        return 4;
    }

    @Override
    public String pluginName() {
        return "CeresDemoPlugin4";
    }

    @Override
    public boolean stopPluginProcessing() {
        return false;
    }

    @Override
    public boolean skipPlugin() {
        return false;
    }

    @Override
    public Mono<Void> doPlugin(CeresContext context,CeresPluginChain chain) {
        log.info("doPlugin !");
        ServerWebExchange exchange =(ServerWebExchange) context.getCeresRequst();
        ServerHttpResponse response=exchange.getResponse();
        ClientResponse clientResponse = exchange.getAttribute(CeresUtils.CLIENT_RESPONSE_ATTR);
        if (clientResponse == null) {
            log.info("clientResponse is null !");
            return Mono.empty();
        }
        return response.writeWith(clientResponse.body(BodyExtractors.toDataBuffers())).doOnCancel(() -> cleanup(exchange));
    }
    
    private void cleanup(ServerWebExchange exchange) {
        ClientResponse clientResponse = exchange.getAttribute(CeresUtils.CLIENT_RESPONSE_ATTR);
        if (clientResponse != null) {
            clientResponse.bodyToMono(Void.class).subscribe();
        }
    }
}
