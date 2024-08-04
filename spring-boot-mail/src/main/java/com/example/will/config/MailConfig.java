
package com.example.will.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        // 配置郵件發送的相關屬性，這裡僅為示例，實際配置需根據你的郵件服務提供商進行設定
        mailSender.setHost("your-mail-host");
        mailSender.setPort(587);
        mailSender.setUsername("your-username");
        mailSender.setPassword("your-password");
        mailSender.setProtocol("smtp");
        mailSender.setDefaultEncoding("UTF-8");

        // 其他相關配置，如 TLS/SSL 設定等

        return mailSender;
    }
}
