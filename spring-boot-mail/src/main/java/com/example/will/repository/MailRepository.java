package com.example.will.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.will.entity.OaEmail;
/**
 * 邮件管理
 * 创建者 小柒2012
 * 创建时间	2017年9月9日
 */
public interface MailRepository extends JpaRepository<OaEmail, Integer> {
	
}
