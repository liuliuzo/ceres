package com.mastercard.ceres.core.start;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.util.StringUtils;

/**
 * @className ApplicationStartListener
 * @description
 * @author liuliu
 * @email liuliu.zhao@mastercard.com
 * @date 2019-03-15 10:45
 **/
public class ApplicationStartListener implements ApplicationListener<WebServerInitializedEvent> {

    private static final Logger log = LoggerFactory.getLogger(ApplicationStartListener.class);

    @Override
    public void onApplicationEvent(final WebServerInitializedEvent event) {
        int port = event.getWebServer().getPort();
        final String host = getHost();
        final String domain = System.getProperty("Ceres.httpPath");
        if (StringUtils.isEmpty(domain)) {
            CereDomain.getInstance().setHttpPath("http://" + String.join(":", host, String.valueOf(port)));
            log.info("HttpPath: {}", CereDomain.getInstance().getHttpPath());
        } else {
            CereDomain.getInstance().setHttpPath(domain);
            log.info("HttpPath: {}", CereDomain.getInstance().getHttpPath());
        }
    }

    private String getHost() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            log.error("Get host error!", e);
            return "127.0.0.1";
        }
    }
}
