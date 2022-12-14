package testScenarios;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class TestNGWindows {
	
	public String username = "shajahan.ar";
	public String accesskey = "N39EHbYmnKurXr0MhNKDCwCcuCO0RcFYdRzHA7PxT0J9HqMlyq";
	public static RemoteWebDriver driver = null;
	public String gridURL = "@hub.lambdatest.com/wd/hub";
	boolean status = false;
	public WebDriverWait wait;

	@BeforeClass
	public void setUp() throws Exception {
		Reporter.log("------Starting Chrome in Win10 --------", true);
		ChromeOptions browserOptions = new ChromeOptions();
		browserOptions.setPlatformName("Windows 10");
		browserOptions.setBrowserVersion("86.0");
		HashMap<String, Object> ltOptions = new HashMap<String, Object>();
		ltOptions.put("username", username);
		ltOptions.put("accessKey", accesskey);
		ltOptions.put("visual", true);
		ltOptions.put("video", true);
		ltOptions.put("network", true);
		ltOptions.put("build", "LambdaWinChromeBuild");
		ltOptions.put("project", "LambdaDemo2");
		ltOptions.put("name", "WinChromeTestParallel");
		ltOptions.put("console", "true");
		ltOptions.put("w3c", true);
		browserOptions.setCapability("LT:Options", ltOptions);
		try {
			driver = new RemoteWebDriver(new URL("https://" + username + ":" + accesskey + gridURL), browserOptions);
		} catch (MalformedURLException e) {
			System.out.println("Invalid grid URL");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

	@Test
	public void lambdaTest() {
		/*----------- 
		 * 1. Navigate to https://www.lambdatest.com/

		 * - Navigating to Lambdatest,
		 * - Using navigate().to() instead of get()
		 * ------------------------- */
		Reporter.log("---------- Step 1 started -------------", true);
		driver.navigate().to("https://www.lambdatest.com/");
		Reporter.log("---------- Step 1 ended -------------", true);
		/*
		 * ------------- 2. Perform an explicit wait till the time all the elements in
		 * the DOM are available.
		 * 
		 * - Explicit wait 
		 * -------------------------*/
		Reporter.log("---------- Step 2 started -------------", true);
		wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		Reporter.log("---------- Step 2 ended -------------", true);
		/*-------------- 
		 * 3. Scroll to the WebElement ???SEE ALL INTEGRATIONS??? using the scrollIntoView() method. You are free to use any of the available
			  web locators (e.g., XPath, CssSelector, etc.).

		 * - Scrolling using scrollIntoView method
		 * ---------------- */
		Reporter.log("---------- Step 3 started -------------", true);
		WebElement sai = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//img[@alt='LambdaTest Integrations']/..")));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(false);", sai);
		Reporter.log("---------- Step 3 ended -------------", true);
		/*------------ 
		 * 4. Click on the link and ensure that it opens in a new Tab.
		 * 
		 * - Click intercepted by other webelement; closed cookies 
		 * - Link is not opening in new tab by default on click - Made Link Opening in new Tab
		 * -----------------
		 */
		Reporter.log("---------- Step 4 started -------------", true);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='X']"))).click();
		String clicklnk = Keys.chord(Keys.CONTROL, Keys.ENTER);
		sai.sendKeys(clicklnk);
		Reporter.log("---------- Step 4 ended -------------", true);
		/* ----------- 
		 * 5. Save the window handles in a "List" (or array). Print the
		 *    window handles of the opened windows (now there are two windows open).
		 * 
		 * - Storing WindowHandles to "List" and printing. - return type of
		     getWindowHandles is Set, - typecasted to ArrayList to store in List
		 * -------------
		 */
		Reporter.log("---------- Step 5 started -------------", true);
		List<String> allHandles = new ArrayList<String>(driver.getWindowHandles());
		String parentHandle = driver.getWindowHandle();
		for (String wH : allHandles) {
			System.out.println(wH);
			if (wH != parentHandle)
				driver.switchTo().window(wH); // Switching to New Tab Opening on step 4
		}
		Reporter.log("---------- Step 5 ended -------------", true);
		/*-------
		 * 6. Verify whether the URL is the same as the expected URL (if not, throw an
		 *    Assert).

		 * - Verifying URL using SoftAssert
		 *--------*/
		Reporter.log("---------- Step 6 started -------------", true);
		String expectedUrl = "https://www.lambdatest.com/integrations";
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(driver.getCurrentUrl(), expectedUrl);
		Reporter.log(driver.getCurrentUrl());
		Reporter.log("---------- Step 6 ended -------------", true);
		/*---------
		 * 7. On that page, scroll to the page where the WebElement (Codeless Automation)
		 * 	  is present.
		 *----------*/
		Reporter.log("---------- Step 7 started -------------", true);
		WebElement codeless = driver.findElement(By.xpath("//h2[(text() = 'Codeless Automation')]"));
		js.executeScript("arguments[0].scrollIntoView();", wait.until(ExpectedConditions.visibilityOf(codeless)));
		Reporter.log("---------- Step 7 ended -------------", true);
		/*---------
		 * 8. Click the ???LEARN MORE??? link for Testing Whiz. The page should open in the
		 *    same window.

		 * - There is no "LEARN MORE' link for Testing whiz,
		 * - clicking "Integrate Katalon Studio with LambdaTest" link for Testing Whiz
		 * - Opens in same window
		 *-----------*/
		Reporter.log("---------- Step 8 started -------------", true);
		WebElement learnMore = driver.findElement(By.xpath("//a[text()='Integrate Katalon Studio with LambdaTest']"));
		learnMore.click();
		Reporter.log("---------- Step 8 ended -------------", true);
		/*
		 * 9. Check if the title of the page is ???TestingWhiz Integration | LambdaTest???.
		 * If not, raise an Assert.
		 * 
		 * - Page Title : Step By Step Guide to Integrate Lambdatest With Katalon | LambdaTest
		 */
		Reporter.log("---------- Step 9 started -------------", true);
		String expectedTitle = "Step By Step Guide to Integrate Lambdatest With Katalon | LambdaTest";
		Reporter.log("Title in TestSenario Document wrong: 'TestingWhiz Integration | LambdaTest'", true);
		Reporter.log("Actual Title: " + driver.getTitle(), true);
		sa.assertEquals(driver.getTitle(), expectedTitle);
		Reporter.log("---------- Step 9 ended -------------", true);
		/*
		 * 10. Close the current window using the window handle [which we obtained in
		 * step (5)]
		 * 
		 * - Please check step 5, control manually transfered in 5. 
		 * - why do we use window handle to close "current window"; just driver.close() works
		 */
		Reporter.log("---------- Step 10 started -------------", true);
		allHandles.remove(driver.getWindowHandle());
		driver.close();// closing current window
		driver.switchTo().window(allHandles.get(0));// switching control to existing window
		Reporter.log("---------- Step 10 ended -------------", true);
		/*
		 * ---------- 11. Print the current window count. ----------
		 */
		Reporter.log("---------- Step 11 started -------------", true);
		Reporter.log("Current Window count: " + allHandles.size(), true);
		Reporter.log("---------- Step 11 ended -------------", true);
		/*----------
		 * 12. On the current window, set the URL to https://www.lambdatest.com/blog. 
		 *----------*/
		Reporter.log("---------- Step 12 started -------------", true);
		driver.navigate().to("https://www.lambdatest.com/blog");
		Reporter.log("---------- Step 12 ended -------------", true);
		/*----------
		 * 13. Click on the ???Community??? link and verify whether the URL is
		 	   https://community.lambdatest.com/.
		 *----------*/
		Reporter.log("---------- Step 13 started -------------", true);
		expectedUrl = "https://community.lambdatest.com/";
		driver.findElement(By.linkText("Community")).click();
		Reporter.log("Community URL verification result: " + driver.getCurrentUrl().equals(expectedUrl), true);
		Reporter.log("---------- Step 13 ended -------------", true);
		/*---------- 
		 * 14. Close the current browser window.
		 * 
		 * - invoking @AfterMethod teardown() from BaseTest 
		 * ----------*/
		Reporter.log("---------- Step 14 -------------", true);
		Reporter.log("---------- Tear Down -------------", true);

		sa.assertAll();

	}

	@AfterClass
	public void tearDown() throws Exception {
		if (driver != null) {
			((JavascriptExecutor) driver).executeScript("lambda-status=" + status);
			driver.quit();
		}
	}
}
