package com.asset.service;

import com.asset.utils.Email;
import org.springframework.stereotype.Service;

@Service
public interface IMailService {
	 /**
	  * 纯文本
	  * @Author  科帮网
	  * @param mail
	  * @throws Exception  void
	  * @Date	2017年7月20日
	  * 更新日志
	  * 2017年7月20日  科帮网 首次创建
	  */
	 void send(Email mail);
	 /**
	  * 富文本
	  * @Author  科帮网
	  * @param mail
	  * @throws Exception  void
	  * @Date	2017年7月20日
	  * 更新日志
	  * 2017年7月20日  科帮网 首次创建
	  *
	  */
	  void sendHtml(Email mail) throws Exception;
	 /**
	  * 模版发送 freemarker
	  * @Author  科帮网
	  * @param mail
	  * @throws Exception  void
	  * @Date	2017年7月20日
	  * 更新日志
	  * 2017年7月20日  科帮网 首次创建
	  *
	  */
	  void sendFreemarker(Email mail) throws Exception;
	 /**
	  * 模版发送 thymeleaf(弃用、需要配合模板)
	  * @Author  科帮网
	  * @param mail
	  * @throws Exception  void
	  * @Date	2017年7月20日
	  * 更新日志
	  * 2017年7月20日  科帮网 首次创建
	  *
	  */
	 void sendThymeleaf(Email mail) throws Exception;
}
