import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductDetails extends BasePage {
  private static final By byProductName = By.xpath("//h1[@itemprop='name']");
  private String productName;

  public ProductDetails(WebDriver driver, String productName) {
    super(driver, getPageLoadedCondition(productName));
    this.productName = productName;
  }

  public boolean isProductNameCorrect() {
    return driver
        .findElement(byProductName)
        .getText()
        .equals(productName);
  }

  public boolean isProductTitleDisplayed() {
    return driver.findElement(byProductName).isDisplayed();
  }

  private static ExpectedCondition<WebElement> getPageLoadedCondition(String productName) {
    return ExpectedConditions.visibilityOfElementLocated(byProductName);
  }
}
