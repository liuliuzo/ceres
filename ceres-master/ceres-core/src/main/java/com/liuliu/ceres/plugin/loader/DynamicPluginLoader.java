package com.liuliu.ceres.plugin.loader;

import java.util.List;
import java.util.SortedSet;

import com.liuliu.ceres.plugin.CeresPlugin;
import com.liuliu.ceres.plugin.PluginType;

/**
 * @className DynamicPluginLoader
 * @description
 * @author liuliu
 * @version 1.0
 * @email liuliu.zhao@mastercard.com
 */
public class DynamicPluginLoader implements CeresPluginLoader {

    public DynamicPluginLoader(List<CeresPlugin> pluginsList) {
    }

    @Override
    public SortedSet<CeresPlugin> getPluginsByType(PluginType pluginType) {
        return null;
    }

    @Override
    public CeresPlugin getPluginByNameAndType(String pluginName, PluginType pluginType) {
        return null;
    }

}
