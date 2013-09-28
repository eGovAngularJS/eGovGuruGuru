package egovframework.com.guruguru.dashboard.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.com.guruguru.dashboard.user.service.UserInfoService;
import egovframework.com.guruguru.dashboard.user.vo.UserInfo;

@Controller
@RequestMapping("/user")
public class UserInfoController {

	@Autowired
	private UserInfoService userInfoService;
	
	@RequestMapping("/retrieveUserInfoList")
	@ResponseBody
	public List<UserInfo> retrieveUserInfoList() {
		List<UserInfo> list = userInfoService.retrieveUserInfoList();
		
		return list;
	} 
}
