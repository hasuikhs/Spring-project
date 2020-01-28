package com.test.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.test.domain.GlobalDefine;

@Service
public class CalendarServiceImpl implements CalendarService {

	DateFormat formatYM = new SimpleDateFormat("yyyy. MM");
	
	@SuppressWarnings("deprecation")
	@Override
	public Map<String, Object> calendar(int year, int month, int ms, long userno) {

		Map<String, Object> result = new HashMap<String, Object>();

		result.put("year", year);
		result.put("month", month);

		result.put("userno", userno);
		
		year -= 1900;
		month--;

		Date cal = new Date();

		result.put("today", cal.getMonth() + 1);

		cal.setYear(year);
		cal.setMonth(month);
		cal.setDate(1);

		Date cloneCal = new Date();
		cloneCal.setMonth(cal.getMonth());

		// 말일 구하기
		int lastDayOfMonth = calculLastDay(cal, cloneCal);

		
		// 현재 연/월
		String dateStr = formatYM.format(cal.getTime());
		result.put("calTitle", dateStr);

		ArrayList<String> arrCal = new ArrayList<String>();

		int day = 1;
		int lengthOfMonth = cal.getDay() + lastDayOfMonth;

		int normalLength = GlobalDefine.WEEK_DAY * 5;
		int maxLength = GlobalDefine.WEEK_DAY * 6;

		// 월먼저 일먼저 계산
		int msYesOrNo = calculMonSun(ms);

		// 캘린더 어레이 리스트에 추가
		addCalendarArray(cal, arrCal, day, lengthOfMonth, maxLength, msYesOrNo);

		result.put("ms", ms);

		int weekCnt = (arrCal.get(35) == " ") ? normalLength / GlobalDefine.WEEK_DAY : maxLength / GlobalDefine.WEEK_DAY;

		// 2중 리스트
		List<List<String>> weeks = new ArrayList<List<String>>();

		// 2중 리스트가 선언되면 아래와 같이 sub된 리스트를 리스트에 넣을 수 있다.
		for (int i = 0; i < weekCnt; i++) {
			weeks.add(arrCal.subList(i * GlobalDefine.WEEK_DAY, (i + 1) * GlobalDefine.WEEK_DAY));
		}

		// readTsv
		Map<String, Map<String, String>> holy = readTsv(cal);

		// mapedSchedule
		List<List<List<Map<String, String>>>> schedulCal = mapedSchedule(cal, weeks, holy);

		result.put("collection", schedulCal);

		return result;
	}

	// 월먼저 일먼저
	private int calculMonSun(int ms) {
		int msYesOrNo = 0;
		if (ms == 0) {
			msYesOrNo = 0;
		} else {
			msYesOrNo = 1;
		}
		return msYesOrNo;
	}

	// 말일 구하기
	@SuppressWarnings("deprecation")
	private int calculLastDay(Date cal, Date cloneCal) {
		int lastDayOfMonth = 28;
		for (; lastDayOfMonth <= 35; lastDayOfMonth++) {
			cloneCal.setDate(lastDayOfMonth);
			if (cloneCal.getMonth() != cal.getMonth()) {
				lastDayOfMonth--;
				break;
			}
		}
		return lastDayOfMonth;
	}

	@SuppressWarnings("deprecation")
	private void addCalendarArray(Date cal, ArrayList<String> arrCal, int day, int lengthOfMonth, int maxLength,
			int msYesOrNo) {
		for (; msYesOrNo <= maxLength; msYesOrNo++) {
			if ((msYesOrNo >= cal.getDay()) && (msYesOrNo < lengthOfMonth)) {
				arrCal.add(Integer.toString(day));
				day++;
			} else {
				arrCal.add(" ");
			}
		}
	}

	@SuppressWarnings("deprecation")
	private Map<String, Map<String, String>> readTsv(Date cal) {
		List<String> split;
		Map<String, Map<String, String>> holy = new HashMap<String, Map<String, String>>();

		File file = new File("C:\\Users\\Bizspring\\Documents\\tsv.txt");
		try {
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));

			String line = "";

			while ((line = br.readLine()) != null) {
				split = Arrays.asList(line.split("\t"));
				if (Integer.parseInt(split.get(0).substring(0, 4)) == (cal.getYear() + 1900)
						&& Integer.parseInt(split.get(0).substring(5, 7)) == (cal.getMonth() + 1)) {

					Map<String, String> map = new HashMap<String, String>();

					if (!holy.containsKey(Integer.toString(Integer.parseInt(split.get(0).substring(8))))) {
						map.put("anni", split.get(1));
						map.put("holiday", split.get(2));
					} else {
						Map<String, String> temp = new HashMap<String, String>();
						temp = holy.get(Integer.toString(Integer.parseInt(split.get(0).substring(8))));
						map.put("anni", temp.get("anni"));
						map.put("holiday", temp.get("holiday"));
						map.put("addAnni", split.get(1));
						map.put("holiday2", split.get(2));
					}
					holy.put(Integer.toString(Integer.parseInt(split.get(0).substring(8))), map);
				}
			}
		} catch (Exception e) {
			System.out.println("text 마지막 줄(공백)을 삭제 바람");
		}
		return holy;
	}

	@SuppressWarnings("deprecation")
	private List<List<List<Map<String, String>>>> mapedSchedule(Date cal, List<List<String>> weeks,
			Map<String, Map<String, String>> holy) {
		List<List<List<Map<String, String>>>> schedulCal = new ArrayList<List<List<Map<String, String>>>>();

		for (List<String> week : weeks) {
			List<List<Map<String, String>>> innerList = new ArrayList<List<Map<String, String>>>();

			Iterator<String> it = week.iterator();

			while (it.hasNext()) {
				List<Map<String, String>> innerListOfList = new ArrayList<Map<String, String>>();
				Map<String, String> schedule = new HashMap<String, String>();
				String itThis = it.next();
				schedule.put("day", itThis);

				if (holy.containsKey(itThis)) {
					schedule.put("isHoliday", holy.get(itThis).get("holiday"));
					schedule.put("anni", holy.get(itThis).get("anni"));
					if (holy.get(itThis).containsKey("addAnni")) {
						schedule.put("addAnni", holy.get(itThis).get("addAnni"));
						schedule.put("isHoliday2", holy.get(itThis).get("holiday2"));
					}
				}
				if (itThis != " ") {
					cal.setDate(Integer.parseInt(itThis));
					if (cal.getDay() == 0 || cal.getDay() == 6) {
						schedule.put("isHoliday", "Y");
					}
				}
				innerListOfList.add(schedule);
				innerList.add(innerListOfList);
			}
			schedulCal.add(innerList);
		}

		return schedulCal;
	}
}
