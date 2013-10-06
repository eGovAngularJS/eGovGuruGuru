package egovframework.com.guruguru.dashboard.visit.service;

import java.util.List;
import java.util.Map;

/**
 * 방문자 관련 정보를 가져오기 위한 클래스.
 * <p>
 * 기간에 따른 지역, 나이, 성별 방문자 정보를 조회한다.
 * 
 * @author 
 *
 */
public interface VisitorService {
	
	/**
	 * 기간에 따른 방문자 수를 조회한다.
	 * 
	 * @param param
	 * @return
	 */
	List<Map<String, Object>> retrieveVistCountInfo(Map param);
	
	/**
	 * 나이별 방문자 수를 조회한다.
	 * 
	 * @param param
	 * @return
	 */
	Map<String, Object> retrieveAgeCountInfo(Map param);

	/**
	 * 성별 방문자 수를 조회한다.
	 * 
	 * @param param
	 * @return
	 */
	Map<String, Object> retrieveGenderCountInfo(Map param);
	
}
