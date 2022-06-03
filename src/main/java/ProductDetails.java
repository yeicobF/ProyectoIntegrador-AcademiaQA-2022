import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductDetails extends BasePage {
  private static final By byProductName = By.xpath("//h1[@itemprop='name']");

  public ProductDetails(WebDriver driver, ExpectedCondition<WebElement> pageLoadedCondition, String productName) {
    super(driver, getPageLoadedCondition(productName));
  }

  public boolean isProductNameCorrect(String productName) {
    return driver
        .findElement(byProductName)
        .getText()
        .equals(productName);
  }

  public boolean isProductNameDisplayed() {
    return driver.findElement(byProductName).isDisplayed();
  }

  private static ExpectedCondition<WebElement> getPageLoadedCondition(String productName) {
    return ExpectedConditions.visibilityOfElementLocated(byProductName);
  }
}
