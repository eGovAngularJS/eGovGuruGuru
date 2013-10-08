package egovframework.com.guruguru.dashboard.visit.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.com.guruguru.dashboard.visit.service.VisitorService;

/**
 * 방문자 관련 정보를 가져오기 위한 클래스.
 * <p>
 * 지역, 나이, 성별 방문자 정보를 조회한다.
 * 
 * @author 
 *
 */
@Controller
@RequestMapping("/visit")
public class VisitorController {

	@Autowired
	private VisitorService visitorService;
	
	/**
	 * 기간에 따른 방문자 수를 조회한다.
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	@RequestMapping("/retrieveVisitInfo")
	@ResponseBody
	public Map<String, Object> retrieveVisitInfo(@RequestParam(defaultValue="2013") String year,
			@RequestParam(required=false) String month, 
			@RequestParam(required=false) String day) {
		Map<String, Object> param = new HashMap<String, Object>();
		
		param.put("yyyy", year);
		param.put("mm", month);
		param.put("dd", day);
				
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap.put("visit", visitorService.retrieveVistCountInfo(param));
		
		param.put("option", "unique");
		
		resultMap.put("unique", visitorService.retrieveVistCountInfo(param));
		
		return resultMap;
	}
	
	/**
	 * 지역별 방문자 수를 조회한다.
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	@RequestMapping("/retrieveAreaInfo")
	@ResponseBody
	public Map<String, Object> retrieveAreaInfo(@RequestParam(defaultValue="2013") String year,
			@RequestParam(required=false) String month, 
			@RequestParam(required=false) String day) {
		Map<String, Object> param = new HashMap<String, Object>();
		
		param.put("yyyy", year);
		param.put("mm", month);
		param.put("dd", day);
		
		return visitorService.retrieveAreaCountInfo(param);
	}
	
	/**
	 * 나이별 방문자 수를 조회한다.
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
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
	
	/**
	 * 성별 방문자 수를 조회한다.
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
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
