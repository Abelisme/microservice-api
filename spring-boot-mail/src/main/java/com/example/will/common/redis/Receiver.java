package com.example.will.common.redis;

import com.example.will.common.model.Email;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.will.service.IMailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class Receiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);
//    @Reference(check = false)
	@Autowired
	private IMailService mailService;
    private CountDownLatch latch;

    @Autowired
    public Receiver(CountDownLatch latch) {
        this.latch = latch;
    }

    public void receiveMessage(String message) {
        LOGGER.info("接收email消息 <{}>",message);
        if(message == null){
            LOGGER.info("接收email消息 <" + null + ">");
        }else {
        	ObjectMapper mapper = new ObjectMapper();  
			try {
				Email email = mapper.readValue(message, Email.class);
				mailService.send(email);
				LOGGER.info("接收email消息内容 <{}>",email.getContent());
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
        latch.countDown();
    }
}
