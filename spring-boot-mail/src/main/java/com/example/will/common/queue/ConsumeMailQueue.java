package com.example.will.common.queue;

import com.example.will.common.model.Email;
import com.example.will.service.IMailService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
/**
 * 消費隊列
 * 創建者 爪哇筆記
 * 創建時間	2017年8月4日
 */
@Component
public class ConsumeMailQueue implements QueueConstant{

	private static final Logger logger = LoggerFactory.getLogger(ConsumeMailQueue.class);

//	@Autowired
	private IMailService mailService;

	public ConsumeMailQueue(IMailService mailService) {
		this.mailService = mailService;
	}

//	@PostConstruct
//	public void startThread() {
//		ExecutorService e = Executors.newFixedThreadPool(2);// 兩個大小的固定線程池
//		e.submit(new PollMail(mailService));
//		e.submit(new PollMail(mailService));
//	}
//
//	class PollMail implements Runnable {
//		IMailService mailService;
//
//		public PollMail(IMailService mailService) {
//			this.mailService = mailService;
//		}
//
//		@Override
//		public void run() {
//			while (true) {
//				try {
////					System.out.println("xxx");
//					Email mail = MailQueue.getMailQueue().consume();
//					if (mail != null) {
//						logger.info("剩餘郵件總數:{}",MailQueue.getMailQueue().size());
//						mailService.send(mail);
//					}
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	}
	@JmsListener(destination = INSTANCE_NAME)
	public void receiveMessage(String messageContent) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			Email mail = mapper.readValue(messageContent, Email.class);
			// 處理接收到的郵件對象
			System.out.println("Received email from: " + mail.getEmail());
			if (mail != null) {
//				logger.info("剩餘郵件總數:{}", MailQueue.getMailQueue().size(jmsTemplate));
				mailService.send(mail);
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@PreDestroy
	public void stopThread() {
		logger.info("destroy");
	}
}
