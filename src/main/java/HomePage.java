import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

public class HomePage extends BasePage {
  private static final By byHomeSalesLogo = By.xpath("//div[@id='homepage-slider']");
  private static final By byContactUsButton = By.xpath("//a[@title='Contact Us']");
  private static final By bySignIn = By.xpath("//a[@class='login' and contains(text(), 'Sign in')]");
  public static final ExpectedCondition<WebElement> pageLoadedCondition = ExpectedConditions
      .visibilityOfElementLocated(byHomeSalesLogo);
  private final By byPopularSection = By
      .xpath("//ul[@id='home-page-tabs']//a[@href='#homefeatured' and text()='Popular']");
  private final By bySectionElements = By.xpath("//ul[@id='homefeatured']/li");

  // https://www.guru99.com/xpath-selenium.html
  By byLinkFromElement = By.xpath("//child::a[@class='product_img_link']");
  By byAddToCartFromElement = By.xpath("//child::a[@title='Add to cart']");
  private By byProductAddedModal = By.xpath("//div[@id='layer_cart']");
  private By byProductNameFromModal = By.xpath("//span[@id='layer_cart_product_title']");
  private By byModalTitleFromModal = By.xpath("//div[contains(@class, 'layer_cart_product')]//h2/i[@class='icon-ok']");
  private By byCheckOutShoppingCart = By.xpath("//div[@class='shopping_cart']//a[@title='View my shopping cart']");
  private final By byShoppingCartDropdown = By.xpath("//div[@class='cart_block block exclusive']");
  private By byModalCheckoutButton = By.xpath("//a[@title='Proceed to checkout']");

  public HomePage(WebDriver driver) {
    super(driver, pageLoadedCondition);
  }

  public boolean isHomeLogoDisplayed() {
    return driver.findElement(byHomeSalesLogo).isDisplayed();
  }

  public void clickPopularSection() {
    driver.findElement(byPopularSection).click();
  }

  /**
   * Obtenemos la lista de elementos dentro de una sección como "Popular".
   * Aquí solo obtenemos el "li", pero no los enlaces.
   */
  public List<WebElement> getSectionElements() {
    return driver.findElements(bySectionElements);
  }

  public String getSectionElementName(WebElement sectionElement) {
    return sectionElement.findElement(byLinkFromElement).getAttribute("title");
  }

  public void addSectionElementToCart(WebElement sectionElement) {
    sectionElement.findElement(byAddToCartFromElement).click();
  }

  private WebElement getProductAddedModal() {
    return driver.findElement(byProductAddedModal);
  }

  public void waitUntilModalIsDisplayed(Wait<WebDriver> wait) {
    wait.until(ExpectedConditions
        .visibilityOfElementLocated(byProductAddedModal));
  }

  public boolean isProductAddedModalDisplayed() {
    WebElement productAddedModal = getProductAddedModal();
    return productAddedModal
        .isDisplayed();
  }

  public boolean isProductAddedModalTitleDisplayed() {
    return getProductAddedModal()
        .findElement(byModalTitleFromModal)
        .isDisplayed();
  }

  public boolean isAddedProductNameDisplayedInModal(String productName) {
    return getProductAddedModal()
        .findElement(byProductNameFromModal)
        .getText()
        .equals(productName);
  }

  public void clickModalCheckOutShoppingCart() {
    getProductAddedModal()
        .findElement(byModalCheckoutButton)
        .click();
  }

  public void goToSectionElementDetails(WebElement sectionElement) {
    // Encontramos el enlace del elemento a partir del nodo en que está el elemento.
    sectionElement.findElement(byLinkFromElement).click();
  }

  // public void displayShoppingCart() {
  // driver.findElement(byShoppingCartDropdown).click();
  // }

  public void clickContactUs() {
    driver.findElement(byContactUsButton).click();
  }

  public void clickSignIn() {
    driver.findElement(bySignIn).click();
  }

  public void clickCheckoutShoppingCart() {
    driver.findElement(byCheckOutShoppingCart).click();
  }
}
