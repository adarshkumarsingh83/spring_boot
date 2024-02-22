package com.espark.adarsh.configuration;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;

@Configuration
@EnableSolrRepositories(
        basePackages = "com.espark.adarsh.repository.solr",
        namedQueriesLocation = "classpath:config/solr/solr-named-queries.properties")
public class SolrConfiguration {

    @Value("${spring.data.solr.host}")
    private String solrSeverUrl;

    @Bean("solrClient")
    public SolrClient solrClient() {
        return new HttpSolrClient.Builder(solrSeverUrl).build();
    }

    @Bean("solrTemplate")
    public SolrTemplate solrTemplate(SolrClient client) throws Exception {
        return new SolrTemplate(client);
    }



}
