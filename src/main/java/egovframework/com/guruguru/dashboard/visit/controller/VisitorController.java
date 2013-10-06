package egovframework.com.guruguru.dashboard.visit.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.com.guruguru.dashboard.visit.service.VisitorService;

@Controller
@RequestMapping("/visit")
public class VisitorController {

	@Autowired
	private VisitorService visitorService;
	
	@RequestMapping("/retrieveAgeInfo")
	@ResponseBody
	public Map<String, Object> retrieveAgeInfo(@RequestParam(defaultValue="2013") String year,
			@RequestParam(required=false) String month, 
			@RequestParam(required=false) String day) {
		Map<String, Object> param = new HashMap<String, Object>();
		
		param.put("yyyy", year);
		param.put("mm", month);
		param.put("dd", day);
		
		return visitorService.retrieveAgeCountInfo(param);
	}
	
	@RequestMapping("/retrieveGenderInfo")
	@ResponseBody
	public Map<String, Object> retrieveGenderInfo(@RequestParam(defaultValue="2013") String year,
			@RequestParam(required=false) String month, 
			@RequestParam(required=false) String day) {
		Map<String, Object> param = new HashMap<String, Object>();
		
		param.put("yyyy", year);
		param.put("mm", month);
		param.put("dd", day);
		
		return visitorService.retrieveGenderCountInfo(param);
	}
	
}
