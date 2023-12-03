package pages;

import locators.SortPageConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SortPage {
    private final WebDriver driver;

    private final By lowToHigh = By.xpath(SortPageConstants.lowToHigh);

    private final By highToLow = By.xpath(SortPageConstants.highToLow);

    public SortPage(WebDriver driver){
        this.driver = driver;
    }

    public void sortByPrice(boolean isLowToHigh)
    {
        if (isLowToHigh) {
            driver.findElement(lowToHigh).click();
        } else {
            driver.findElement(highToLow).click();
        }
    }
}
