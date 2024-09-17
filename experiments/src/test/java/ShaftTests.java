import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

@TestInstance(Lifecycle.PER_CLASS)
public class ShaftTests {
    private WebDriver driver;
    public ShaftTests(){
        this.driver = new EdgeDriver();
        EasyWeb.login(driver);
       
    }

    @Test
    @DisplayName("Lowest Weight Default")
    void lowestWeightDefault(){
        EasyWeb.loadNewThermatiteConfig(driver);
        EasyWeb.navigateToPage(driver, "Door Model and Dimensions");
        EasyWeb.setField(By.id("DOOR_WIDTH_FT"), "8", driver);
        EasyWeb.setField(By.id("DOOR_HEIGHT_FT"), "8", driver);
       
    
        EasyWeb.navigateToPage(driver, "Torsion Assembly");
        assertEquals(driver.findElement(By.id("SHAFT")).getAttribute("value"), "T");
        assertEquals(driver.findElement(By.id("SHAFT")).findElements(By.tagName("option")).size(), 4);
    }
    @Test
    @DisplayName("Medium Weight Options")
    void mediumWeightOptions(){
        EasyWeb.loadNewThermatiteConfig(driver);
        EasyWeb.navigateToPage(driver, "Door Model and Dimensions");
        EasyWeb.setField(By.id("DOOR_WIDTH_FT"), "14", driver);
        EasyWeb.setField(By.id("DOOR_HEIGHT_FT"), "14", driver);
        EasyWeb.navigateToPage(driver, "Torsion Assembly");
        assertEquals(driver.findElement(By.id("SHAFT")).getAttribute("value"), "K");
        assertEquals(driver.findElement(By.id("SHAFT")).findElements(By.tagName("option")).size(), 3);

    }
    @Test
    @DisplayName("Heavy Weight < 1000lbs")
    void heavyLessThanOneThousand(){
        EasyWeb.loadNewThermatiteConfig(driver);
        EasyWeb.navigateToPage(driver, "Door Model and Dimensions");
        EasyWeb.setField(By.id("DOOR_WIDTH_FT"), "18", driver);
        EasyWeb.setField(By.id("DOOR_HEIGHT_FT"), "18", driver);
        EasyWeb.navigateToPage(driver, "Torsion Assembly");
        assertEquals(driver.findElement(By.id("SHAFT")).getAttribute("value"), "S");
        assertEquals(driver.findElement(By.id("SHAFT")).findElements(By.tagName("option")).size(), 2);
    }
    @Test
    @DisplayName("Heavy Weight > 1000lbs")
    void heavyGreaterThanOneThousand(){
        EasyWeb.loadNewThermatiteConfig(driver);
        EasyWeb.navigateToPage(driver, "Door Model and Dimensions");
        EasyWeb.setField(By.id("DOOR_WIDTH_FT"), "20", driver);
        EasyWeb.setField(By.id("DOOR_HEIGHT_FT"), "18", driver);
        EasyWeb.navigateToPage(driver, "Torsion Assembly");
        assertEquals(driver.findElement(By.id("SHAFT")).getAttribute("value"), "A");
        assertEquals(driver.findElement(By.id("SHAFT")).findElements(By.tagName("option")).size(), 1);
    }
    @AfterAll
    void cleanup(){
      driver.quit();    
    }
}
