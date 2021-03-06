package testtest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

// http://mwultong.blogspot.com/2006/11/java-gaussian-gauss-random-number.html
public class NormDist {
	public static void main(String[] args) {
		Random random = new Random();

		List<Double> list = new ArrayList<Double>();

		for (int i = 0; i < 24; i++) {
			list.add(random.nextGaussian() + 3);
		}

		Collections.sort(list);
		double sum = 0;
		for (double x : list) {
			sum += x;
		}

		for (double x : list) {
			System.out.println("비율 : " + x / sum);
		}

		System.out.println("===================================");
		System.out.println("합계 : " + sum);
		System.out.println("최대 : " + Collections.max(list) + "");
		System.out.println("최소 : " + Collections.min(list));
	}
}