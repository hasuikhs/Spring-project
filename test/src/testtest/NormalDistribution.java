package testtest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

// http://mwultong.blogspot.com/2006/11/java-gaussian-gauss-random-number.html
public class NormalDistribution {
	public static void main(String[] args) {
		Random random = new Random();
		
		List<Double> list = new ArrayList<Double>();
		
		for (int i = 0; i < 100; i++) {
			list.add(random.nextGaussian());
		}
		
		Collections.sort(list);
		
		for(double x : list) {
			System.out.println(x);
		}
	}
}