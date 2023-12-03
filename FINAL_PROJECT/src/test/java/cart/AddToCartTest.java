package cart;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MiniCartPage;
import pages.ProductPage;
import pages.SearchPage;

public class AddToCartTest extends BaseTest {
    @Test
    public void addToCartTest(){
        SearchPage search = homePage.search("book");
        ProductPage product = search.selectProduct();
        product.addToCart();
        MiniCartPage cart = homePage.viewCart();

        boolean result = cart.checkElement();
        Assert.assertEquals(result,true);
    }
}
