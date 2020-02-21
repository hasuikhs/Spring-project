package testtest;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RandomTest {
	public static void main(String[] args) {
		NewsVO vo = new NewsVO();

		vo.setCreateDate(String.valueOf(86));

		System.out.println(vo);
		
		Date date = new Date();
		
		date.setTime(Integer.parseInt(vo.getCreateDate()));
		System.out.println(date.getTime());
		
		Date ndate = new Date();
		ndate.setYear(2020 - 1900);
		ndate.setMonth(2 - 1);
		ndate.setDate(20);
		ndate.setHours(18);
		ndate.setMinutes(17);
		
		System.out.println(ndate.getTime());
		
		Date today = new Date();
		System.out.println(today.getTime());
		
		Date testDay = new Date();
		testDay.setTime(today.getTime() + Integer.parseInt(vo.getCreateDate()));
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd E HH:mm:ss:S");
		
		
		System.out.println(testDay.getTime());
		System.out.println(sdf.format(testDay));
		
	}
}
