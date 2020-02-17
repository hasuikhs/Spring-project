package testtest;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NewsReader {
	public static void main(String[] args) {
		// ���๮��("\n")���� �о���ϴ� ���� readLine�� �ƴ� read�� �ѹ��� �о�� �Ѵ�.

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
			// ����Ʈ ������ �����б�
			String filePath = "D:\\Dev\\JAVA\\data\\news\\contents.txt"; // ��� ����
			FileInputStream fileStream = new FileInputStream(filePath);// ���� ��Ʈ�� ����

			// ���� ����String�� ������ ����
			byte[] readBuffer = new byte[fileStream.available()];
			while (fileStream.read(readBuffer) != -1) {
			}
			fileStream.close();
			
			// ����
			String news = new String(readBuffer, "UTF-8");
			
			// ���� ����Ʈ
			List<String> paragraphList = new ArrayList<String>();
			paragraphList = Arrays.asList(news.split("\\n"));
			
			// ���� ����Ʈ
			List<String> strList = new ArrayList<String>();
			for(String str : paragraphList) {
				strList.addAll(Arrays.asList(str.split("\\. ")));
			}
			
		} catch (Exception e) {
			e.getStackTrace();
		}

	}
}
