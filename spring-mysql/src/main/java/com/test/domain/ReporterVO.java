package com.test.domain;

public class ReporterVO {
	private int userno;
	private String userid;
	private String name;
	private double activity;

	public ReporterVO() {
		super();
	}

	public ReporterVO(int userno, String userid, String name, double activity) {
		super();
		this.userno = userno;
		this.userid = userid;
		this.name = name;
		this.activity = activity;
	}

	public int getUserno() {
		return userno;
	}

	public void setUserno(int userno) {
		this.userno = userno;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getActivity() {
		return activity;
	}

	public void setActivity(double activity) {
		this.activity = activity;
	}

	@Override
	public String toString() {
		return "ReporterVO [userno=" + userno + ", userid=" + userid + ", name=" + name + ", activity=" + activity
				+ "]";
	}
}
