package com.liuliu.ceres.plugin.base;

import com.liuliu.ceres.plugin.CeresPlugin;
import com.liuliu.ceres.plugin.PluginType;

/**
 * @className OutBoundPlugin
 * @description
 * @author liuliu
 * @version 1.0
 * @email liuliu.zhao@mastercard.com
 */
public abstract class OutBoundPlugin implements CeresPlugin {
    public PluginType pluginType() {
        return PluginType.OUTBOUND;
    }
}
