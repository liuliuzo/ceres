package com.mastercard.ceres.plugin.chain;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mastercard.ceres.core.CeresContext;
import com.mastercard.ceres.plugin.CeresPlugin;
import com.mastercard.ceres.plugin.PluginType;
import com.mastercard.ceres.plugin.loader.CeresPluginLoader;

import reactor.core.publisher.Mono;

/**
 * @className DefaultCeresPluginChain
 * @description
 * @author liuliu
 * @email liuliu.zhao@mastercard.com
 * @date 2019-03-15 10:45
 **/
public class DefaultCeresPluginChain implements CeresPluginChain {

    private static final Logger log = LoggerFactory.getLogger(DefaultCeresPluginChain.class);

    private Set<CeresPlugin> inboundPlugins;
    private Set<CeresPlugin> endpointPlugins;
    private Set<CeresPlugin> outboundPlugins;
    private Set<CeresPlugin> errorPlugins;

    public DefaultCeresPluginChain(final CeresPluginLoader ceresPluginLoader) {
        this.inboundPlugins = ceresPluginLoader.getPluginsByType(PluginType.INBOUND);
        this.endpointPlugins = ceresPluginLoader.getPluginsByType(PluginType.ENDPOINT);
        this.outboundPlugins = ceresPluginLoader.getPluginsByType(PluginType.OUTBOUND);
        this.errorPlugins = ceresPluginLoader.getPluginsByType(PluginType.ERROR);
    }

    @Override
    public Mono<Void> execute(CeresContext ceresContext) {
        return Mono.defer(() -> {
            try {
                processPlugins(inboundPlugins, ceresContext);
                processPlugins(endpointPlugins, ceresContext);
                processPlugins(outboundPlugins, ceresContext);
                return Mono.empty();
            } catch (Exception e) {
                log.error("error occurred",e);
                processPlugins(errorPlugins, ceresContext);
                return Mono.empty();
            }
        });
    }

    private void processPlugins(Set<CeresPlugin> ceresPlugins, CeresContext ceresContext) {
        for (CeresPlugin ceresPlugin : ceresPlugins) {
            if (!ceresPlugin.skipPlugin()) {
                log.info("process pluginType:{},pluginName:{}", ceresPlugin.pluginType(), ceresPlugin.pluginName());
                ceresPlugin.doPlugin(ceresContext);
            } else {
                log.info("skiped not process pluginName:{}!", ceresPlugin.pluginName());
            }
        }
    }
}