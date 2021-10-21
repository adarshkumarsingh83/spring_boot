package com.espark.adarsh;

import com.espark.adarsh.service.MessageService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import static org.mockito.Mockito.*;

@ExtendWith({OutputCaptureExtension.class, MockitoExtension.class})
public class MessageServiceTest {


    private static final String input = "adarsh";

    @Test
    public void testGetMessage() {
        MessageService messageService = Mockito.mock(MessageService.class);
        when(messageService.getMessage(input)).thenReturn(MessageService.template + input);
        String response = messageService.getMessage(input);
        Assertions.assertThat(response).isEqualTo(MessageService.template + input);
    }

    @Test
    public void testGetMessage(CapturedOutput output) {
        MessageService messageService = new MessageService();
        when(messageService.getMessage(input)).thenReturn(MessageService.template + input);
        String response = messageService.getMessage(input);
        Assertions.assertThat(response).isEqualTo(MessageService.template + input);
        Assertions.assertThat(output).contains(MessageService.template + input);
    }
}
