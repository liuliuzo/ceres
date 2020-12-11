package com.mastercard.ceres.plugin.loader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.google.common.collect.Maps;
import com.mastercard.ceres.plugin.CeresPlugin;
import com.mastercard.ceres.plugin.PluginType;

/**
 * @className StaticPluginLoader
 * @description
 * @author liuliu
 * @email liuliu.zhao@mastercard.com
 * @date 2019-03-15 10:45
 **/
public class StaticPluginLoader implements CeresPluginLoader {

    private static final Logger log = LoggerFactory.getLogger(StaticPluginLoader.class);

    /**
     * Maps plugins <PluginType,Plugin>
     */
    private final ConcurrentMap<PluginType, SortedSet<CeresPlugin>> pluginMap = Maps.newConcurrentMap();
    private final ConcurrentMap<PluginType, Map<String, CeresPlugin>> pluginsByTypeAndName = Maps.newConcurrentMap();
    
    public StaticPluginLoader(List<CeresPlugin> plugins) {
        Assert.notNull(plugins, "plugins can not null !");
        initpluginMap(plugins);
    }

    private void initpluginMap(List<CeresPlugin> plugins) {
        plugins.forEach(plugin -> {
            Assert.notNull(plugin.pluginType(), "pluginType can not null !");
            Assert.notNull(plugin.pluginName(), "pluginName can not null !");
            log.info("loader plugin type:{},name:{},order:{}",plugin.pluginType(),plugin.pluginName(),plugin.pluginOrder());
            pluginMap.computeIfAbsent(plugin.pluginType(), k -> new TreeSet<>(FILTER_COMPARATOR)).add(plugin);
            pluginsByTypeAndName.computeIfAbsent(plugin.pluginType(), k -> new HashMap<>()).put(plugin.pluginName(), plugin);
        });
        Set<CeresPlugin> inboundPlugins = this.getPluginsByType(PluginType.INBOUND);
        Set<CeresPlugin> endpointPlugins = this.getPluginsByType(PluginType.ENDPOINT);
        Set<CeresPlugin> outboundPlugins = this.getPluginsByType(PluginType.OUTBOUND);
        Set<CeresPlugin> errorPlugins = this.getPluginsByType(PluginType.ERROR);
        log.info("INBOUND plugins:{}", inboundPlugins);
        log.info("ENDPOINT plugins:{}", endpointPlugins);
        log.info("OUTBOUND plugins:{}", outboundPlugins);
        log.info("ERROR plugins:{}", errorPlugins);
    }

    @Override
    public SortedSet<CeresPlugin> getPluginsByType(PluginType pluginType) {
        Assert.notNull(pluginType, "pluginType can not null !");
        return pluginMap.get(pluginType);
    }

    @Override
    public CeresPlugin getPluginByNameAndType(String pluginName,PluginType pluginType) {
        Assert.notNull(pluginName, "pluginName can not null !");
        Assert.notNull(pluginType, "pluginType can not null !");
        Map<String, CeresPlugin> pluginsByName = pluginsByTypeAndName.get(pluginType);
        if (pluginsByName == null) {
            log.warn("pluginsByName is null");
            return null;
        }
        return pluginsByName.get(pluginName);
    }
}
