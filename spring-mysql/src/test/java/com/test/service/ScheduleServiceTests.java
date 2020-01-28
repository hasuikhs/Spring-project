package com.test.service;

import static org.junit.Assert.assertNotNull;

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
public class ScheduleServiceTests {

	@Setter(onMethod_ = { @Autowired })
	private ScheduleService service;

	//@Test
	public void testExist() {
		log.info(service);
		assertNotNull(service);
	}

	//@Test
	public void testGetList() {
		service.getList().forEach(schedule -> log.info(schedule));
	}
	
	@Test
	public void testGetScheduleList() {
		service.getScheduleList(1).forEach(sch -> log.info(sch));
	}
}
