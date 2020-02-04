package com.grade.service;

import java.util.HashMap;
import java.util.List;

public interface GradeService {
	public List<HashMap<String, String>> readData();
	
	public List<HashMap<String, Object>> calculDataBySubject();
}