package com.mastercard.ceres.utils;

import java.util.Objects;
import org.springframework.http.MediaType;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

/**
 * @className WebFluxResultUtils
 * @description
 * @author liuliu
 * @version 1.0
 * @email liuliu.zhao@mastercard.com
 */
public class WebFluxResultUtils {
	public static Mono<Void> result(final ServerWebExchange exchange, final Object result) {
		exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
		return exchange.getResponse().writeWith(Mono.just(exchange.getResponse().bufferFactory()
				.wrap(Objects.requireNonNull(JsonUtils.toJson(result)).getBytes())));
	}
}
