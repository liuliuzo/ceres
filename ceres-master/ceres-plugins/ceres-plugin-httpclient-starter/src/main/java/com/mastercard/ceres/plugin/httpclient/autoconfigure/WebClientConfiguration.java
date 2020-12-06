package com.mastercard.ceres.plugin.httpclient.autoconfigure;

import java.util.Objects;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import com.mastercard.ceres.filter.CeresFilter;
import com.mastercard.ceres.plugin.httpclient.webClient.WebClientEndpointFilter;
import com.mastercard.ceres.plugin.httpclient.webClient.WebClientOutBoundFilter;

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
    public CeresFilter webClientEndpointFilter(final ObjectProvider<HttpClient> httpClient) {
        WebClient webClient = WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(Objects.requireNonNull(httpClient.getIfAvailable())))
                .build();
        return new WebClientEndpointFilter(webClient);
    }

    @Bean
    public CeresFilter webClientOutBoundFilter() {
        return new WebClientOutBoundFilter();
    }
}
