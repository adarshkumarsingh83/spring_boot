package com.espark.adarsh.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/*
 * @author Adarsh
 * @author $LastChangedBy: adarsh $
 * @version $Revision: 0001 $, $Date:: 15/6/20 10:12 AM#$
 */
@Slf4j
public class RestTemplateInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body,
                                        ClientHttpRequestExecution execution) throws IOException {
        log.info("label=RestTemplateInterceptor requestUrl={},requestMethod={}, requestHeader={} ",
                request.getURI(), request.getMethod(), request.getHeaders());
        ClientHttpResponse response = execution.execute(request, body);
        log.info("label=RestTemplateInterceptor responseHeaders={}, responseStatusCode={}, responseBody={} ",
                response.getHeaders() ,response.getStatusCode(),response.getBody());
        return response;
    }
}