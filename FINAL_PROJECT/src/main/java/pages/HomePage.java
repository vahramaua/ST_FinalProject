package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import locators.*;


import java.time.Duration;

public class HomePage {
    private final WebDriver driver;
    private final By searchField = By.xpath(HomePageConstants.searchBar);

    private final By searchButton = By.xpath(HomePageConstants.searchButton);

    private final By cartIcon = By.xpath(HomePageConstants.cartIcon);

    private final By viewCartButton = By.xpath(HomePageConstants.viewCartButton);

    private final By logo = By.xpath(HomePageConstants.logo);

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    public void fillSearch(String a){
        WebElement searchButton = new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.elementToBeClickable(searchField));
        searchButton.sendKeys(a);
        searchButton.submit();
    }

    public SearchPage search(String searchItem){
        fillSearch(searchItem);
        return new SearchPage(driver,searchItem);
    }

    public MiniCartPage viewCart(){
        try {
            goHome();

            driver.findElement(cartIcon).click();

            Thread.sleep(2000);
        } catch(InterruptedException e) {
            System.out.println("got interrupted!");
        }

        return new MiniCartPage(driver);
    }

    public void goHome() {
        driver.findElement(logo).click();
    }
}
