package com.espark.adarsh.service;

import com.espark.adarsh.document.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;

import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.espark.adarsh.util.Constant.INDEX;
import static com.espark.adarsh.util.Constant.TYPE;

@Service
@Slf4j
public class PersonService {


    private RestHighLevelClient client;


    private ObjectMapper objectMapper;

    @Autowired
    public PersonService(RestHighLevelClient client, ObjectMapper objectMapper) {
        this.client = client;
        this.objectMapper = objectMapper;
    }

    public String createPersonDocument(Person document) throws Exception {

        UUID uuid = UUID.randomUUID();
        document.setId(uuid.toString());

        IndexRequest indexRequest = new IndexRequest(INDEX, TYPE, document.getId())
                .source(convertPersonDocumentToMap(document));

        IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
        return indexResponse.getResult().name();
    }

    public Person findById(String id) throws Exception {

        GetRequest getRequest = new GetRequest(INDEX, TYPE, id);

        GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
        Map<String, Object> resultMap = getResponse.getSource();

        return convertMapToPersonDocument(resultMap);

    }


    public String updatePerson(Person document) throws Exception {

        Person resultDocument = findById(document.getId());

        UpdateRequest updateRequest = new UpdateRequest(
                INDEX,
                TYPE,
                resultDocument.getId());

        updateRequest.doc(convertPersonDocumentToMap(document));
        UpdateResponse updateResponse = client.update(updateRequest, RequestOptions.DEFAULT);

        return updateResponse
                .getResult()
                .name();

    }

    public List<Person> findAll() throws Exception {


        SearchRequest searchRequest = buildSearchRequest(INDEX, TYPE);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse =
                client.search(searchRequest, RequestOptions.DEFAULT);

        return getSearchResult(searchResponse);
    }


    public List<Person> findPersonByName(String name) throws Exception {


        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(INDEX);
        searchRequest.types(TYPE);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        MatchQueryBuilder matchQueryBuilder = QueryBuilders
                .matchQuery("name", name)
                .operator(Operator.AND);

        searchSourceBuilder.query(matchQueryBuilder);

        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse =
                client.search(searchRequest, RequestOptions.DEFAULT);

        return getSearchResult(searchResponse);

    }

    public String deletePersonDocument(String id) throws Exception {

        DeleteRequest deleteRequest = new DeleteRequest(INDEX, TYPE, id);
        DeleteResponse response = client.delete(deleteRequest, RequestOptions.DEFAULT);

        return response
                .getResult()
                .name();

    }

    private Map<String, Object> convertPersonDocumentToMap(Person profileDocument) {
        return objectMapper.convertValue(profileDocument, Map.class);
    }

    private Person convertMapToPersonDocument(Map<String, Object> map) {
        return objectMapper.convertValue(map, Person.class);
    }


    public List<Person> searchByTechnology(String technology) throws Exception {

        SearchRequest searchRequest = buildSearchRequest(INDEX, TYPE);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        QueryBuilder queryBuilder = QueryBuilders
                .boolQuery()
                .must(QueryBuilders
                        .matchQuery("technologies.name", technology));

        searchSourceBuilder.query(QueryBuilders.nestedQuery("technologies", queryBuilder, ScoreMode.Avg));

        searchRequest.source(searchSourceBuilder);

        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);

        return getSearchResult(response);
    }

    private List<Person> getSearchResult(SearchResponse response) {

        SearchHit[] searchHit = response.getHits().getHits();

        List<Person> profileDocuments = new ArrayList<>();

        for (SearchHit hit : searchHit) {
            profileDocuments
                    .add(objectMapper
                            .convertValue(hit
                                    .getSourceAsMap(), Person.class));
        }

        return profileDocuments;
    }

    private SearchRequest buildSearchRequest(String index, String type) {

        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(index);
        searchRequest.types(type);

        return searchRequest;
    }
}
