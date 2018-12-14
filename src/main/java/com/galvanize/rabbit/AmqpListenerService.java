package com.galvanize.rabbit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("!test-amqp")
public class AmqpListenerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AmqpListenerService.class);

    private final PDFService pdfService;

    @Autowired
    public AmqpListenerService(PDFService pdfService) {
        this.pdfService = pdfService;
    }

    @RabbitListener(queues = "${amqp.verify.queue}")
    public void verifyListener(final EventNotification eventNotification) throws Exception {
        LOGGER.info("Received message: {} from event exchange topic.", eventNotification);
        PDFService.sendPDF(eventNotification);
    }
}