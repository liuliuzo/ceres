package com.mastercard.ceres.plugin.httpclient.autoconfigure;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mastercard.ceres.plugin.CeresPlugin;
import com.mastercard.ceres.plugin.httpclient.netty.NettyClientOutBoundPlugin;
import com.mastercard.ceres.plugin.httpclient.netty.NettyHttpClientEndpointPlugin;

import reactor.netty.http.client.HttpClient;

/**
 * @className NettyHttpClientConfiguration
 * @description
 * @author liuliu
 * @email liuliu.zhao@mastercard.com
 * @date 2019-03-15 10:45
 **/
@Configuration
@ConditionalOnProperty(name = "ceres.httpclient.strategy", havingValue = "netty")
public class NettyHttpClientConfiguration {

    @Bean
    public CeresPlugin nettyHttpClientEndpointPlugin(final ObjectProvider<HttpClient> httpClient) {
        return new NettyHttpClientEndpointPlugin(httpClient.getIfAvailable());
    }

    @Bean
    public CeresPlugin nettyClientOutBoundPlugin() {
        return new NettyClientOutBoundPlugin();
    }

}
