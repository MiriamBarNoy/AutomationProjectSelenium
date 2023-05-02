package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ProductPage {
    WebDriver cr_driver;

    //search box
      private By searchBox = By.name("header-big-screen-search-box");

    //search lens
      private By searchLens = By.xpath("//*[@id=\"header-search-form\"]/button/img");

    //select color
     private By colorSelectionField = By.xpath("//*[@id=\"dk_container_Colour-936124\"]/a");

    //select size
    private By sizeSelectionField = By.id("dk_container_Size-C81-777");


    //add to bag
    private By addToBag = By.xpath("//*[@id=\"Style936124\"]/section/div[4]/div[4]/div[4]/div/a[1]");

   //view bag
   private By viewBag = By.linkText("VIEW/EDIT BAG");

    //add quantity
    private By addQuantityField = By.xpath("//*[@id=\"dk_container_Qty_1\"]/a");
  //

    //checkout
     private By checkout = By.linkText("CHECKOUT");

    private By bagIcon = By.xpath("//*[@id=\"platform_modernisation_header\"]/header/div[1]/nav/div[7]/div[2]/a/div");

    public void SearchByModel(String modelID)
    {
        cr_driver.findElement(searchBox).sendKeys(modelID);
        cr_driver.findElement(searchLens).click();
    }

    public void setColor(String index)
    {
        By colorDropDown = By.xpath("//*[@id=\"dk_container_Colour-936124\"]/div/ul/li["+index+"]/a");
        cr_driver.findElement(colorSelectionField).click();
        cr_driver.findElement(colorDropDown).click();
    }

    public void setSize(String index)
    {
        By sizeDropDown = By.xpath("//*[@id=\"dk_container_Size-C81-777\"]/div/ul/li["+index+"]/a");
        cr_driver.findElement(sizeSelectionField).click();
        cr_driver.findElement(sizeDropDown).click();
    }
    public void verifyBag(String num)
    {
        WebElement bagNum = cr_driver.findElement(bagIcon);
        String ExpectedText = num;
        Assert.assertEquals(ExpectedText, bagNum.getText());
    }
    public void addBag ()
    {
        cr_driver.findElement(addToBag).click();
    }


    public void viewEditBag ()
    {
        cr_driver.findElement(viewBag).click();
    }



    public void addAmount(String units) throws InterruptedException {
        cr_driver.findElement(addQuantityField).click();
        Thread.sleep(1000);
        By addQuantityDropBox = By.xpath("//*[@id=\"dk_container_Qty_1\"]/div/ul/li["+units+"]/a");
        cr_driver.findElement(addQuantityDropBox).click();
        Thread.sleep(1000);

    }
    public void checkout ()
    {
        cr_driver.findElement(checkout).click();
    }


    //constructor
    public ProductPage(WebDriver cr_driver) {
        this.cr_driver = cr_driver;
    }
}
