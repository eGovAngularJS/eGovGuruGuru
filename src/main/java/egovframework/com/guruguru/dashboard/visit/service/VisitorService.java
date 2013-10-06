package egovframework.com.guruguru.dashboard.visit.service;

import java.util.Map;

public interface VisitorService {
	
	Map<String, Object> retrieveAgeCountInfo(Map param);

	Map<String, Object> retrieveGenderCountInfo(Map param);
	
}
