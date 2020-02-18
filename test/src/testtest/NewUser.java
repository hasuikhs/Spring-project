package testtest;

import java.util.Random;
import java.util.UUID;

public class NewUser {
	public static void main(String[] args) {
		
		UserVO user = new UserVO();
		
		
		String userid = UUID.randomUUID().toString();
		
		user.setUserid(userid);
		
		Random rand = new Random();
		
		double activity = rand.nextGaussian();
		
		user.setActivity(activity);
		System.out.println(user);
//		for(int i = 0; i < 100; i++) {
//			System.out.println(rand.nextGaussian()+ 1);
//		}
	}
		
}
