package pages;

import locators.SearchPageConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public class SearchPage {
    private WebDriver driver;
    private String item;
    private By productGrid = By.xpath(SearchPageConstants.products);
    private By emptyDescription = By.xpath(SearchPageConstants.emptyDescription);
    private By sort = By.xpath(SearchPageConstants.sort);

    public SearchPage(WebDriver driver,String item){
        this.driver=driver;
        this.item=item;
    }

    public boolean incorrectSpelling(String correctSpelling){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        List<WebElement> products = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy ((productGrid)));
        return helper(products, correctSpelling);
    }

    public boolean elements(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        List<WebElement> products = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy ((productGrid)));
        return helper(products, item);
    }

    public boolean helper(List<WebElement> products, String correctSpelling){
        boolean condition = false;
        if(products.isEmpty()){
            return false;
        }else{
            for (int i = 0; i < products.size(); i++) {
                WebElement b = products.get(i);
                if(b.getText().toLowerCase().contains(correctSpelling.toLowerCase())){
                    condition = true;
                }else{
                    condition = false;
                    break;
                }
            }
        }
        return condition;
    }

    public boolean elementsNotExisting(){
        return driver.findElements(By.className(SearchPageConstants.emptyDescription)).size() != 0;
    }

    public FilterPage filter(){
        return new FilterPage(driver);
    }

    public ProductPage selectProduct() {
        driver.findElement(productGrid).click();

        return new ProductPage(driver);
    }

    public SortPage sort()
    {
        driver.findElement(sort).click();

        return new SortPage(driver);
    }
}
