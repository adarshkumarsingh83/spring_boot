package com.espark.adarsh.client.exception;

public class InputTranslationException extends RuntimeException {
    public InputTranslationException(String errorWhileProcessingInputArguments, Exception e) {
        super(errorWhileProcessingInputArguments, e);
    }
}
