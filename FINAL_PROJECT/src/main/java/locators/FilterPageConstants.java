package locators;

public class FilterPageConstants {
    public static final String between = "//*[contains(text(),'$50 to $100')]";
    public static final String min = "//input[contains(@name, 'filter.v.price.gte')]";
    public static final String max = "//input[contains(@name, 'filter.v.price.lte')]";
    public static final String errorMessage = "//h2[@data-at='filter_error']";
    public static final String button  = "#filter_price_range button";
    public static final String filter = "filter_price_range_heading";
}



