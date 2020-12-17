package com.mastercard.ceres.plugin.httpclient.webClient;

import java.time.Duration;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
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
        ServerWebExchange exchange = (ServerWebExchange) context.getCeresRequst();
        long timeout = (long) Optional.ofNullable(exchange.getAttribute(Constants.HTTP_TIME_OUT)).orElse(3000L);
        HttpMethod method = HttpMethod.valueOf(exchange.getRequest().getMethodValue());
        WebClient webClient = WebClientUtil.getWebClient("http://www.jianshu.com/", httpClient);
        WebClient.RequestBodySpec requestBodySpec = webClient.method(method).uri("u/fb39c14e77d9");
        return handleRequestBody(requestBodySpec, exchange, timeout, chain, context);
    }

    private MediaType buildMediaType(final ServerWebExchange exchange) {
        return MediaType.valueOf(Optional.ofNullable(exchange
                .getRequest()
                .getHeaders().getFirst(HttpHeaders.CONTENT_TYPE))
                .orElse(MediaType.APPLICATION_JSON_VALUE));
    }

    private Mono<Void> handleRequestBody(WebClient.RequestBodySpec requestBodySpec, ServerWebExchange exchange,
            long timeout, CeresPluginChain chain, CeresContext context) {
                return requestBodySpec.headers(httpHeaders -> {
                    httpHeaders.addAll(exchange.getRequest().getHeaders());
                    httpHeaders.remove(HttpHeaders.HOST);
                })
                .contentType(buildMediaType(exchange))
                .body(BodyInserters.fromDataBuffers(exchange.getRequest().getBody()))
                .exchange()
                .doOnError(e -> log.error(e.getMessage()))
                .timeout(Duration.ofMillis(timeout))
                .flatMap(e -> doNext(e, exchange, chain, context));

    }

    private Mono<Void> doNext(final ClientResponse res, final ServerWebExchange exchange, final CeresPluginChain chain,
            CeresContext context) {
        if (res.statusCode().is2xxSuccessful()) {
            exchange.getAttributes().put(Constants.CLIENT_RESPONSE_RESULT_TYPE, "SUCCESS");
        } else {
            exchange.getAttributes().put(Constants.CLIENT_RESPONSE_RESULT_TYPE, "ERROR");
        }
        exchange.getAttributes().put(Constants.CLIENT_RESPONSE_ATTR, res);
        return chain.execute(context);
    }

}
