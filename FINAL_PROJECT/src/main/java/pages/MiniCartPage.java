package pages;

import locators.HomePageConstants;
import locators.MiniCartPageConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MiniCartPage {
    private final WebDriver driver;
    public MiniCartPage(WebDriver driver){
        this.driver = driver;
    }

    private final By product = By.xpath(MiniCartPageConstants.product);

    private final By removeProduct = By.xpath(MiniCartPageConstants.removeProduct);
    private final By increaseButton = By.xpath(MiniCartPageConstants.increaseButton);
    private final By decreaseButton = By.xpath(MiniCartPageConstants.decreaseButton);
    private final By quantityInput = By.xpath(MiniCartPageConstants.quantityInput);

    public boolean checkElement() {
        return driver.findElements(product).size() != 0;
    }

    public boolean isCartEmpty() {
        return driver.findElements(By.className(MiniCartPageConstants.emptyCartDescClassName)).size() != 0;
    }

    public void removeProductFromCart() {
        try {
            driver.findElement(removeProduct).click();

            Thread.sleep(2000);
        } catch(InterruptedException e) {
            System.out.println("got interrupted!");
        }
    }

    public void addProductCount() {
        try {
            driver.findElement(increaseButton).click();

            Thread.sleep(3000);
        } catch(InterruptedException e) {
            System.out.println("got interrupted!");
        }
    }

    public void minusProductCount() {
        try {
            driver.findElement(decreaseButton).click();

            Thread.sleep(3000);
        } catch(InterruptedException e) {
            System.out.println("got interrupted!");
        }
    }

    public int getQuantity()
    {
        return Integer.parseInt(driver.findElement(quantityInput).getAttribute("value"));
    }
}

