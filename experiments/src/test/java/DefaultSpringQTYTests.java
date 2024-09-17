import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

@TestInstance(Lifecycle.PER_CLASS)
public class DefaultSpringQTYTests {
     private WebDriver driver;
    public DefaultSpringQTYTests(){
        this.driver = new EdgeDriver();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofMillis(50000));

        EasyWeb.login(driver);
    }
    void sleep(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Test
    @DisplayName("T150 Single")
    void t150SingleTest(){
        EasyWeb.loadNewThermatiteConfig(driver);
        EasyWeb.navigateToPage(driver, "Door Model and Dimensions");
       EasyWeb.setField(By.id("DOOR_WIDTH_FT"), "8", driver);
       EasyWeb.setField(By.id("DOOR_WIDTH_IN"), "0", driver);
       EasyWeb.setField(By.id("DOOR_HEIGHT_FT"), "8", driver);
       EasyWeb.setField(By.id("DOOR_HEIGHT_IN"), "0", driver);


        EasyWeb.navigateToPage(driver, "Springs");
        sleep(2000);
        assertTrue(driver.findElement(By.id("SPRINGS_ASS_COM")).getAttribute("value").contains("Q1"));
    }
    @Test
    @DisplayName("T150 Double")
    void t150DoubleTest(){
        EasyWeb.loadNewThermatiteConfig(driver);
        EasyWeb.navigateToPage(driver, "Door Model and Dimensions");
        
        EasyWeb.setField(By.id("DOOR_WIDTH_FT"), "12", driver);
        EasyWeb.setField(By.id("DOOR_WIDTH_IN"), "0", driver);
        EasyWeb.setField(By.id("DOOR_HEIGHT_FT"), "10", driver);
        EasyWeb.setField(By.id("DOOR_HEIGHT_IN"), "0", driver);

        EasyWeb.navigateToPage(driver, "Springs");
        sleep(2000);

        assertTrue(driver.findElement(By.id("SPRINGS_ASS_COM")).getAttribute("value").contains("Q2"));
    }
    @Test
    @DisplayName("T150 Quadruple")
    void t150QuadTest(){
        EasyWeb.loadNewThermatiteConfig(driver);
        EasyWeb.navigateToPage(driver, "Door Model and Dimensions");
       
        EasyWeb.setField(By.id("DOOR_WIDTH_FT"), "18", driver);
        EasyWeb.setField(By.id("DOOR_WIDTH_IN"), "0", driver);
        EasyWeb.setField(By.id("DOOR_HEIGHT_FT"), "12", driver);
        EasyWeb.setField(By.id("DOOR_HEIGHT_IN"), "0", driver);

        EasyWeb.navigateToPage(driver, "Springs");
        sleep(2000);

        assertTrue(driver.findElement(By.id("SPRINGS_ASS_COM")).getAttribute("value").contains("Q4"));
    }
    @Test
    @DisplayName("T175 Double")
    void t175DoubleTest(){
        EasyWeb.loadNewThermatiteConfig(driver);
        EasyWeb.navigateToPage(driver, "Door Model and Dimensions");
        EasyWeb.setField(By.id("DOOR_MODEL"), "T175", "D", driver);
        EasyWeb.setField(By.id("DOOR_WIDTH_FT"), "8", driver);
        EasyWeb.setField(By.id("DOOR_WIDTH_IN"), "0", driver);
        EasyWeb.setField(By.id("DOOR_HEIGHT_FT"), "10", driver);
        EasyWeb.setField(By.id("DOOR_HEIGHT_IN"), "0", driver);

        EasyWeb.navigateToPage(driver, "Springs");
        sleep(2000);

        assertTrue(driver.findElement(By.id("SPRINGS_ASS_COM")).getAttribute("value").contains("Q2"));
    }
    @Test
    @DisplayName("T175 Quadruple")
    void t175QuadTest(){
        EasyWeb.loadNewThermatiteConfig(driver);
        EasyWeb.navigateToPage(driver, "Door Model and Dimensions");
        EasyWeb.setField(By.id("DOOR_MODEL"), "T175", "D", driver);
        EasyWeb.setField(By.id("DOOR_WIDTH_FT"), "18", driver);
        EasyWeb.setField(By.id("DOOR_WIDTH_IN"), "0", driver);
        EasyWeb.setField(By.id("DOOR_HEIGHT_FT"), "14", driver);
        EasyWeb.setField(By.id("DOOR_HEIGHT_IN"), "0", driver);


        EasyWeb.navigateToPage(driver, "Springs");
        sleep(2000);

        assertTrue(driver.findElement(By.id("SPRINGS_ASS_COM")).getAttribute("value").contains("Q4"));
    }
    @Test
    @DisplayName("T175 Sextuple")
    void t175SexTest(){
        EasyWeb.loadNewThermatiteConfig(driver);
        EasyWeb.navigateToPage(driver, "Door Model and Dimensions");
        EasyWeb.setField(By.id("DOOR_MODEL"), "T175", "D", driver);
        EasyWeb.setField(By.id("DOOR_WIDTH_FT"), "30", driver);
        EasyWeb.setField(By.id("DOOR_WIDTH_IN"), "0", driver);
        EasyWeb.setField(By.id("DOOR_HEIGHT_FT"), "18", driver);
        EasyWeb.setField(By.id("DOOR_HEIGHT_IN"), "0", driver);


        EasyWeb.navigateToPage(driver, "Springs");
        sleep(2000);

        assertTrue(driver.findElement(By.id("SPRINGS_ASS_COM")).getAttribute("value").contains("Q6"));
    }
    // @Test
    // @DisplayName("T175 Special Case")
    // void t175SpecialTest(){
    //     Selenium.loadNewThermatiteConfig(driver);
    //     Selenium.navigateToPage(driver, "Door Model and Dimensions");
    //     driver.findElement(By.id("DOOR_MODEL")).sendKeys("T175");
    //     sleep(500);
    //     driver.findElement(By.id("DOOR_WIDTH_FT")).sendKeys("32");
    //     driver.findElement(By.id("DOOR_WIDTH_IN")).sendKeys("0");
    //     driver.findElement(By.id("DOOR_HEIGHT_FT")).sendKeys("14");
    //     driver.findElement(By.id("DOOR_HEIGHT_IN")).sendKeys("0");

    //     Selenium.navigateToPage(driver, "Springs");
    //     assertTrue(driver.findElement(By.id("SPRINGS_ASS_COM")).getAttribute("value").contains("Q4"));
    // }
    @AfterAll
    void cleanup(){
       driver.quit();
    }
}
