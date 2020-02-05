package com.grade.service;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class GradeServiceImpl implements GradeService {

	@Override
	public List<HashMap<String, String>> readData() {
		String path = "D:\\Dev\\JAVA\\data\\";

		List<ArrayList<HashMap<String, String>>> allGradeList = readFiles(path);

		List<HashMap<String, String>> gradeListByUser = mergeLists(allGradeList);

		calculGrade(gradeListByUser);

		// korean
		Collections.sort(gradeListByUser, new CompareKorDesc());
		int cnt = 0;
		for (Map<String, String> map : gradeListByUser) {
			if (cnt == 0) {
				map.put("korRank", "+");
			} else if (cnt == gradeListByUser.size() - 1) {
				map.put("korRank", "-");
			} else {
				map.put("korRank", " ");
			}
			cnt++;
		}

		// english
		Collections.sort(gradeListByUser, new CompareEngDesc());
		cnt = 0;
		for (Map<String, String> map : gradeListByUser) {
			if (cnt == 0) {
				map.put("engRank", "+");
			} else if (cnt == gradeListByUser.size() - 1) {
				map.put("engRank", "-");
			} else {
				map.put("engRank", " ");
			}
			cnt++;
		}

		// math
		Collections.sort(gradeListByUser, new CompareMathDesc());
		cnt = 0;
		for (Map<String, String> map : gradeListByUser) {
			if (cnt == 0) {
				map.put("mathRank", "+");
			} else if (cnt == gradeListByUser.size() - 1) {
				map.put("mathRank", "-");
			} else {
				map.put("mathRank", " ");
			}
			cnt++;
		}

		// sum
		Collections.sort(gradeListByUser, new CompareSumDesc());
		cnt = 0;
		for (Map<String, String> map : gradeListByUser) {
			if (cnt == 0) {
				map.put("sumRank", "+");
			} else if (cnt == gradeListByUser.size() - 1) {
				map.put("sumRank", "-");
			} else {
				map.put("sumRank", " ");
			}
			cnt++;
		}

		return gradeListByUser;
	}

	// Calculate Grade
	private static void calculGrade(List<HashMap<String, String>> gradeListByUser) {

		for (HashMap<String, String> map : gradeListByUser) {
			Iterator<String> it = map.keySet().iterator();
			int sum = 0;
			double avg = 0;
			while (it.hasNext()) {
				String str = it.next();
				if (!str.equals("name")) {
					sum += Integer.parseInt(map.get(str));
				}
			}
			avg = sum / 3.0;

			String sumStr = Integer.toString(sum);
			String avgStr = String.format("%.3f", avg);
			map.merge("sum", sumStr, (v1, v2) -> sumStr);
			map.merge("avg", avgStr, (v1, v2) -> avgStr);
		}
	}

	// Merge Lists
	private static List<HashMap<String, String>> mergeLists(List<ArrayList<HashMap<String, String>>> allGradeList) {
		List<HashMap<String, String>> gradeListByUser = new ArrayList<HashMap<String, String>>();

		int cnt = 0;
		for (List<HashMap<String, String>> mapList : allGradeList) {
			int innerCnt = 0;
			for (HashMap<String, String> map : mapList) {
				if (cnt == 0) {
					gradeListByUser.add(map);
				} else {
					Iterator<String> it = map.keySet().iterator();
					while (it.hasNext()) {
						String itNext = it.next();
						if (!itNext.equals("name")) {
							gradeListByUser.get(innerCnt).merge(itNext, map.get(itNext), (v1, v2) -> map.get(itNext));
						}
					}
				}
				innerCnt++;
			}
			cnt++;
		}
		return gradeListByUser;
	}

	// Read File
	@SuppressWarnings("resource")
	private static List<ArrayList<HashMap<String, String>>> readFiles(String path) {
		File dir = new File(path);
		File[] fileList = dir.listFiles();

		List<ArrayList<HashMap<String, String>>> allGradeList = new ArrayList<ArrayList<HashMap<String, String>>>();

		for (File file : fileList) {

			List<HashMap<String, String>> gradeList = new ArrayList<HashMap<String, String>>();

			if (file.isFile()) {

				String fileName = file.getName();
				String realFileName = Arrays.asList(fileName.split("\\.")).get(0);

				try {
					String filePath = path + fileName;

					BufferedReader br = new BufferedReader(
							new InputStreamReader(new BufferedInputStream(new FileInputStream(filePath))));

					String line = "";
					while ((line = br.readLine()) != null) {

						Map<String, String> userGrade = new HashMap<String, String>();

						List<String> list = Arrays.asList(line.split("\t"));

						userGrade.put("name", list.get(0));
						userGrade.put(realFileName, list.get(1));

						gradeList.add((HashMap<String, String>) userGrade);
					}

					allGradeList.add((ArrayList<HashMap<String, String>>) gradeList);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return allGradeList;
	}

	// sum & avg
	@Override
	public List<HashMap<String, Object>> calculDataBySubject() {

		List<HashMap<String, String>> mapList = readData();

		List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String, Object>>();

		int korSum = 0;
		int engSum = 0;
		int mathSum = 0;
		int totalSum = 0;
		double avgSum = 0;
		HashMap<String, Object> subjectSum = new HashMap<String, Object>();
		for (HashMap<String, String> map : mapList) {
			korSum += Integer.parseInt(map.get("korean"));
			engSum += Integer.parseInt(map.get("english"));
			mathSum += Integer.parseInt(map.get("math"));
			totalSum += Integer.parseInt(map.get("sum"));
			avgSum += Double.parseDouble(map.get("avg"));
		}
		subjectSum.put("korsum", korSum);
		subjectSum.put("engsum", engSum);
		subjectSum.put("mathsum", mathSum);
		subjectSum.put("totalsum", totalSum);
		subjectSum.put("avgsum", avgSum);

		resultList.add(subjectSum);

		double korAvg = korSum / (double) mapList.size();
		double engAvg = engSum / (double) mapList.size();
		double mathAvg = mathSum / (double) mapList.size();
		double sumAvg = totalSum / (double) mapList.size();
		double totalAvg = avgSum / (double) mapList.size();

		Map<String, Object> subjectAvg = new HashMap<String, Object>();

		subjectAvg.put("koravg", korAvg);
		subjectAvg.put("engavg", engAvg);
		subjectAvg.put("mathavg", mathAvg);
		subjectAvg.put("sumavg", sumAvg);
		subjectAvg.put("totalavg", totalAvg);

		resultList.add((HashMap<String, Object>) subjectAvg);

		return resultList;
	}

	// sort
	@Override
	public List<HashMap<String, String>> sort(String sort) {

		List<HashMap<String, String>> mapList = readData();

		if (sort == null) {
			sort = "sum";
		}
		switch (sort) {
		case "korean":
			Collections.sort(mapList, new CompareKorDesc());
			break;

		case "english":
			Collections.sort(mapList, new CompareEngDesc());
			break;

		case "math":
			Collections.sort(mapList, new CompareMathDesc());
			break;

		case "sum":
			Collections.sort(mapList, new CompareSumDesc());
			break;
		}

		int rank = 1;
		for (Map<String, String> map : mapList) {
			map.put("rank", Integer.toString(rank));
			rank++;
		}

		return mapList;
	}
}

// order by korean
class CompareKorDesc implements Comparator<Map<String, String>> {

	@Override
	public int compare(Map<String, String> o1, Map<String, String> o2) {
		return Integer.parseInt(o1.get("korean")) > Integer.parseInt(o2.get("korean")) ? -1
				: Integer.parseInt(o1.get("korean")) < Integer.parseInt(o2.get("korean")) ? 1 : 0;
	}
}

// order by english
class CompareEngDesc implements Comparator<Map<String, String>> {

	@Override
	public int compare(Map<String, String> o1, Map<String, String> o2) {
		return Integer.parseInt(o1.get("english")) > Integer.parseInt(o2.get("english")) ? -1
				: Integer.parseInt(o1.get("english")) < Integer.parseInt(o2.get("english")) ? 1 : 0;
	}
}

// order by math
class CompareMathDesc implements Comparator<Map<String, String>> {

	@Override
	public int compare(Map<String, String> o1, Map<String, String> o2) {
		return Integer.parseInt(o1.get("math")) > Integer.parseInt(o2.get("math")) ? -1
				: Integer.parseInt(o1.get("math")) < Integer.parseInt(o2.get("math")) ? 1 : 0;
	}
}

// order by sum
class CompareSumDesc implements Comparator<Map<String, String>> {

	@Override
	public int compare(Map<String, String> o1, Map<String, String> o2) {
		return Integer.parseInt(o1.get("sum")) > Integer.parseInt(o2.get("sum")) ? -1
				: Integer.parseInt(o1.get("sum")) < Integer.parseInt(o2.get("sum")) ? 1 : 0;
	}
}