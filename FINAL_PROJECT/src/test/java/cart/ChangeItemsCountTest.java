package cart;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.MiniCartPage;
import pages.ProductPage;
import pages.SearchPage;

public class ChangeItemsCountTest extends BaseTest {
    private MiniCartPage miniCartPage;

    private void addProductAndViewCart() {
        SearchPage search = homePage.search("book");
        ProductPage product = search.selectProduct();
        product.addToCart();
        miniCartPage = homePage.viewCart();
    }

    @Test
    public void addItemCountTest(){
        addProductAndViewCart();

        int quantity = miniCartPage.getQuantity();
        miniCartPage.addProductCount();
        int changedQuantity = miniCartPage.getQuantity();

        Assert.assertEquals(quantity == changedQuantity - 1,true);
    }

    @Test
    public void minusItemCountTest(){
        addProductAndViewCart();
        miniCartPage.addProductCount();

        int quantity = miniCartPage.getQuantity();
        miniCartPage.minusProductCount();
        int changedQuantity = miniCartPage.getQuantity();

        Assert.assertEquals(quantity == changedQuantity + 1,true);
    }

    @Test
    public void minusAndCheckCartEmptyTest(){
        addProductAndViewCart();
        miniCartPage.minusProductCount();

        Assert.assertEquals(miniCartPage.checkElement(),false);
    }
}
