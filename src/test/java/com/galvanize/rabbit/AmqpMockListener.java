package com.galvanize.rabbit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("test-amqp")
public class AmqpMockListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(AmqpMockListener.class);

    private EventNotification eventNotification = new EventNotification("11","test","/Users/ctsuser1/Downloads/file.pdf","1544404634", (long) 10);

    @RabbitListener(queues = "${amqp.verify.queue}")
    public void verifyListener(final  EventNotification eventNotification) throws Exception {
        LOGGER.info("Received event notification: {} from verifyListenerTest.", eventNotification);
        PDFService.sendPDF(eventNotification);
    }

}


