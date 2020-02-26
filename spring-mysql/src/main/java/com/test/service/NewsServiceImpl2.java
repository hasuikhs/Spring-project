package com.test.service;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import com.test.domain.NewsVO;
import com.test.domain.ReporterVO;
import com.test.mapper.ReporterMapper;

public class NewsServiceImpl2 {
	
	private static ReporterMapper reportermapper;
	
	public static void main(String[] args) {

		int inputCnt = 1000000;
		// Create titleList
		List<String> titleList = createTitleList();

		// Create ContentStrList
		List<String> contentStrList = createContentStrList();
		
		// Create ReporterList with activity
		List<HashMap<String, Object>> reporterListWithActivity = createReporterListWithActivity(inputCnt);
		
		// Create RandomNewsListByReporterActivity
		List<NewsVO> newsList = createRandomNewsByReporterActivity(titleList, contentStrList, reporterListWithActivity);
		
		// newsList shuffle
		Collections.shuffle(newsList);
		// inputCnt 개의 뉴스 생성
		
		// 월별 퍼센티지 적용
		List<Double> usingInternetMonthList = Arrays.asList(11.0, 7.0, 8.0, 7.0, 8.0, 1.0, 7.0, 6.0, 7.0, 8.0, 9.0,	11.0);

		Random rand = new Random();

		for (int i = 0; i < usingInternetMonthList.size(); i++) {

			if (i < usingInternetMonthList.size() - 1) {
				int maxIntervalRange = (int) Math.ceil(usingInternetMonthList.get(i) * 1.2);
				int minIntervalRange = (int) Math.ceil(usingInternetMonthList.get(i) * 0.8);

				double randomIntervalRange = ((int) ((rand.nextInt((maxIntervalRange - minIntervalRange) + 1) + minIntervalRange + rand.nextDouble()) * 100) / 10000.0);
				usingInternetMonthList.set(i, randomIntervalRange);
			} else {
				double prbSum = 1.0;
				for(int j = 0; j < usingInternetMonthList.size() - 1; j++) {
					prbSum -= usingInternetMonthList.get(j);
				}
				prbSum = ((int) (prbSum * 10000)) / 10000.0;
				usingInternetMonthList.set(i, prbSum);
			}
		}
		
		
		
		
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
