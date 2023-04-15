package com.example.kafkastudies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import scala.concurrent.impl.FutureConvertersImpl;

import java.sql.Struct;
import java.util.*;

@SpringBootApplication
@Configuration
public class KafkastudiesApplication {


	static PropertiesKafkaGeneral propertiesKafkaGeneral;

	public KafkastudiesApplication(PropertiesKafkaGeneral propertiesKafkaGeneral) {
		KafkastudiesApplication.propertiesKafkaGeneral = propertiesKafkaGeneral;
	}

	public static void main(String[] args) {
		SpringApplication.run(KafkastudiesApplication.class, args);

		Properties properties = new Properties();

		properties.put("bootstrap.servers", propertiesKafkaGeneral.getHost());
		properties.put("key.serializer", propertiesKafkaGeneral.getKeySerializer());
		properties.put("value.serializer", propertiesKafkaGeneral.getValueSerializer());
		properties.put("topics", propertiesKafkaGeneral.getTopics());
		properties.put("value.deserializer", propertiesKafkaGeneral.getValueDeserializer());
		properties.put("key.deserializer", propertiesKafkaGeneral.getKeyDeserializer());

		KafkaSimpleProducer.makeMessages(properties);

		KafkaSimpleConsumer.consume(properties, propertiesKafkaGeneral.getTopics());

	}

}
