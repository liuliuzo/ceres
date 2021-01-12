package com.liuliu.ceres.plugin.chain;

import com.liuliu.ceres.core.CeresContext;

import reactor.core.publisher.Mono;

/**
 * @className CeresPluginChain
 * @description
 * @author liuliu
 * @email liuliu.zhao@mastercard.com
 * @date 2019-03-15 10:45
 **/
public interface CeresPluginChain {
    Mono<Void> execute(CeresContext context);
}
