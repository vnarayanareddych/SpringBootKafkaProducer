package com.springboot.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;

import com.springboot.model.Student;

@Service
public class KafkaSender {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Value("${spring.kafka.template.default-topic}")
	private String topicName;

	public void sendData(Student student) {
		Map<String, Object> headers = new HashMap<>();

		headers.put(KafkaHeaders.TOPIC, topicName);

		kafkaTemplate.send(new GenericMessage<Student>(student, headers));
		
		System.out.println("Data - " + student.toString() + " sent to Kafka Topic - " + topicName);
	}
}
