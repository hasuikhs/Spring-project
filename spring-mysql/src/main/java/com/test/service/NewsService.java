package com.test.service;

import java.util.List;
import java.util.Map;

public interface NewsService {

	public List<Map<String, Object>> getList();

	public Map<String, Object> get(int newsno);

	public void insert();
	
}
