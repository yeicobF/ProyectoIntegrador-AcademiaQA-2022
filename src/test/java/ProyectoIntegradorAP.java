import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProyectoIntegradorAP extends AutomationPracticeBaseTest {

  @Test
  public void goToProductDetailsFromPopularGallery() {
    final HomePage homePage = new HomePage(driver);
    final Wait<WebDriver> wait = BasePage.getNewWait(10, 250, driver);

    homePage.clickPopularSection();
    WebElement sectionElement = homePage.getSectionElements().get(0);
    String productName = homePage.getSectionElementName(sectionElement);
    homePage.goToSectionElementDetails(sectionElement);

    ProductDetails productDetailsPage = new ProductDetails(driver, productName);
    productDetailsPage.waitUntilPageIsLoaded(wait);
    Assert.assertTrue(
        productDetailsPage.isProductNameCorrect()
        && productDetailsPage.isProductNameDisplayed()
    );
  }
}
