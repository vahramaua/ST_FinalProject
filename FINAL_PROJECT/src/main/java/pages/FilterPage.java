package pages;

import locators.FilterPageConstants;
import locators.SearchPageConstants;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.yaml.snakeyaml.parser.ParserImpl;

import java.time.Duration;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class FilterPage {
    private final WebDriver driver;
    private final By productGrid = By.xpath(SearchPageConstants.products);
    private final By min = By.xpath(FilterPageConstants.min);
    private final By max = By.xpath(FilterPageConstants.max);
    private final By button = By.cssSelector(FilterPageConstants.button);
    private final By between = By.xpath(FilterPageConstants.between);
    private final By error = By.xpath(FilterPageConstants.errorMessage);

    public FilterPage(WebDriver driver){
        this.driver = driver;
    }

    public boolean helper(List<WebElement> products, int one, int two){
        boolean condition = false;
        if(products.isEmpty()){
            return false;
        }else{
            for (int i = 0; i < products.size(); i++) {
                WebElement b = products.get(i);
                String product = b.getText();
                int startIndex = product.indexOf("$") + 1;
                int endIndex = product.indexOf(".", startIndex);
                String result = product.substring(startIndex, endIndex);
                int num = Integer.parseInt(result);
                System.out.println(num);
                if(num>=one && num<=two){
                    condition = true;
                }else{
                    condition = false;
                    break;
                }
            }
        }
        return condition;
    }

    public boolean under25(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,3500)");
        List<WebElement> products = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy ((productGrid)));
        return helper(products, 0, 25);
    }



    public boolean between50And100(){
        WebElement b = driver.findElement(between);
        b.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,3500)");
        List<WebElement> products = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy ((productGrid)));
        return helper(products, 50, 100);
    }

    public boolean custom(String num1, String num2){
        WebElement low = driver.findElement(min);
        low.sendKeys(num1);
        WebElement high = driver.findElement(max);
        high.sendKeys(num2);
        WebElement apply = new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.presenceOfElementLocated(button));
        apply.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,3500)");
        List<WebElement> products = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy ((productGrid)));
        return helper(products, Integer.parseInt(num1), Integer.parseInt(num2));

    }


    public boolean customWrongNumbers(String num1, String num2){
        WebElement low = driver.findElement(min);
        low.sendKeys(num1);
        WebElement high = driver.findElement(max);
        high.sendKeys(num2);
        WebElement apply = new WebDriverWait(driver, Duration.ofSeconds(10)).
                until(ExpectedConditions.elementToBeClickable(button));
        apply.click();
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,1000)");
        WebElement message = new WebDriverWait(driver, Duration.ofSeconds(10)).
                    until(ExpectedConditions.presenceOfElementLocated(error));
        if(message.getText().contains("Sorry, there are no products that match your filter choices.")){
                return true;
            }

        return false;
    }

    public boolean customFail(String num1, String num2){
        try{
            return customWrongNumbers(num1, num2);
        }catch (TimeoutException e){
           return true;
        }

    }

    public void priceFromFilter(String value) {
        WebElement minField = driver.findElement(min);
        minField.click();
        minField.sendKeys(value);

        minField.submit();
    }

    public void priceToFilter(String value) {
        WebElement maxField = driver.findElement(max);
        maxField.click();
        maxField.sendKeys(value);

        maxField.submit();
    }
}
