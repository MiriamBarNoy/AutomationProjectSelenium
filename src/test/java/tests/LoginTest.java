package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.xml.sax.SAXException;
import pages.RegisterPage;
import utilities.Constants;
import utilities.Utilities;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class LoginTest {
    private static WebDriver cr_driver;
    private static ExtentSparkReporter spark = new ExtentSparkReporter("spark.html");
    private static ExtentReports report = new ExtentReports();

    String currentTime = String.valueOf(System.currentTimeMillis());
    RegisterPage registerPage = new RegisterPage(cr_driver);
    Utilities utilities = new Utilities(cr_driver);




    @BeforeClass
    public static void prepareTest() throws IOException, SAXException, ParserConfigurationException, InterruptedException {
        System.setProperty("webdriver.chrome.driver", Constants.CHROME_DRIVER_LOCATION);
        cr_driver = new ChromeDriver();
        cr_driver.manage().window().maximize();
        cr_driver.get(Constants.BASIC_URL);
        cr_driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        spark.config().setReportName("Login Report");
        report.attachReporter(spark);
        cr_driver.navigate().to(Constants.BASIC_URL);
        Thread.sleep(4000);
    }
///This should test the login - it was blocked by the site and will fail - so this will be reported as failed with relevant screenshot
    @Test
    public void login() throws InterruptedException, ParserConfigurationException, IOException, SAXException {
        ExtentTest loginTest = report.createTest("login");
        loginTest.log(Status.INFO, "this test verifies success login with correct user/password ");
        try{
        registerPage.Login((utilities.getDataItem("USERNAME", 0)), (utilities.getDataItem("PASSWORD", 0)));

           Assert.assertTrue(cr_driver.getCurrentUrl().equals(utilities.getDataItem("URL", 0)));
           loginTest.log(Status.PASS, " the test passed successfully ");
      }
       catch (AssertionError assertionError) {
           loginTest.fail("test failed - There was a problem with login", MediaEntityBuilder.createScreenCaptureFromPath(utilities.takeScreenShot("target\\screenshots\\"+ currentTime)).build());
        }

    }
    
    @AfterClass
    public static void AfterClass() throws InterruptedException {
        Thread.sleep(5000);
        cr_driver.quit();
        report.flush();
    }
}