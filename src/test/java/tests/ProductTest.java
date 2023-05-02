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
import pages.ProductPage;
import utilities.Constants;
import utilities.Utilities;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ProductTest {
    private static WebDriver cr_driver;
    private static ExtentSparkReporter spark = new ExtentSparkReporter("spark.html");
    private static ExtentReports report = new ExtentReports();

    String currentTime = String.valueOf(System.currentTimeMillis());

    ProductPage productPage = new ProductPage(cr_driver);
    Utilities utilities = new Utilities(cr_driver);




    @BeforeClass
    public static void prepareTest() throws IOException, SAXException, ParserConfigurationException, InterruptedException {
        System.setProperty("webdriver.chrome.driver", Constants.CHROME_DRIVER_LOCATION);
        cr_driver = new ChromeDriver();
        cr_driver.manage().window().maximize();
        cr_driver.navigate().to(Constants.BASIC_URL);
        cr_driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        spark.config().setReportName("Product Page Report");
        report.attachReporter(spark);
        Thread.sleep(4000);
    }
//This will test the search product by model
    @Test
    public void searchByModel() throws InterruptedException, ParserConfigurationException, IOException, SAXException {
        ExtentTest searchByModelTest = report.createTest("searchByModel");
        searchByModelTest.log(Status.INFO, "this test verifies success search for specific model");

        try{
        productPage.SearchByModel(utilities.getDataItem("MODEL", 0));
        cr_driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Assert.assertTrue(cr_driver.getCurrentUrl().contains(utilities.getDataItem("MODEL", 0)));
        searchByModelTest.log(Status.PASS, " the search by model test passed successfully ");
        }
        catch (AssertionError assertionError) {
            searchByModelTest.fail("searching by model test failed - see screen shot or test log for more information", MediaEntityBuilder.createScreenCaptureFromPath(utilities.takeScreenShot("target\\screenshots\\"+ currentTime)).build());
        }
        cr_driver.navigate().back();
    }
//this  will test the add to bag functionality with correct params
    @Test
    public void addToBag() throws InterruptedException, ParserConfigurationException, IOException, SAXException {
        ExtentTest addToBagTest = report.createTest("addToBag");
        addToBagTest.log(Status.INFO, "this test verifies success search for specific model");

        try{
        productPage.SearchByModel(utilities.getDataItem("MODEL", 0));
        productPage.setColor("2");
        productPage.setSize("5");
        cr_driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        productPage.addBag();
        Thread.sleep(2000);
        productPage.verifyBag("1");
        addToBagTest.log(Status.PASS, " the search by model test passed successfully ");
        }
        catch (AssertionError assertionError) {
            addToBagTest.fail("searching by model test failed - see screen shot or test log for more information", MediaEntityBuilder.createScreenCaptureFromPath(utilities.takeScreenShot("target\\screenshots\\"+ currentTime)).build());
        }
        cr_driver.navigate().back();
    }

    //this will test the option to add more units on bag
    @Test
    public void addUnits() throws InterruptedException, ParserConfigurationException, IOException, SAXException {
        ExtentTest addUnitsTest = report.createTest("addUnits");
        addUnitsTest.log(Status.INFO, "this test verifies success search for specific model");

        try{
        productPage.SearchByModel(utilities.getDataItem("MODEL", 0));
        productPage.setColor("2");
        productPage.setSize("5");
        cr_driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        productPage.addBag();
        Thread.sleep(5000);
        productPage.viewEditBag();
        cr_driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        productPage.addAmount("3");
        cr_driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        productPage.verifyBag("3");
        addUnitsTest.log(Status.PASS, " the search by model test passed successfully ");
        }
        catch (AssertionError assertionError) {
            addUnitsTest.fail("searching by model test failed - see screen shot or test log for more information", MediaEntityBuilder.createScreenCaptureFromPath(utilities.takeScreenShot("target\\screenshots\\"+ currentTime)).build());
        }
        cr_driver.navigate().back();
    }

    //checkout options will be tested as part of the payment test
    @AfterClass
    public static void AfterClass() throws InterruptedException {
        Thread.sleep(5000);
        cr_driver.quit();
        report.flush();
    }

}
