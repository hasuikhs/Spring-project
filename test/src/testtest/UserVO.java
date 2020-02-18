package testtest;

public class UserVO {
	private int userno;
	private String userid;
	private double activity;

	public UserVO() {
		super();
	}

	public UserVO(int userno, String userid, double activity) {
		super();
		this.userno = userno;
		this.userid = userid;
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

	public double getActivity() {
		return activity;
	}

	public void setActivity(double activity) {
		this.activity = activity;
	}

	@Override
	public String toString() {
		return "UserVO [userno=" + userno + ", userid=" + userid + ", activity=" + activity + "]";
	}
}
