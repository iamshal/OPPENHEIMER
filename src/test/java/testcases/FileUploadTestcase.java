package testcases;


import org.testng.annotations.Test;

import utilities.TestUtil;

import base.Page;
import pages.HomePage;

@Test(dataProviderClass = TestUtil.class, dataProvider = "dp")
public class FileUploadTestcase extends BaseTest {
	
	public void fileUploadTestcase(String Path) {
		HomePage hp = new HomePage();
		System.out.println(Path);
		System.out.println("-------------------------------------------------------");
		hp.fileUpload(Path);

}
}
