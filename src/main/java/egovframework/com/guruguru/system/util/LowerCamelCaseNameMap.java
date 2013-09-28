package egovframework.com.guruguru.system.util;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import com.google.common.base.CaseFormat;

public class LowerCamelCaseNameMap extends LinkedHashMap<String, Object> {

	private static final long serialVersionUID = 1L;

	public Object put(String key, Object value) {
		return super.put(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, key), value);
	}
	
	public void putAll(Map<? extends String, ? extends Object> m) {
		for (Iterator<? extends Map.Entry<? extends String, ? extends Object>> i = m.entrySet().iterator(); i.hasNext(); ) {
			Map.Entry<? extends String, ? extends Object> e = i.next();
			super.put(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, e.getKey()), e.getValue());
		}
	}
}
