package com.test.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.domain.GlobalDefine;
import com.test.domain.ScheduleVO;
import com.test.mapper.ScheduleMapper;

@Service
public class ScheduleServiceImpl implements ScheduleService{

	@Autowired
	private ScheduleMapper mapper;
	
	@Override
	public ScheduleVO getScheduleOfDayByUserno(Map<String, Integer> map) {
		return mapper.getScheduleOfDayByUserno(map);
	}

	@SuppressWarnings("deprecation")
	@Override
	public String getDayName(Map<String, Integer> map) {
		DateFormat formatYM = new SimpleDateFormat("yyyy-MM-dd-E");
		Date date = new Date();
		date.setYear(map.get("year") - GlobalDefine.CORRECTED_YEAR);
		date.setMonth(map.get("month") - GlobalDefine.CORRECTED_MONTH);
		date.setDate(map.get("day"));
		
		String datestr = formatYM.format(date);
		return datestr;
	}
}
