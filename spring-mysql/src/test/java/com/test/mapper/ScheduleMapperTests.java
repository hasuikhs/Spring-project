package com.test.mapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.test.domain.ScheduleVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ScheduleMapperTests {

	@Setter(onMethod_ = @Autowired)
	private ScheduleMapper mapper;
	
	//@Test
	public void testGetList() {
		mapper.getList().forEach(schedule -> log.info(schedule));
	}
	
	//@Test
	public void testScheduleList( ) {
		mapper.getScheduleList(1);
	}
	
	//@Test
	public void testInsert() {
		ScheduleVO schedule = new ScheduleVO();
		Date date = new Date();
		date.setDate(30);
		schedule.setUserno(1);
		schedule.setAnnititle("ÈÞ°¡");
		schedule.setAnnidate(date);
		schedule.setIsholiday("Y");
		
		mapper.insertSchedule(schedule);
		log.info(schedule);
	}
	
	//@Test
	public void testUpdate() {
		ScheduleVO schedule = new ScheduleVO();
		Date date = new Date();
		date.setDate(30);
		schedule.setCno(4);
		schedule.setAnnititle("½°");
		schedule.setAnnidate(date);
		schedule.setIsholiday("Y");
		
		mapper.updateSchedule(schedule);
	}
	
	//@Test
	public void testDelete() {
		mapper.deleteSchedule(4);
	}
	
	//@Test
	public void testScheduleByID() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("userno", 1);
		map.put("year", 2020);
		map.put("month", 1);
		map.put("day", 28);
		
		log.info(mapper.getScheduleOfDayByUserno(map));
	}
}
