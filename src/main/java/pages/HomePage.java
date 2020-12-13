package pages;




import java.io.IOException;

import base.Page;



public class HomePage extends Page {
	
	public HomePage fileUpload(String Path) {
		wait("2");
		type("UploadCSV_XPATH", Path);
		wait("2");
	return new HomePage();
		
	}
	
	public HomePage CalculateRelief(String natid, String relief) {
		
	return new HomePage();
		
	}
	
	public void DispenseNow() throws IOException {
		getCssValueColor("DispenseNow_XPATH");
		
		getText("DispenseNow_XPATH");
		verifyEquals("Dispense Now", getText("DispenseNow_XPATH"));
		click("DispenseNow_XPATH");
		CashDispensed("CashDispensed_XPATH");
	}

	
;
	

	
	

}
