package com.test.domain;

import java.util.Date;

import lombok.Data;

@Data
public class ScheduleVO {
	private int cno;
	private int userno;
	private String annititle;
	private Date annidate;
	private String isholiday;
}
