package com.liuliu.ceres.core;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebHandler;

import com.liuliu.ceres.autoconfigure.CeresProperties;
import com.liuliu.ceres.plugin.chain.DefaultCeresPluginChain;
import com.liuliu.ceres.plugin.loader.CeresPluginLoader;

import lombok.Getter;
import lombok.Setter;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

/**
 * @className CeresHandler
 * @description
 * @author liuliu
 * @email liuliu.zhao@mastercard.com
 * @date 2019-03-15 10:45
 **/
public class CeresPluginWebHandler implements WebHandler {

    private static final Logger log = LoggerFactory.getLogger(CeresPluginWebHandler.class);

    @Getter
    @Setter
    private CeresPluginLoader ceresPluginLoader;

    private Scheduler scheduler;

    public CeresPluginWebHandler(CeresPluginLoader ceresPluginLoader,CeresProperties ceresProperties) {
            String schedulerType = ceresProperties.getSchedulerType();
            if (Objects.equals(schedulerType, "fixed")) {
                int threads = Integer.parseInt(System.getProperty(ceresProperties.getWorkThreads(), "" + Math.max((Runtime.getRuntime().availableProcessors() << 1) + 1, 16)));
                scheduler = Schedulers.newParallel("ceres-work-threads", threads);
            } else {
                scheduler = Schedulers.elastic();
            }
        this.ceresPluginLoader = ceresPluginLoader;
    }

    @Override
    public Mono<Void> handle(ServerWebExchange exchange) {
        CeresContext ceresContext = new CeresContext();
        ceresContext.setCeresRequst(exchange);
        return new DefaultCeresPluginChain(ceresPluginLoader).execute(ceresContext).subscribeOn(scheduler);
    }
}
