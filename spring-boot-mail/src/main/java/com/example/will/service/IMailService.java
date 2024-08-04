package com.example.will.service;

import com.example.will.common.model.Email;
import com.example.will.common.model.Result;

public interface IMailService {
	 /**
	  * 純文本
	  * @Author  爪哇筆記
	  * @param mail
	  * @throws Exception  void
	  * @Date	2017年7月20日
	  * 更新日誌
	  * 2017年7月20日  爪哇筆記 首次創建
	  */
	 void send(Email mail) throws Exception;
	 /**
	  * 富文本
	  * @Author  爪哇筆記
	  * @param mail
	  * @throws Exception  void
	  * @Date	2017年7月20日
	  * 更新日誌
	  * 2017年7月20日  爪哇筆記 首次創建
	  *
	  */
	  void sendHtml(Email mail) throws Exception;
	 /**
	  * 模版發送 freemarker
	  * @Author  爪哇筆記
	  * @param mail
	  * @throws Exception  void
	  * @Date	2017年7月20日
	  * 更新日誌
	  * 2017年7月20日  爪哇筆記 首次創建
	  *
	  */
	  void sendFreemarker(Email mail) throws Exception;
	 /**
	  * 模版發送 thymeleaf(棄用、需要配合模板)
	  * @Author  爪哇筆記
	  * @param mail
	  * @throws Exception  void
	  * @Date	2017年7月20日
	  * 更新日誌
	  * 2017年7月20日  爪哇筆記 首次創建
	  *
	  */
	 void sendThymeleaf(Email mail) throws Exception;
	 /**
	  * 隊列
	  * @Author  科幫網
	  * @param mail
	  * @throws Exception  void
	  * @Date	2017年8月4日
	  * 更新日誌
	  * 2017年8月4日  爪哇筆記 首次創建
	  *
	  */
	 void sendQueue(Email mail) throws Exception;
	 /**
	  * Redis 隊列
	  * @Author  爪哇筆記
	  * @param mail
	  * @throws Exception  void
	  * @Date	2017年8月13日
	  * 更新日誌
	  * 2017年8月13日  科幫網 首次創建
	  *
	  */
	 void sendRedisQueue(Email mail) throws Exception;

    /**
     * 郵件列表
     * @param mail
     * @return
     */
	 Result listMail(Email mail);
}
