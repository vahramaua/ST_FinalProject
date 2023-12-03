package filter;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.FilterPage;
import pages.ProductPage;
import pages.SearchPage;
import pages.SortPage;

public class FilterTest extends BaseTest {
    @Test
    public void minPriceFilterTest(){
        SearchPage search = homePage.search("book");
        FilterPage page = search.filter();
        String minValue = "100";
        page.priceFromFilter(minValue);

        SortPage sort = search.sort();
        sort.sortByPrice(true);

        try {
            Thread.sleep(2000);
        } catch(InterruptedException e) {
            System.out.println("got interrupted!");
        }

        ProductPage productPage = search.selectProduct();
        WebElement elementPrice = productPage.elementPrice();
        boolean result = Integer.parseInt(elementPrice.getText().split("\\.")[0].replaceAll("[\\D]", "")) >= Integer.parseInt(minValue);

        Assert.assertEquals(result,true);
    }

    @Test
    public void maxPriceFilterTest(){
        SearchPage search = homePage.search("book");
        FilterPage page = search.filter();
        String maxValue = "300";
        page.priceToFilter(maxValue);

        SortPage sort = search.sort();
        sort.sortByPrice(false);

        try {
            Thread.sleep(2000);
        } catch(InterruptedException e) {
            System.out.println("got interrupted!");
        }

        ProductPage productPage = search.selectProduct();
        WebElement elementPrice = productPage.elementPrice();
        boolean result = Integer.parseInt(elementPrice.getText().split("\\.")[0].replaceAll("[\\D]", "")) <= Integer.parseInt(maxValue);

        Assert.assertEquals(result,true);
    }
}
