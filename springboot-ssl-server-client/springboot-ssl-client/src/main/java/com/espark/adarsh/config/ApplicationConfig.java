package com.espark.adarsh.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Configuration
public class ApplicationConfig {

    @Autowired
    ApplicationConfigProps applicationConfigProps;

    @Bean
    public RestTemplate restTemplate()
            throws IOException, CertificateException, NoSuchAlgorithmException,
            KeyStoreException, KeyManagementException {
        log.info("label=SslIntegrationService restTemplate() ");

        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

        SSLContext sslContext = SSLContextBuilder
                .create()
                .loadTrustMaterial(ResourceUtils.getFile(applicationConfigProps.getKeyStore()),
                        applicationConfigProps.getKeyStorePassword().toCharArray())
                .loadTrustMaterial(null, acceptingTrustStrategy)
                .setProtocol(applicationConfigProps.getProtocall())
                .build();

        HttpClient httpClient = HttpClients.custom()
                .setSSLContext(sslContext)
                .build();

        HttpComponentsClientHttpRequestFactory requestFactory
                = new HttpComponentsClientHttpRequestFactory();

        requestFactory.setHttpClient(httpClient);

        RestTemplate restTemplate = new RestTemplate(requestFactory);

        List<ClientHttpRequestInterceptor> interceptors
                = restTemplate.getInterceptors();
        if (CollectionUtils.isEmpty(interceptors)) {
            interceptors = new ArrayList<>();
        }
        interceptors.add(new RestTemplateInterceptor());
        restTemplate.setInterceptors(interceptors);

        return restTemplate;
    }
}
