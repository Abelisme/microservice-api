package com.example.will.common.queue;

import com.example.will.common.model.Email;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.Enumeration;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 郵件隊列
 * 創建者 Will
 */
@Service
public class MailQueue implements QueueConstant {
	 //隊列大小
    static final int QUEUE_MAX_SIZE = 1000;

//    static BlockingQueue<Email> blockingQueue = new LinkedBlockingQueue<Email>(QUEUE_MAX_SIZE);

    /**
     * 私有的默認構造子，保證外界無法直接實例化
     */
    private MailQueue(){};
    /**
     * 類級的內部類，也就是靜態的成員式內部類，該內部類的實例與外部類的實例
     * 沒有綁定關係，而且只有被調用到才會裝載，從而實現了延遲加載
     */
    private static class SingletonHolder{
        /**
         * 靜態初始化器，由JVM來保證線程安全
         */
		private static MailQueue queue = new MailQueue();
    }
    //單例隊列
    public static MailQueue getMailQueue(){
        return SingletonHolder.queue;
    }
    //生產入隊
    public void produce(Email mail, JmsTemplate jmsTemplate) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String messageContent = mapper.writeValueAsString(mail);
            jmsTemplate.convertAndSend(INSTANCE_NAME, messageContent);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
    //消費出隊
    public Email consume(JmsTemplate jmsTemplate) {
        return (Email) jmsTemplate.receiveAndConvert(INSTANCE_NAME);
    }
    // 獲取隊列大小
    public int size(JmsTemplate jmsTemplate) {
        // ActiveMQ 不支持直接獲取隊列大小，需要使用 JmsTemplate.browse 方法來遍歷隊列
        // 這可能會對性能有影響，請謹慎使用
        AtomicInteger counter = new AtomicInteger();
        jmsTemplate.browse("mailQueue", (session, browser) -> {
            Enumeration<?> enumeration = browser.getEnumeration();
            while (enumeration.hasMoreElements()) {
                enumeration.nextElement();
                counter.incrementAndGet();
            }
            return null;
        });
        return counter.get();
    }
}
