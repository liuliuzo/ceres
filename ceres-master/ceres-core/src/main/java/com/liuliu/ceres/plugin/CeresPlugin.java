package com.liuliu.ceres.plugin;

import com.liuliu.ceres.core.CeresContext;
import com.liuliu.ceres.plugin.chain.CeresPluginChain;

import reactor.core.publisher.Mono;

/**
 * @className CeresPlugin
 * @description
 * @author liuliu
 * @version 1.0
 * @email liuliu.zhao@mastercard.com
 */
public interface CeresPlugin {

    /**
     * pluginName
     * @return
     */
    String pluginName();
    
    /**
     * pluginType
     * @return
     */
    PluginType pluginType();

    /**
     * priority order to execute plugin
     * @return
     */
    int pluginOrder();

    /**
     * whether to execute this plugin
     * @return
     */
    boolean skipPlugin();
    

    Mono<Void> doPlugin(CeresContext context,CeresPluginChain chain);
}