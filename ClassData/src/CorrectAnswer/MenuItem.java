package CorrectAnswer;

public class MenuItem {

	private Integer pcode;
	private Integer code;
	private String title;

	public MenuItem(Integer code, Integer pcode, String title) {
		// TODO Auto-generated constructor stub
		this.code = code;
		this.pcode = pcode;
		this.title = title;
	}

	@Override
	public String toString() {
		return "MenuItem [pcode=" + pcode + ", code=" + code + ", title=" + title + "]";
	}

	public Integer getPcode() {
		return pcode;
	}

	public void setPcode(Integer pcode) {
		this.pcode = pcode;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}