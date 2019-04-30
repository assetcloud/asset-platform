package com.asset.utils;

import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class Parametermap extends HashMap implements Map {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Map map = null;
	HttpServletRequest request;

	public Parametermap(HttpServletRequest request) {
		this.request = request;
		try {
			request.setCharacterEncoding("UTF-8");

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Map<String, String[]> properties = request.getParameterMap();
		Map returnMap = new HashMap<>();

		Iterator<Entry<String, String[]>> entrys = properties.entrySet().iterator();
		Entry entry;
		while (entrys.hasNext()) {
			String name="";
			String val="";
			entry = entrys.next();
			name = (String) entry.getKey();
			Object value = entry.getValue();
			if (value==null) {
				val="";
			}else if(value instanceof String[]) {
				String [] values=(String[]) value;
				for (int i = 0; i < values.length; i++) {
					val = values[i]+","+val;
					
				}
				val=val.substring(0,val.length()-1);
			}else {
				val=value.toString();
			}
			returnMap.put(name, val);
		}
		returnMap.put("SessionId", request.getSession().getId());
		map = returnMap;
	}

	@Override
	public Object get(Object key) {
		return map.get(key);
	}

	public Object put(Object key, Object val) {
		if (val == null) {
			return map.put(key, "");
		}
		return map.put(key, val);
	}

	public Object putObject(Object key, Object val) {
		return map.put(key, val);
	}
	@Override
	public Object remove(Object key) {
		return map.remove(key);
	}
	@Override
	public void clear() {
		map.clear();
	}
	@Override
	public boolean containsKey(Object key) {
		return map.containsKey(key);
	}
	
	
	@Override
	public boolean containsValue(Object value) {
		return map.containsValue(value);
	}
	@Override
	public Set entrySet() {
		return map.entrySet();
	}
	
	@Override
	public boolean isEmpty() {
		return map.isEmpty();
	}
	@Override
	public Set keySet() {
		return map.keySet();
	}
	@Override
	public void putAll(Map m) {
		map.putAll(m);
	}
	@Override
	public int size() {
		return map.size();
	}
	@Override
	public Collection values() {
		return map.values();
	}
}
