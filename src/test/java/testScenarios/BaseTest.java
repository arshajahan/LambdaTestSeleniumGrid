package testScenarios;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseTest {
	
	public String username = "shajahan.ar";
	public String accesskey = "N39EHbYmnKurXr0MhNKDCwCcuCO0RcFYdRzHA7PxT0J9HqMlyq";
	public static RemoteWebDriver driver = null;
	public String gridURL = "@hub.lambdatest.com/wd/hub";
	boolean status = false;
	public WebDriverWait wait;

}
