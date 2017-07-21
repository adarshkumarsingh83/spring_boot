package com.espark.adarsh.service;

import com.espark.adarsh.bean.ResponseBean;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.asynchttpclient.*;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;


@Service("httpRequestService")
public class AsynchHttpRequestService {

    private static final Logger LOGGER = Logger.getLogger(AsynchHttpRequestService.class);


    @SuppressWarnings("serial")
    private static final Map<String, String> defaultHeaders = new HashMap<String, String>() {
        {
            put("Content-Type", "application/json");
        }
    };

    static AsyncHttpClientConfig config = new DefaultAsyncHttpClientConfig.Builder()
            .setAcceptAnyCertificate(true)
            .build();

    public final Future<Response> post(String endpoint, String body) {
        return makeRequest(RequestMethod.POST, endpoint, body);
    }

    public final Future<Response> post(String endpoint, String body, Map<String, String> headers) {
        return makeRequest(RequestMethod.POST, endpoint, body, headers);
    }

    public final Future<Response> put(String endpoint, String body) {
        return makeRequest(RequestMethod.PUT, endpoint, body);
    }

    public final Future<Response> get(String endpoint) {
        return makeRequest(RequestMethod.GET, endpoint, null);
    }

    private Future<Response> makeRequest(RequestMethod requestMethod, String endpoint, String body) {
        return makeRequest(requestMethod, endpoint, body, null);
    }

    public Future<Response> makeRequest(RequestMethod requestMethod, String endpoint, String body,Map<String,String> headers) {
        return executeRequest(requestMethod, endpoint, body, headers);
    }

    private Future<Response> executeRequest(RequestMethod requestMethod, String endpoint, String body, Map<String, String> headers) {
        final AsyncHttpClient client = new DefaultAsyncHttpClient(config);
        BoundRequestBuilder request;
        switch (requestMethod) {
            case POST:
                request = client.preparePost(endpoint);
                break;
            case PUT:
                request = client.preparePut(endpoint);
                break;
            case GET:
                request = client.prepareGet(endpoint);
                break;
            default:
                LOGGER.error("Http Method NOt Supported");
                throw new UnsupportedOperationException("HTTP METHOD NOT SUPPORTED ");
        }

        if (headers != null) {
            for (Map.Entry<String, String> each : headers.entrySet()) {
                request.addHeader(each.getKey(), each.getValue());
            }
        } else {
            for (Map.Entry<String, String> each : defaultHeaders.entrySet()) {
                request.addHeader(each.getKey(), each.getValue());
            }
        }

        if (null != body) {
            request.setBody(body);
        }

        return request.execute(new AsyncCompletionHandler<Response>() {

            @Override
            public Response onCompleted(Response response) throws Exception {
                client.close();
                return response;
            }

            @Override
            public void onThrowable(Throwable t) {
                super.onThrowable(t);
                LOGGER.error(t);
                try {
                    client.close();
                } catch (IOException e) {
                    LOGGER.error(e);
                }
            }
        });

    }
}
