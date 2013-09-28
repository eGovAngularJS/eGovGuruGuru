package egovframework.com.guruguru.system.dao;

import java.util.List;

public interface CommonDao {

	<E> List<E> selectList(String statementId, Object args);
	
	<T> T selectObject(String statementId, Object args);
	
	int insertObject(String statementId, Object args);
	
	int updateObject(String statementId, Object args);
	
	int deleteObject(String statementId, Object args);
}
