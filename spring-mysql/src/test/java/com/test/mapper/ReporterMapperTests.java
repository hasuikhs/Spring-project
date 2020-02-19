package com.test.mapper;

import java.util.Random;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.test.domain.ReporterVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReporterMapperTests {

	@Setter(onMethod_ = @Autowired)
	private ReporterMapper mapper;

	@Test
	public void testReporterList() {
		mapper.getList().forEach(reporter -> log.info(reporter));
	}

	// SELECT userno, name, ROUND(activity / (SELECT SUM(activity) FROM reporter), 5) FROM reporter WHERE userno = ? ;
	@Test
	public void testInsert() {
		Random rand = new Random();

		String[] fName = { "김", "이", "박", "최", "정", "강", "조", "윤", "장", "임", "한", "오", "서", "신", "권", "황", "안", "송",
				"전", "홍", "유", "고", "문", "양", "손", "배", "조", "백", "허", "유", "남", "심", "노", "하", "곽", "성", "차", "주", "우",
				"구", "민", "남궁", "선우", "제갈" };
		String[] lName = { "민준", "서준", "예준", "도윤", "시우", "주원", "하준", "지호", "지후", "준서", "준우", "현우", "지훈", "도현", "건우",
				"우진", "민재", "현준", "선우", "서진", "연우", "정우", "유준", "승현", "준혁", "승우", "지환", "시윤", "승민", "지우", "서연", "서윤",
				"서현", "민서", "하은", "하윤", "윤서", "지민", "지유", "채원", "지윤", "은서", "수아", "다은", "예은", "수빈", "지아", "소율", "예원",
				"예린", "지원", "소윤", "유진", "시은", "지안", "하린", "채은", "가은", "윤아" };
		
		for (int i = 0; i < 100; i++) {
			ReporterVO reporter = new ReporterVO();
			UUID userid = UUID.randomUUID();
			reporter.setUserid(userid.toString());
			
			reporter.setName(fName[rand.nextInt(fName.length)] + lName[rand.nextInt(lName.length)]);
			reporter.setActivity(Math.round((rand.nextGaussian() + 3) * 100000) / 100000.0);
			
			mapper.insert(reporter);
			log.info(reporter);
		}
	}

	// @Test
	public void testRead() {
		ReporterVO reporter = mapper.read(1);

		log.info(reporter);
	}
}