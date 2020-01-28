package com.test.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
	
	@Test
	public void testScheduleList( ) {
		mapper.getScheduleList(1);
	}
}
