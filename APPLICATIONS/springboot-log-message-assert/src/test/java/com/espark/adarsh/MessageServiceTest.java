package com.espark.adarsh;

import com.espark.adarsh.service.MessageService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

@ExtendWith({OutputCaptureExtension.class, MockitoExtension.class})
public class MessageServiceTest {

    @Test
    public void testGetMessage(CapturedOutput output) {
        MessageService messageService = new MessageService();
        messageService.getMessage("adarsh");
        Assertions.assertThat(output).contains(MessageService.template + "adarsh");
    }
}
