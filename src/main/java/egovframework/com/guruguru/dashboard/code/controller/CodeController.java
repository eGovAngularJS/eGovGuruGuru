package egovframework.com.guruguru.dashboard.code.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.com.guruguru.dashboard.code.service.CodeService;

@Controller
@RequestMapping("/code")
public class CodeController {

	@Autowired
	private CodeService codeService;
	
	@RequestMapping("/retrieveDateCodeList")
	@ResponseBody
	public List<String> retrieveDateCodeList(@RequestParam(required=false) String year,
			@RequestParam(required=false) String month) {
		Map<String, Object> param = new HashMap<String, Object>();
		
		param.put("yyyy", year);
		param.put("mm", month);
		
		return codeService.retrieveDateCodeList(param);
	}
}
