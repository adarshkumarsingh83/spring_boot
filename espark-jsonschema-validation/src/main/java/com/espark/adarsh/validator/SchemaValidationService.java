package com.espark.adarsh.validator;

import java.io.IOException;

public interface SchemaValidationService {

    void validateRequest(String requestData) throws IOException;

}
