package com.mastercard.ceres.plugin.httpclient.webClient;

import java.time.Duration;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestBodySpec;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;
import org.springframework.web.server.ServerWebExchange;

import com.mastercard.ceres.constant.Constants;
import com.mastercard.ceres.core.CeresContext;
import com.mastercard.ceres.plugin.base.EndpointPlugin;
import com.mastercard.ceres.plugin.chain.CeresPluginChain;
import com.mastercard.ceres.plugin.httpclient.webClient.utils.WebClientUtil;

import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

/**
 * @className WebClientEndpointPlugin
 * @description
 * @author liuliu
 * @email liuliu.zhao@mastercard.com
 * @date 2019-03-15 10:45
 **/
public class WebClientEndpointPlugin extends EndpointPlugin {

    private static final Logger log = LoggerFactory.getLogger(WebClientEndpointPlugin.class);

    private ObjectProvider<HttpClient> httpClient;

    public WebClientEndpointPlugin(final ObjectProvider<HttpClient> httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public String pluginName() {
        return "WebClientEndpointPlugin";
    }

    @Override
    public int pluginOrder() {
        return 2;
    }

    @Override
    public boolean skipPlugin() {
        return false;
    }

    @Override
    public Mono<Void> doPlugin(CeresContext context, CeresPluginChain chain) {
        log.info("doPlugin {}!", this);
        ServerWebExchange exchange = context.getCeresRequst();
        ServerHttpRequest request = exchange.getRequest();
        HttpMethod method = HttpMethod.valueOf(exchange.getRequest().getMethodValue());
        WebClient webClient = WebClientUtil.getWebClient("http://127.0.0.1:8080/", httpClient);
        RequestBodySpec bodySpec = webClient.method(method).uri("hello/mono").headers(httpHeaders -> {
            httpHeaders.addAll(exchange.getRequest().getHeaders());
            httpHeaders.remove(HttpHeaders.HOST);
        });;

        RequestHeadersSpec<?> headersSpec;
        if (requiresBody(method)) {
            headersSpec = bodySpec.body(BodyInserters.fromDataBuffers(request.getBody()));
        }
        else {
            headersSpec = bodySpec;
        }
 
        long timeout = (long) Optional.ofNullable(exchange.getAttribute(Constants.HTTP_TIME_OUT)).orElse(5000L);
        return handleRequestBody(bodySpec, exchange, timeout, chain, context);
    }

    private MediaType buildMediaType(final ServerWebExchange exchange) {
        return MediaType
                .valueOf(Optional.ofNullable(exchange.getRequest().getHeaders().getFirst(HttpHeaders.CONTENT_TYPE))
                        .orElse(MediaType.APPLICATION_JSON_VALUE));
    }

    private Mono<Void> handleRequestBody(RequestBodySpec bodySpec, ServerWebExchange exchange, long timeout,
            CeresPluginChain chain, CeresContext context) {
        return bodySpec
                .contentType(buildMediaType(exchange))
                .exchange()
                .doOnError(e -> log.error(e.getMessage()))
                .timeout(Duration.ofMillis(timeout))
                .flatMap(res -> doNext(res, exchange, chain, context));
    }

    private Mono<Void> doNext(ClientResponse clientResponse,ServerWebExchange exchange,CeresPluginChain chain,CeresContext context) {
        ServerHttpResponse response = exchange.getResponse();
        response.getHeaders().putAll(clientResponse.headers().asHttpHeaders());
        response.setStatusCode(clientResponse.statusCode());
        exchange.getAttributes().put(Constants.CLIENT_RESPONSE_ATTR, clientResponse);
        return chain.execute(context);
    }

    private boolean requiresBody(HttpMethod method) {
        switch (method) {
        case PUT:
        case POST:
        case PATCH:
            return true;
        default:
            return false;
        }
    }
}
