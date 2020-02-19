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

		String[] fName = { "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "Ȳ", "��", "��",
				"��", "ȫ", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��", "��",
				"��", "��", "����", "����", "����" };
		String[] lName = { "����", "����", "����", "����", "�ÿ�", "�ֿ�", "����", "��ȣ", "����", "�ؼ�", "�ؿ�", "����", "����", "����", "�ǿ�",
				"����", "����", "����", "����", "����", "����", "����", "����", "����", "����", "�¿�", "��ȯ", "����", "�¹�", "����", "����", "����",
				"����", "�μ�", "����", "����", "����", "����", "����", "ä��", "����", "����", "����", "����", "����", "����", "����", "����", "����",
				"����", "����", "����", "����", "����", "����", "�ϸ�", "ä��", "����", "����" };
		
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