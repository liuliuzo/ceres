package com.mastercard.ceres.bootstrap.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.mastercard.ceres.core.CeresContext;
import com.mastercard.ceres.plugin.base.OutBoundPlugin;

import reactor.core.publisher.Mono;

@Component
public class CeresDemoPlugin3 extends OutBoundPlugin {

    private static final Logger log = LoggerFactory.getLogger(CeresDemoPlugin3.class);

    @Override
    public int pluginOrder() {
        return 3;
    }

    @Override
    public String pluginName() {
        return "CeresDemoPlugin3";
    }

    @Override
    public boolean stopPluginProcessing() {
        return false;
    }

    @Override
    public boolean skipPlugin() {
        return true;
    }

    @Override
    public Mono<Void> doPlugin(CeresContext context) {
        log.info("doPlugin !");
        return Mono.empty();
    }

}
