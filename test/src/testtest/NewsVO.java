package testtest;

import java.util.Date;

public class NewsVO {
	private int newsno;
	private int userno;
	private String createDate;
	private String title;
	private String content;

	public NewsVO() {
		super();
	}

	public NewsVO(int newsno, int userno, String createDate, String title, String content) {
		super();
		this.newsno = newsno;
		this.userno = userno;
		this.createDate = createDate;
		this.title = title;
		this.content = content;
	}

	public int getNewsno() {
		return newsno;
	}

	public void setNewsno(int newsno) {
		this.newsno = newsno;
	}

	public int getUserno() {
		return userno;
	}

	public void setUserno(int userno) {
		this.userno = userno;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "NewsVO [newsno=" + newsno + ", userno=" + userno + ", createDate=" + createDate + ", title=" + title
				+ ", content=" + content + "]";
	}
}
