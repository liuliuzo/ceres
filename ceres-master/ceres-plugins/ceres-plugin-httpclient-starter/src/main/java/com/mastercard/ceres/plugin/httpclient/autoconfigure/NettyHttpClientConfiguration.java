package com.mastercard.ceres.plugin.httpclient.autoconfigure;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mastercard.ceres.filter.CeresFilter;
import com.mastercard.ceres.plugin.httpclient.netty.NettyClientOutBoundFilter;
import com.mastercard.ceres.plugin.httpclient.netty.NettyHttpClientEndpointFilter;

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
    public CeresFilter nettyHttpClientEndpointFilter(final ObjectProvider<HttpClient> httpClient) {
        return new NettyHttpClientEndpointFilter(httpClient.getIfAvailable());
    }

    @Bean
    public CeresFilter nettyClientOutBoundFilter() {
        return new NettyClientOutBoundFilter();
    }

}
