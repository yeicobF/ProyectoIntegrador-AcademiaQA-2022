import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.Test;

public class ProyectoIntegradorAP extends AutomationPracticeBaseTest {

  @Test
  public void goToProductDetailsFromPopularGallery() {
    final HomePage homePage = new HomePage(driver);
    final Wait<WebDriver> wait = BasePage.getNewWait(10, 250, driver);

  }
}
