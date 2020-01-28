package com.test.service;

import java.util.Map;

public interface CalendarService {
	
	public Map<String, Object> calendar(int year, int month, int ms, long userno);
}
