package com.mastercard.ceres.bootstrap.test;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.codec.HttpMessageReader;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.HandlerStrategies;

import com.mastercard.ceres.core.CeresContext;
import com.mastercard.ceres.plugin.base.InBoundPlugin;
import com.mastercard.ceres.plugin.chain.CeresPluginChain;

import reactor.core.publisher.Mono;

@Component
public class CeresDemoPlugin0 extends InBoundPlugin {

    private static final Logger log = LoggerFactory.getLogger(CeresDemoPlugin0.class);

    private final List<HttpMessageReader<?>> messageReaders;
    
    public CeresDemoPlugin0() {
        this.messageReaders = HandlerStrategies.withDefaults().messageReaders();
    }
    
    @Override
    public int pluginOrder() {
        return 0;
    }

    @Override
    public String pluginName() {
        return "CeresDemoPlugin1";
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
