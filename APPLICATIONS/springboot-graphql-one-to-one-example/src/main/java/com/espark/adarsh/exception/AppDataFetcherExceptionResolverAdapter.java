package com.espark.adarsh.exception;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;


@Slf4j
@Component
public class AppDataFetcherExceptionResolverAdapter extends DataFetcherExceptionResolverAdapter {

    @Override
    protected List<GraphQLError> resolveToMultipleErrors(Throwable ex, DataFetchingEnvironment env) {
        log.error("exception {}",ex.getMessage());
        if(ex!=null){
            if(ex instanceof EmployeeNotFoundException){
                return Arrays.asList((EmployeeNotFoundException)ex);
            }
        }
         return Arrays.asList(GraphqlErrorBuilder.newError().message(ex.getMessage()).errorType(ErrorType.DataFetchingException).build());
    }

    @Override
    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
        log.error("exception {}",ex.getMessage());
        if(ex!=null){
            if(ex instanceof EmployeeNotFoundException){
                return (EmployeeNotFoundException)ex;
            }
        }
        return GraphqlErrorBuilder.newError().message(ex.getMessage()).errorType(ErrorType.DataFetchingException).build();
    }
}
