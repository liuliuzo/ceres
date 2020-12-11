package com.mastercard.ceres.plugin.httpclient.netty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mastercard.ceres.core.CeresContext;
import com.mastercard.ceres.plugin.base.OutBoundPlugin;
import com.mastercard.ceres.plugin.chain.CeresPluginChain;

import reactor.core.publisher.Mono;


/**
 * @className NettyClientOutBoundPlugin
 * @description
 * @author liuliu
 * @email liuliu.zhao@mastercard.com
 * @date 2019-03-15 10:45
 **/
public class NettyClientOutBoundPlugin extends OutBoundPlugin {
    private static final Logger log = LoggerFactory.getLogger(NettyClientOutBoundPlugin.class);

    @Override
    public String pluginName() {
        return "NettyClientOutBoundPlugin";
    }

    @Override
    public int pluginOrder() {
        return 4;
    }

    @Override
    public boolean skipPlugin() {
        return false;
    }

    @Override
    public Mono<Void> doPlugin(CeresContext context,CeresPluginChain chain) {
        log.info("doPlugin {} !",this);
        return Mono.empty();
    }

    @Override
    public boolean stopPluginProcessing() {
        return false;
    }
}
