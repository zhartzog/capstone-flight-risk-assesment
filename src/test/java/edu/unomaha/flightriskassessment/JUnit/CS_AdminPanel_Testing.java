package edu.unomaha.flightriskassessment.JUnit;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

// Ensure the frontend is running before running this test suite.

public class CS_AdminPanel_Testing {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "https://www.google.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testOpenPanels() throws Exception {
    driver.get("http://localhost:3000/AdminPanel/SearchStudent");
    driver.findElement(By.xpath("(//button[@type='button'])[3]")).click();
    driver.findElement(By.xpath("//div[@id='root']/div/div/div/div[2]/div/div")).click();
    driver.findElement(By.xpath("//div[@id='root']/div/div/div/div[2]/div[2]/div[2]")).click();
    driver.findElement(By.xpath("//div[@id='root']/div/div/div/div[2]/div[2]/div[3]")).click();
    driver.findElement(By.xpath("//div[@id='root']/div/div/div/div[2]/div[2]/div[4]")).click();
    driver.findElement(By.xpath("//div[@id='root']/div/div/div/div[2]/div[2]/div")).click();
    driver.findElement(By.xpath("//div[@id='root']/div/div/div/div[2]/div/div[2]")).click();
    driver.findElement(By.xpath("//div[@id='root']/div/div/div/div[2]/div[2]/div[2]")).click();
    driver.findElement(By.xpath("//div[@id='root']/div/div/div/div[2]/div[2]/div[3]")).click();
    driver.findElement(By.xpath("//div[@id='root']/div/div/div/div[2]/div[2]/div[4]")).click();
    driver.findElement(By.xpath("//div[@id='root']/div/div/div/div[2]/div[2]/div[5]")).click();
    driver.findElement(By.xpath("//div[@id='root']/div/div/div/div[2]/div[2]/div[6]")).click();
    driver.findElement(By.xpath("//div[@id='root']/div/div/div/div[2]/div[2]/div")).click();
    driver.findElement(By.xpath("(//button[@type='button'])[4]")).click();
    driver.findElement(By.xpath("(//button[@type='button'])[8]")).click();
    driver.findElement(By.xpath("(//button[@type='button'])[9]")).click();
    driver.findElement(By.xpath("(//button[@type='button'])[10]")).click();
    driver.findElement(By.xpath("(//button[@type='button'])[7]")).click();
    driver.findElement(By.xpath("(//button[@type='button'])[6]")).click();
    driver.findElement(By.xpath("(//button[@type='button'])[8]")).click();
    driver.findElement(By.xpath("(//button[@type='button'])[9]")).click();
    driver.findElement(By.xpath("(//button[@type='button'])[10]")).click();
    driver.findElement(By.xpath("(//button[@type='button'])[11]")).click();
    driver.findElement(By.xpath("(//button[@type='button'])[12]")).click();
    driver.findElement(By.xpath("(//button[@type='button'])[7]")).click();
    driver.findElement(By.xpath("(//button[@type='button'])[3]")).click();
  }

  @Test
  public void testSetHigh() throws Exception {
    driver.get("http://localhost:3000/AdminPanel/CurrentSettings");
    try {
      assertEquals("> Low: 1000 > Medium: 800 > High: 600 >", driver.findElement(By.xpath("//div[@id='root']/div/div/div/div[2]/div[2]/div[5]/p")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.xpath("(//button[@type='button'])[4]")).click();
    driver.findElement(By.xpath("//input[@type='number']")).click();
    driver.findElement(By.xpath("//input[@type='number']")).clear();
    driver.findElement(By.xpath("//input[@type='number']")).sendKeys("123");
    driver.findElement(By.name("Ceiling (Day)")).click();
    driver.findElement(By.xpath("(//button[@type='button'])[3]")).click();
    try {
      assertEquals("> Low: 1000 > Medium: 800 > High: 123 >", driver.findElement(By.xpath("//div[@id='root']/div/div/div/div[2]/div[2]/div[5]/p")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
  }

  @Test
  public void testSetMed() throws Exception {
    driver.get("http://localhost:3000/AdminPanel/CurrentSettings");
    try {
      assertEquals("> Low: 1000 > Medium: 800 > High: 123 >", driver.findElement(By.xpath("//div[@id='root']/div/div/div/div[2]/div[2]/div[5]/p")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.xpath("(//button[@type='button'])[4]")).click();
    driver.findElement(By.xpath("(//input[@type='number'])[2]")).click();
    driver.findElement(By.xpath("(//input[@type='number'])[2]")).clear();
    driver.findElement(By.xpath("(//input[@type='number'])[2]")).sendKeys("123");
    driver.findElement(By.xpath("(//button[@name='Ceiling (Day)'])[2]")).click();
    driver.findElement(By.xpath("(//button[@type='button'])[3]")).click();
    try {
      assertEquals("> Low: 1000 > Medium: 123 > High: 123 >", driver.findElement(By.xpath("//div[@id='root']/div/div/div/div[2]/div[2]/div[5]/p")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
  }

  @Test
  public void testSetLow() throws Exception {
    driver.get("http://localhost:3000/AdminPanel/CurrentSettings");
    try {
      assertEquals("> Low: 1000 > Medium: 123 > High: 123 >", driver.findElement(By.xpath("//div[@id='root']/div/div/div/div[2]/div[2]/div[5]/p")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.xpath("(//button[@type='button'])[4]")).click();
    driver.findElement(By.xpath("(//input[@type='number'])[3]")).click();
    driver.findElement(By.xpath("(//input[@type='number'])[3]")).clear();
    driver.findElement(By.xpath("(//input[@type='number'])[3]")).sendKeys("123");
    driver.findElement(By.xpath("(//button[@name='Ceiling (Day)'])[3]")).click();
    driver.findElement(By.xpath("(//button[@type='button'])[3]")).click();
    try {
      assertEquals("> Low: 123 > Medium: 123 > High: 123 >", driver.findElement(By.xpath("//div[@id='root']/div/div/div/div[2]/div[2]/div[5]/p")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
  }

  @Test
  public void testSetAll() throws Exception {
    driver.get("http://localhost:3000/AdminPanel/CurrentSettings");
    try {
      assertEquals("> Low: 123 > Medium: 123 > High: 123 >", driver.findElement(By.xpath("//div[@id='root']/div/div/div/div[2]/div[2]/div[5]/p")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    driver.findElement(By.xpath("(//button[@type='button'])[4]")).click();
    driver.findElement(By.xpath("//input[@type='number']")).click();
    driver.findElement(By.xpath("//input[@type='number']")).clear();
    driver.findElement(By.xpath("//input[@type='number']")).sendKeys("600");
    driver.findElement(By.xpath("(//input[@type='number'])[2]")).click();
    driver.findElement(By.xpath("(//input[@type='number'])[2]")).clear();
    driver.findElement(By.xpath("(//input[@type='number'])[2]")).sendKeys("800");
    driver.findElement(By.xpath("(//input[@type='number'])[3]")).click();
    driver.findElement(By.xpath("(//input[@type='number'])[3]")).clear();
    driver.findElement(By.xpath("(//input[@type='number'])[3]")).sendKeys("1000");
    driver.findElement(By.name("Ceiling (Day)")).click();
    driver.findElement(By.xpath("(//button[@name='Ceiling (Day)'])[2]")).click();
    driver.findElement(By.xpath("(//button[@name='Ceiling (Day)'])[3]")).click();
    driver.findElement(By.xpath("(//button[@type='button'])[3]")).click();
    try {
      assertEquals("> Low: 1000 > Medium: 800 > High: 600 >", driver.findElement(By.xpath("//div[@id='root']/div/div/div/div[2]/div[2]/div[5]/p")).getText());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
