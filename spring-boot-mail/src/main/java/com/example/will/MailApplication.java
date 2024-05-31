package com.example.will;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 啟動類
 * 創建者 爪哇筆記 https://blog.52itstyle.vip
 * 創建時間	2017年7月19日
 * API: http://localhost:8080/swagger-ui.html
 *
 * 啟動 java -jar spring-boot-mail.jar --server.port=8080
 * linux 下 後台啟動  nohup java -jar spring-boot-mail.jar --server.port=8080 &
 *
 * 2018-10-10 更新說明：
 * 1）原噹噹 Dubbox 2.8.4 替換為 Dubbo 2.6.2
 * 2）原spring-context-dubbo.xml 配置 替換為 dubbo-spring-boot-starter 2.0.0
 * 3）原 zkclient 0.6 替換為 curator-recipes 4.0.1
 * 4）原 zookeeper 3.4.6 升級為 zookeeper 3.5.3
 *
 * 2019-09-08 更新說明：
 * 1）Dubbo 升級為 Apache 組織
 * 2）Dubbo 2.6.2 升級為  Apache Dubbo 2.7.3
 * 3）原 com.alibaba.spring.boot dubbo-spring-boot-starter 2.0.0 升級為  org.apache.dubbo dubbo-spring-boot-starter 2.7.3
 * 2024-03-12 更新說明：
 * 改使用eureka 作為註冊中心
 */
@SpringBootApplication
@EnableScheduling
//必須配置包掃描、否則Dubbo無法註冊服務
//@DubboComponentScan(basePackages = "com.itstyle.mail.service.impl")
public class MailApplication  {
	private static final Logger logger = LoggerFactory.getLogger(MailApplication.class);

	public static void main(String[] args){
		SpringApplication.run(MailApplication.class, args);
		logger.info("郵件服務項目啟動");
	}
}