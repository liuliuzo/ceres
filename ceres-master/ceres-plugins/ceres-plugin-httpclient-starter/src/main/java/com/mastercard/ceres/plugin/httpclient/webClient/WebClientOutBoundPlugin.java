package com.mastercard.ceres.plugin.httpclient.webClient;

import static com.mastercard.ceres.constant.Constants.CLIENT_RESPONSE_ATTR;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.server.ServerWebExchange;

import com.mastercard.ceres.core.CeresContext;
import com.mastercard.ceres.plugin.base.OutBoundPlugin;
import com.mastercard.ceres.plugin.chain.CeresPluginChain;
import com.mastercard.ceres.utils.WebFluxResultUtils;

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
        ServerHttpResponse response = exchange.getResponse();
        ClientResponse clientResponse = exchange.getAttribute(CLIENT_RESPONSE_ATTR);
        if (Objects.isNull(clientResponse) || response.getStatusCode() == HttpStatus.BAD_GATEWAY
                || response.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR) {
            return WebFluxResultUtils.result(exchange, "error");
        } else if (response.getStatusCode() == HttpStatus.GATEWAY_TIMEOUT) {
            return WebFluxResultUtils.result(exchange, "timeout");
        }
        response.setStatusCode(clientResponse.statusCode());
        response.getCookies().putAll(clientResponse.cookies());
        response.getHeaders().putAll(clientResponse.headers().asHttpHeaders());
        return response.writeWith(clientResponse.body(BodyExtractors.toDataBuffers()));
    }
}
