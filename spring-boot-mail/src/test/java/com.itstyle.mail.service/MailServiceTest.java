package com.itstyle.mail.service;

import com.example.will.common.model.Email;
import com.example.will.service.IMailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MailServiceTest {
    @Autowired
    private IMailService mailService;

    @Test
    public void testMailServiceSendQueue() throws Exception {
        try {
            Email mail = new Email();
            mail.setContent("");
            mail.setSubject("111");
            mail.setEmail("123@gmail.com");
            mailService.sendQueue(mail);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
