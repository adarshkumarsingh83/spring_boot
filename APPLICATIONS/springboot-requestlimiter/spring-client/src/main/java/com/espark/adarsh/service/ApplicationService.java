package com.espark.adarsh.service;

import com.espark.adarsh.bean.MyEntry;
import com.espark.adarsh.bean.RequestBean;
import com.espark.adarsh.bean.ResponseBean;
import com.espark.adarsh.consumer.TriConsumer;
import com.espark.adarsh.record.Employee;
import com.espark.adarsh.util.DataGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.*;

@Service
public class ApplicationService {

    static final Logger log = LoggerFactory.getLogger(ApplicationService.class);
    int maxSize = 3;
    final int dataCacheTimeMin = 3;
    private DataGenerator dataGenerator;
    private IntegrationService integrationService;

    public ApplicationService(DataGenerator dataGenerator, IntegrationService integrationService) {
        this.dataGenerator = dataGenerator;
        this.integrationService = integrationService;
    }

    public BiFunction<Map<String, Employee>, List<Employee>, List<Employee>> fetchDataChuck = (employeeCache, employeeList) -> {
        List<Employee> deficitList = new ArrayList<>();
        if (employeeCache.size() < maxSize && !employeeList.isEmpty()) {
            int delta = maxSize - employeeCache.size();
            deficitList = employeeList.stream().limit(delta).toList();
            employeeList.removeAll(employeeList.subList(0, Math.min(delta, employeeList.size())));
            log.info("ApplicationService::fetchDataChuck  delta {} deficitList {} employeeListSize {}",
                    delta, deficitList.size(), employeeList.size());
        }
        return deficitList;
    };

    public BiFunction<String, Employee, ResponseBean<Employee>> makeRequest = (batchId, employee) -> {
        RequestBean<Employee> employeeRequestBean = new RequestBean<>();
        ResponseBean<Employee> employeeResponseBean = null;
        employeeRequestBean.setBatchId(batchId);
        employeeRequestBean.setData(employee);
        log.info("ApplicationService::makeRequest request-> dataId {} batchId {} ",
                employee.id(), batchId);
        employeeResponseBean = integrationService.postEmployee(employeeRequestBean);
        log.info("ApplicationService::makeRequest response-> dataId {} batchId {} ",
                employeeResponseBean.getDate().id(), employeeResponseBean.getBatchId());
        return employeeResponseBean;
    };

    public Supplier<String> generateBatchId = () -> {
        try {
            Thread.currentThread().sleep(10000);
        } catch (InterruptedException e) {
            log.error(e.getLocalizedMessage());
        }
        LocalDateTime localDateTime = LocalDateTime.now();
        int year = localDateTime.getYear();
        int month = localDateTime.getMonthValue();
        int day = localDateTime.getDayOfMonth();
        int hour = localDateTime.getHour();
        int minute = localDateTime.getMinute();
        int second = localDateTime.getSecond();
        return year + month + "" + day + "" + hour + "" + minute + second + System.currentTimeMillis();
    };

    public Function<Map<String, LocalDateTime>, List<String>> filterTimeoutRequest = (employeePostCache) -> {
        return employeePostCache.entrySet().stream()
                .filter(entry -> LocalDateTime.now().isAfter(entry.getValue()))
                .map(Map.Entry::getKey)
                .toList();
    };

    public TriConsumer<Map<String, Employee>, Map<String, LocalDateTime>, List<Employee>> cacheCleanUp
            = (employeeCache, employeePostCache, employeeList) -> {
        filterTimeoutRequest.apply(employeePostCache).forEach(id -> {
            Employee employee = employeeCache.get(id);
            employeePostCache.remove(id);
            employeeCache.remove(id);
            employeeList.add(employee);
            log.info("ApplicationService::cacheCleanUp empId {} ", employee.id());
            log.info("ApplicationService::cacheCleanUp employeeList {} employeeCache {}"
                    , employeeList.size(), employeeCache.size(), employeePostCache.size());

        });
    };

    @Async
    public void postEmployees() {
        final AtomicInteger requestCount = new AtomicInteger(0);
        final AtomicInteger responseCount = new AtomicInteger(0);
        final String batchId = generateBatchId.get();
        final Map<String, Employee> employeeCache = new HashMap<>();
        final Map<String, LocalDateTime> employeePostCache = new HashMap<>();
        log.info("ApplicationService::postEmployees batchId {} ", batchId);
        final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(maxSize);
        List<Employee> employeeList = dataGenerator.generateEmployeeData.apply(batchId,100);
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            if (!employeeList.isEmpty()) {
                List<Employee> deficietList = fetchDataChuck.apply(employeeCache, employeeList);
                if (!deficietList.isEmpty()) {
                    CompletableFuture.runAsync(() -> {
                        deficietList.forEach(employee -> {
                            if (!employeeCache.containsKey(employee.id())) {
                                employeeCache.put(employee.id(), employee);
                                employeePostCache.put(employee.id(), LocalDateTime.now().plusMinutes(dataCacheTimeMin));
                                ResponseBean<Employee> responseBean = makeRequest.apply(batchId, employee);
                                if (responseBean != null && responseBean.getDate() != null) {
                                    employeeCache.remove(responseBean.getDate().id());
                                    employeePostCache.remove(responseBean.getDate().id());
                                    log.info("ApplicationService::postEmployees employeeCache{} employeePostCache{} requestCount {} responseCount {}"
                                            , employeeCache.size(), employeePostCache.size(), requestCount.incrementAndGet(), responseCount.incrementAndGet());
                                }
                            }
                        });

                    });

                } else {
                    cacheCleanUp.accept(employeeCache, employeePostCache, employeeList);
                }
            } else {
                log.info("ApplicationService::postEmployees employeeList is Empty ");
                scheduledExecutorService.shutdownNow();
                log.info("ApplicationService::postEmployees scheduler is shutting down batch {} is completed ", batchId);
            }

        }, 0, 1, TimeUnit.SECONDS);
    }

    @Async
    public void postEmployeeWithCounter(){
        final AtomicInteger counter = new AtomicInteger(0);
        final String batchId = generateBatchId.get();
        log.info("ApplicationService::postEmployeeWithCounter batchId {} ", batchId);
        final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(maxSize);
        List<Employee> employeeList = dataGenerator.generateEmployeeData.apply(batchId,100);
        ConcurrentLinkedQueue<Employee> concurrentLinkedQueue = new ConcurrentLinkedQueue<>(employeeList);
        scheduledExecutorService.scheduleAtFixedRate(()->{
            CompletableFuture.runAsync(()->{
                if(!concurrentLinkedQueue.isEmpty()){
                    if(counter.intValue()<=2){
                        counter.set(counter.intValue()+1);
                        log.info("ApplicationService::postEmployeeWithCounter counter++ {}",counter.get());
                        Employee employee = concurrentLinkedQueue.poll();
                        ResponseBean<Employee> employeeResponseBean  = makeRequest.apply(batchId,employee);
                        if(employeeResponseBean!=null && employeeResponseBean.getDate() != null){
                            log.info("ApplicationService::postEmployeeWithCounter employee request {} response {}",
                                    employee.id(),employeeResponseBean.getDate().id());
                            counter.set(counter.intValue()-1);
                            log.info("ApplicationService::postEmployeeWithCounter counter-- {}",counter.get());
                        }

                    }
                }
            });
            if(concurrentLinkedQueue.isEmpty()){
                log.info("ApplicationService::postEmployeeWithCounter concurrentLinkedQueue is empty ");
                scheduledExecutorService.shutdownNow();
                log.info("ApplicationService::postEmployeeWithCounter scheduler is shutting down and batch {} is completed threadName {}",
                        batchId,Thread.currentThread().getName());
            }

        },0,1,TimeUnit.NANOSECONDS);
    }

    public Function<String,Map<String,List<Employee>>> getDataForPost = (batchId) ->{
        Map<String,List<Employee>> store = new HashMap<>();
        for(int i=0;i<10;i++){
            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
                log.error(e.getLocalizedMessage());
            }
            store.put(batchId,dataGenerator.generateEmployeeData.apply(batchId,10));
        }

        store.entrySet().stream().forEach(entry-> {
            log.info("Entry Details key{} threadName {}",entry.getKey(),Thread.currentThread().getName());
            entry.getValue().stream().forEach(value -> {
                log.info("value {}",value);
            });
        });

        return store;
    };

    private Predicate<AtomicInteger> checkCounter = (counter) -> {
      synchronized (counter){
          return counter.intValue()  < maxSize ;
      }
    };

    private Consumer<AtomicInteger> incrementCounter = (counter) -> {
        synchronized (counter){
             counter.set(counter.intValue()  +1 ); ;
        }
    };

    private Consumer<AtomicInteger> decrementCounter = (counter) -> {
        synchronized (counter){
            counter.set(counter.intValue()  - 1 ); ;
        }
    };

    @Async
    public void postEmployeeData(){
        final AtomicInteger counter = new AtomicInteger(0);
        final String batchId = generateBatchId.get();
        Map<String,List<Employee>> data = getDataForPost.apply(batchId);
        List<MyEntry<String,Employee>> dataToProcess = data.entrySet()
                .stream()
                .flatMap(entry ->{
           return entry.getValue().stream().map(value -> new MyEntry<String,Employee>(entry.getKey(),value)).toList().stream();
        }).toList();
        ConcurrentLinkedQueue<MyEntry<String,Employee>> concurrentLinkedQueue = new ConcurrentLinkedQueue<>(dataToProcess);
        final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(maxSize);
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            CompletableFuture.runAsync(() -> {
                if (!concurrentLinkedQueue.isEmpty()) {
                    if (checkCounter.test(counter)) {
                        incrementCounter.accept(counter);
                        log.info("ApplicationService::postEmployeeData counter++ {}", counter.get());
                        MyEntry<String, Employee> employeeEntry = concurrentLinkedQueue.poll();
                        ResponseBean<Employee> employeeResponseBean = makeRequest.apply(employeeEntry.getKey(), employeeEntry.getValue());
                        if (employeeResponseBean != null && employeeResponseBean.getDate() != null) {
                            log.info("ApplicationService::postEmployeeData employee request {} response {}",
                                    employeeEntry.getValue().id(), employeeResponseBean.getDate().id());
                            decrementCounter.accept(counter);
                            log.info("ApplicationService::postEmployeeData counter-- {}", counter.get());
                        }

                    }
                }
            });
            if(concurrentLinkedQueue.isEmpty() && counter.intValue() == 0 ){
                log.info("ApplicationService::postEmployeeData concurrentLinkedQueue is empty ");
                scheduledExecutorService.shutdownNow();
                log.info("ApplicationService::postEmployeeData scheduler is shutting down and batch {} is completed threadName {}",
                        batchId,Thread.currentThread().getName());
            }

        },0,1,TimeUnit.NANOSECONDS);

    }

}
