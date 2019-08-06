package com.asset.service.impl;

import com.asset.bean.Mail;
import com.asset.mapper.MailMapper;
import com.asset.service.IMailService;
import com.asset.utils.Email;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;


@Component
public class MailServiceImpl extends ServiceImpl<MailMapper, Mail> implements IMailService {
	private static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

	@Autowired
	private MailMapper mailRepository;

	@Autowired
	private JavaMailSender mailSender;//执行者

	@Autowired
	public Configuration configuration;//freemarker

	@Autowired
	private SpringTemplateEngine templateEngine;//thymeleaf

	@Value("${spring.mail.username}")
	public String USER_NAME;//发送者
	@Value("${server.path}")
	public String PATH;//发送者


	@Autowired
    private RedisTemplate<String, String> redisTemplate;

	static {
		 System.setProperty("mail.mime.splitlongparameters","false");
	}

	@Override
	public void send(Email mail) {
		logger.info("发送邮件：{}",mail.getContent());
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
		//这里可以自定义发信名称比如：爪哇笔记
		helper.setFrom(USER_NAME,"资产云开放协同中心");
		helper.setTo(mail.getEmail());
		helper.setSubject(mail.getSubject());
		helper.setText(
				"<!doctype html>\n" +
						"<html lang=\"zh-cmn-Hans\">\n" +
						"<head>\n" +
						"    <meta charset=\"UTF-8\">\n" +
						"    <meta name=\"renderer\" content=\"webkit\" />\n" +
						"    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\" />\n" +
						"    <title>Document</title>\n" +
						"</head>\n" +
						"<body>\n" +
						"    <div class=\"container\"\n" +
						"         style=\" width: 800px;\n" +
						"         height: auto;\n" +
						"         margin: 0 auto;\n" +
						"         border: 1px #dddddd solid;\n" +
						"         border-top: 4px #3498db solid;\n" +
						"         font: 14px Microsoft Yahei;\n" +
						"         color: #333;\">\n" +
						"        <div class=\"main\" style=\"padding: 0 15px;\">\n" +
						"            <div style=\"\n" +
						"                 border: 1px #f39d12 dashed;\n" +
						"                 background-color: #fffdf4;\n" +
						"                 margin: 20px 0;\n" +
						"                 border-radius: 6px;\">\n" +
						"                <table width=\"100%\" style=\"border: none\">\n" +
						"                    <tr>\n" +
						"                        <td style=\"width: 20%; text-align: center; padding-top: 25px\">\n" +
						"                            <img src=\"${path}/image/icon_email_prompt.png\" alt=\"\">\n" +
						"                        </td>\n" +
						"                        <td colspan=\"2\" style=\"font-size: 18px; line-height: 1.6;padding-top: 30px\">\n" +
						"                            <div style=\"text-indent: 36px; padding-right: 24px\">\n" +
						"                                 ${mail.content}\n" +
						"                            </div>\n" +
						"                        </td>\n" +
						"                    </tr>\n" +
						"                </table>\n" +
						"            </div>\n" +
						"        </div>\n" +
						"        <div class=\"footer\" style=\"margin: auto;\n" +
						"                            padding: 15px 0 15px 15px;\n" +
						"                            background-color: #fafafa;\n" +
						"                            border-top: 1px #ddd solid;\n" +
						"                            color: #333;\n" +
						"                            height: auto;\n" +
						"                            zoom: 1;\n" +
						"                            overflow: auto;\">\n" +
						"            <table style=\"width: 100%\">\n" +
						"                <tr>\n" +
						"                    <td style=\"width: 60%\">\n" +
						"                        <h4 style=\"font-size: 16px;\n" +
						"                             margin: 10px 0;\">\n" +
						"                          科帮网科技服务有限公司\n" +
						"                        </h4>\n" +
						"                        <p>\n" +
						"                            公司地址：\n" +
						"                            <b>宇宙银河系太阳系地球村88号</b>\n" +
						"                        </p>\n" +
						"                        <p>\n" +
						"                            官方网站：\n" +
						"                            <a href=\"https://blog.52itstyle.vip\" style=\"text-decoration: none; color: #333; font-weight: bold;\">blog.52itstyle.vip</a>\n" +
						"                        </p>\n" +
						"                        <p>\n" +
						"                            服务热线：\n" +
						"                            <b>\n" +
						"                                <span style=\"display: inline-block; margin-right: 16px;\">17762018584</span>\n" +
						"                            </b>\n" +
						"                        </p>\n" +
						"                    </td>\n" +
						"                    <td style=\"text-align: right;\">\n" +
						"                        <div style=\" display: inline-block;width: 120px ; padding-top: 10px; padding-right: 200px;\">\n" +
						"                            <img src=\"${path}/image/service.jpg\" alt=\"\" style=\"vertical-align: top\">\n" +
						"                        </div>\n" +
						"                    </td>\n" +
						"                </tr>\n" +
						"            </table>\n" +
						"        </div>\n" +
						"    </div>\n" +
						"</body>\n" +
						"</html> ",
				true);
		mailSender.send(message);
	}

	@Override
	public void sendFreemarker(Email mail) throws Exception {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		//这里可以自定义发信名称比如：爪哇笔记
		helper.setFrom(USER_NAME,"资产云开放协同中心");
		helper.setTo(mail.getEmail());
		helper.setSubject(mail.getSubject());
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("mail", mail);
		model.put("path", PATH);
		Template template = configuration.getTemplate(mail.getTemplate());
		String text = FreeMarkerTemplateUtils.processTemplateIntoString(
				template, model);
		helper.setText(text, true);
		mailSender.send(message);
		mail.setContent(text);
	}

	//弃用
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
}
