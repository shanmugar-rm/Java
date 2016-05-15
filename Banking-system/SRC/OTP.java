package se.bank.src;

import java.util.Random;

public class OTP {

	public int create() {
		  Random randomGenerator = new Random();
		  
		  int randomInt = randomGenerator.nextInt(899999)+100000;

		  String obj2 = String.valueOf(randomInt);
		return randomInt;
	}
}
