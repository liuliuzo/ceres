package com.mastercard.ceres.plugin.httpclient.webClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.function.client.WebClient;

import com.mastercard.ceres.core.CeresContext;
import com.mastercard.ceres.filter.base.EndpointFilter;

/**
 * @className WebClientEndpointFilter
 * @description
 * @author liuliu
 * @email liuliu.zhao@mastercard.com
 * @date 2019-03-15 10:45
 **/
public class WebClientEndpointFilter extends EndpointFilter  {
    
    private static final Logger log = LoggerFactory.getLogger(WebClientEndpointFilter.class);

    private final WebClient webClient;

    public WebClientEndpointFilter(final WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public String filterName() {
        return "WebClientEndpointFilter";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean skipFilter() {
        return false;
    }

    @Override
    public void doFilter(CeresContext context) {

    }

    @Override
    public boolean stopFilterProcessing() {
        return false;
    }
}
