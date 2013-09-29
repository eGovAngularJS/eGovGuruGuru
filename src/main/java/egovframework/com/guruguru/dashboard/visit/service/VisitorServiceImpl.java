package egovframework.com.guruguru.dashboard.visit.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.com.guruguru.dashboard.visit.vo.Visitor;
import egovframework.com.guruguru.system.dao.CommonDao;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

@Service
public class VisitorServiceImpl extends AbstractServiceImpl implements VisitorService {

	@Autowired
	private CommonDao commonDao;
	
	public List<Visitor> retrieveVisitorList() {
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<Visitor> list = commonDao.selectList("visitor.selectVisitorList", map);
		
		return list;
	}
}
