package com.mastercard.ceres.bootstrap.test;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestBodySpec;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilterChain;

import com.mastercard.ceres.bootstrap.utils.Constant;
import com.mastercard.ceres.core.CeresContext;
import com.mastercard.ceres.plugin.base.EndpointPlugin;
import com.mastercard.ceres.plugin.chain.CeresPluginChain;

import reactor.core.publisher.Mono;

//@Component
public class CeresDemoPlugin5 extends EndpointPlugin {

    private static final Logger log = LoggerFactory.getLogger(CeresDemoPlugin5.class);

    @Override
    public int pluginOrder() {
        return 2;
    }

    @Override
    public String pluginName() {
        return "CeresDemoPlugin5";
    }

    @Override
    public boolean skipPlugin() {
        return false;
    }

    @Override
    public Mono<Void> doPlugin(CeresContext context,CeresPluginChain chain) {
        log.info("doPlugin !");
        WebClient webClient = WebClient.create();
        ServerWebExchange exchange =(ServerWebExchange) context.getCeresRequst();
        ServerHttpRequest frontEndReq = exchange.getRequest();
        ServerHttpResponse frontEndResp = exchange.getResponse();
        String path = frontEndReq.getPath().pathWithinApplication().value();
        HttpMethod httpMethod = frontEndReq.getMethod();
        RequestBodySpec reqBodySpec = webClient.method(httpMethod).uri("localhost:8080");
        RequestHeadersSpec<?> reqHeadersSpec;
        reqHeadersSpec = reqBodySpec.body(BodyInserters.fromDataBuffers(frontEndReq.getBody()));
        return reqHeadersSpec.exchange().timeout(Duration.ofMillis(1000)).onErrorResume(ex -> {
            Map<String, Object> forwardAttrs = new HashMap<>();
            forwardAttrs.put(Constant.BACKEND_EXCEPTION_ATTR_NAME, ex);
            return this.forward(Constant.BACKEND_EXCEPTION_PATH, exchange, forwardAttrs).then(Mono.empty());
        }).flatMap(backendResponse -> {
            frontEndResp.setStatusCode(backendResponse.statusCode());
            frontEndResp.getHeaders().putAll(backendResponse.headers().asHttpHeaders());
            return frontEndResp.writeWith(backendResponse.bodyToFlux(DataBuffer.class));
        });
    }

    private Mono<Void> forward(String forwardToPath, ServerWebExchange exchange,Map<String, Object> forwardAttrs) {
        WebFilterChain webFilterChain = (WebFilterChain) exchange.getAttributes().get(Constant.WEB_FILTER_ATTR_NAME);
        ServerHttpRequest forwardReq = exchange.getRequest().mutate().path(forwardToPath).build();
        ServerWebExchange forwardExchange = exchange.mutate().request(forwardReq).build();
        if(null != forwardAttrs && !forwardAttrs.isEmpty()) {
            forwardExchange.getAttributes().putAll(forwardAttrs);
        }
        return webFilterChain.filter(forwardExchange);
    }
}
