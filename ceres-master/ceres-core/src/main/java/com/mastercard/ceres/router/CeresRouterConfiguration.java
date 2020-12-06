package com.mastercard.ceres.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.mastercard.ceres.core.CeresHandler;

/**
 * @className CeresRouter
 * @description
 * @author liuliu
 * @email liuliu.zhao@mastercard.com
 * @date 2019-03-15 10:45
 **/
@Configuration
public class CeresRouterConfiguration {

    @Bean
    public RouterFunction<ServerResponse> routeChain(CeresHandler ceresHandler) {
        return RouterFunctions.route(RequestPredicates.path("/ceres/{api}"),ceresHandler::execute);
    }
}
