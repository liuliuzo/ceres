package com.mastercard.ceres.plugin.httpclient.autoconfigure;

import java.io.IOException;
import java.net.URL;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.server.WebServerException;
import org.springframework.util.ResourceUtils;

import lombok.Getter;
import lombok.Setter;
import reactor.netty.resources.ConnectionProvider;
import reactor.netty.tcp.SslProvider;


/**
 * @className HttpClientProperties
 * @description
 * @author liuliu
 * @email liuliu.zhao@mastercard.com
 * @date 2019-03-15 10:45
 **/
@ConfigurationProperties(prefix = HttpClientProperties.PREFIX)
public class HttpClientProperties {

    public static final String PREFIX = "ceres.httpclinet";

    @Getter
    @Setter
    private Integer connectTimeout;

    @Getter
    @Setter
    private Duration responseTimeout;
    
    @Getter
    @Setter
    private boolean wiretap;

    @Getter
    @Setter
    private Pool pool = new Pool();

    @Getter
    @Setter
    private Proxy proxy = new Proxy();

    @Getter
    @Setter
    private Ssl ssl = new Ssl();


    public static class Pool {

        @Getter
        @Setter
        private PoolType type = PoolType.ELASTIC;

        @Getter
        @Setter
        private String name = "proxy";

        @Getter
        @Setter
        private Integer maxConnections = ConnectionProvider.DEFAULT_POOL_MAX_CONNECTIONS;

        @Getter
        @Setter
        private Long acquireTimeout = ConnectionProvider.DEFAULT_POOL_ACQUIRE_TIMEOUT;

        public enum PoolType {
            ELASTIC,
            FIXED,
            DISABLED
        }
    }

    public static class Proxy {

        @Getter
        @Setter
        private String host;

        @Getter
        @Setter
        private Integer port;

        @Getter
        @Setter
        private String username;

        @Getter
        @Setter
        private String password;

        @Getter
        @Setter
        private String nonProxyHostsPattern;
    }

    public class Ssl {

        @Getter
        @Setter
        private boolean useInsecureTrustManager;

        @Getter
        @Setter
        private List<String> trustedX509Certificates = new ArrayList<>();

        // use netty default SSL timeouts
        /**
         * SSL handshake timeout. Default to 10000 ms
         */
        @Getter
        @Setter
        private Duration handshakeTimeout = Duration.ofMillis(10000);

        /**
         * SSL close_notify flush timeout. Default to 3000 ms.
         */
        @Getter
        @Setter
        private Duration closeNotifyFlushTimeout = Duration.ofMillis(3000);

        /**
         * SSL close_notify read timeout. Default to 0 ms.
         */
        @Getter
        @Setter
        private Duration closeNotifyReadTimeout = Duration.ZERO;

        /**
         * The default ssl configuration type. Defaults to TCP.
         */
        @Getter
        @Setter
        private SslProvider.DefaultConfigurationType defaultConfigurationType = SslProvider.DefaultConfigurationType.TCP;

        /**
         * Get trusted x 509 certificates for trust manager x 509 certificate [ ].
         * @return the x 509 certificate [ ]
         */
        public X509Certificate[] getTrustedX509CertificatesForTrustManager() {
            try {
                CertificateFactory certificateFactory = CertificateFactory
                        .getInstance("X.509");
                List<Certificate> allCerts = new ArrayList<>();
                for (String trustedCert : ssl.getTrustedX509Certificates()) {
                    try {
                        URL url = ResourceUtils.getURL(trustedCert);
                        Collection<? extends Certificate> certs = certificateFactory.generateCertificates(url.openStream());
                        allCerts.addAll(certs);
                    } catch (IOException e) {
                        throw new WebServerException("Could not load certificate '" + trustedCert + "'", e);
                    }
                }
                return allCerts.toArray(new X509Certificate[allCerts.size()]);
            } catch (CertificateException e) {
                throw new WebServerException("Could not load CertificateFactory X.509", e);
            }
        }
    }
}
