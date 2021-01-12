package com.liuliu.ceres.bootstrap.test;

import static com.mastercard.ceres.constant.Constants.CLIENT_RESPONSE_ATTR;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;

import com.mastercard.ceres.core.CeresContext;
import com.mastercard.ceres.plugin.base.EndpointPlugin;
import com.mastercard.ceres.plugin.chain.CeresPluginChain;

import reactor.core.publisher.Mono;

//@Component
public class CeresDemoPlugin6 extends EndpointPlugin {

    private static final Logger log = LoggerFactory.getLogger(CeresDemoPlugin6.class);

    @Override
    public int pluginOrder() {
        return 2;
    }

    @Override
    public String pluginName() {
        return "CeresDemoPlugin6";
    }

    @Override
    public boolean skipPlugin() {
        return false;
    }

    @Override
    public Mono<Void> doPlugin(CeresContext context, CeresPluginChain chain) {
        log.info("doPlugin !");
        ServerWebExchange exchange = (ServerWebExchange) context.getCeresRequst();
        // retrieve data from domain service
        WebClient webClient = WebClient.builder().baseUrl("http://127.0.0.1:8083").build();
        webClient.method(HttpMethod.POST)
            .uri("/demo05/mono")
            .accept(MediaType.APPLICATION_JSON)
            .exchange().flatMap(res -> {
                    ServerHttpResponse response = exchange.getResponse();
                    response.getHeaders().putAll(res.headers().asHttpHeaders());
                    response.setStatusCode(res.statusCode());
                    exchange.getAttributes().put(CLIENT_RESPONSE_ATTR, res);
                    return chain.execute(context);
        }).block();
        return chain.execute(context);
    }
}
