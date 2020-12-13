package testcases;

import org.testng.annotations.Test;

import base.Page;
import pages.HomePage;
import utilities.TestUtil;

@Test(dataProviderClass = TestUtil.class, dataProvider = "dp1")
public class CalculateRelief extends BaseTest {
	public void calculateRelief(String natid, String relief) {
		HomePage hp = new HomePage();
		hp.CalculateRelief(natid, relief);
		System.out.println(natid + relief) ;		
	}
}
