package com.liuliu.ceres.proxy;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestBodySpec;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

@RestController
public class ApiProxyController {
    WebClient webClient = WebClient.create();

    @Value("${backend.service.url.prefix}")
    String backendServiceUrlPrefix;

    @Value("${backend.service.timeout.inmillis}")
    long backendServiceTimeoutInMillis;

    @RequestMapping("/**")
    public Mono<Void> proxyRequest(ServerWebExchange exchange) {
        ServerHttpRequest frontEndReq = exchange.getRequest();
        ServerHttpResponse frontEndResp = exchange.getResponse();
        String path = frontEndReq.getPath().pathWithinApplication().value();
        HttpMethod httpMethod = frontEndReq.getMethod();
        RequestBodySpec reqBodySpec = webClient.method(httpMethod).uri(backendServiceUrlPrefix.concat(path))
                .headers(httpHeaders -> {
                    httpHeaders.addAll(frontEndReq.getHeaders());
                    httpHeaders.remove("HOST");
                });

        RequestHeadersSpec<?> reqHeadersSpec;
        if (requireHttpBody(httpMethod)) {
            reqHeadersSpec = reqBodySpec.body(BodyInserters.fromDataBuffers(frontEndReq.getBody()));
        } else {
            reqHeadersSpec = reqBodySpec;
        }

        return reqHeadersSpec.exchange().timeout(Duration.ofMillis(backendServiceTimeoutInMillis)).onErrorResume(ex -> {
            Map<String, Object> forwardAttrs = new HashMap<>();
            forwardAttrs.put(Constant.BACKEND_EXCEPTION_ATTR_NAME, ex);
            return WebfluxForwardingUtil.forward(Constant.BACKEND_EXCEPTION_PATH, exchange, forwardAttrs)
                    .then(Mono.empty());
        }).flatMap(backendResponse -> {
            frontEndResp.setStatusCode(backendResponse.statusCode());
            frontEndResp.getHeaders().putAll(backendResponse.headers().asHttpHeaders());
            return frontEndResp.writeWith(backendResponse.bodyToFlux(DataBuffer.class));
        });
    }

    private boolean requireHttpBody(HttpMethod method) {
        return HttpMethod.POST == method || HttpMethod.PUT == method || HttpMethod.PATCH == method
                || HttpMethod.TRACE == method;
    }

}
