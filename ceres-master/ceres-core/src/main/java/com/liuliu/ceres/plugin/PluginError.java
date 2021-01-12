package com.liuliu.ceres.plugin;

/**
 * @className PluginError
 * @description
 * @author liuliu
 * @version 1.0
 * @email liuliu.zhao@mastercard.com
 */
public class PluginError implements Cloneable {

    private String pluginName;
    private String pluginType;
    private Throwable exception = null;

    public PluginError(String pluginName, String pluginType, Throwable exception) {
        this.pluginName = pluginName;
        this.pluginType = pluginType;
        this.exception = exception;
    }

    public String getPluginName() {
        return pluginName;
    }

    public String getPluginType() {
        return pluginType;
    }

    public Throwable getException() {
        return exception;
    }

    @Override
    public Object clone() {
        return new PluginError(pluginName, pluginType, exception);
    }

    @Override
    public String toString() {
        return "PluginError [pluginName=" + pluginName + ", pluginType=" + pluginType + ", exception=" + exception+ "]";
    }
}
