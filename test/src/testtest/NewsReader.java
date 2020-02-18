package testtest;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class NewsReader {
	public static void main(String[] args) {
		// 개행문자("\n")까지 읽어야하는 경우는 readLine이 아닌 read로 한번에 읽어야 한다.

		List<String> titleList = new ArrayList<String>();
		List<String> ParaList = new ArrayList<String>();
		List<String> oldStrList = new ArrayList<String>();
		List<String> newStrList = new ArrayList<String>();
		try {
			String titlePath = "D:\\Dev\\JAVA\\data\\news\\titles.txt";
			FileInputStream titlefis = new FileInputStream(titlePath);
			byte[] titleBuffer = new byte[titlefis.available()];
			while (titlefis.read(titleBuffer) != -1) {
			}
			titlefis.close();

			String titles = new String(titleBuffer, "UTF-8");
			titleList = Arrays.asList(titles.split("\\n"));

			// 바이트 단위로 파일읽기
			String contentPath = "D:\\Dev\\JAVA\\data\\news\\contents.txt"; // 대상 파일
			FileInputStream contentfis = new FileInputStream(contentPath);// 파일 스트림 생성

			// 버퍼 선언
			byte[] readBuffer = new byte[contentfis.available()];
			while (contentfis.read(readBuffer) != -1) {
			}
			contentfis.close();

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

		} catch (Exception e) {
			e.getStackTrace();
		}

		// 랜덤 기사 만들기
		Random rand = new Random();

		// 입력 기사 수
		int inputNumber = 10000;
		for (int i = 0; i < inputNumber; i++) {
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
	}
}
