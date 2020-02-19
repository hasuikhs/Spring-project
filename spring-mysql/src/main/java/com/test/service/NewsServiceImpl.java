package com.test.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
	public void insert() {
		List<String> titleList = new ArrayList<String>();
		List<String> oldStrList = new ArrayList<String>();
		List<String> newStrList = new ArrayList<String>();

		int inputCnt = 1000;
		
		titleList = preCreateNews(titleList, oldStrList, newStrList);
		
		List<ReporterVO> reporterList = new ArrayList<ReporterVO>();
		reporterList = reportermapper.getList();
		
		double sumActivity = 0;
		for(ReporterVO vo : reporterList) {
			sumActivity += vo.getActivity();
		}
		
		List<Map<String, Object>> newReporterList = new ArrayList<Map<String, Object>>();
		for(ReporterVO vo : reporterList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userno", vo.getUserno());
			map.put("cntNews", Math.round(inputCnt * (vo.getActivity() / sumActivity) ) );
		}

		// 리스트<뉴스VO> 만들고 
		// 맵에 정해진 뉴스의 숫자별로 뉴스VO를 만들어서 리스트<뉴스VO> ADD
		// 리스트<뉴스VO>를 Collections.shuffle(리스트<뉴스VO>)
		// 시간(총 입력수 / 86400초)(일) 정하고 for문 리스트<뉴스VO> 돌아가면서 newsmapper.insert(뉴스VO)
		
		// 랜덤 기사 만들기
		Random rand = new Random();

		NewsVO vo = new NewsVO();
		String title = titleList.get(rand.nextInt(titleList.size()));
		
		vo.setTitle(title);
		// 랜덤 문단 수
		// nextInt(n) : 0 ~ n-1 범위
		int cntPara = rand.nextInt(4) + 3;
		StringBuilder news = new StringBuilder();
		for (int j = 0; j < cntPara; j++) {

			StringBuilder para = new StringBuilder();

			// 랜덤 문장 수
			int cntString = rand.nextInt(4) + 3;

			for (int k = 0; k < cntString; k++) {
				para.append(newStrList.get(rand.nextInt(newStrList.size()))).append(" ");
			}

			news.append(para).append("\n");

		}
		vo.setContent(news.toString());
		System.out.println(vo);

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
