package egovframework.com.guruguru.dashboard.state.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Splitter;

@Controller
@RequestMapping("/state")
public class StateController {

	@RequestMapping("/getStateInfo")
	@ResponseBody
	public String getStateInfo() {
		String os = System.getProperty("os.name");
		
		if (os.equalsIgnoreCase("linux")) {
			getMemroyInfo(null);
		}
		
//		System.out.println(System.getProperty("os.name"));
//		
//		String line = "";
//		
//		try {
//			ProcessBuilder pb = new ProcessBuilder("/bin/sh", "-c", "vmstat");
//			Process proc = pb.start();
//			
//			BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
//			
//			while ((line = br.readLine()) != null) {
//				Iterator<String> it = Splitter.on(' ').omitEmptyStrings().split(line).iterator();
//				
//				while (it.hasNext()) {
//					System.out.println(it.next());
//				}
//			}
//			
//			br.close();
//			
//		} catch (IOException ioe) {
//			throw new RuntimeException(ioe.getMessage());
//		}
		
		return "sample";
	}
	
	private void getMemroyInfo(Map<String, Object> map) {
		String memoryInfo = executeProcess("/bin/sh", "-c", "free");
		
		int i = 0;
		
		Iterator<String> it = Splitter.on(' ').omitEmptyStrings().split(memoryInfo).iterator();
		
		while (it.hasNext()) {
			System.out.println(it.next());
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
