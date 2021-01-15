package com.espark.adarsh.configuration.conditions;

import org.springframework.boot.autoconfigure.condition.ConditionMessage;
import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.ClassUtils;

import java.util.Arrays;

public class RemoteCondition extends SpringBootCondition {

    private static final String[] CLASS_NAMES = { "com.espark.adarsh.service.EsparkServiceRemoteImpl"};

    @Override
    public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {
        ConditionMessage.Builder message = ConditionMessage.forCondition("Remote");

        return Arrays.stream(CLASS_NAMES)
                .filter(className -> ClassUtils.isPresent(className, context.getClassLoader()))
                .map(className -> ConditionOutcome.match(message.found("class").items(
                        ConditionMessage.Style.NORMAL, className))).findAny()
                .orElseGet(() -> ConditionOutcome.noMatch(message.didNotFind("class", "classes").items(
                        ConditionMessage.Style.NORMAL, Arrays.asList(CLASS_NAMES))));
    }

}
