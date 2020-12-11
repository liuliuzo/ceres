package com.mastercard.ceres.plugin.httpclient.webClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.WebClient;

import com.mastercard.ceres.core.CeresContext;
import com.mastercard.ceres.plugin.base.EndpointPlugin;
import com.mastercard.ceres.plugin.chain.CeresPluginChain;

import reactor.core.publisher.Mono;

/**
 * @className WebClientEndpointPlugin
 * @description
 * @author liuliu
 * @email liuliu.zhao@mastercard.com
 * @date 2019-03-15 10:45
 **/
public class WebClientEndpointPlugin extends EndpointPlugin  {
    
    private static final Logger log = LoggerFactory.getLogger(WebClientEndpointPlugin.class);

    private final WebClient webClient;

    public WebClientEndpointPlugin(final WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public String pluginName() {
        return "WebClientEndpointPlugin";
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
        log.info("doPlugin {}!", this);
        String baseUrl = "http://127.0.0.1:8080";
        WebClient webClient = WebClient.create(baseUrl);
        WebClient.RequestBodyUriSpec request = webClient.method(HttpMethod.GET);
        return Mono.empty();
    }

}
