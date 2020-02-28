package com.test.service;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import com.test.domain.NewsVO;
import com.test.domain.ReporterVO;
import com.test.mapper.ReporterMapper;

public class NewsServiceImpl2 {

	private static ReporterMapper reportermapper;

	public static void main(String[] args) {

		// inputCnt 개의 전체 뉴스 생성
		int inputCnt = 1000000;

		// Create titleList
		List<String> titleList = createTitleList();

		// Create ContentStrList
		List<String> contentStrList = createContentStrList();

		// Create ReporterListWithActivity
		List<HashMap<String, Object>> reporterListWithActivity = createReporterListWithActivity(inputCnt);

		// Create RandomNewsListByReporterActivity
		List<NewsVO> newsList = createRandomNewsByReporterActivity(titleList, contentStrList, reporterListWithActivity);

		// Shuffle newsList
		Collections.shuffle(newsList);

		Random rand = new Random();

		// create MonthlyNewsCntList
		List<Integer> monthlyNewsCntList = createMonthlyNewsCntList(inputCnt, rand);
		
		// create dateList
		List<String> dateList = new ArrayList<String>();
		Calendar cal = Calendar.getInstance();
		int year = 2020;

		createDateList(rand, monthlyNewsCntList, dateList, cal, year);
		
		int i = 0;
		for (NewsVO news : newsList) {
			news.setDate(dateList.get(i));
			i++;
		}
		System.out.println(newsList);
	}

	private static void createDateList(Random rand, List<Integer> monthlyNewsCntList, List<String> dateList,
			Calendar cal, int year) {
		List<Double> usingInternetWeekList = Arrays.asList(80.0, 100.0, 100.0, 90.0, 90.0, 90.0, 80.0);

		for (int i = 0; i < monthlyNewsCntList.size(); i++) {
			int day = 1;
			cal.set(year, i, day);
			int monthlyNewsCnt = monthlyNewsCntList.get(i);

			int dailyNewsCnt = monthlyNewsCnt / cal.getActualMaximum(Calendar.DATE);

			List<Integer> dailyNewsCntList = new ArrayList<Integer>();
			Date tempDate = new Date();
			tempDate.setYear(year - 1900);
			tempDate.setMonth(i);

			int LastDayOfMonth = cal.getActualMaximum(Calendar.DATE);
			while (day <= LastDayOfMonth) {
				tempDate.setDate(day);

				double tempPrb = usingInternetWeekList.get(tempDate.getDay());

				int maxTempPrb = (int) Math.ceil(tempPrb * 1.1);
				int minTempPrb = (int) Math.ceil(tempPrb * 0.9);

				double randomTempPrb = ((int) ((rand.nextInt((maxTempPrb - minTempPrb) + 1) + minTempPrb
						+ rand.nextDouble()) * 100)) / 10000.0;
				dailyNewsCntList.add((int) (dailyNewsCnt * randomTempPrb));
				if (day == LastDayOfMonth) {
					int sumCntDailyNews = 0;
					for (Integer cntDailyNews : dailyNewsCntList) {
						sumCntDailyNews += cntDailyNews;
					}

					int restCntOfMonthNews = monthlyNewsCnt - sumCntDailyNews;
					int cntCorNews = restCntOfMonthNews / LastDayOfMonth;

					int sumCntNewsOfDay = 0;
					for (int j = 0; j < LastDayOfMonth; j++) {
						int cntCorNewsInt = dailyNewsCntList.get(j) + cntCorNews;
						sumCntNewsOfDay += cntCorNewsInt;
						dailyNewsCntList.set(j, cntCorNewsInt);
						if ((sumCntNewsOfDay != monthlyNewsCnt) && (j == (LastDayOfMonth - 1))) {

							int randDay = rand.nextInt(LastDayOfMonth);
							int extra = sumCntNewsOfDay - monthlyNewsCnt;
							dailyNewsCntList.set(randDay, dailyNewsCntList.get(randDay) - extra);
						}
					}
				}
				day++;
			}

			int tDay = 1;
			for (Integer cntOfDailyNews : dailyNewsCntList) {
				List<Double> usingInternetDayList = Arrays.asList(0.0424, 0.0322, 0.0213, 0.0172, 0.0149, 0.0131, 0.013,
						0.0167, 0.024, 0.04, 0.0447, 0.0474, 0.0459, 0.0487, 0.0471, 0.0569, 0.0675, 0.0711, 0.0596,
						0.0555, 0.0499, 0.0557, 0.0566, 0.0586);

				List<Integer> hourlyNews = new ArrayList<Integer>();
				for (int k = 0; k < usingInternetDayList.size(); k++) {
					int standardUsingHour = (int) (usingInternetDayList.get(k) * 10000);
					int maxUsingHour = (int) Math.ceil(standardUsingHour * 1.02);
					int minUsingHour = (int) Math.floor(standardUsingHour * 0.98);

					double randomUsingHour = (rand.nextInt((maxUsingHour - minUsingHour) + 1) + minUsingHour) / 10000.0;

					hourlyNews.add((int) Math.round(cntOfDailyNews * randomUsingHour));
				}

				int sumOfHourNews = 0;
				for (int k = 0; k < hourlyNews.size(); k++) {
					sumOfHourNews += hourlyNews.get(k);
					if ((sumOfHourNews != cntOfDailyNews) && (k == hourlyNews.size() - 1)) {
						int randomHour = rand.nextInt(hourlyNews.size());
						int extra = sumOfHourNews - cntOfDailyNews;

						hourlyNews.set(randomHour, hourlyNews.get(randomHour) - extra);
					}
				}

				System.out.println(hourlyNews);

				int millisTimeOfHour = 3600000; // 1시간 밀리초

				Date curDate = new Date();
				curDate.setYear(year - 1900);
				curDate.setMonth(i);
				curDate.setDate(tDay);
				curDate.setMinutes(0);
				curDate.setSeconds(0);
				for (int k = 0; k < hourlyNews.size(); k++) {
					int sumIntervalTime = 0;

					// k = 0 ~ 23 시 지정
					curDate.setHours(k);
					int interTime = millisTimeOfHour / hourlyNews.get(k);

					for (int l = 0; l < hourlyNews.get(k); l++) {
						Date tmpDate = new Date();
						tmpDate.setTime(curDate.getTime() + sumIntervalTime);
						
						SimpleDateFormat fmtDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
						dateList.add(fmtDate.format(tmpDate).toString());
						sumIntervalTime += interTime;
					}
				}
				tDay++;
			}
		}
	}

	private static List<Integer> createMonthlyNewsCntList(int inputCnt, Random rand) {
		List<Double> usingInternetMonthList = Arrays.asList(9.0, 9.0, 7.0, 7.0, 5.0, 7.0, 9.0, 9.0, 7.0, 6.0, 7.0, 8.0);

		double prbSum = 1.0;
		for (int i = 0; i < usingInternetMonthList.size(); i++) {

			if (i < usingInternetMonthList.size() - 1) {
				int maxIntervalRange = (int) Math.ceil(usingInternetMonthList.get(i) * 1.1);
				int minIntervalRange = (int) Math.ceil(usingInternetMonthList.get(i) * 0.9);

				double randomIntervalRange = ((int) ((rand.nextInt((maxIntervalRange - minIntervalRange) + 1)
						+ minIntervalRange + rand.nextDouble()) * 100)) / 10000.0;
				usingInternetMonthList.set(i, randomIntervalRange);
				prbSum -= randomIntervalRange;
			} else {
				prbSum = ((int) (prbSum * inputCnt)) / (double) inputCnt;
				usingInternetMonthList.set(i, prbSum);
			}
		}

		List<Integer> monthlyNewsCntList = new ArrayList<Integer>();

		int sumCnt = 0;
		for (Double percent : usingInternetMonthList) {
			monthlyNewsCntList.add((int) Math.ceil(inputCnt * percent));
			sumCnt += (int) Math.ceil(inputCnt * percent);

			if ((sumCnt != inputCnt) && (percent == usingInternetMonthList.get(usingInternetMonthList.size() - 1))) {
				int extra = sumCnt - inputCnt;
				int randMonth = rand.nextInt(monthlyNewsCntList.size());
				monthlyNewsCntList.set(randMonth, monthlyNewsCntList.get(randMonth) - extra);
			}
		}
		return monthlyNewsCntList;
	}

	private static List<NewsVO> createRandomNewsByReporterActivity(List<String> titleList, List<String> contentStrList,
			List<HashMap<String, Object>> reporterListWithActivity) {
		Random rand = new Random();

		List<NewsVO> newsList = new ArrayList<NewsVO>();

		for (HashMap<String, Object> map : reporterListWithActivity) {
			for (int i = 0; i < ((Long) map.get("cntNews")).intValue(); i++) {
				NewsVO newsvo = new NewsVO();

				newsvo.setUserno((Integer) map.get("userno"));

				newsvo.setTitle(titleList.get(rand.nextInt(titleList.size())));

				int cntPara = rand.nextInt(4) + 3;
				StringBuilder news = new StringBuilder();
				for (int j = 0; j < cntPara; j++) {
					StringBuilder para = new StringBuilder();
					int cntString = rand.nextInt(4) + 3;
					for (int k = 0; k < cntString; k++) {
						para.append(contentStrList.get(rand.nextInt(contentStrList.size()))).append(" ");
					}
					news.append(para).append("\n");
				}
				newsvo.setContent(news.toString());
				newsList.add(newsvo);
			}
		}
		return newsList;
	}

	private static List<HashMap<String, Object>> createReporterListWithActivity(int inputCnt) {
		List<HashMap<String, Object>> reporterListWithActivity = new ArrayList<HashMap<String, Object>>();

		List<ReporterVO> reporterList = reportermapper.getList();

		System.out.println(reporterList);
		double sumActivity = 0;
		for (ReporterVO vo : reporterList) {
			sumActivity += vo.getActivity();
		}

		for (ReporterVO vo : reporterList) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("userno", vo.getUserno());
			map.put("cntNews", Math.round(inputCnt * (vo.getActivity() / sumActivity)));
			reporterListWithActivity.add(map);
		}
		return reporterListWithActivity;
	}

	private static List<String> createContentStrList() {
		List<String> contentStrList = new ArrayList<String>();
		// createContentStrList
		String contentPath = "D:\\Dev\\JAVA\\data\\news\\contents.txt";

		try {
			FileInputStream contentFis = new FileInputStream(contentPath);
			byte[] contentBuffer = new byte[contentFis.available()];
			while (contentFis.read(contentBuffer) != -1) {
			}
			contentFis.close();

			List<String> paraList = new ArrayList<String>();

			// 뉴스 자르기
			// 뉴스
			String news = new String(contentBuffer, "UTF-8");

			// 문단 리스트
			paraList = Arrays.asList(news.split("\\n"));

			// 문장 리스트
			List<String> oldStrList = new ArrayList<String>();
			for (String str : paraList) {
				// \r 제거 후 split
				oldStrList.addAll(Arrays.asList(str.replaceAll("\r", "").split("\\. ")));
			}

			// . 다시 붙이기
			for (String str : oldStrList) {
				String lastChar = Character.toString(str.charAt(str.length() - 1));
				if (!lastChar.equals(".")) {
					str += ".";
				}
				contentStrList.add(str);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return contentStrList;
	}

	private static List<String> createTitleList() {
		List<String> titleList = new ArrayList<String>();

		// createTitleList
		String titlePath = "D:\\Dev\\JAVA\\data\\news\\titles.txt";
		try {
			FileInputStream titleFis = new FileInputStream(titlePath);
			byte[] titleBuffer = new byte[titleFis.available()];
			while (titleFis.read(titleBuffer) != -1) {
			}
			titleFis.close();

			String titles = new String(titleBuffer, "UTF-8");
			titleList = Arrays.asList(titles.split("\\n"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return titleList;
	}
}
