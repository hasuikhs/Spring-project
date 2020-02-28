package testtest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class ListTest {
	public static void main(String[] args) {

		List<String> dateList = new ArrayList<String>();
		
		Random rand = new Random();

		int inputCnt = 1000000;
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
		// System.out.println(monthlyNewsCntList);	// [91100, 106800, 79900, 70700, 51500, 85200, 104900, 102000, 84900, 74600, 78300, 70100]
		// 100만개 확인 완
		Calendar cal = Calendar.getInstance();
		int year = 2020;

		List<Double> usingInternetWeekList = Arrays.asList(80.0, 100.0, 100.0, 90.0, 90.0, 90.0, 80.0);
		
		int testtest = 0;
		// for 0 ~ 11
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
			// 설정된 날부터 말일까지
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
			
			// System.out.println(dailyNewsCntList); // [1868, 1590, 1750, 1683, 1401, 1596, 1988, 1895, 1711, 1650, 1567, 1495, 1545, 1777, 1864, 1636, 1536, 1686, 1356, 1440, 1898, 1725, 1673, 1661, 1685, 1596, 1382, 1720, 1767, 1667, 1792]
			// 월별 뉴스리스트 확인 완

			// 1 ~ 말일
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
				
				int millisTimeOfHour = 3600000;	// 1시간 밀리초
				
				System.out.println(year + "년" + i +"월" + tDay + "일");	// i = 0 ~ 11
				Date curDate = new Date();
				curDate.setYear(year - 1900);
				System.out.println(curDate.getYear());
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
//						System.out.println(tmpDate);
						
						SimpleDateFormat fmtDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
//						System.out.println(fmtDate.format(tmpDate));
						dateList.add(fmtDate.format(tmpDate).toString());
						sumIntervalTime += interTime;
						testtest++;
					}
					
				}
				tDay++;
			}
		}
		System.out.println(dateList);
	}
}
