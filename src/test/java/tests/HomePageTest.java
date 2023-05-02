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
import pages.HomePage;
import pages.RegisterPage;
import utilities.Constants;
import utilities.Utilities;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static java.time.Duration.*;

public class HomePageTest {
    private static WebDriver cr_driver;
    private static ExtentSparkReporter spark = new ExtentSparkReporter("spark.html");
    private static ExtentReports report = new ExtentReports();

    String currentTime = String.valueOf(System.currentTimeMillis());

    HomePage homePage = new HomePage(cr_driver);
    Utilities utilities = new Utilities(cr_driver);




    @BeforeClass
    public static void prepareTest() throws IOException, SAXException, ParserConfigurationException, InterruptedException {
        System.setProperty("webdriver.chrome.driver", Constants.CHROME_DRIVER_LOCATION);
        cr_driver = new ChromeDriver();
        cr_driver.manage().window().maximize();
        cr_driver.navigate().to(Constants.HOME_URL);
        cr_driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        spark.config().setReportName("HomePage Report");
        report.attachReporter(spark);
        Thread.sleep(4500);
    }
    ///This would test left panel links - it is only a sample and do not test all side links
    @Test
   public void leftPanelLink() throws InterruptedException, ParserConfigurationException, IOException, SAXException {
        ExtentTest leftPanelLinkTest = report.createTest("leftPanelLink");
        leftPanelLinkTest.log(Status.INFO, "this test verifies success references and left side panel usage");
        try{
        homePage.openSideLink();
            cr_driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            Assert.assertTrue(cr_driver.getCurrentUrl().equals(utilities.getDataItem("URL", 1)));
            leftPanelLinkTest.log(Status.PASS, " the side panel test passed successfully ");
        }
        catch (AssertionError assertionError) {
            leftPanelLinkTest.fail("side panel test failed - see screen shot or test log for more information", MediaEntityBuilder.createScreenCaptureFromPath(utilities.takeScreenShot("target\\screenshots\\"+ currentTime)).build());
        }
        cr_driver.navigate().back();
    }

    ///This would test the categories in the center of page
    @Test
    public void categoriesLink() throws InterruptedException, ParserConfigurationException, IOException, SAXException {
        ExtentTest categoriesLinkTest = report.createTest("CategoriesLink");
        categoriesLinkTest.log(Status.INFO, "this test verifies success references and central categories");
        try{
        homePage.openCentralCategory();
            cr_driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
            Assert.assertTrue(cr_driver.getCurrentUrl().equals(utilities.getDataItem("URL", 2)));
            categoriesLinkTest.log(Status.PASS, " the central categories test passed successfully ");
        }
        catch (AssertionError assertionError) {
            categoriesLinkTest.fail("central categories test failed - see screen shot or test log for more information", MediaEntityBuilder.createScreenCaptureFromPath(utilities.takeScreenShot("target\\screenshots\\"+ currentTime)).build());
        }
        cr_driver.navigate().back();
    }
    ///This would test the top banner category  links
    @Test
    public void bannerMainLink() throws InterruptedException, ParserConfigurationException, IOException, SAXException {
        ExtentTest bannerMainLinkTest = report.createTest("BannerMainLink");
        bannerMainLinkTest.log(Status.INFO, "this test verifies success references on top banner");
        try{
            homePage.openBannerCategory();
            cr_driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            Assert.assertTrue(cr_driver.getCurrentUrl().equals(utilities.getDataItem("URL", 3)));
            bannerMainLinkTest.log(Status.PASS, " the top banner test passed successfully ");
        }
        catch (AssertionError assertionError) {
            bannerMainLinkTest.fail("top banner test failed - see screen shot or test log for more information", MediaEntityBuilder.createScreenCaptureFromPath(utilities.takeScreenShot("target\\screenshots\\"+ currentTime)).build());
        }
        cr_driver.navigate().back();
    }
    ///This would test the change language functionality from home page
    @Test
    public void ChangeLanguage() throws InterruptedException, ParserConfigurationException, IOException, SAXException {
        ExtentTest ChangeLanguageTest = report.createTest("ChangeLanguage");
        ChangeLanguageTest.log(Status.INFO, "this test verifies that language change functionality");
        try{
        homePage.changeLang();
            cr_driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
           Assert.assertTrue(cr_driver.getCurrentUrl().equals(utilities.getDataItem("URL", 4)));
            ChangeLanguageTest.log(Status.PASS, " the language change functionality passed successfully ");
        }
        catch (AssertionError assertionError) {
            ChangeLanguageTest.fail("language change functionality failed - see screen shot or test log for more information", MediaEntityBuilder.createScreenCaptureFromPath(utilities.takeScreenShot("target\\screenshots\\"+ currentTime)).build());
        }
        cr_driver.navigate().back();
    }
//This is a purposely failing test to see it is correctly shown in report generated
    @Test
    public void FailingTestsForReport() throws InterruptedException, ParserConfigurationException, IOException, SAXException {
        ExtentTest FailingTestsForReportTest = report.createTest("FailingTestsForReport");
        FailingTestsForReportTest.log(Status.INFO, "this test verifies success references and central categories");
        try{
        homePage.openCentralCategory();
            cr_driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            Assert.assertTrue(cr_driver.getCurrentUrl().equals(utilities.getDataItem("URL", 0)));
            FailingTestsForReportTest.log(Status.PASS, " the central categories test passed successfully ");
        }
        catch (AssertionError assertionError) {
            FailingTestsForReportTest.fail("central categories test failed - see screen shot or test log for more information", MediaEntityBuilder.createScreenCaptureFromPath(utilities.takeScreenShot("target\\screenshots\\"+ currentTime)).build());
        }
        cr_driver.navigate().back();
    }
    @AfterClass
    public static void AfterClass() throws InterruptedException {
        Thread.sleep(5000);
        cr_driver.quit();
        report.flush();
    }
}

