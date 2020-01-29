package com.test.service;

import java.util.Map;

import com.test.domain.ScheduleVO;

public interface ScheduleService {
	
	public String getDayName(Map<String, Integer> map);
	
	public ScheduleVO getScheduleOfDayByUserno(Map<String, Integer> map);
}
