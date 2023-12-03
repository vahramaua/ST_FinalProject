package cart;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MiniCartPage;
import pages.ProductPage;
import pages.SearchPage;

public class RemoveFromCartTest extends BaseTest {
    @Test
    public void removeFromCart(){
        SearchPage search = homePage.search("book");
        ProductPage product = search.selectProduct();
        product.addToCart();
        MiniCartPage cart = homePage.viewCart();
        cart.removeProductFromCart();

        boolean result = cart.checkElement();
        Assert.assertEquals(result,false);
    }
}
