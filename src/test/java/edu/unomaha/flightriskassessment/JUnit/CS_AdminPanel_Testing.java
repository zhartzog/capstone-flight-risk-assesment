package edu.unomaha.flightriskassessment.JUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.regex.Pattern;
import static org.apache.commons.lang3.StringUtils.join;

// Ensure frontend is running before running this test suite.

public class CS_AdminPanel_Testing {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "https://www.google.com/";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testOpenPanels() throws Exception {
		selenium.open("http://localhost:3000/AdminPanel/SearchStudent");
		selenium.click("xpath=(//button[@type='button'])[3]");
		selenium.click("//div[@id='root']/div/div/div/div[2]/div/div");
		selenium.click("//div[@id='root']/div/div/div/div[2]/div[2]/div[2]");
		selenium.click("//div[@id='root']/div/div/div/div[2]/div[2]/div[3]");
		selenium.click("//div[@id='root']/div/div/div/div[2]/div[2]/div[4]");
		selenium.click("//div[@id='root']/div/div/div/div[2]/div[2]/div");
		selenium.click("//div[@id='root']/div/div/div/div[2]/div/div[2]");
		selenium.click("//div[@id='root']/div/div/div/div[2]/div[2]/div[2]");
		selenium.click("//div[@id='root']/div/div/div/div[2]/div[2]/div[3]");
		selenium.click("//div[@id='root']/div/div/div/div[2]/div[2]/div[4]");
		selenium.click("//div[@id='root']/div/div/div/div[2]/div[2]/div[5]");
		selenium.click("//div[@id='root']/div/div/div/div[2]/div[2]/div[6]");
		selenium.click("//div[@id='root']/div/div/div/div[2]/div[2]/div");
		selenium.click("xpath=(//button[@type='button'])[4]");
		selenium.click("xpath=(//button[@type='button'])[8]");
		selenium.click("xpath=(//button[@type='button'])[9]");
		selenium.click("xpath=(//button[@type='button'])[10]");
		selenium.click("xpath=(//button[@type='button'])[7]");
		selenium.click("xpath=(//button[@type='button'])[6]");
		selenium.click("xpath=(//button[@type='button'])[8]");
		selenium.click("xpath=(//button[@type='button'])[9]");
		selenium.click("xpath=(//button[@type='button'])[10]");
		selenium.click("xpath=(//button[@type='button'])[11]");
		selenium.click("xpath=(//button[@type='button'])[12]");
		selenium.click("xpath=(//button[@type='button'])[7]");
		selenium.click("xpath=(//button[@type='button'])[3]");
	}

	@Test
	public void testSetHigh() throws Exception {
		selenium.open("http://localhost:3000/AdminPanel/CurrentSettings");
		verifyEquals("> Low: 1000 > Medium: 800 > High: 600 >", selenium.getText("//div[@id='root']/div/div/div/div[2]/div[2]/div[5]/p"));
		selenium.click("xpath=(//button[@type='button'])[4]");
		selenium.click("//input[@type='number']");
		selenium.type("//input[@type='number']", "123");
		selenium.click("name=Ceiling (Day)");
		selenium.click("xpath=(//button[@type='button'])[3]");
		verifyEquals("> Low: 1000 > Medium: 800 > High: 123 >", selenium.getText("//div[@id='root']/div/div/div/div[2]/div[2]/div[5]/p"));
	}

	@Test
	public void testSetMed() throws Exception {
		selenium.open("http://localhost:3000/AdminPanel/CurrentSettings");
		verifyEquals("> Low: 1000 > Medium: 800 > High: 123 >", selenium.getText("//div[@id='root']/div/div/div/div[2]/div[2]/div[5]/p"));
		selenium.click("xpath=(//button[@type='button'])[4]");
		selenium.click("xpath=(//input[@type='number'])[2]");
		selenium.type("xpath=(//input[@type='number'])[2]", "123");
		selenium.click("xpath=(//button[@name='Ceiling (Day)'])[2]");
		selenium.click("xpath=(//button[@type='button'])[3]");
		verifyEquals("> Low: 1000 > Medium: 123 > High: 123 >", selenium.getText("//div[@id='root']/div/div/div/div[2]/div[2]/div[5]/p"));
	}

	@Test
	public void testSetLow() throws Exception {
		selenium.open("http://localhost:3000/AdminPanel/CurrentSettings");
		verifyEquals("> Low: 1000 > Medium: 123 > High: 123 >", selenium.getText("//div[@id='root']/div/div/div/div[2]/div[2]/div[5]/p"));
		selenium.click("xpath=(//button[@type='button'])[4]");
		selenium.click("xpath=(//input[@type='number'])[3]");
		selenium.type("xpath=(//input[@type='number'])[3]", "123");
		selenium.click("xpath=(//button[@name='Ceiling (Day)'])[3]");
		selenium.click("xpath=(//button[@type='button'])[3]");
		verifyEquals("> Low: 123 > Medium: 123 > High: 123 >", selenium.getText("//div[@id='root']/div/div/div/div[2]/div[2]/div[5]/p"));
	}

	@Test
	public void testSetAll() throws Exception {
		selenium.open("http://localhost:3000/AdminPanel/CurrentSettings");
		verifyEquals("> Low: 123 > Medium: 123 > High: 123 >", selenium.getText("//div[@id='root']/div/div/div/div[2]/div[2]/div[5]/p"));
		selenium.click("xpath=(//button[@type='button'])[4]");
		selenium.click("//input[@type='number']");
		selenium.type("//input[@type='number']", "600");
		selenium.click("xpath=(//input[@type='number'])[2]");
		selenium.type("xpath=(//input[@type='number'])[2]", "800");
		selenium.click("xpath=(//input[@type='number'])[3]");
		selenium.type("xpath=(//input[@type='number'])[3]", "1000");
		selenium.click("name=Ceiling (Day)");
		selenium.click("xpath=(//button[@name='Ceiling (Day)'])[2]");
		selenium.click("xpath=(//button[@name='Ceiling (Day)'])[3]");
		selenium.click("xpath=(//button[@type='button'])[3]");
		verifyEquals("> Low: 1000 > Medium: 800 > High: 600 >", selenium.getText("//div[@id='root']/div/div/div/div[2]/div[2]/div[5]/p"));
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
