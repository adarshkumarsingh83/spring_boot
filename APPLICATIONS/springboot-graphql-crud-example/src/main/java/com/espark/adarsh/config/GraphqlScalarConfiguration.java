package com.espark.adarsh.config;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.language.StringValue;
//import graphql.scalars.ExtendedScalars;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;
import graphql.schema.GraphQLScalarType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Map;

@Configuration
public class GraphqlScalarConfiguration {

    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return wiringBuilder -> wiringBuilder
                .scalar(dateScalar())
                .scalar(jsonScalar());
                //.scalar(ExtendedScalars.Json)
                //.scalar(ExtendedScalars.Date);
    }


    public GraphQLScalarType dateScalar() {
        return GraphQLScalarType.newScalar()
                .name("Date") //graphql type define in the schema file
                .description("Java 8 LocalDate as scalar.")
                .coercing(new Coercing<LocalDate, String>() {
                    @Override
                    public String serialize(final Object dataFetcherResult) {
                        if (dataFetcherResult instanceof LocalDate) {
                            return dataFetcherResult.toString();
                        } else {
                            throw new CoercingSerializeException("Expected a LocalDate object.");
                        }
                    }

                    @Override
                    public LocalDate parseValue(final Object input) {
                        try {
                            if (input instanceof String) {
                                return LocalDate.parse((String) input);
                            } else {
                                throw new CoercingParseValueException("Expected a String");
                            }
                        } catch (DateTimeParseException e) {
                            throw new CoercingParseValueException(String.format("Not a valid date: '%s'.", input), e
                            );
                        }
                    }

                    @Override
                    public LocalDate parseLiteral(final Object input) {
                        if (input instanceof StringValue) {
                            try {
                                return LocalDate.parse(((StringValue) input).getValue());
                            } catch (DateTimeParseException e) {
                                throw new CoercingParseLiteralException(e);
                            }
                        } else {
                            throw new CoercingParseLiteralException("Expected a StringValue.");
                        }
                    }
                })
                .build();
    }


    public GraphQLScalarType jsonScalar() {
        ObjectMapper objectMapper = new ObjectMapper();
        return GraphQLScalarType.newScalar()
                .name("JSON") //graphql type define in the schema file
                .description("Java MAP as scalar.")
                .coercing(new Coercing<Map<String, String>, String>() {
                    @Override
                    public String serialize(final Object dataFetcherResult) {
                        if (dataFetcherResult instanceof Map) {
                            try {
                                return objectMapper.writeValueAsString(dataFetcherResult);
                            } catch (JsonProcessingException e) {
                                throw new RuntimeException(e);
                            }
                        } else {
                            throw new CoercingSerializeException("Expected a Map object.");
                        }
                    }

                    @Override
                    public Map<String, String> parseValue(final Object input) {

                        if (input instanceof StringValue) {
                            try {
                                return objectMapper.readValue(input.toString()
                                        , new TypeReference<Map<String, String>>() {
                                        });


                            } catch (JsonProcessingException e) {
                                throw new CoercingParseLiteralException(e);
                            }
                        } else {
                            throw new CoercingParseValueException("Expected a String");
                        }
                    }


                    @Override
                    public Map<String, String> parseLiteral(final Object input) {
                        if (input instanceof StringValue) {
                            try {
                                return objectMapper.readValue(((StringValue) input).getValue()
                                        , new TypeReference<Map<String, String>>() {
                                        });
                            } catch (JsonProcessingException e) {
                                throw new CoercingParseLiteralException(e);
                            }
                        } else {
                            throw new CoercingParseLiteralException("Expected a StringValue.");
                        }
                    }
                }).build();

    }

}
