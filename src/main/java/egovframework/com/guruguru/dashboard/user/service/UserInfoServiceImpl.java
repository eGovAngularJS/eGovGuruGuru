package egovframework.com.guruguru.dashboard.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.com.guruguru.dashboard.user.vo.UserInfo;
import egovframework.com.guruguru.system.dao.CommonDao;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

@Service
public class UserInfoServiceImpl extends AbstractServiceImpl implements UserInfoService {

	@Autowired
	private CommonDao commonDao;
	
	public List<UserInfo> retrieveUserInfoList() {
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<UserInfo> list = commonDao.selectList("userInfo.selectUserInfoList", map);
		
		return list;
	}
}
