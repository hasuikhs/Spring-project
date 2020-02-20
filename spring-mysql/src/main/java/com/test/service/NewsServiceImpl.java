package com.test.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

		int inputCnt = 100;

		titleList = preCreateNews(titleList, oldStrList, newStrList);

		List<Map<String, Object>> newReporterList = createNewReporterList(inputCnt);

		List<NewsVO> newsList = createRandomNewsByReporterActivity(titleList, newStrList, newReporterList);

		Collections.shuffle(newsList);

		// �ð�(�� �Է¼� / 86400��)(��) ���ϰ� for�� ����Ʈ<����VO> ���ư��鼭 newsmapper.insert(����VO)
		int millisTimeOfDay = 86400000;
		
		int cnt = newsList.size();
		
		Random rand = new Random();
		
		int normalIntervalWriteTime = millisTimeOfDay / cnt;
		
		int maxIntervalRange = (int) Math.ceil(normalIntervalWriteTime * 1.2);
		int minIntervalRange = (int) Math.floor(normalIntervalWriteTime * 0.8);
		
		for (NewsVO news : newsList) {
			int intervalRange = rand.nextInt((maxIntervalRange - minIntervalRange) +1) + minIntervalRange;
		}

		System.out.println(cnt);
	}

	private List<NewsVO> createRandomNewsByReporterActivity(List<String> titleList, List<String> newStrList,
			List<Map<String, Object>> newReporterList) {
		Random rand = new Random();

		// ����Ʈ<����VO> �����
		List<NewsVO> newsList = new ArrayList<NewsVO>();

		// �ʿ� ������ ������ ���ں��� ����VO�� ���� ����Ʈ<����VO> ADD
		for (Map<String, Object> map : newReporterList) {
			for (int i = 0; i <  ((Long) map.get("cntNews")).intValue(); i++) {
				NewsVO newsvo = new NewsVO();
				// ���� ��ȣ
				newsvo.setUserno((Integer) map.get("userno"));

				// ����
				String title = titleList.get(rand.nextInt(titleList.size()));
				newsvo.setTitle(title);

				// ����
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
		// ���� �ڸ���
		// ����
		String news = new String(readBuffer, "UTF-8");

		// ���� ����Ʈ
		ParaList = Arrays.asList(news.split("\\n"));

		// ���� ����Ʈ
		for (String str : ParaList) {
			// \r ���� �� split
			oldStrList.addAll(Arrays.asList(str.replaceAll("\r", "").split("\\. ")));
		}

		// . �ٽ� ���̱�
		for (String str : oldStrList) {
			String lastChar = Character.toString(str.charAt(str.length() - 1));
			if (!lastChar.equals(".")) {
				str += ".";
			}
			newStrList.add(str);
		}
	}

	private byte[] createContentList() throws FileNotFoundException, IOException {
		// ����Ʈ ������ �����б�
		String contentPath = "D:\\Dev\\JAVA\\data\\news\\contents.txt"; // ��� ����
		FileInputStream contentfis = new FileInputStream(contentPath);// ���� ��Ʈ�� ����

		// ���� ����
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
