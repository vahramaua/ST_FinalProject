package pages;

import locators.ProductPageConstants;
import locators.SearchPageConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductPage {
    private WebDriver driver;
    private final By addToCardButton = By.xpath(ProductPageConstants.addToCartButton);
    private final By viewCartButton = By.xpath(ProductPageConstants.viewCartButton);

    private By price = By.xpath(ProductPageConstants.price);

    public ProductPage(WebDriver driver){
        this.driver = driver;
    }
    public void addToCart(){
        driver.findElement(addToCardButton).click();
    }

    public WebElement elementPrice(){
        return driver.findElement(price);
    }
}
