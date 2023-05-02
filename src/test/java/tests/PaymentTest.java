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
import pages.PaymentPage;
import pages.ProductPage;
import pages.RegisterPage;
import utilities.Constants;
import utilities.Utilities;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class PaymentTest {

    private static WebDriver cr_driver;
    private static ExtentSparkReporter spark = new ExtentSparkReporter("spark.html");
    private static ExtentReports report = new ExtentReports();

    String currentTime = String.valueOf(System.currentTimeMillis());

    ProductPage productPage = new ProductPage(cr_driver);
    RegisterPage registerPage = new RegisterPage(cr_driver);
    PaymentPage paymentPage = new PaymentPage(cr_driver);
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
    //this test verifies that when a non registered user checkout to pay - he gets registration page
    @Test
    public void nonRegitredUser() throws InterruptedException, ParserConfigurationException, IOException, SAXException {
        ExtentTest nonRegitredUserTest = report.createTest("nonRegitredUser");
        nonRegitredUserTest.log(Status.INFO, "this test verifies that when a non registered user checkout to pay - he gets registration page");
        try{
            productPage.SearchByModel(utilities.getDataItem("MODEL", 0));
            productPage.setColor("2");
            productPage.setSize("5");
            Thread.sleep(2000);
            productPage.addBag();
            Thread.sleep(2000);
            paymentPage.goCheckout();
            Assert.assertTrue(cr_driver.getCurrentUrl().equals(utilities.getDataItem("URL", 5)));
            nonRegitredUserTest.log(Status.PASS, " the test passed successfully ");
        }
        catch (AssertionError assertionError) {
            nonRegitredUserTest.fail("some problem with non registered users checkout - see screen shot or test log for more information", MediaEntityBuilder.createScreenCaptureFromPath(utilities.takeScreenShot("target\\screenshots\\"+ currentTime)).build());
        }
        cr_driver.navigate().to(Constants.BASIC_URL);
    }
    //this  will test registered payment
    // The test will fail on the login due to site limitation and be reported as failure
    @Test
    public void registeredUserInvalidCard() throws InterruptedException, ParserConfigurationException, IOException, SAXException {
        ExtentTest  registeredUserInvalidCardTest = report.createTest("registredUserInvalidCard");
        registeredUserInvalidCardTest.log(Status.INFO, "this test verifies the payment page");
        try{
        registerPage.Login((utilities.getDataItem("USERNAME", 0)), (utilities.getDataItem("PASSWORD", 0)));
        Assert.assertTrue(cr_driver.getCurrentUrl().equals(utilities.getDataItem("URL", 0)));
        productPage.SearchByModel(utilities.getDataItem("MODEL", 0));
        productPage.setColor("2");
        productPage.setSize("5");
        cr_driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        productPage.addBag();
        paymentPage.goCheckout();
        Assert.assertTrue(cr_driver.getCurrentUrl().equals(utilities.getDataItem("URL", 6)));
        paymentPage.paymentForm("01","28","458045668999");
        paymentPage.errorVerification("Enter a valid card number");
        }
        catch (AssertionError assertionError) {

            registeredUserInvalidCardTest.fail("searching by model test failed - see screen shot or test log for more information", MediaEntityBuilder.createScreenCaptureFromPath(utilities.takeScreenShot("target\\screenshots\\"+ currentTime)).build());
        }
        cr_driver.navigate().to(Constants.BASIC_URL);
    }
    @AfterClass
    public static void AfterClass() throws InterruptedException {
        Thread.sleep(5000);
        cr_driver.quit();
        report.flush();
    }

}
