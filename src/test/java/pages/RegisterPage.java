package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {

    WebDriver cr_driver;
    //Locators definition for Login page
    private  By myAccout = By.xpath("//*[@id=\"platform_modernisation_header\"]/header/div[1]/nav/div[4]/div[2]/div/a/span");
    private  By enterEmailField = By.id("EmailOrAccountNumber");
    private  By passwordField = By.id("Password");
    private  By signInBtn = By.id("SignInNow");



    //Function to fill registration login
    public void Login(String mailField,String password)
    {
        cr_driver.findElement(myAccout).click();
        cr_driver.findElement(enterEmailField).sendKeys(mailField);
        cr_driver.findElement(passwordField).sendKeys(password);
        cr_driver.findElement(signInBtn).click();
    }
    //constructor


    public RegisterPage(WebDriver cr_driver) {
        this.cr_driver = cr_driver;
    }
}
