import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
@Disabled
public class DualShaftStandardLiftTests {
    final String[] STD_LIFT_DBL_AUX = {
             "425-1105/1" , "425-1106/1" , "425-1101/2" , "411-227/4" , "411-225/4" ,
             "213-041/8" , "214-818/8" ,
             "426-103/2" , "436-0047/2" , "423-337/4" , "7-2350/1" , "328-252/12" 
    };
    final String[] INVALID_DRUMS = {"Drum D1100-216,Cable 3/16 Inch(4.8 mm),Shaft 1 Inch(25.4)mm,GAL","Drum D1100-216,Cable 1/8 Inch(3.2 mm),Shaft 1 Inch(25.4)mm,GAL","Drum D1100-216,Cable 3/16 Inch(4.8 mm),Shaft 1.25 Inch(31.75)mm,GAL","Drum D1100-216,Cable 1/8 Inch(3.2 mm),Shaft 1.25 Inch(31.75)mm,GAL","Drum D1350-336,Cable 1/4 Inch(4.8 mm),Shaft 1.25 Inch(31.75)mm,GAL","Drum D400-144,Cable 1/8 Inch(3.2 mm),Shaft 1 Inch(25.4)mm,GAL","Drum D525-216,Cable 1/8 Inch(3.2 mm),Shaft 1 Inch(25.4)mm,GAL","Drum D525-216,Cable 3/16 Inch(4.8 mm),Shaft 1 Inch(25.4)mm,GAL","Drum D800-384,Cable 1/4 Inch(6.4 mm),Shaft 1 Inch(25.4)mm,GAL","Drum D800-384,Cable 3/16 Inch(4.8 mm),Shaft 1 Inch(25.4)mm,GAL","Drum D850-132,Cable 1/8 Inch(3.2 mm),Shaft 1 Inch(25.4)mm,GAL","Drum D850-132,Cable 3/16 Inch(4.8 mm),Shaft 1 Inch(25.4)mm,GAL","Drum D1100-216,Cable 5/32 Inch (3.97MM),Shaft 1 Inch(25.4)mm,GAL","Drum D1100-216,Cable 5/32 Inch (3.97MM),Shaft 1.25 Inch(31.75)mm,GAL","Drum D400-144,Cable 5/32 Inch (3.97MM),Shaft 1 Inch(25.4)mm,GAL","Drum D525-216,Cable 5/32 Inch (3.97MM),Shaft 1 Inch(25.4)mm,GAL","Drum D850-132,Cable 5/32 Inch (3.97MM),Shaft 1 Inch(25.4)mm,GAL","Drum D850-132,Cable 5/32 Inch (3.97MM),Shaft 1.25 Inch(31.75)mm,GAL","Drum D1100-216,Cable 3/16 Inch(4.8 mm),Shaft 1 Inch(25.4)mm,SS","Drum D1100-216,Cable 1/8 Inch(3.2 mm),Shaft 1 Inch(25.4)mm,SS","Drum D1100-216,Cable 3/16 Inch(4.8 mm),Shaft 1.25 Inch(31.75)mm,SS","Drum D1100-216,Cable 1/8 Inch(3.2 mm),Shaft 1.25 Inch(31.75)mm,SS","Drum D1350-336,Cable 1/4 Inch(4.8 mm),Shaft 1.25 Inch(31.75)mm,SS","Drum D400-144,Cable 1/8 Inch(3.2 mm),Shaft 1 Inch(25.4)mm,SS","Drum D525-216,Cable 1/8 Inch(3.2 mm),Shaft 1 Inch(25.4)mm,SS","Drum D525-216,Cable 3/16 Inch(4.8 mm),Shaft 1 Inch(25.4)mm,SS","Drum D800-384,Cable 1/4 Inch(6.4 mm),Shaft 1 Inch(25.4)mm,SS","Drum D800-384,Cable 1/4 Inch(6.4 mm),Shaft 1.25 Inch(31.75)mm,SS","Drum D800-384,Cable 3/16 Inch(4.8 mm),Shaft 1.25 Inch(31.75)mm,SS","Drum D800-384,Cable 3/16 Inch(4.8 mm),Shaft 1 Inch(25.4)mm,SS","Drum D850-132,Cable 1/8 Inch(3.2 mm),Shaft 1 Inch(25.4)mm,SS","Drum D850-132,Cable 3/16 Inch(4.8 mm),Shaft 1 Inch(25.4)mm,SS","Drum D1100-216,Cable 5/32 Inch (3.97MM),Shaft 1 Inch(25.4)mm,SS","Drum D1100-216,Cable 5/32 Inch (3.97MM),Shaft 1.25 Inch(31.75)mm,SS","Drum D400-144,Cable 5/32 Inch (3.97MM),Shaft 1 Inch(25.4)mm,SS","Drum D525-216,Cable 5/32 Inch (3.97MM),Shaft 1 Inch(25.4)mm,SS","Drum D850-132,Cable 5/32 Inch (3.97MM),Shaft 1 Inch(25.4)mm,SS","Drum D850-132,Cable 5/32 Inch (3.97MM),Shaft 1.25 Inch(31.75)mm,SS","Drum D1350-336,Cable 1/4 Inch(6.4 mm),Shaft 1 Inch(25.4)mm","Drum D850-132,Cable 1/8 Inch(3.2 mm),Shaft 1.25 Inch(31.75)mm","Drum D850-132,Cable 3/16 Inch(4.8 mm),Shaft 1.25 Inch(31.75)mm"};

    private WebDriver driver;

    public DualShaftStandardLiftTests() {
        this.driver = new EdgeDriver();
        EasyWeb.login(driver);
        loadSTDLIFTDBLAUX();
        EasyWeb.navigateToPage(driver, "Hardware Options");
        EasyWeb.setField(By.id("LIFT_TYPE"), "Standard Lift - Radius(16 inch/406mm)","Std_Lift_16R", driver);
        EasyWeb.navigateToPage(driver, "Springs");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(d -> driver.findElement(By.id("simple-confirm")).isDisplayed());
        ((JavascriptExecutor)driver).executeScript("nextPage()");

        while(driver.getCurrentUrl().equals("https://easywebdev.rwdoors.com/spr/Configuration/interface/edit")){
            if(EasyWeb.getCurrentPage(driver).equals("Springs")){   
               wait.until(d -> driver.findElement(By.id("SPRINGS_ASS_COM_dataTable")).findElement(By.tagName("input")).isDisplayed());
            }
            ((JavascriptExecutor)driver).executeScript("nextPage()");
        }
        wait.until(d -> driver.findElement(By.id("bom-output")).isDisplayed());
        
    }
    private void loadSTDLIFTDBLAUX(){
        EasyWeb.loadNewThermatiteConfig(driver);
        EasyWeb.navigateToPage(driver, "Door Model and Dimensions");
        EasyWeb.setField(By.id("DOOR_MODEL"), "T175", "D", driver);
        EasyWeb.setField(By.id("DOOR_WIDTH_FT"), "4", driver);
        EasyWeb.setField(By.id("DOOR_WIDTH_IN"), "0", driver);
        EasyWeb.setField(By.id("DOOR_HEIGHT_FT"), "22", driver);
    }
    // @Test
    // @DisplayName("Drum Test")
    // void validDrumsOnly() {
    //     loadSTDLIFTDBLAUX();
    //     EasyWeb.navigateToPage(driver, "Springs");
    //     ((JavascriptExecutor)driver).executeScript("nextPage()");
    //     ((JavascriptExecutor)driver).executeScript("nextPage()");
    //     String values = driver.findElement(By.id("DRUMS_CABLES_SHAFT_SELECTION_dataTable")).getText();
    //     assertTrue(values.contains("Drum D800-384,Cable 1/4 Inch(6.4 mm),Shaft 1.25 Inch(31.75)mm,GAL"));
    //     assertTrue(values.contains("Drum D800-384,Cable 3/16 Inch(4.8 mm),Shaft 1.25 Inch(31.75)mm,GAL"));
    //     for(String drum : INVALID_DRUMS){
    //         assertFalse(values.contains(drum));
    //     }
    // }
    @ParameterizedTest(name= "Line Items Test")
    @ValueSource(strings = {
        "425-1105/1" , "425-1106/1" , "425-1101/2" , "411-227/4" , "411-225/4" ,
        "213-041/8" , "214-818/8" ,
        "426-103/2" , "436-0047/2" , "423-337/4" , "7-2350/1" , "328-252/12" 
}) 
    void lineItemOnBom(String lineItemWithQuantity){
        String partNumber = lineItemWithQuantity.split("/")[0];
        String quantity = lineItemWithQuantity.split("/")[1];
        String bom = driver.findElement(By.id("bom-output")).getText();
        System.out.println(bom.split("\\n"));
        driver.quit();
    }
}
