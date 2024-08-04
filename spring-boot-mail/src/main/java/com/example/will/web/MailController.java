package com.example.will.web;

import com.example.will.common.model.Email;
import com.example.will.common.model.Result;
import com.example.will.service.IMailService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags ="郵件管理")
@RestController
@RequestMapping("/mail")
public class MailController {

//	@Reference(version = "1.0.0")
	@Autowired
	private IMailService mailService;

    /**
     * 簡單測試
     * @param mail
     * @return
     */
	@PostMapping("send")
	public Result send(Email mail) {
		try {
			mailService.sendFreemarker(mail);
		} catch (Exception e) {
			e.printStackTrace();
			return  Result.error();
		}
		return  Result.ok();
	}

    /**
     * 列表
     * @param mail
     * @return
     */
	@PostMapping("list")
	public Result list(Email mail) {
		return mailService.listMail(mail);
	}

    /**
     * 隊列測試
     * @param mail
     * @return
     */
    @PostMapping("queue")
    public Result queue(@RequestBody Email mail) {
        try {
            mailService.sendQueue(mail);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.ok();
    }
}
