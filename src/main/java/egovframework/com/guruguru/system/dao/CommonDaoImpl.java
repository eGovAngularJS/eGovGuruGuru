package egovframework.com.guruguru.system.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import egovframework.rte.psl.dataaccess.EgovAbstractMapper;

public class CommonDaoImpl extends EgovAbstractMapper implements CommonDao {

	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public <E> List<E> selectList(String statementId, Object args) {
		return sqlSession.selectList(statementId, args);
	}
	
	public <T> T selectObject(String statementId, Object args) {
		return sqlSession.selectOne(statementId, args);
	}
	
	public int insertObject(String statementId, Object args) {
		return sqlSession.insert(statementId, args);
	}
	
	public int updateObject(String statementId, Object args) {
		return sqlSession.update(statementId, args);
	}
	
	public int deleteObject(String statementId, Object args) {
		return sqlSession.delete(statementId, args);
	}
}
