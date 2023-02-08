package com.unicard.rabbit;

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
    	ObjectMapper mapper = new ObjectMapper();
    	RequestBean bean = null;
		try {
			bean = mapper.readValue(message, RequestBean.class);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Long result = service.save(bean.getList(), bean.getMessageId());
		Long result = bean.getMessageId();
      	producer.sendMessage(result);

        LOGGER.info(String.format("Received message -> %s", result.toString()));
    }
}