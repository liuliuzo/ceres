package com.mastercard.ceres.core.db.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @className JPAConfiguration
 * @description
 * @author liuliu
 * @email liuliu.zhao@mastercard.com
 * @date 2019-03-15 10:45
 **/
@Configuration
@EnableJpaRepositories("com.mastercard.ceres.core.db.repository")
public class JPAConfiguration {
}
