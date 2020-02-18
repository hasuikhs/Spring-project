package testtest;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class NewsReader {
	public static void main(String[] args) {
		// ���๮��("\n")���� �о���ϴ� ���� readLine�� �ƴ� read�� �ѹ��� �о�� �Ѵ�.

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

			// ����Ʈ ������ �����б�
			String contentPath = "D:\\Dev\\JAVA\\data\\news\\contents.txt"; // ��� ����
			FileInputStream contentfis = new FileInputStream(contentPath);// ���� ��Ʈ�� ����

			// ���� ����
			byte[] readBuffer = new byte[contentfis.available()];
			while (contentfis.read(readBuffer) != -1) {
			}
			contentfis.close();

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

		} catch (Exception e) {
			e.getStackTrace();
		}

		// ���� ��� �����
		Random rand = new Random();

		// �Է� ��� ��
		int inputNumber = 10000;
		for (int i = 0; i < inputNumber; i++) {
			NewsVO vo = new NewsVO();
			String title = titleList.get(rand.nextInt(titleList.size()));
			vo.setTitle(title);
			// ���� ���� ��
			// nextInt(n) : 0 ~ n-1 ����
			int cntPara = rand.nextInt(4) + 3;
			StringBuilder news = new StringBuilder();
			for (int j = 0; j < cntPara; j++) {

				StringBuilder para = new StringBuilder();

				// ���� ���� ��
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
