package com.galvanize.rabbit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AmqpSenderServiceTest {

    @Mock
    RabbitTemplate rabbitTemplate;

    @InjectMocks
    AmqpSenderService amqpSenderService;

    private String mockExchange = "";
    private String mockRoutingKey = "";
    private String mockMessageData = "";

    @Test
    public void sendMessageTest() {
        doNothing().when(rabbitTemplate)
                .convertAndSend(mockExchange, mockRoutingKey, mockMessageData);
        amqpSenderService.sendMessage(mockExchange, mockRoutingKey, mockMessageData);
        verify(rabbitTemplate)
                .convertAndSend(mockExchange, mockRoutingKey, mockMessageData);
    }
}
