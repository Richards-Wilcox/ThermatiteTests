import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

@TestInstance(Lifecycle.PER_CLASS)
public class CouplerTests {
     private WebDriver driver;
     //Above this width (17'10") a coupler is required
     private static final int LIMIT = 214;
    public CouplerTests(){
        this.driver = new EdgeDriver();

        EasyWeb.login(driver);
        
    }
    @Test
    @DisplayName("Tube Shaft Coupler Disabled")
    void isTubeShaftDisabled(){
        EasyWeb.loadNewThermatiteConfig(driver);
        EasyWeb.setDimensions( 8,0, 8,0, driver);
        EasyWeb.navigateToPage(driver, "Torsion Assembly");
        EasyWeb.setDropdown(By.id("SHAFT"), "Tube Shaft","T", driver);
        String couplerValue = driver.findElement(By.id("COUPLER")).getAttribute("value");
        assertEquals(couplerValue, "No");
        int children = driver.findElement(By.id("COUPLER")).findElements(By.tagName("option")).size();
        assertEquals(children, 1);
    }
    @Test
    @DisplayName("Keyed Tube Shaft Coupler Disabled")
    void isKeyedTubeShaftDisabled(){
        EasyWeb.loadNewThermatiteConfig(driver);
        EasyWeb.setDimensions( 14,0, 14,0, driver);
        EasyWeb.navigateToPage(driver, "Torsion Assembly");
        EasyWeb.setDropdown(By.id("SHAFT"), "Keyed Tube Shaft","K", driver);
        String couplerValue = driver.findElement(By.id("COUPLER")).getAttribute("value");
        assertEquals(couplerValue, "No");
        int children = driver.findElement(By.id("COUPLER")).findElements(By.tagName("option")).size();
        assertEquals(children, 1);
    }
    @Test
    @DisplayName("Solid 1\" Shaft < 12\"")
    void isSolidOneInchEnabledLessTwelveFeet(){
        EasyWeb.loadNewThermatiteConfig(driver);
        EasyWeb.setDimensions( 8,0, 8,0, driver);
        EasyWeb.navigateToPage(driver, "Torsion Assembly");
        EasyWeb.setDropdown(By.id("SHAFT"), "1\" Solid Shaft","S", driver);
        int children = driver.findElement(By.id("COUPLER")).findElements(By.tagName("option")).size();
        assertEquals(children, 2);
    }
    @Test
    @DisplayName("Solid 1.25\" Shaft < 12\"")
    void isSolidOneAndAQuarterInchEnabledLessTwelveFeet(){
        EasyWeb.loadNewThermatiteConfig(driver);
        EasyWeb.setDimensions( 10,0, 10,0, driver);
        EasyWeb.navigateToPage(driver, "Torsion Assembly");
        EasyWeb.setDropdown(By.id("SHAFT"), "1 1/4\" Solid Shaft","A", driver);
        int children = driver.findElement(By.id("COUPLER")).findElements(By.tagName("option")).size();
        assertEquals(children, 2);
    }
    @Test
    @DisplayName("Solid 1\" Shaft Coupler Mandated")
    void isCouplerMandatedGreaterThanLimitOneInch(){
        EasyWeb.loadNewThermatiteConfig(driver);
        int widthFeet = LIMIT/12;
        int widthInches = LIMIT%12;
        EasyWeb.setDimensions( widthFeet+1,widthInches, 8,0, driver);
        EasyWeb.navigateToPage(driver, "Torsion Assembly");
        EasyWeb.setDropdown(By.id("SHAFT"), "1\" Solid Shaft","S", driver);
        int children = driver.findElement(By.id("COUPLER")).findElements(By.tagName("option")).size();
        assertEquals(children, 1);
        String couplerValue = driver.findElement(By.id("COUPLER")).getAttribute("value");
        assertEquals(couplerValue, "Yes");
    }
    @Test
    @DisplayName("Solid 1.25\" Shaft Coupler Mandated")
    void isCouplerMandatedGreaterThanLimitOneAndQuarterInch(){
        EasyWeb.loadNewThermatiteConfig(driver);
        int widthFeet = LIMIT/12;
        int widthInches = LIMIT%12;
        EasyWeb.setDimensions( widthFeet+1,widthInches, 8,0, driver);
        EasyWeb.navigateToPage(driver, "Torsion Assembly");
        EasyWeb.setDropdown(By.id("SHAFT"), "1 1/4\" Solid Shaft","A", driver);
        int children = driver.findElement(By.id("COUPLER")).findElements(By.tagName("option")).size();
        assertEquals(children, 1);
        String couplerValue = driver.findElement(By.id("COUPLER")).getAttribute("value");
        assertEquals(couplerValue, "Yes");
    }
    @AfterAll
    void cleanup(){
        driver.quit();
    }
}
