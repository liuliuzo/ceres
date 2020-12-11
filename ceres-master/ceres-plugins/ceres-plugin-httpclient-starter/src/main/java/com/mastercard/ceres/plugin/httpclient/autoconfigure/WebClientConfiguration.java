package com.mastercard.ceres.plugin.httpclient.autoconfigure;

import java.util.Objects;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import com.mastercard.ceres.plugin.CeresPlugin;
import com.mastercard.ceres.plugin.httpclient.webClient.WebClientEndpointPlugin;
import com.mastercard.ceres.plugin.httpclient.webClient.WebClientOutBoundPlugin;

import reactor.netty.http.client.HttpClient;


/**
 * @className WebClientConfiguration
 * @description
 * @author liuliu
 * @email liuliu.zhao@mastercard.com
 * @date 2019-03-15 10:45
 **/
@Configuration
@ConditionalOnProperty(name = "ceres.httpclient.strategy", havingValue = "webClient", matchIfMissing = true)
public class WebClientConfiguration {

    @Bean
    public CeresPlugin webClientEndpointPlugin(final ObjectProvider<HttpClient> httpClient) {
        WebClient webClient = WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(Objects.requireNonNull(httpClient.getIfAvailable())))
                .build();
        return new WebClientEndpointPlugin(webClient);
    }

    @Bean
    public CeresPlugin webClientOutBoundPlugin() {
        return new WebClientOutBoundPlugin();
    }
}
