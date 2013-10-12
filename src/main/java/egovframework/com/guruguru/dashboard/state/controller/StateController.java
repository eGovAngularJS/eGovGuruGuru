package egovframework.com.guruguru.dashboard.state.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

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
		System.out.println(System.getProperty("os.name"));
		
		String line = "";
		
		try {
			ProcessBuilder pb = new ProcessBuilder("/bin/sh", "-c", "vmstat");
			Process proc = pb.start();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			
			while ((line = br.readLine()) != null) {
				Iterator<String> it = Splitter.on(' ').omitEmptyStrings().split(line).iterator();
				
				while (it.hasNext()) {
					System.out.println(it.next());
				}
			}
		} catch (IOException ioe) {
			throw new RuntimeException(ioe.getMessage());
		}
		
		return "sample";
	}
}
