package com.liuliu.ceres.bootstrap.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;

import com.mastercard.ceres.core.CeresContext;
import com.mastercard.ceres.plugin.base.EndpointPlugin;
import com.mastercard.ceres.plugin.chain.CeresPluginChain;
import com.mastercard.ceres.utils.WebFluxResultUtils;

import reactor.core.publisher.Mono;

import static com.mastercard.ceres.constant.Constants.CLIENT_RESPONSE_ATTR;

//@Component
public class CeresDemoPlugin2 extends EndpointPlugin {

    private static final Logger log = LoggerFactory.getLogger(CeresDemoPlugin2.class);

    @Override
    public int pluginOrder() {
        return 2;
    }

    @Override
    public String pluginName() {
        return "CeresDemoPlugin2";
    }

    @Override
    public boolean skipPlugin() {
        return false;
    }

    @Override
    public Mono<Void> doPlugin(CeresContext context,CeresPluginChain chain) {
        log.info("doPlugin !");
        ServerWebExchange exchange =(ServerWebExchange) context.getCeresRequst();
        // retrieve data from domain service
        WebClient webClient= WebClient.create("http://centos.org");

        webClient
                .post()
                .uri("/")
                .exchange().flatMap(res ->
                {   ServerHttpResponse response = exchange.getResponse();
                    response.getHeaders().putAll(res.headers().asHttpHeaders());
                    response.setStatusCode(res.statusCode());
                    exchange.getAttributes().put(CLIENT_RESPONSE_ATTR, res);
                    return chain.execute(context);
                }).block();

        return chain.execute(context);
    }
}
