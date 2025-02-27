package org.example.tests;

import org.example.pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTest {
    WebDriver driver;
    LoginPage loginPage;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://gps.peobd.org/login");
        driver.manage().window().maximize();
        //System.out.println("Driver Setup");

        loginPage = new LoginPage(driver);
    }

    @Test(priority = 2)
    public void testValidLogin() throws InterruptedException {
        loginPage.enterUsername("admin");
        loginPage.enterPassword("123");
        loginPage.clickLoginButton();
        // Add assertions here
        Thread.sleep(2000);

        try {
            String txt = driver.findElement(By.xpath("//b[normalize-space()='GPS']")).getText();
            System.out.println(txt);
            Assert.assertEquals("GPS", txt);
        } catch(Throwable t) {
            org.testng.Assert.fail("Expected and Actual result do not match");
        }
    }

    @Test(priority = 1)
    public void testInValidLogin() throws InterruptedException {
        loginPage.enterUsername("admin");
        loginPage.enterPassword("1234");
        loginPage.clickLoginButton();
        // Add assertions here
        Thread.sleep(2000);

        try {
            String txt = driver.findElement(By.xpath("//b[normalize-space()='GPS']")).getText();
            System.out.println(txt);
            Assert.assertEquals("GPS", txt);
        } catch(Throwable t) {
            org.testng.Assert.fail("Expected and Actual result do not match");
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

