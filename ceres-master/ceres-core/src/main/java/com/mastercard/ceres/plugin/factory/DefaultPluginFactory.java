package com.liuliu.ceres.plugin.factory;

import com.liuliu.ceres.plugin.CeresPlugin;

/**
 * @className DefaultPluginFactory
 * @description
 * @author liuliu
 * @email liuliu.zhao@mastercard.com
 * @date 2019-03-15 10:45
 **/
public class DefaultPluginFactory implements CeresPluginFactory {
    @Override
    public CeresPlugin newInstance(Class clazz) throws InstantiationException, IllegalAccessException {
        return (CeresPlugin) clazz.newInstance();
    }
}
