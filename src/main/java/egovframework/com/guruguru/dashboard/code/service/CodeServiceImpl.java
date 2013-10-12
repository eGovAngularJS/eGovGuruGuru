package egovframework.com.guruguru.dashboard.code.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.com.guruguru.system.dao.CommonDao;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

@Service
public class CodeServiceImpl extends AbstractServiceImpl implements CodeService {

	@Autowired
	private CommonDao commonDao;
	
	public List<String> retrieveDateCodeList(Map param) {
		return commonDao.selectList("code.selectDateCodeInfo", param);
	}
}
