package com.liuliu.ceres.plugin.httpclient.webClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.server.ServerWebExchange;

import com.liuliu.ceres.constant.Constants;
import com.liuliu.ceres.core.CeresContext;
import com.liuliu.ceres.plugin.base.OutBoundPlugin;
import com.liuliu.ceres.plugin.chain.CeresPluginChain;

import reactor.core.publisher.Mono;

/**
 * @className WebClientOutBoundPlugin
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
        return 3;
    }

    @Override
    public boolean skipPlugin() {
        return false;
    }

    @Override
    public Mono<Void> doPlugin(CeresContext context, CeresPluginChain chain) {
        log.info("doPlugin !");
        ServerWebExchange exchange = context.getCeresRequst();
        return chain.execute(context)
                .doOnError(throwable -> cleanup(exchange))
                .then(Mono.defer(() -> {
                    ClientResponse clientResponse = exchange.getAttribute(Constants.CLIENT_RESPONSE_ATTR);
                    if (clientResponse == null) {
                        return Mono.empty();
                    }
                    log.trace("WebClientOutBoundPlugin start");
                    ServerHttpResponse response = exchange.getResponse();

                    return response.writeWith(clientResponse.body(BodyExtractors.toDataBuffers()))
                            .doOnCancel(() -> cleanup(exchange));
                }));
    }

    private void cleanup(ServerWebExchange exchange) {
        ClientResponse clientResponse = exchange.getAttribute(Constants.CLIENT_RESPONSE_ATTR);
        if (clientResponse != null) {
            clientResponse.bodyToMono(Void.class).subscribe();
        }
    }
}
