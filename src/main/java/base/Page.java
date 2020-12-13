package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


import utilities.ExcelReader;
import utilities.ExtentManager;
import utilities.TestUtil;


public class Page {

	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static ExcelReader excel = new ExcelReader(
			System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\Testdata.xlsx");
	public static ExcelReader excel1 = new ExcelReader(
			System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\calculateRelief.xlsx");
	public static WebDriverWait wait;
	public ExtentReports rep = ExtentManager.getInstance();
	public static ExtentTest test;
	public static String browser;

	
	/*
	 * Logs,
	 * Properties - OR, Config
	 * Excel
	 * Implicit and ExplicitWait
	 * Extent Reports
	 * 
	 * 
	 * 
	 * 
	 */

	public Page() {

		if (driver == null) {

			try {
				fis = new FileInputStream(System.getProperty("user.dir")
						+ "\\src\\test\\resources\\properties\\Config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				config.load(fis);
				log.debug("Config file loaded !!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		

			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				OR.load(fis);
				log.debug("OR file loaded !!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//Jenkins Browser filter configuration
			if (System.getenv("browser") != null && !System.getenv("browser").isEmpty()) {

				browser = System.getenv("browser");
			} else {

				browser = config.getProperty("browser");

			}

			config.setProperty("browser", browser);

			
			
			if (config.getProperty("browser").equals("firefox")) {

				// System.setProperty("webdriver.gecko.driver", "gecko.exe");
				driver = new FirefoxDriver();

			} else if (config.getProperty("browser").equals("chrome")) {
			
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\chromedriver.exe");

			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_setting_values.notifications", 2);
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", prefs);
			options.addArguments("--disable-extensions");
			options.addArguments("--disable-infobars");

			driver = new ChromeDriver(options);
			}else if (config.getProperty("browser").equals("ie")) {

				System.setProperty("webdriver.ie.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();

			}
			
			
			
			
			
			
			driver.get(config.getProperty("testsiteurl"));
			log.debug("Navigated to : " + config.getProperty("testsiteurl"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),
					TimeUnit.SECONDS);
			wait = new WebDriverWait(driver, 5);
			
			
		}
	}
	
	
	
	public static void quit(){
		
		driver.quit();
		
	}
	
	
	
	public static void waitForElement(String locator) {

		try {
			String ElementWait1 = "30";
			int WaitElementSeconds1 = new Integer(ElementWait1);
			if (locator.endsWith("_XPATH")) {
				WebDriverWait wait = new WebDriverWait(driver, WaitElementSeconds1);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(locator))));
				// ATUReports.add("Wait - " + values[0], false);
			}
		} catch (Exception e) {
			// ATUReports.add("Wait - " + values[0], LogAs.FAILED,
			// new CaptureScreen(CaptureScreen.ScreenshotOf.BROWSER_PAGE));
			Reporter.log(
					"Waited for Element and the Element does not appear in the given time period so test got failure",
					true);
			e.printStackTrace();
		}

	}
	
	
	public static void jsClickByXPath(String locator) {

		try {
			waitForElement(locator);

			if (locator.endsWith("_XPATH")) {

				WebElement element = driver.findElement(By.xpath(OR.getProperty(locator)));

				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();", element);
				log.debug("Clicking on : " + locator);
				test.log(LogStatus.INFO, "Clicking on : " + locator);

			}

		} catch (Exception e) {

			e.printStackTrace();
			Assert.fail();
		}
	}
	
	public static void highLightElement(String locator) {
		if (locator.endsWith("_XPATH")) {

			WebElement webElement = driver.findElement(By.xpath(OR.getProperty(locator)));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", webElement,
					"color: yellow; border: 3px solid yellow;");
			js.executeScript("arguments[0].setAttribute('style', arguments[1]);", webElement, "");
		}
	}
	
	
	
	public static boolean isDisplayed(String locator) {

		try {
			if (locator.endsWith("_XPATH")) {

				WebElement webElement = driver.findElement(By.xpath(OR.getProperty(locator)));
				return webElement.isDisplayed();
			}
		}

		catch (Exception e) {
		}
		return false;
	}
	
	
	

	public static String getText(String locator) {

		try {
			WebElement webElement = driver.findElement(By.xpath(OR.getProperty(locator)));

			String text = webElement.getText().trim();
			Reporter.log(text, true);
			// ATUReports.add(values[0], "", text, true);
			return text;

		} catch (Exception e) {
			e.printStackTrace();

			return null;

		}

	}
	
	
	
	public static void getCssValueColor(String locator) {
		WebElement webElement = driver.findElement(By.xpath(OR.getProperty(locator)));

		String color = webElement.getCssValue("background-color");
		System.out.print(webElement.getText() + " ");
		System.out.println("Color " + color);
		String hex = Color.fromString(color).asHex();
		System.out.println(hex);
if (hex.equals(hex)){
	System.out.println("hex colour is" + hex);
}
	}


	public Boolean CashDispensed(String locator) {
		WebElement webElement = driver.findElement(By.xpath(OR.getProperty(locator)));
		System.out.println(webElement.getText());
		
		if(webElement.getText().equals("Cash dispensed"))
            return true;
		
        else
            return false;
		
	}
	
	
	
	
	
	
	
	
	
	
	//Common Keywords
	public static void click(String locator) {

		if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).click();
		}
		log.debug("Clicking on an Element : "+locator);
		test.log(LogStatus.INFO, "Clicking on : " + locator);
	}

	public static void type(String locator, String value) {

		if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);
		}

		log.debug("Typing in an Element : "+locator+" entered value as : "+value);
		
		test.log(LogStatus.INFO, "Typing in : " + locator + " entered value as " + value);

	}
	
	static WebElement dropdown;

	public void select(String locator, String value) {

		if (locator.endsWith("_CSS")) {
			dropdown = driver.findElement(By.cssSelector(OR.getProperty(locator)));
		} else if (locator.endsWith("_XPATH")) {
			dropdown = driver.findElement(By.xpath(OR.getProperty(locator)));
		} else if (locator.endsWith("_ID")) {
			dropdown = driver.findElement(By.id(OR.getProperty(locator)));
		}
		
		Select select = new Select(dropdown);
		select.selectByVisibleText(value);

		log.debug("Selecting from an element : "+locator+" value as : "+value);
		test.log(LogStatus.INFO, "Selecting from dropdown : " + locator + " value as " + value);

	}

	public boolean isElementPresent(By by) {

		try {

			driver.findElement(by);
			return true;

		} catch (NoSuchElementException e) {

			return false;

		}

	}
	
	public static void wait(String inputData) {
		// wait(5);
		try {
			int time = Integer.parseInt(inputData);
			int seconds = time * 1000;
			Thread.sleep(seconds);
			Reporter.log("Waited for element", true);
			// ATUReports.add("Waited for element", inputData, false);
		} catch (InterruptedException e) {
			Reporter.log("Waited for element", false);
			// ATUReports.add("Wait for element", inputData, LogAs.FAILED,
			// new CaptureScreen(CaptureScreen.ScreenshotOf.BROWSER_PAGE));
			e.printStackTrace();
			Assert.fail();
		}
	}

	public static void verifyEquals(String expected, String actual) throws IOException {

		try {

			Assert.assertEquals(actual, expected);

		} catch (Throwable t) {

			TestUtil.captureScreenshot();
			// ReportNG
			Reporter.log("<br>" + "Verification failure : " + t.getMessage() + "<br>");
			Reporter.log("<a target=\"_blank\" href=" + TestUtil.screenshotName + "><img src=" + TestUtil.screenshotName
					+ " height=200 width=200></img></a>");
			Reporter.log("<br>");
			Reporter.log("<br>");
			// Extent Reports
			test.log(LogStatus.FAIL, " Verification failed with exception : " + t.getMessage());
			test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName));

		}

}
}
