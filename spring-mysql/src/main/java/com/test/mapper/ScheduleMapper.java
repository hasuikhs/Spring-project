package com.test.mapper;

import java.util.List;
import java.util.Map;

import com.test.domain.ScheduleVO;

public interface ScheduleMapper {
	
	public List<ScheduleVO> getList();
	
	public List<ScheduleVO> getScheduleList(int userno);
	
	public void insertSchedule(ScheduleVO schedule);
	
	public int deleteSchedule(int cno);
	
	public int updateSchedule(ScheduleVO schedule);
	
	public ScheduleVO getScheduleOfDayByUserno(Map<String, Integer> map);
}
