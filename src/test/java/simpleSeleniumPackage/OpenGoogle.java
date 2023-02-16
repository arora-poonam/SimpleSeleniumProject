package simpleSeleniumPackage;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class OpenGoogle {
	RemoteWebDriver driver;

	@BeforeTest
	public void beforeTest() throws MalformedURLException {
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
		LocalDateTime now = LocalDateTime.now();  
		String buildName = "build_" + dtf.format(now);
		
		ChromeOptions browserOptions = new ChromeOptions();
		browserOptions.setPlatformName("Windows 10");
		browserOptions.setBrowserVersion("latest");
		Map<String, Object> sauceOptions = new HashMap<String, Object>();
		sauceOptions.put("username", System.getenv("SAUCE_USERNAME"));
        sauceOptions.put("accessKey", System.getenv("SAUCE_ACCESS_KEY"));
		sauceOptions.put("build", buildName);
		sauceOptions.put("name", "Open Google Test");
		browserOptions.setCapability("sauce:options", sauceOptions);

		URL url = new URL("https://ondemand.us-west-1.saucelabs.com:443/wd/hub");
		driver = new RemoteWebDriver(url, browserOptions);
		
	}
	
	@Test
	public void testCase() {
		driver.get("https://www.google.com");
		
	}
	
	@AfterTest
	public void afterTest() {
		driver.quit();
		
	}
}
