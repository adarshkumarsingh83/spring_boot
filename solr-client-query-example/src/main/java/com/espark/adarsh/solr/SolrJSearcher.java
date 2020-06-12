package com.espark.adarsh.solr;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;

import java.io.IOException;

public class SolrJSearcher {
    public static void main(String[] args) throws IOException, SolrServerException {

        SolrClient client = new HttpSolrClient.Builder("http://localhost:8983/solr/adarsh").build();

        SolrQuery query = new SolrQuery();
        query.setQuery("book query");
        query.addFilterQuery("cat:book");
        query.addFilterQuery("name:The Legend of the Hobbit part 200");
        query.setFields("id", "price", "merchant", "cat");
        query.setStart(0);
        query.addSort("id", SolrQuery.ORDER.desc);

        QueryResponse response = client.query(query);
        SolrDocumentList results = response.getResults();
        for (int i = 0; i < results.size(); ++i) {
            System.out.println(results.get(i));
        }
    }
}
