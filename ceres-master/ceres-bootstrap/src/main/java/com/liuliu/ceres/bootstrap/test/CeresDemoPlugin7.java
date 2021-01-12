package com.liuliu.ceres.bootstrap.test;

import static com.liuliu.ceres.constant.Constants.CLIENT_RESPONSE_ATTR;

import java.util.Objects;

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
import com.liuliu.ceres.utils.WebFluxResultUtils;

import reactor.core.publisher.Mono;

//@Component
public class CeresDemoPlugin7 extends OutBoundPlugin {

    private static final Logger log = LoggerFactory.getLogger(CeresDemoPlugin7.class);

    @Override
    public int pluginOrder() {
        return 3;
    }

    @Override
    public String pluginName() {
        return "CeresDemoPlugin7";
    }

    @Override
    public boolean skipPlugin() {
        return true;
    }

    @Override
    public Mono<Void> doPlugin(CeresContext context, CeresPluginChain chain) {
        log.info("doPlugin !");
        ServerWebExchange exchange = (ServerWebExchange) context.getCeresRequst();
        ServerHttpResponse response = exchange.getResponse();
        ClientResponse clientResponse = exchange.getAttribute(CLIENT_RESPONSE_ATTR);
        if (Objects.isNull(clientResponse) 
                  || response.getStatusCode() == HttpStatus.BAD_GATEWAY
                  || response.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR) {
            return WebFluxResultUtils.result(exchange, "error");
        } else if (response.getStatusCode() == HttpStatus.GATEWAY_TIMEOUT) {
            return WebFluxResultUtils.result(exchange, "timeout");
        }
        response.setStatusCode(clientResponse.statusCode());
//        response.getCookies().putAll(clientResponse.cookies());
//        response.getHeaders().putAll(clientResponse.headers().asHttpHeaders());
        return response.writeWith(clientResponse.body(BodyExtractors.toDataBuffers()));
    }
}