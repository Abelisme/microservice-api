package com.example.will.service.impl;

import com.example.will.common.dynamicquery.DynamicQuery;
import com.example.will.common.model.Email;
import com.example.will.common.util.Constants;
import com.example.will.entity.OaEmail;
import com.example.will.repository.MailRepository;
import com.example.will.service.IMailService;
import com.example.will.common.model.Result;
import com.example.will.common.queue.MailQueue;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.util.ResourceUtils;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.will.common.queue.QueueConstant.JMS_TEMPLATE;

//@Service(version = "1.0.0")
@Service
public class MailServiceImpl implements IMailService {
	private static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);
	@Autowired
	private DynamicQuery dynamicQuery;
	@Autowired
	private MailRepository mailRepository;
	@Autowired
	private JavaMailSender mailSender;//執行者
	@Autowired
	public Configuration configuration;//freemarker
	@Autowired
	private SpringTemplateEngine  templateEngine;//thymeleaf
//	@Value("${spring.mail.username}")
	@Value("345849402@qq.com")
	public String USER_NAME;//發送者
	@Value("http://localhost:8083")
	public String PATH;//發送者

	@Autowired
	@Qualifier(JMS_TEMPLATE)
	private JmsTemplate jmsTemplate;


	@Autowired
    private RedisTemplate<String, String> redisTemplate;

	static {
		 System.setProperty("mail.mime.splitlongparameters","false");
	}

	@Override
	public void send(Email mail) throws Exception {
		logger.info("發送郵件：{}",mail.getContent());
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(USER_NAME);
		message.setTo(mail.getEmail());
		message.setSubject(mail.getSubject());
		message.setText(mail.getContent());
		mailSender.send(message);
	}

	@Override
	public void sendHtml(Email mail) throws Exception {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		//這裏可以自定義發信名稱比如：爪哇筆記
		helper.setFrom(USER_NAME,"爪哇筆記");
		helper.setTo(mail.getEmail());
		helper.setSubject(mail.getSubject());
		helper.setText(
				"<html><body><img src=\"cid:springcloud\" ></body></html>",
				true);
		// 發送圖片
		File file = ResourceUtils.getFile("classpath:static"
				+ Constants.SF_FILE_SEPARATOR + "image"
				+ Constants.SF_FILE_SEPARATOR + "springcloud.png");
		helper.addInline("springcloud", file);
		// 發送附件
		file = ResourceUtils.getFile("classpath:static"
				+ Constants.SF_FILE_SEPARATOR + "file"
				+ Constants.SF_FILE_SEPARATOR + "關注科幫網獲取更多源碼.zip");
		helper.addAttachment("科幫網", file);
		mailSender.send(message);
	}

	@Override
	public void sendFreemarker(Email mail) throws Exception {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		//這裏可以自定義發信名稱比如：xxx
		helper.setFrom(USER_NAME,"xxx");
		helper.setTo(mail.getEmail());
		helper.setSubject(mail.getSubject());
		Map<String, Object> model = new HashMap<>();
		model.put("mail", mail);
		model.put("path", PATH);
		Template template = configuration.getTemplate(mail.getTemplate());
		String text = FreeMarkerTemplateUtils.processTemplateIntoString(
				template, model);
		helper.setText(text, true);
		mailSender.send(message);
		mail.setContent(text);
		OaEmail oaEmail = new OaEmail(mail);
		mailRepository.save(oaEmail);
	}
	//棄用
	@Override
	public void sendThymeleaf(Email mail) throws Exception {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom(USER_NAME);
		helper.setTo(mail.getEmail());
		helper.setSubject(mail.getSubject());
		Context context = new Context();
		context.setVariable("email", mail);
		String text = templateEngine.process(mail.getTemplate(), context);
		helper.setText(text, true);
		mailSender.send(message);
	}

	@Override
	public void sendQueue(Email mail) {
		MailQueue.getMailQueue().produce(mail, jmsTemplate);
	}
	@Override
	public void sendRedisQueue(Email mail) throws Exception {
		redisTemplate.convertAndSend("mail", mail);
	}

	@Override
	public Result listMail(Email mail) {
		List<OaEmail> list = mailRepository.findAll();
		return Result.ok(list);
	}
}
