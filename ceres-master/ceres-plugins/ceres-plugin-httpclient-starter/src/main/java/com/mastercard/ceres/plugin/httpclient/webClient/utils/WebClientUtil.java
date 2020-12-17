package com.mastercard.ceres.plugin.httpclient.webClient.utils;

import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;

import com.google.common.collect.Maps;

import reactor.netty.http.client.HttpClient;

/**
 * @className WebClientUtil
 * @description
 * @author liuliu
 * @email liuliu.zhao@mastercard.com
 * @date 2019-03-15 10:45
 **/
public class WebClientUtil {

    private static Map<String, WebClient> webClients = Maps.newHashMap();

    public static Map<String, WebClient> getWebClients() {
        return webClients;
    }

    public static void setWebClients(Map<String, WebClient> webClients) {
        WebClientUtil.webClients = webClients;
    }

    public static WebClient getWebClient(String baseUrl,ObjectProvider<HttpClient> httpClient) {
        WebClient webClient = WebClient.builder().baseUrl(baseUrl)
                .clientConnector(new ReactorClientHttpConnector(Objects.requireNonNull(httpClient.getIfAvailable())))
                .build();
        return webClients.computeIfAbsent(baseUrl, k -> webClient);
    }
}
