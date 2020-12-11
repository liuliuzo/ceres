package com.mastercard.ceres.plugin.base;

import com.mastercard.ceres.plugin.CeresPlugin;
import com.mastercard.ceres.plugin.PluginType;

/**
 * @className InBoundPlugin
 * @description
 * @author liuliu
 * @version 1.0
 * @email liuliu.zhao@mastercard.com
 */
public abstract class InBoundPlugin implements CeresPlugin {
    public PluginType pluginType() {
        return PluginType.INBOUND;
    }
}
