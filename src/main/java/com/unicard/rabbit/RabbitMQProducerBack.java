package com.unicard.rabbit;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducerBack {

	@Value("${rabbitmq.exchange4.name}")
	private String exchange4;

	@Value("${rabbitmq.routing4.key}")
	private String routingKey4;

	private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQProducerBack.class);

	private RabbitTemplate rabbitTemplate;

	public RabbitMQProducerBack(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	public void sendMessage(Long message){
		LOGGER.info(String.format("Message sent -> %s", message));
		rabbitTemplate.convertAndSend(exchange4, routingKey4, message);
	}
}