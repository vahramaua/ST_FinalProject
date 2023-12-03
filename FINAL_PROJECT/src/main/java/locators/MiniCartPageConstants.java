package locators;

public class MiniCartPageConstants {
    public static final String product = "//div[contains(@class, 'mini-cart__line-item')]";
    public static final String removeProduct = "//a[contains(@class, 'mini-cart__quantity-remove')]";
    public static final String emptyCartDescClassName = "mini-cart__empty-state";
    public static final String increaseButton = "//button[contains(@title, 'Increase quantity by 1')]";
    public static final String decreaseButton = "//button[contains(@title, 'Decrease quantity by 1')]";
    public static final String quantityInput = "//input[contains(@class, 'quantity-selector__value')]";
}
