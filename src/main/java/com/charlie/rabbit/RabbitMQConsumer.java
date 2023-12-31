package com.charlie.rabbit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RabbitMQConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQConsumer.class);

    @Autowired
    private RabbitMQProducerBack producer;
//    @Autowired
//    private ArchiveService service;

    @RabbitListener(queues = {"${rabbitmq.queue3.name}"})
    public void consume(String message){
    	if (message == null) return;

    	ObjectMapper mapper = new ObjectMapper();
    	RequestBean bean = null;
		try {
			bean = mapper.readValue(message, RequestBean.class);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String jsonStr = null;
		try {
			jsonStr = mapper.writeValueAsString(bean);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     	producer.sendMessage(jsonStr);

        LOGGER.info(String.format("Received message -> %s", jsonStr));
    }
}