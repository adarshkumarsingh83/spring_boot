package com.espark.adarsh.service;

import com.espark.adarsh.bean.HttpRequestCallableBean;
import com.espark.adarsh.bean.ResponseBean;
import io.netty.handler.codec.http.HttpMethod;
import org.apache.log4j.Logger;
import org.asynchttpclient.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Service
public class ParallelTaskService {

    private static final Logger LOGGER = Logger.getLogger(ParallelTaskService.class);

    @Value("${application.parallel.thread}")
    private Integer parallelThread;

    @Autowired(required = true)
    @Qualifier(value = "httpRequestService")
    private AsynchHttpRequestService asynchHttpRequestService;

    private ExecutorService executor;

    @PostConstruct
    public void init() {
        this.executor = Executors.newWorkStealingPool(parallelThread);
    }

    /**
     *
     * @param httpRequestCallableBeans
     * @return
     * @throws Exception
     */
    public List<Response> executeRequest(final List<HttpRequestCallableBean> httpRequestCallableBeans) throws Exception {
        final List<Callable<Response>> callableList = this.getTasks(httpRequestCallableBeans);
        final List<Future<Response>> result = this.executor.invokeAll(callableList);
        final List<Response> responseList = this.getResponse(result);
        return responseList;
    }

    private List<Response> getResponse(List<Future<Response>> futureList) {
        final List<Response> responseBeanList = new ArrayList<Response>();
        futureList.stream().forEach((future) -> {
            try {
                final Response response = future.get();
                responseBeanList.add(response);
            } catch (InterruptedException | ExecutionException e) {
                LOGGER.error(e);
            }
        });
        return responseBeanList;
    }

    private List<Callable<Response>> getTasks(final List<HttpRequestCallableBean> httpRequestCallableBeans) {
        final List<Callable<Response>> callableList = new ArrayList<Callable<Response>>();
        httpRequestCallableBeans.stream().forEach((httpRequestCallableBean) -> {
            callableList.add(() -> {
                return (Response) this.asynchHttpRequestService.makeRequest(httpRequestCallableBean.getHttpMethod(), httpRequestCallableBean.getUrl()
                        , (httpRequestCallableBean.getBodyData()!=null )?httpRequestCallableBean.getBodyData().toString():""
                        , httpRequestCallableBean.getHeaders()).get();
            });
        });
        return callableList;
    }
}
