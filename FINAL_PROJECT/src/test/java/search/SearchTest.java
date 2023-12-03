package search;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SearchPage;

public class SearchTest extends BaseTest {

    @Test
    public void testSuccessfulProductName(){
        SearchPage search = homePage.search("book");
        boolean result = search.elements();
        Assert.assertEquals(result,true);
    }

    @Test
    public void testSuccessfulProductNameAllCaps(){
        SearchPage search = homePage.search("BOOK");
        boolean result = search.elements();
        Assert.assertEquals(result,true);
    }

    @Test
    public void testSuccessfulHalfProductName(){
        SearchPage search = homePage.search("boo");
        boolean result = search.elements();
        Assert.assertEquals(result,true);
    }

    @Test
    public void testProductNameMisspelled(){
        SearchPage search = homePage.search("bopk");
        boolean result = search.incorrectSpelling("book");
        Assert.assertEquals(result,true);
    }

    @Test
    public void testUnSuccessfulProductName(){
        SearchPage search = homePage.search("notexistproductname");
        boolean result = search.elementsNotExisting();
        Assert.assertEquals(result,true);
    }
}
