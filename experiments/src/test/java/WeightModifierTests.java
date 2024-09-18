import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
@TestInstance(Lifecycle.PER_CLASS)
public class WeightModifierTests {
        private WebDriver driver;
    public WeightModifierTests(){
        this.driver = new EdgeDriver();

        EasyWeb.login(driver);
        EasyWeb.loadNewThermatiteConfig(driver);
        
    }
    @Test
    @DisplayName("Weight Test")
    void weightTest(){
        EasyWeb.navigateToPage(driver, "Weight Modifier (lbs)");
        driver.findElement(By.id("MODIFY_SPRININING_WEIGHT")).sendKeys("200");;
        EasyWeb.navigateToPage(driver, "Springing Calculations - *Hidden");
        assertEquals(driver.findElement(By.id("ONDOOR_WEIGHT")).getAttribute("value"), "360.6130000005");
    }
    @AfterAll
    void cleanup(){
        driver.quit();
    }
}
