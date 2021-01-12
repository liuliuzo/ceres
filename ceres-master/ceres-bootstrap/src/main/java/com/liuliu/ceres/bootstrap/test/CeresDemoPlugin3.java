package com.liuliu.ceres.bootstrap.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.server.ServerWebExchange;

import com.liuliu.ceres.core.CeresContext;
import com.liuliu.ceres.plugin.base.OutBoundPlugin;
import com.liuliu.ceres.plugin.chain.CeresPluginChain;

import reactor.core.publisher.Mono;

import static com.liuliu.ceres.constant.Constants.CLIENT_RESPONSE_ATTR;

//@Component
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
        return true;
    }

    @Override
    public Mono<Void> doPlugin(CeresContext context,CeresPluginChain chain) {
        log.info("doPlugin !");
        ServerWebExchange exchange =(ServerWebExchange) context.getCeresRequst();
        ServerHttpResponse response = exchange.getResponse();
        ClientResponse clientResponse = exchange.getAttribute(CLIENT_RESPONSE_ATTR);
        if (clientResponse == null) {
            return Mono.empty();
        }


        return response.writeWith(clientResponse.body(BodyExtractors.toDataBuffers()))
                // .log("webClient response")
                .doOnCancel(() -> cleanup(exchange));

		// exchange.getResponse().setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
		// return exchange.getResponse().setComplete();
    }

    private void cleanup(ServerWebExchange exchange) {
        ClientResponse clientResponse = exchange.getAttribute(CLIENT_RESPONSE_ATTR);
        if (clientResponse != null) {
            clientResponse.bodyToMono(Void.class).subscribe();
        }
    }

}
