import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

public class ShoppingCartSummaryPage extends BasePage {
  private static final By byPageTitle = By.xpath("//h1[@id='cart_title']");
  private final By byEmptyShoppingCartMessage = By
      .xpath("//p[@class='alert alert-warning' and text()='Your shopping cart is empty.']");
  // Ruta relativa para el botón de eliminado a partir de un item.
  private final By byDeleteFromItem = By.xpath("//a[@title='Delete']");
  private final By byShoppingCartElements = By.xpath("//table[@id='cart_summary']//tr[contains(@class, 'cart_item')]");
  private final By byProductNameTagFromItem = By
      .xpath("//p[@class='product-name']");

  public ShoppingCartSummaryPage(WebDriver driver) {
    super(driver, getPageLoadedCondition());

  }

  private static ExpectedCondition<WebElement> getPageLoadedCondition() {
    return ExpectedConditions.visibilityOfElementLocated(byPageTitle);
  }

  public boolean isPageTitleDisplayed() {
    return driver.findElement(byPageTitle).isDisplayed();
  }

  public boolean areThereShoppingCartElements() {
    return getEveryShoppingCartItem().size() > 0;
  }

  public void waitUntilEmptyCartMessageIsDisplayed(Wait<WebDriver> wait) {
    wait.until(ExpectedConditions.visibilityOfElementLocated(byEmptyShoppingCartMessage));
  }

  public boolean isEmptyCartMessageDisplayed() {
    return driver.findElement(byEmptyShoppingCartMessage).isDisplayed();
  }

  public List<WebElement> getEveryShoppingCartItem() {
    return driver.findElements(byShoppingCartElements);
  }

  private void deleteShoppingCartItem(WebElement item) {
    item.findElement(byDeleteFromItem).click();
  }

  public void deleteEveryShoppingCartItem(List<WebElement> items) {
    for (WebElement item : items) {
      deleteShoppingCartItem(item);
    }
  }

  public boolean isProductInItems(List<WebElement> items, String productName) {
    String currentItemName;
    By byProductNameFromTag;
    for (WebElement item : items) {
      byProductNameFromTag = By
          .xpath("//a[text()='" + productName + "']");
      currentItemName = item
          .findElement(byProductNameTagFromItem)
          .findElement(byProductNameFromTag)
          .getText();

      // Se encontró el producto en la lista de elementos del carrito de
      // compras.
      if (currentItemName.equals(productName)) {
        return true;
      }
    }

    // Nunca salió del ciclo, por lo que no encontró el producto.
    return false;
  }
}
