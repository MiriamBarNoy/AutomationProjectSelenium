package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PaymentPage {

    WebDriver cr_driver;
    private By checkout = By.linkText("CHECKOUT");
    private By creditCardRadioBtn = By.id("PaymentId");
    private By cardNumberField = By.id("cardNumber");
    private By expireMonthField = By.id("expiryMonth");
    private By expireYearField = By.id("expiryYear");

    private By securityCode = By.id("securityCode");

    private By payNow = By.id("submitButton");

    private By errorCard = By.id("cardNumber-hint");

    public void goCheckout() {

        cr_driver.findElement(checkout).click();
    }
    public void paymentForm(String expireMonth,String expireYear,String cardNum)
    {
        cr_driver.findElement(creditCardRadioBtn).click();
        cr_driver.findElement(expireMonthField).sendKeys(expireMonth);
        cr_driver.findElement(expireYearField).sendKeys(expireYear);
        cr_driver.findElement(cardNumberField).click();
        cr_driver.findElement(cardNumberField).sendKeys(cardNum);
        cr_driver.findElement(securityCode).sendKeys(cardNum);
    }
    public void errorVerification(String errorText)
    {
        WebElement errorOnCard = cr_driver.findElement(errorCard);
        String ExpectedText = errorText;
        Assert.assertEquals(ExpectedText, errorOnCard.getText());
    }
    public PaymentPage(WebDriver cr_driver) {
        this.cr_driver = cr_driver;
    }
}
