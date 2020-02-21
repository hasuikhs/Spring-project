package com.test.mapper;

import java.util.HashMap;
import java.util.Map;

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
public class NewsMapperTests {
	@Setter(onMethod_ = @Autowired)
	private NewsMapper mapper;

	// @Test
	public void testReporterList() {
		mapper.getList().forEach(news -> log.info(news));
	}

	@Test
	public void testGetStat() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("dateType", "m");
		mapper.getStatistics(map).forEach(stat -> log.info(stat));
	}
}
