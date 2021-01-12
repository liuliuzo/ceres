package com.mastercard.ceres.autoconfigure;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.support.BeanDefinitionValidationException;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import com.mastercard.ceres.core.CeresPluginWebHandler;
import com.mastercard.ceres.core.CeresWebFilter;
import com.mastercard.ceres.core.start.ApplicationStartListener;
import com.mastercard.ceres.plugin.CeresPlugin;
import com.mastercard.ceres.plugin.loader.CeresPluginLoader;
import com.mastercard.ceres.plugin.loader.StaticPluginLoader;
import com.mastercard.ceres.spring.CeresApplicationContextAware;

/**
 * @className CeresAutoConfiguration
 * @description
 * @author liuliu
 * @email liuliu.zhao@mastercard.com
 * @date 2019-03-15 10:45
 **/
@Configuration
@EnableConfigurationProperties(CeresProperties.class)
public class CeresAutoConfiguration {

    @Bean
    public CeresPluginLoader ceresPluginLoader(final ObjectProvider<List<CeresPlugin>> plugins,CeresProperties ceresProperties) {
        List<CeresPlugin> pluginsList = plugins.getIfAvailable(Collections::emptyList);
        if (CollectionUtils.isEmpty(pluginsList)) {
            throw new BeanDefinitionValidationException("plugins can not null !");
        }
        return new StaticPluginLoader(pluginsList);
    }

    @Bean
    public CeresPluginWebHandler ceresPluginWebHandler(CeresPluginLoader ceresPluginLoader,CeresProperties ceresProperties) {
        return new CeresPluginWebHandler(ceresPluginLoader,ceresProperties);
    }

    @Bean
    public ApplicationStartListener applicationStartListener() {
        return new ApplicationStartListener();
    }

    @Bean
    public ApplicationContextAware applicationContextAware() {
        return new CeresApplicationContextAware();
    }

    @Bean
    public CeresWebFilter ceresWebFilter(CeresPluginWebHandler ceresPluginWebHandler) {
        CeresWebFilter ceresWebFilter = new CeresWebFilter();
        ceresWebFilter.setCeresPluginWebHandler(ceresPluginWebHandler);
        return ceresWebFilter;
    }
}
