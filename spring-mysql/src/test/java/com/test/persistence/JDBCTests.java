package com.test.persistence;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.DriverManager;

import lombok.extern.log4j.Log4j;


@Log4j
public class JDBCTests {
	static {
		try {
			// 8���� ���ĺ��� com.mysql.cj.jdbc.Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
	//@Test
	public void testConnection() {
		try(Connection con = DriverManager.getConnection(
				// 5.1.X ���� �������� KST Ÿ������ �ν����� ����
				"jdbc:mysql://localhost:3306/test?characterEncoding=UTF-8&serverTimezone=UTC",
				"book_ex",
				"book_ex")){
			
			log.info(con);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
}
