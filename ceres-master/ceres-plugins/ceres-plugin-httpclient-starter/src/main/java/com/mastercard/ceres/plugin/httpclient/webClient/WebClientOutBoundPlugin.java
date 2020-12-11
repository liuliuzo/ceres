package com.mastercard.ceres.plugin.httpclient.webClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ServerWebExchange;

import com.mastercard.ceres.core.CeresContext;
import com.mastercard.ceres.plugin.base.OutBoundPlugin;
import com.mastercard.ceres.plugin.chain.CeresPluginChain;

import reactor.core.publisher.Mono;

import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @className NettyHttpClientPlugin
 * @description
 * @author liuliu
 * @email liuliu.zhao@mastercard.com
 * @date 2019-03-15 10:45
 **/
public class WebClientOutBoundPlugin extends OutBoundPlugin {

    private static final Logger log = LoggerFactory.getLogger(WebClientOutBoundPlugin.class);

    @Override
    public String pluginName() {
        return "WebClientOutBoundPlugin";
    }

    @Override
    public int pluginOrder() {
        return 0;
    }

    @Override
    public boolean skipPlugin() {
        return false;
    }

    @Override
    public Mono<Void> doPlugin(CeresContext context,CeresPluginChain chain) {
        ServerWebExchange exchange =(ServerWebExchange) context.getCeresRequst();
        log.info("doPlugin {}!", this);
        return Mono.empty();
    }
}
