package com.example.kafkastudies;


import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class KafkaSimpleProducer {

    static void makeMessages(Properties props) {
        KafkaProducer<String, String> myProducer = new KafkaProducer<String, String>(props);
        try {
            for (int i = 0; i <= 150; i++) {
                myProducer.send(new ProducerRecord<String, String>
                        (
                                "my-topic", Integer.toString(i),
                                "Message: " + Integer.toString(i))
                );
            }
        } catch (Exception e) {
            e.getMessage();
        } finally {
            myProducer.close();
        }
    }
}

