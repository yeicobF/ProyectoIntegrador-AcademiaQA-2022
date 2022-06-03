import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProyectoIntegradorTest extends AutomationPracticeBaseTest {

  @Test(priority = 1, testName = "ID-1. Mostrar galería de prendas populares y aceder a detalles de producto")
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
        productDetailsPage.isProductTitleDisplayed(),
        "No se muestra el título de producto correctamente.");
    Assert.assertTrue(productDetailsPage.isProductNameCorrect(),
        "No se muestra el nombre del producto correctamente en la página de detalles.");
  }

  @Test(priority = 2, testName = "ID-2. Mostrar galería de prendas populares y agregar producto al carrito")
  public void addProductToCartFromPopularGallery() {
    final HomePage homePage = new HomePage(driver);
    final Wait<WebDriver> wait = BasePage.getNewWait(10, 250, driver);

    homePage.clickPopularSection();
    WebElement sectionElement = homePage.getSectionElements().get(0);
    String productName = homePage.getSectionElementName(sectionElement);

    homePage.addSectionElementToCart(sectionElement);
    // Esperamos a que se muestre el modal. Si pasamos al siguiente paso
    // inmediatamente, lanzará excepción por no estar aún disponible.
    homePage.waitUntilModalIsDisplayed(wait);

    Assert.assertTrue(
        homePage.isProductAddedModalDisplayed(),
        "El modal no se muestra correctamente.");
    Assert.assertTrue(
        homePage.isProductAddedModalTitleDisplayed(),
        "No se muestra el mensaje el título con el ícono del modal que indica que el producto se agregó correctamente.");

    Assert.assertTrue(homePage.isAddedProductNameDisplayedInModal(productName),
        "El nombre del producto no se muestra en el modal correctamente.");
  }
}
