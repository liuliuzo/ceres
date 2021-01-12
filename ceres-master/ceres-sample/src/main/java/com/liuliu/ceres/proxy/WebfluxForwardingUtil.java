package com.liuliu.ceres.proxy;

import java.util.Map;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

public class WebfluxForwardingUtil {
    public static Mono<Void> forward(String forwardToPath, ServerWebExchange exchange,Map<String, Object> forwardAttrs) {
        WebFilterChain webFilterChain = (WebFilterChain) exchange.getAttributes().get(Constant.WEB_FILTER_ATTR_NAME);
        ServerHttpRequest forwardReq = exchange.getRequest().mutate().path(forwardToPath).build();
        ServerWebExchange forwardExchange = exchange.mutate().request(forwardReq).build();
        if(null != forwardAttrs && !forwardAttrs.isEmpty()) {
            forwardExchange.getAttributes().putAll(forwardAttrs);
        }
        return webFilterChain.filter(forwardExchange);
    }
}
