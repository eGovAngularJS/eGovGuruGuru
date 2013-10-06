package egovframework.com.guruguru.dashboard.visit.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.com.guruguru.system.dao.CommonDao;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

@Service
public class VisitorServiceImpl extends AbstractServiceImpl implements VisitorService {

	@Autowired
	private CommonDao commonDao;
	
	/**
	 * {@inheritDoc}
	 */
	public List<Map<String, Object>> retrieveVistCountInfo(Map param) {
		List<Map<String, Object>> visitCountList = commonDao.selectList("selectVisitCountInfo", param);
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		
		for (Map<String, Object> visitMap : visitCountList) {
			Map<String, Object> map = new HashMap<String, Object>();
			
			map.put(String.valueOf(visitMap.get("period")), visitMap.get("cnt"));
			
			resultList.add(map);
		}
		
		return resultList;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Map<String, Object> retrieveAgeCountInfo(Map param) {
		return commonDao.selectObject("visitor.selectAgeCountInfo", param);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Map<String, Object> retrieveGenderCountInfo(Map param) {
		List<Map<String, Object>> genderCountList = commonDao.selectList("visitor.selectGenderCntInfo", param);
		List<Map<String, Object>> genderUniqueCountList = commonDao.selectList("visitor.selectGenderUniqueCntInfo", param);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		for (Map<String, Object> genderMap : genderCountList) {
			if (genderMap.containsValue("Y")) {
				resultMap.put("womenCount", genderMap.get("cnt"));
			} else {
				resultMap.put("menCount", genderMap.get("cnt"));
			}
		}
		
		for (Map<String, Object> genderUniqueMap : genderUniqueCountList) {
			if (genderUniqueMap.containsValue("Y")) {
				resultMap.put("womenUniqueCount", genderUniqueMap.get("cnt"));
			} else {
				resultMap.put("menUniqueCount", genderUniqueMap.get("cnt"));
			}
		}
		
		return resultMap;	
	}
	
}
