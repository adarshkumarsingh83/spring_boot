package com.espark.adarsh.controller;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.embedded.EmbeddedSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ApplicationController {

   /* @Autowired
    private SolrTemplate solrTemplate;

    @PostMapping("/data")
    public @ResponseBody UpdateResponse saveData(@RequestBody Map<String,String> data){
       return solrTemplate.saveBean(data);
    }

    @GetMapping("/data/{id}")
    public Map<String,String> getData(@PathVariable("id") String id){
        return this.solrTemplate.getById(id, HashMap.class);
    }*/

    @Autowired
    private SolrClient solrServer;

    @GetMapping("/data")
    public long getData() throws Exception {
        SolrQuery query = new SolrQuery();
        query.setQuery("*:*");
        QueryResponse resp = solrServer.query(query);
        long numDocs = resp.getResults().getNumFound();
        return numDocs;
    }
}
