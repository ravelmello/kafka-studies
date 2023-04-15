package com.example.kafkastudies;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

@Component
public class KafkaSimpleConsumer {

    static PropertiesKafkaGeneral propertiesKafkaGeneral;

    public KafkaSimpleConsumer(PropertiesKafkaGeneral propertiesKafkaGeneral) {
        KafkastudiesApplication.propertiesKafkaGeneral = propertiesKafkaGeneral;
    }

    static void consume(Properties props, List<String> topics) {
        KafkaConsumer consumer = new KafkaConsumer(props);
        consumer.subscribe(topics);

        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(20);
                for(ConsumerRecord<String, String> record : records) {
                    System.out.println(String.format("Topic: %s, Partition: %s, Key: %s, Value: %s",
                            record.topic(), record.partition(), record.key(), record.value()));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage() + e.getCause());
        } finally {
            consumer.close();
        }

    }
}
