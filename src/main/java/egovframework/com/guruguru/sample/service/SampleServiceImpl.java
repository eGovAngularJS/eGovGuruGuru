package egovframework.com.guruguru.sample.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.com.guruguru.sample.vo.CmmnCode;
import egovframework.com.guruguru.system.dao.CommonDao;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

@Service
public class SampleServiceImpl extends AbstractServiceImpl implements SampleService {

	@Autowired
	private CommonDao commonDao;

	public List<CmmnCode> getCodeSampleList(String codeId, String codeName) {
		Map<String, String> args = new HashMap<String, String>();
		
		args.put("codeId", codeId);
		args.put("codeName", codeName);
		
		List<CmmnCode> list = commonDao.selectList("sample.selectCodeSampleList", args);
				
		return list;
	}
}