package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class HomePage {
    WebDriver cr_driver;

    //side links location (during test in this project only one is being tested and located
    private By sideLinkWhatsNew = By.linkText("New In Homeware");

    //Category on page
    private By centralCategoryLivingRoom = By.xpath("//*[@id=\"buttonlist1\"]/div/div[2]/div/div/div[4]/a");

   // Banner category
    private By bannerCategoryBaby =  By.xpath("//*[@id=\"meganav-link-2\"]/div");


   //Language change
   private By languageIcon = By.xpath("//*[@id=\"platform_modernisation_header\"]/header/div[1]/nav/div[9]/button/img");
   private By hebrewBtn = By.xpath("//*[@id=\"platform_modernisation_header\"]/header/div[1]/nav/div[10]/div/div[3]/div/div[4]/div/button[1]");
   private By shopNowBtn = By.xpath("//*[@id=\"platform_modernisation_header\"]/header/div[1]/nav/div[10]/div/div[3]/div/div[5]/button");

    //Open side link
    public void openSideLink()

    {
        cr_driver.findElement(sideLinkWhatsNew).click();
    }

    //Open Central categories
    public void openCentralCategory()
    {
        cr_driver.findElement(centralCategoryLivingRoom).click();
    }

    //Change Lang
    public void changeLang() throws InterruptedException {
        cr_driver.findElement(languageIcon).click();
        Thread.sleep(1500);
        Actions actions = new  Actions(cr_driver);
        WebElement changeLang = cr_driver.findElement(hebrewBtn);
        actions.doubleClick(changeLang).build().perform();
        Thread.sleep(1500);
        cr_driver.findElement(shopNowBtn).click();
        Thread.sleep(1500);
    }

    //Use banner categories
    public void openBannerCategory() {
      Actions actions = new  Actions(cr_driver);
      WebElement babyBanner = cr_driver.findElement(bannerCategoryBaby);
      actions.doubleClick(babyBanner).build().perform();
    }

    //constructor
    public HomePage(WebDriver cr_driver) {
        this.cr_driver = cr_driver;
    }
}
