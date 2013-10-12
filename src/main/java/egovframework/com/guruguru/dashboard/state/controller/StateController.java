package egovframework.com.guruguru.dashboard.state.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Splitter;

import egovframework.com.guruguru.system.util.NumberUtils;

@Controller
@RequestMapping("/state")
public class StateController {

	@RequestMapping("/getStateInfo")
	@ResponseBody
	public Map<String, Object> getRandomStateInfo() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap.put("serverLoad", NumberUtils.getRandomInt(75, 90));
		resultMap.put("usedMem", NumberUtils.getRandomInt(3350, 3500));
		resultMap.put("processLoad", NumberUtils.getRandomInt(26, 35));
		resultMap.put("diskLoad", NumberUtils.getRandomInt(57, 65));
		resultMap.put("networkLoad", NumberUtils.getRandomInt(42, 56));
		
		return resultMap;
	}
	
	@RequestMapping("/getRealStateInfo")
	@ResponseBody
	public Map<String, Object> getStateInfo() {		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String os = System.getProperty("os.name");
		
		if (os.equalsIgnoreCase("linux")) {
			getMemroyInfo(resultMap);
		}
		
		return resultMap;
	}
	
	private void getMemroyInfo(Map<String, Object> paramMap) {
		String memoryInfo = executeProcess("/bin/sh", "-c", "free");
		Iterator<String> it = Splitter.on(' ').omitEmptyStrings().split(memoryInfo).iterator();
		
		String[] memInfo = {"memTotal", "memUsed", "memFree", "memShared", "memBuffers"};		
		
		int i = 0;
		int len = memInfo.length;
		
		while (it.hasNext()) {
			String value = it.next();
			
			if (i > len) {
				if (i > 10) break;
				paramMap.put(memInfo[i - (len + 1)], value);
			}
			
			i++;
		}
	}
	
	private String executeProcess(String... cmd) {
		StringBuffer sb = new StringBuffer();
		
		try {
			ProcessBuilder pb = new ProcessBuilder(cmd);
			Process proc = pb.start();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			String line = "";
			
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			
		} catch (IOException ioe) {
			throw new RuntimeException(ioe.getMessage());
		}
		
		return sb.toString();
	}
}
