package com.liuliu.ceres.plugin.chain;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.liuliu.ceres.core.CeresContext;
import com.liuliu.ceres.plugin.CeresPlugin;
import com.liuliu.ceres.plugin.PluginType;
import com.liuliu.ceres.plugin.loader.CeresPluginLoader;

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

    private List<CeresPlugin> plugins = Lists.newArrayList();
    private Set<CeresPlugin> inboundPlugins;
    private Set<CeresPlugin> endpointPlugins;
    private Set<CeresPlugin> outboundPlugins;

    private int index;

    public DefaultCeresPluginChain(final CeresPluginLoader ceresPluginLoader) {
        this.inboundPlugins = ceresPluginLoader.getPluginsByType(PluginType.INBOUND);
        this.endpointPlugins = ceresPluginLoader.getPluginsByType(PluginType.ENDPOINT);
        this.outboundPlugins = ceresPluginLoader.getPluginsByType(PluginType.OUTBOUND);
        this.plugins.addAll(inboundPlugins);
        this.plugins.addAll(endpointPlugins);
        this.plugins.addAll(outboundPlugins);
        this.index = 0;
    }

    private DefaultCeresPluginChain(DefaultCeresPluginChain parent, int index) {
        this.plugins = parent.getPlugins();
        this.index = index;
    }

    public List<CeresPlugin> getPlugins() {
        return plugins;
    }

    @Override
    public Mono<Void> execute(CeresContext ceresContext) {
        return Mono.defer(() -> {
            if (this.index < plugins.size()) {
                CeresPlugin plugin = plugins.get(this.index);
                DefaultCeresPluginChain chain = new DefaultCeresPluginChain(this, this.index + 1);
                return plugin.doPlugin(ceresContext, chain);
            } else {
                return Mono.empty(); // complete
            }
        });
    }
}