package com.test.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.test.domain.UserVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class UserServiceTests {
	
	@Setter(onMethod_ = {@Autowired})
	private UserService service;
	
	@Test
	public void testLogin() {
		UserVO user = new UserVO();
		user.setUserid("test01");
		user.setPassword("1234");
		log.info("service test");

		service.login(user);
		if (service.login(user) == null) {
			System.out.println("로그인 실패");
		} else {
			System.out.println("로그인 성공");
		}
	}
	
}
