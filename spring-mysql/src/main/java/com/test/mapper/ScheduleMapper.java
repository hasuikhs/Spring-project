package com.test.mapper;

import java.util.List;

import com.test.domain.ScheduleVO;

public interface ScheduleMapper {
	public List<ScheduleVO> getList();
	
	public List<ScheduleVO> getScheduleList(int userno);
}
