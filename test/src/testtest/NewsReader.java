package testtest;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NewsReader {
	public static void main(String[] args) {
		// 개행문자("\n")까지 읽어야하는 경우는 readLine이 아닌 read로 한번에 읽어야 한다.

//		Path path = Paths.get("D:\\Dev\\JAVA\\data\\news\\contents.txt");
//
//		Charset cs = StandardCharsets.UTF_8;
//		List<String> list = new ArrayList<String>();
//		try {
//			list = Files.readAllLines(path, cs);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		for (String x : list) {
//			System.out.println(x);
//			
//		}
//		

		try {
			// 바이트 단위로 파일읽기
			String filePath = "D:\\Dev\\JAVA\\data\\news\\contents.txt"; // 대상 파일
			FileInputStream fileStream = new FileInputStream(filePath);// 파일 스트림 생성

			// 버퍼 선언String의 마지막 문자
			byte[] readBuffer = new byte[fileStream.available()];
			while (fileStream.read(readBuffer) != -1) {
			}
			fileStream.close();
			
			// 뉴스
			String news = new String(readBuffer, "UTF-8");
			
			// 문단 리스트
			List<String> paragraphList = new ArrayList<String>();
			paragraphList = Arrays.asList(news.split("\\n"));
			
			// 문장 리스트
			List<String> strList = new ArrayList<String>();
			for(String str : paragraphList) {
				strList.addAll(Arrays.asList(str.split("\\. ")));
			}
			
		} catch (Exception e) {
			e.getStackTrace();
		}

	}
}
