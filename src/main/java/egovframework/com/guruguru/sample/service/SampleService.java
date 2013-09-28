package egovframework.com.guruguru.sample.service;

import java.util.List;

import egovframework.com.guruguru.sample.vo.CmmnCode;

public interface SampleService {

	List<CmmnCode> getCodeSampleList(String codeId, String codeName);
	
}
