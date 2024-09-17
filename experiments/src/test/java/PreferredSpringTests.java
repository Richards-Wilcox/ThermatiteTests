import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;


@TestInstance(Lifecycle.PER_CLASS)
public class PreferredSpringTests {
   // private final String[] PREFERRED_SPRINGS = {"2 5/8\" d, .263\"","2 5/8\" d, .263\"","2 5/8\" d, .273\"","2 5/8\" d, .283\"","2 5/8\" d, .263\"","2 5/8\" d, .273\"","2 5/8\" d, .273\"","2 5/8\" d, .283\"","2 5/8\" d, .273\"","3 3/4\" d, .295\"","3 3/4\" d, .295\"","2 5/8\" d, .263\"","2 5/8\" d, .283\"","3 3/4\" d, .331\"","6\" d, .313\"","6\" d, .313\"","3 3/4\" d, .331\"","2 5/8\" d, .244\"","2 5/8\" d, .244\"","3 3/4\" d, .307\"","2 5/8\" d, .263\"","2 5/8\" d, .283\"","3 3/4\" d, .307\"","2 5/8\" d, .263\"","6\" d, .363\"","2 5/8\" d, .250\"","3 3/4\" d, .344\"","6\" d, .406\"","3 3/4\" d, .344\"","6\" d, .363\"","6\" d, .406\"","3 3/4\" d, .307\"","2 5/8\" d, .295\"","3 3/4\" d, .283\"","6\" d, .363\"","3 3/4\" d, .283\"","2 5/8\" d, .250\"","6\" d, .363\"","3 3/4\" d, .263\"","3 3/4\" d, .307\"","6\" d, .394\"","6\" d, .394\"","2 5/8\" d, .250\"","3 3/4\" d, .263\"","3 3/4\" d, .273\"","6\" d, .394\"","3 3/4\" d, .273\"","6\" d, .394\"","3 3/4\" d, .320\"","2 5/8\" d, .234\"","2 5/8\" d, .244\"","2 5/8\" d, .295\"","6\" d, .344\""};
     private WebDriver driver;
    public PreferredSpringTests(){
        this.driver = new EdgeDriver();
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
    @DisplayName("Generates Proper Spring Test 1")
    void generatesProperSpringT1(){
        EasyWeb.loadNewThermatiteConfig(driver);
        EasyWeb.navigateToPage(driver, "Door Model and Dimensions");
        EasyWeb.setField(By.id("DOOR_MODEL"), "T175", "D", driver);
        EasyWeb.setField(By.id("DOOR_WIDTH_FT"), "14", driver);
        EasyWeb.setField(By.id("DOOR_HEIGHT_FT"), "14", driver);
        EasyWeb.navigateToPage(driver, "Springs");
       sleep(2000);
        assertEquals(driver.findElement(By.id("SPRINGS_ASS_COM")).getAttribute("value"), "433-6106Q2");
    }
    @Test
    @DisplayName("Generates Proper Spring Test 2")
    void generatesProperSpringT2(){
        EasyWeb.loadNewThermatiteConfig(driver);
        EasyWeb.navigateToPage(driver, "Door Model and Dimensions");
        EasyWeb.setField(By.id("DOOR_MODEL"), "T175", "D", driver);
        EasyWeb.setField(By.id("DOOR_WIDTH_FT"), "14", driver);
        EasyWeb.setField(By.id("DOOR_HEIGHT_FT"), "14", driver);
        

        EasyWeb.navigateToPage(driver, "Hardware Options");
        EasyWeb.setField(By.id("LIFT_TYPE"), "High_Lift", driver);
        EasyWeb.setField(By.id("HI_LFT_INPUT"), "30", driver);


        EasyWeb.navigateToPage(driver, "Torsion Assembly");
        EasyWeb.setField(By.id("SHAFT"), "1 1/4\" Solid Shaft", "A", driver);
        //The Springs page often takes a long time to load, this is just a way to make sure we get there. 
        try{
            EasyWeb.navigateToPage(driver, "Springs");

        }catch(TimeoutException e){
            driver.navigate().refresh();
            sleep(300);
            ((JavascriptExecutor)driver).executeScript("prevPage()");
        }
        sleep(2000);
        assertEquals(driver.findElement(By.id("SPRINGS_ASS_COM")).getAttribute("value"), "433-6108Q2");
    }
    @Test
    @DisplayName("Generates Proper Spring Test 3")
    void generatesProperSpringT3(){
        EasyWeb.loadNewThermatiteConfig(driver);
        EasyWeb.navigateToPage(driver, "Door Model and Dimensions");
        EasyWeb.setField(By.id("DOOR_MODEL"), "T175", "D", driver);
        EasyWeb.setField(By.id("DOOR_WIDTH_FT"), "14", driver);
        EasyWeb.setField(By.id("DOOR_HEIGHT_FT"), "14", driver);
        

        EasyWeb.navigateToPage(driver, "Hardware Options");
        EasyWeb.setField(By.id("LIFT_TYPE"), "Vertical Lift", "VL", driver);

        //The Springs page often takes a long time to load, this is just a way to make sure we get there. 
        try{
            EasyWeb.navigateToPage(driver, "Springs");

        }catch(TimeoutException e){
            driver.navigate().refresh();
            sleep(300);
            ((JavascriptExecutor)driver).executeScript("prevPage()");
        }
        sleep(2000);
        assertEquals(driver.findElement(By.id("SPRINGS_ASS_COM")).getAttribute("value"), "433-6114Q2");
    }
    @AfterAll
    void cleanup(){
        driver.quit();
    }
}
