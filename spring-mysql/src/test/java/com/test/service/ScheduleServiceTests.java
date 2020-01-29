package com.test.service;

import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
public class ScheduleServiceTests {

	@Setter(onMethod_ = {@Autowired})
	private ScheduleService service;
	
	//@Test
	public void testExist() {
		log.info(service);
		assertNotNull(service);
	}
	
	//@Test
	public void testGet() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("userno", 1);
		map.put("year", 2020);
		map.put("month", 1);
		map.put("day", 28);
		log.info(service.getScheduleOfDayByUserno(map));
	}
	
	//@Test
	@SuppressWarnings("deprecation")
	public void testInsert() {
		ScheduleVO schedule = new ScheduleVO();
		Date date = new Date();
		date.setDate(30);
		schedule.setUserno(2);
		schedule.setAnnititle("∏Òø‰¿œ");
		schedule.setAnnidate(date);
		schedule.setIsholiday("N");
		
		service.insertSchedule(schedule);
	}
}
