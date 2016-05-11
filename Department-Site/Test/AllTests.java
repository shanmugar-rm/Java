package se.term_project;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AllTests extends TestCase {

	public static Test suite() {
		TestSuite suite = new TestSuite(AllTests.class.getName());
		//$JUnit-BEGIN$
		suite.addTestSuite(Test1.class);
		suite.addTestSuite(Test2.class);
		suite.addTestSuite(Test3.class);
		suite.addTestSuite(Test4.class);
		suite.addTestSuite(Test5.class);
		suite.addTestSuite(Test6.class);
		suite.addTestSuite(Test7.class);
		suite.addTestSuite(Test8.class);
		suite.addTestSuite(Test9.class);
		suite.addTestSuite(Test10.class);
		suite.addTestSuite(Test11.class);
		suite.addTestSuite(Test12.class);
		suite.addTestSuite(Test13.class);
		suite.addTestSuite(Test14.class);
		suite.addTestSuite(Test15.class);
		suite.addTestSuite(Test16.class);
		suite.addTestSuite(Test17.class);
		suite.addTestSuite(Test18.class);
		suite.addTestSuite(Test19.class);
		suite.addTestSuite(Test20.class);
		suite.addTestSuite(Test21.class);
		suite.addTestSuite(Test22.class);
		suite.addTestSuite(Test23.class);
		suite.addTestSuite(Test24.class);
		suite.addTestSuite(Test25.class);
		suite.addTestSuite(Test26.class);
		suite.addTestSuite(Test27.class);
		suite.addTestSuite(Test28.class);
		suite.addTestSuite(Test29.class);
		suite.addTestSuite(Test30.class);
		suite.addTestSuite(Test31.class);
		//$JUnit-END$
		return suite;
	}

}
