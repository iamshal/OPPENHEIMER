package testcases;

import java.io.IOException;

import org.testng.annotations.Test;

import pages.HomePage;

@Test
public class DispenseNowTestcase extends BaseTest {
	
	public void dispesneNowTestcase() throws IOException {
		HomePage hp = new HomePage();
		hp.DispenseNow();
}
}