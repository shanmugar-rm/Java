package se.bank.src;


import java.util.Random;

public class OTP {

	public int create() {
		  Random randomGenerator = new Random();
		  
		  int randomInt = randomGenerator.nextInt(899999)+100000;
		  
		  System.out.println("In otp class");
		  //Integer obj1 = new Integer(2009);
		 // String obj2 = new String(randomInt);
		  String obj2 = String.valueOf(randomInt);
		 // System.out.println("hashCode for an integer is " + obj1.hashCode());
		  System.out.println("hashCode for a string is " + obj2.hashCode());
		  
		
		return randomInt;
	}
}

 
