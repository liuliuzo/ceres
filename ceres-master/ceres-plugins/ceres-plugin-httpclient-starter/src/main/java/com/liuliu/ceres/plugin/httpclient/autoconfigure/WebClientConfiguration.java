package com.liuliu.ceres.plugin.httpclient.autoconfigure;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.liuliu.ceres.plugin.httpclient.webClient.WebClientEndpointPlugin;
import com.liuliu.ceres.plugin.httpclient.webClient.WebClientOutBoundPlugin;
import com.liuliu.ceres.plugin.CeresPlugin;
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
        return new WebClientEndpointPlugin(httpClient);
    }

    @Bean
    public CeresPlugin webClientOutBoundPlugin() {
        return new WebClientOutBoundPlugin();
    }
}
