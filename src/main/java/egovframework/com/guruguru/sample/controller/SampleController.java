package egovframework.com.guruguru.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.com.guruguru.sample.service.SampleService;
import egovframework.com.guruguru.sample.vo.CmmnCode;

@Controller
@RequestMapping("/sample/test")
public class SampleController {
	
	@Autowired
	private SampleService sampleService;

	@RequestMapping("/retrieveCodeList")
	@ResponseBody
	public List<CmmnCode> retrieveCodeList() {
		List<CmmnCode> list = sampleService.getCodeSampleList("", "");
		
		return list;
	}
}
