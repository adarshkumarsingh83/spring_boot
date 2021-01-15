package com.espark.adarsh.web;

import com.espark.adarsh.bean.Person;
import com.espark.adarsh.service.CacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/v1/api")
public class PersonController {

    @Autowired
    CacheService cacheService;

    @PostMapping("/person/{cacheName}")
    public boolean save(@PathVariable("cacheName") String cacheName, @RequestBody Person person) {
        return this.cacheService.save(cacheName, person);
    }

    @PutMapping("/person/{cacheName}")
    public boolean update(@PathVariable("cacheName") String cacheName, @RequestBody Person person) {
        return this.cacheService.update(cacheName, person);
    }

    @GetMapping("/person/{cacheName}/{personId}")
    public Object get(@PathVariable("cacheName") String cacheName, @PathVariable("personId") Long personId) {
        return this.cacheService.get(cacheName, personId);
    }

    @DeleteMapping("/person/{cacheName}/{personId}/{personId}")
    public boolean delete(@PathVariable("cacheName") String cacheName, @PathVariable("personId") Long personId) {
        return this.cacheService.delete(cacheName, personId);
    }

    @DeleteMapping("/person/{cacheName}")
    public boolean clearCache(@PathVariable("cacheName") String cacheName) {
        return this.cacheService.clearCache(cacheName);
    }
}
