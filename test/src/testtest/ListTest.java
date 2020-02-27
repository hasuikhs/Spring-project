package testtest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class ListTest {
	public static void main(String[] args) {

		Random rand = new Random();

		int inputCnt = 1000000;
		List<Double> usingInternetMonthList = Arrays.asList(9.0, 9.0, 7.0, 7.0, 5.0, 7.0, 9.0, 9.0, 7.0, 6.0, 7.0, 8.0);

		double prbSum = 1.0;
		for (int i = 0; i < usingInternetMonthList.size(); i++) {

			if (i < usingInternetMonthList.size() - 1) {
				int maxIntervalRange = (int) Math.ceil(usingInternetMonthList.get(i) * 1.1);
				int minIntervalRange = (int) Math.ceil(usingInternetMonthList.get(i) * 0.9);

				double randomIntervalRange = ((int) ((rand.nextInt((maxIntervalRange - minIntervalRange) + 1) + minIntervalRange + rand.nextDouble()) * 100)) / 10000.0;
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

			if (sumCnt > inputCnt || (sumCnt < inputCnt
					&& percent == usingInternetMonthList.get(usingInternetMonthList.size() - 1))) {
				int extra = sumCnt - inputCnt;
				int randMonth = rand.nextInt(monthlyNewsCntList.size());
				monthlyNewsCntList.set(randMonth, monthlyNewsCntList.get(randMonth) - extra);
			}
		}

		Calendar cal = Calendar.getInstance();
		int year = 2020;

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
						if ((sumCntNewsOfDay > monthlyNewsCnt)
								|| ((sumCntNewsOfDay < monthlyNewsCnt) && (j == LastDayOfMonth - 1))) {

							int randDay = rand.nextInt(LastDayOfMonth);
							int extra = sumCntNewsOfDay - monthlyNewsCnt;
							dailyNewsCntList.set(randDay, dailyNewsCntList.get(randDay) - extra);
						}
					}
					
					System.out.println(i + 1 + "¿ù");
					System.out.println(dailyNewsCntList);
					
					List<Double> usingInternetDayList = Arrays.asList(0.0424, 0.0322, 0.0213, 0.0172, 0.0149, 0.0131, 0.013, 0.0167,
							0.024, 0.04, 0.0447, 0.0474, 0.0459, 0.0487, 0.0471, 0.0569, 0.0675, 0.0711, 0.0596, 0.0555, 0.0499,
							0.0557, 0.0566, 0.0586);
					
					for(int dayOfMonth : dailyNewsCntList) {
						System.out.println(dayOfMonth);
					}
				}
				day++;
			}
		}
	}
}
