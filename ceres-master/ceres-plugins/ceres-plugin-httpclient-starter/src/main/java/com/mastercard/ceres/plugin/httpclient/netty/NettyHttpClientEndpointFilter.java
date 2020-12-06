package com.mastercard.ceres.plugin.httpclient.netty;

import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.buffer.NettyDataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.AbstractServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ResponseStatusException;

import com.mastercard.ceres.constant.Constants;
import com.mastercard.ceres.core.CeresContext;
import com.mastercard.ceres.filter.base.EndpointFilter;

import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.http.client.HttpClientResponse;

/**
 * @className NettyHttpClientEndpointFilter
 * @description
 * @author liuliu
 * @email liuliu.zhao@mastercard.com
 * @date 2019-03-15 10:45
 **/
public class NettyHttpClientEndpointFilter extends EndpointFilter {

    private static final Logger log = LoggerFactory.getLogger(NettyHttpClientEndpointFilter.class);

    private final HttpClient httpClient;

    public NettyHttpClientEndpointFilter(final HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public String filterName() {
        return "NettyHttpClientEndpointFilter";
    }

    @Override
    public int filterOrder() {
        return 3;
    }

    @Override
    public boolean skipFilter() {
        return false;
    }

    @Override
    public void doFilter(CeresContext ceresContext) {
        log.info("doFilter {} !",this);
        ServerRequest request = (ServerRequest) ceresContext.getCeresRequst();
        String context = request.exchange().getAttribute(Constants.CONTEXT);
        log.info(context);
        ServerHttpRequest serverHttpRequest = request.exchange().getRequest();
//        URI uri = request.exchange().getRequest().getURI();
//        String path = uri.getPath();
        String api = request.pathVariable("api");

//        final HttpMethod method = HttpMethod.valueOf(serverHttpRequest.getMethodValue());
//        HttpHeaders filtered = serverHttpRequest.getHeaders();
//        final DefaultHttpHeaders httpHeaders = new DefaultHttpHeaders();
//        filtered.forEach(httpHeaders::set);
//        log.info("HttpHeaders httpHeaders :{}", httpHeaders.toString());
//       String url = request.exchange().getAttribute(Constants.HTTP_URL);
//        if (StringUtils.isEmpty(url)) {
            //Object error = SoulResultWrap.error(SoulResultEnum.CANNOT_FIND_URL.getCode(), SoulResultEnum.CANNOT_FIND_URL.getMsg(), null);
            //return WebFluxResultUtils.result(exchange, error);
//        }
//        log.info("you request,The resulting urlPath is :{}", url);

        final HttpMethod method = HttpMethod.GET;
        String url = "localhost:8080/" + api;
        log.info("you request,The resulting urlPath is :{}", url);
        final DefaultHttpHeaders httpHeaders = new DefaultHttpHeaders();
        Flux<HttpClientResponse> responseFlux = this.httpClient
                                                .headers(headers -> headers.add(httpHeaders))
                                                .request(method)
                                                .uri(url)
                                                .send((req, nettyOutbound) ->nettyOutbound.send(serverHttpRequest.getBody().map(dataBuffer -> ((NettyDataBuffer) dataBuffer) .getNativeBuffer())))
                                                .responseConnection((res, connection) -> {
                    request.exchange().getAttributes().put(Constants.CLIENT_RESPONSE_ATTR, res);
                    request.exchange().getAttributes().put(Constants.CLIENT_RESPONSE_CONN_ATTR, connection);
                    ServerHttpResponse response = request.exchange().getResponse();
                    HttpHeaders headers = new HttpHeaders();
                    res.responseHeaders().forEach(entry -> headers.add(entry.getKey(), entry.getValue()));
                    String contentTypeValue = headers.getFirst(HttpHeaders.CONTENT_TYPE);
                    if (StringUtils.hasLength(contentTypeValue)) {
                        request.exchange().getAttributes().put(Constants.ORIGINAL_RESPONSE_CONTENT_TYPE_ATTR, contentTypeValue);
                    }
                    HttpStatus status = HttpStatus.resolve(res.status().code());
                    if (status != null) {
                        response.setStatusCode(status);
                    } else if (response instanceof AbstractServerHttpResponse) {
                        ((AbstractServerHttpResponse) response).setStatusCodeValue(res.status().code());
                    } else {
                        throw new IllegalStateException("Unable to set status code on response: " + res.status().code() + ", " + response.getClass());
                    }
                    response.getHeaders().putAll(headers);
                    return Mono.just(res);
                });
        long timeout = (long) Optional.ofNullable(request.exchange().getAttribute(Constants.HTTP_TIME_OUT)).orElse(3000L);
        Duration duration = Duration.ofMillis(timeout);
        responseFlux = responseFlux.timeout(duration,Mono.error(new TimeoutException("Response took longer than timeout: " + duration)))
                       .onErrorMap(TimeoutException.class, th -> new ResponseStatusException(HttpStatus.GATEWAY_TIMEOUT, th.getMessage(), th));
    }

    @Override
    public boolean stopFilterProcessing() {
        return false;
    }
}
