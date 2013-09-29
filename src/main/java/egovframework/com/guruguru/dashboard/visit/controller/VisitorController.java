package egovframework.com.guruguru.dashboard.visit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.com.guruguru.dashboard.visit.service.VisitorService;
import egovframework.com.guruguru.dashboard.visit.vo.Visitor;

@Controller
@RequestMapping("/visit")
public class VisitorController {

	@Autowired
	private VisitorService visitorService;
	
	@RequestMapping("/retrieveVisitorList")
	@ResponseBody
	public List<Visitor> retrieveVisitorList() {
		List<Visitor> list = visitorService.retrieveVisitorList();
		
		return list;
	}
}
