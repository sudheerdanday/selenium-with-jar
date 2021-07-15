package org.wtt.test;

import java.net.MalformedURLException;

import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.TestRunner;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.itest.util.PropertiesUtility;

public class SeleniumDocker {

	private static WebDriver driver;

	@BeforeSuite
	public void setupBeforeSuite(ITestContext context) {

		TestRunner testRunner = (TestRunner) context;

	}

	@BeforeTest
	public void setUp() throws MalformedURLException {

		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setPlatform(Platform.LINUX);
		String key = PropertiesUtility.loadApplicationProperties().getProperty("grid.url");
		driver = new RemoteWebDriver(new URL(key), capabilities);
		System.out.println("driver loaded.................ra worst");
		System.out.println(driver);
	}

	@AfterTest
	public void afterTest() throws MalformedURLException {
		// declaration and instantiation of objects/variables
		// driver.quit();
	}

	@Test(priority = 1, description = "this is test simple", testName = "simpleTest")
	public void simpleTest() throws MalformedURLException {
		// Launch website
		driver.navigate().to(PropertiesUtility.loadApplicationProperties().getProperty("application.url"));
		String tittle = driver.getTitle();
		System.out.println("tittle" + tittle);
		Assert.assertEquals("Tutorials List - Javatpoint", driver.getTitle());
	}

	@Test(priority = 2, description = "this is test to test the check box", testName =  "testCheckBox")
	public void testCheckBox() throws MalformedURLException {
		WebElement element = driver.findElement(By.xpath("//*[@id=\"link\"]/div/ul/li[3]/a"));
		element.click();
		WebElement element2 = driver.findElement(By.xpath("//*[@id=\"city\"]/table/tbody/tr/td/h1"));
		String expectedText = element2.getText();
		System.out.println("checkBox():" + expectedText);
		Assert.assertEquals("Java Tutorial", expectedText);
		driver.navigate().refresh();
	}

}
