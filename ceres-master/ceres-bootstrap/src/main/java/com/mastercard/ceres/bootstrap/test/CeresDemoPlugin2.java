package com.mastercard.ceres.bootstrap.test;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.mastercard.ceres.core.CeresContext;
import com.mastercard.ceres.plugin.base.EndpointPlugin;
import com.mastercard.ceres.plugin.chain.CeresPluginChain;
import com.mastercard.ceres.utils.JsonUtils;

import reactor.core.publisher.Mono;

/**
 * @className CeresDemoPlugin2
 * @description
 * @author liuliu
 * @version 1.0
 * @email liuliu.zhao@mastercard.com
 */
@Component
public class CeresDemoPlugin2 extends EndpointPlugin {

    private static final Logger log = LoggerFactory.getLogger(CeresDemoPlugin2.class);

    @Override
    public int pluginOrder() {
        return 2;
    }

    @Override
    public String pluginName() {
        return "CeresDemoPlugin2";
    }

    @Override
    public boolean skipPlugin() {
        return false;
    }

    @Override
    public Mono<Void> doPlugin(CeresContext context,CeresPluginChain chain) {
        log.info("doPlugin !");


        return chain.execute(context);
    }
}
