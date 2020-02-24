package com.test.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Timer;

import org.springframework.stereotype.Service;

import com.test.domain.NewsVO;
import com.test.domain.ReporterVO;
import com.test.mapper.NewsMapper;
import com.test.mapper.ReporterMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class NewsServiceImpl implements NewsService {

	private NewsMapper newsmapper;
	private ReporterMapper reportermapper;

	@Override
	public List<Map<String, Object>> getList() {
		return newsmapper.getList();
	}

	@Override
	public Map<String, Object> get(int newsno) {
		return newsmapper.get(newsno);
	}
	
	@Override
	public List<Map<String, Object>> getStatistics(String dateType){
		return newsmapper.getStatistics(dateType); 
	}
	
	@Override
	public void insert() {
		List<String> titleList = new ArrayList<String>();
		List<String> oldStrList = new ArrayList<String>();
		List<String> newStrList = new ArrayList<String>();

		int inputCnt = 100000;

		titleList = preCreateNews(titleList, oldStrList, newStrList);

		List<Map<String, Object>> newReporterList = createNewReporterList(inputCnt);

		List<NewsVO> newsList = createRandomNewsByReporterActivity(titleList, newStrList, newReporterList);

		Collections.shuffle(newsList);

		int year = 2020;
		int month = 2;
		int date = 14;
		int hour = 0;
		int minute = 0;
				
		Date setDate = new Date();
		setDate.setYear(year - 1900);
		setDate.setMonth(month - 1);
		setDate.setDate(date);
		setDate.setHours(hour);
		setDate.setMinutes(minute);

		int millisTimeOfDay = 86400000;
		int millisTimeOfHour = 3600000;
		List<Double> normDayList = Arrays.asList(0.001, 0.002, 0.004, 0.008, 0.015, 0.02, 0.03, 0.04, 0.06, 0.08, 0.10, 0.14, 0.14, 0.10, 0.08, 0.06, 0.04, 0.03, 0.02, 0.015, 0.008, 0.004, 0.002, 0.001);
		
		List<Double> intervalTimeList = new ArrayList<Double>();
		
		for (Double normOfHour : normDayList) {
			double newsOfHour = inputCnt * normOfHour;
			intervalTimeList.add(newsOfHour);
		}
		
		System.out.println(intervalTimeList);
		
		Random rand = new Random();
		
		List<Double> timeList = new ArrayList<Double>();
		double sumIntervalTime = 0;
		for(Double intervalTime : intervalTimeList) {
			for(double i = 0; i < intervalTime; i++) {
				
				double interTime = millisTimeOfHour / intervalTime;
				
				int maxIntervalRange = (int) Math.ceil(interTime * 1.2);
				int minIntervalRange = (int) Math.floor(interTime * 0.8);
				
				int randomIntervalTime = rand.nextInt((maxIntervalRange - minIntervalRange) + 1) + minIntervalRange;
				
				timeList.add(sumIntervalTime);
				sumIntervalTime += randomIntervalTime;
			}
		}
		System.out.println(timeList.size());
		System.out.println(sumIntervalTime);
		
		SimpleDateFormat fmtDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");

		int sizeOfNewsList = newsList.size() < timeList.size() ? newsList.size() : timeList.size();
		
		List<NewsVO> newsLst = new ArrayList<NewsVO>();
		
		for (int i = 0; i < sizeOfNewsList; i++) {
			NewsVO newsvo = newsList.get(i);
			long setTime = (long) (setDate.getTime() + timeList.get(i));
			
			Date writeDate = new Date();
			writeDate.setTime(setTime);
			newsvo.setDate(fmtDate.format(writeDate));
			newsLst.add(newsvo);
		}
		System.out.println(newsLst);
		
		
		
		
		int batch = 1000;
		
		autoInsertBatch(newsList, fmtDate, batch);
	}

	private void autoInsertBatch(List<NewsVO> newsList, SimpleDateFormat fmtDate, int batch) {
		Timer timer = new Timer();
		
		int sizeOfListOfList = newsList.size() / batch;
		
		List<List<NewsVO>> separatedNewsList = new ArrayList<List<NewsVO>>();
		for (int i = 0; i < sizeOfListOfList; i++) {
			separatedNewsList.add(newsList.subList(i * batch, (i + 1) * batch));
		}
		
		int cnt = 0;
		for (List<NewsVO> batchNews : separatedNewsList) {
			System.out.println(cnt);
			try {
				newsmapper.batchInsert(batchNews);
			} catch (Exception e) {
				e.printStackTrace();
			}
			cnt++;
		}
	}

	private List<NewsVO> createRandomNewsByReporterActivity(List<String> titleList, List<String> newStrList,
			List<Map<String, Object>> newReporterList) {
		Random rand = new Random();

		// 리스트<뉴스VO> 만들고
		List<NewsVO> newsList = new ArrayList<NewsVO>();

		// 맵에 정해진 뉴스의 숫자별로 뉴스VO를 만들어서 리스트<뉴스VO> ADD
		for (Map<String, Object> map : newReporterList) {
			for (int i = 0; i < ((Long) map.get("cntNews")).intValue(); i++) {
				NewsVO newsvo = new NewsVO();
				// 유저 번호
				newsvo.setUserno((Integer) map.get("userno"));

				// 제목
				String title = titleList.get(rand.nextInt(titleList.size()));
				newsvo.setTitle(title);

				// 내용
				int cntPara = rand.nextInt(4) + 3;
				StringBuilder news = new StringBuilder();
				for (int j = 0; j < cntPara; j++) {
					StringBuilder para = new StringBuilder();
					int cntString = rand.nextInt(4) + 3;
					for (int k = 0; k < cntString; k++) {
						para.append(newStrList.get(rand.nextInt(newStrList.size()))).append(" ");
					}
					news.append(para).append("\n");
				}
				newsvo.setContent(news.toString());
				newsList.add(newsvo);
			}
		}
		return newsList;
	}

	private List<Map<String, Object>> createNewReporterList(int inputCnt) {
		List<ReporterVO> reporterList = new ArrayList<ReporterVO>();
		reporterList = reportermapper.getList();

		double sumActivity = 0;
		for (ReporterVO vo : reporterList) {
			sumActivity += vo.getActivity();
		}

		List<Map<String, Object>> newReporterList = new ArrayList<Map<String, Object>>();
		for (ReporterVO vo : reporterList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userno", vo.getUserno());
			map.put("cntNews", Math.round(inputCnt * (vo.getActivity() / sumActivity)));
			newReporterList.add(map);
		}
		return newReporterList;
	}

	private List<String> preCreateNews(List<String> titleList, List<String> oldStrList, List<String> newStrList) {
		try {
			titleList = createTitleList();

			byte[] readBuffer = createContentList();

			createNewsStringList(oldStrList, newStrList, readBuffer);

		} catch (Exception e) {
			e.getStackTrace();
		}
		return titleList;
	}

	private void createNewsStringList(List<String> oldStrList, List<String> newStrList, byte[] readBuffer)
			throws UnsupportedEncodingException {
		List<String> ParaList = new ArrayList<String>();
		// 뉴스 자르기
		// 뉴스
		String news = new String(readBuffer, "UTF-8");

		// 문단 리스트
		ParaList = Arrays.asList(news.split("\\n"));

		// 문장 리스트
		for (String str : ParaList) {
			// \r 제거 후 split
			oldStrList.addAll(Arrays.asList(str.replaceAll("\r", "").split("\\. ")));
		}

		// . 다시 붙이기
		for (String str : oldStrList) {
			String lastChar = Character.toString(str.charAt(str.length() - 1));
			if (!lastChar.equals(".")) {
				str += ".";
			}
			newStrList.add(str);
		}
	}

	private byte[] createContentList() throws FileNotFoundException, IOException {
		// 바이트 단위로 파일읽기
		String contentPath = "D:\\Dev\\JAVA\\data\\news\\contents.txt"; // 대상 파일
		FileInputStream contentfis = new FileInputStream(contentPath);// 파일 스트림 생성

		// 버퍼 선언
		byte[] readBuffer = new byte[contentfis.available()];
		while (contentfis.read(readBuffer) != -1) {
		}
		contentfis.close();
		return readBuffer;
	}

	private List<String> createTitleList() throws FileNotFoundException, IOException, UnsupportedEncodingException {
		List<String> titleList;
		String titlePath = "D:\\Dev\\JAVA\\data\\news\\titles.txt";
		FileInputStream titlefis = new FileInputStream(titlePath);
		byte[] titleBuffer = new byte[titlefis.available()];
		while (titlefis.read(titleBuffer) != -1) {
		}
		titlefis.close();

		String titles = new String(titleBuffer, "UTF-8");
		titleList = Arrays.asList(titles.split("\\n"));
		return titleList;
	}

}
