package egovframework.com.guruguru.dashboard.visit.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;

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
		List<Map<String, Object>> visitCountList = commonDao.selectList("visitor.selectVisitCountInfo", param);
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
	public Map<String, Object> retrieveAreaCountInfo(Map param) {
		List<Map<String, Object>> areaCountList = commonDao.selectList("visitor.selectAreaCountInfo", param);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		int sum = 0;
		int value = 0;
		
		int periodIndex = 0;
		int periodLength = getPeriodCount(param);
		
		int[] period = new int[periodLength];
		
		String prevArea = "";
		String tempArea = "";
		
		for (int i = 0; i < areaCountList.size(); i++) {
			Map<String, Object> areaMap = areaCountList.get(i);
			
			tempArea = (String) areaMap.get("area");
			
			if (i == 0) prevArea = tempArea;
			
			if (!tempArea.equals(prevArea)) {
				processResultMap(resultMap, prevArea, sum, period);
			
				prevArea = tempArea;
				
				period = new int[periodLength];
				sum = 0;
			}
			
			periodIndex = Integer.parseInt((String) areaMap.get("period"));
			value = (Integer) areaMap.get("cnt");
			
			period[periodIndex - 1] = value;
			sum += value;
			
			if (i == areaCountList.size() - 1) {				
				processResultMap(resultMap, tempArea, sum, period);
			}
		}
		
		return resultMap;
	}
	
	private void processResultMap(Map<String, Object> resultMap, String area, int sum, int[] period) {
		Map<String, Object> tempMap = new HashMap<String, Object>();
		
		tempMap.put("sum", sum);
		tempMap.put("period", period);
		
		resultMap.put(area, tempMap);
	}
	
	private int getPeriodCount(Map<String, Object> param) {
		String day = (String) param.get("dd");
		String month = (String) param.get("mm");
		String year = (String) param.get("yyyy");
		
		if (!Strings.isNullOrEmpty(day)) {
			return 24;
		} else if (!Strings.isNullOrEmpty(month)) {
			return getDayOfMonth(Integer.parseInt(year), Integer.parseInt(month));
		} else {
			return 12;
		}
	}
	
	private int getDayOfMonth(int year, int month) {
		DateTime dateTime = new DateTime(year, month, 1, 0, 0);
		return dateTime.dayOfMonth().getMaximumValue();
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
