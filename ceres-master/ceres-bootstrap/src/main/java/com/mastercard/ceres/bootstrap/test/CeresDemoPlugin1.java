package com.mastercard.ceres.bootstrap.test;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.codec.HttpMessageReader;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.server.HandlerStrategies;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ServerWebExchange;

import com.mastercard.ceres.core.CeresBody;
import com.mastercard.ceres.core.CeresContext;
import com.mastercard.ceres.plugin.base.InBoundPlugin;
import com.mastercard.ceres.plugin.chain.CeresPluginChain;

import reactor.core.publisher.Mono;


/**
 * @className CeresDemoPlugin1
 * @description
 * @author liuliu
 * @version 1.0
 * @email liuliu.zhao@mastercard.com
 */
@Component
public class CeresDemoPlugin1 extends InBoundPlugin {

    //temp here
    private static final String TEMP = "TEMP";
    private static final String DUBBO_PARAMS = "dubbo_params";
    

    private static final Logger log = LoggerFactory.getLogger(CeresDemoPlugin1.class);
    
    private final List<HttpMessageReader<?>> messageReaders;
    
    public CeresDemoPlugin1() {
        this.messageReaders = HandlerStrategies.withDefaults().messageReaders();
    }

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
        final ServerHttpRequest request = exchange.getRequest();
        final MultiValueMap<String, String> queryParams = request.getQueryParams();
        CeresBody ceresBody = new CeresBody();
        ceresBody.setAppKey(queryParams.getFirst(TEMP));
        log.info("ceresBody:{} !", ceresBody.toString());
        if (Objects.nonNull(ceresBody)) {
            MediaType mediaType = request.getHeaders().getContentType();
            ServerRequest serverRequest = ServerRequest.create(exchange, messageReaders);
            if (MediaType.APPLICATION_JSON.isCompatibleWith(mediaType)) {
                return body(context, serverRequest, chain);
            } else {
                return Mono.empty();
            }
        }
        return chain.execute(context);
    }

    private Mono<Void> body(CeresContext context, final ServerRequest serverRequest, final CeresPluginChain chain) {
        ServerWebExchange exchange =(ServerWebExchange) context.getCeresRequst();
        return serverRequest.bodyToMono(String.class).switchIfEmpty(Mono.defer(() -> Mono.just(""))).flatMap(body -> {
            exchange.getAttributes().put(DUBBO_PARAMS, body);
            return chain.execute(context);
        });
    }
}
