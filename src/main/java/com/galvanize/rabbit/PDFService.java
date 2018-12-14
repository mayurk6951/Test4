package com.galvanize.rabbit;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
@Service
public class PDFService {

    private final static Logger LOGGER = LoggerFactory.getLogger(PDFService.class);

    private static AmqpSenderService senderService;
    @Value("${amqp.exchange.name}")
    static
    String exchangeName;
    @Value("${amqp.verify.routing.key}")
    String verifyRoutingKey;
    @Value("${amqp.checkout.routing.key}")
    static
    String checkoutRoutingKey;

    @Autowired
    public PDFService(AmqpSenderService amqpSenderService) {
        this.senderService = amqpSenderService;
    }




    public static void sendPDF(EventNotification eventNotification) throws Exception {
        int i =1;
    if(eventNotification.getPages()>=i)
    {
        PDDocument document = PDDocument.load(new File(eventNotification.getFilelocation()));
        if (!document.isEncrypted()) {
            PDFTextStripper stripper = new PDFTextStripper();
            stripper.setStartPage(i);
            stripper.setEndPage(i+1);
            String text = stripper.getText(document);

            try {
                senderService.sendMessage(exchangeName, checkoutRoutingKey, text);
        } catch (RuntimeException e) {
            LOGGER.error("Text wasn't extracted. RabbitMQ wrong.");
            throw new Exception("Text wasn't extracted.. RabbitMQ wrong.");
        }

            i++;
        }
        document.close();
    }

    }

}
