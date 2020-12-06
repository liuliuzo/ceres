package com.mastercard.ceres.autoconfigure;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.support.BeanDefinitionValidationException;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.util.CollectionUtils;

import com.mastercard.ceres.core.CeresHandler;
import com.mastercard.ceres.filter.CeresFilter;
import com.mastercard.ceres.filter.loader.StaticFilterLoader;
import com.mastercard.ceres.router.CeresRouter;
import com.mastercard.ceres.spring.ApplicationContextHelper;

/**
 * @className CeresAutoConfiguration
 * @description
 * @author liuliu
 * @email liuliu.zhao@mastercard.com
 * @date 2019-03-15 10:45
 **/
@Configuration
@EnableConfigurationProperties(CeresProperties.class)
@Import({ CeresRouter.class })
public class CeresAutoConfiguration {

    @Bean
    public StaticFilterLoader staticFilterLoader(final ObjectProvider<List<CeresFilter>> filters) {
        List<CeresFilter> filtersList = filters.getIfAvailable(Collections::emptyList);
        if (CollectionUtils.isEmpty(filtersList)) {
            throw new BeanDefinitionValidationException("filters can not null !");
        }
        return new StaticFilterLoader(filtersList);
    }

    @Bean
    public CeresHandler ceresHandler(StaticFilterLoader staticFilterLoader) {
        return new CeresHandler(staticFilterLoader);
    }

    @Bean
    public ApplicationContextHelper applicationContextHelper() {
        return new ApplicationContextHelper();
    }
}
