import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.cdimascio.dotenv.Dotenv;


public class EasyWeb {
    public static void main(String[] args) throws Exception{
       WebDriver driver = new EdgeDriver();
       EasyWeb.login(driver);
       EasyWeb.loadNewThermatiteConfig(driver);
       EasyWeb.navigateToPage(driver, "Door Model and Dimensions");
       EasyWeb.setDropdown(By.id("DOOR_MODEL"), "T175", "D", driver);
       EasyWeb.setDropdown(By.id("DOOR_WIDTH_FT"), "4", driver);
       EasyWeb.setDropdown(By.id("DOOR_WIDTH_IN"), "0", driver);
       EasyWeb.setDropdown(By.id("DOOR_HEIGHT_FT"), "22", driver);
       EasyWeb.navigateToPage(driver, "Hardware Options");
       EasyWeb.setDropdown(By.id("LIFT_TYPE"), "Standard Lift - Radius(16 inch/406mm)", "Std_Lift_16R", driver);
       EasyWeb.navigateToPage(driver, "Springs");
       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
       wait.until(d -> driver.findElement(By.id("simple-confirm")).isDisplayed());
       ((JavascriptExecutor) driver).executeScript("nextPage()");

       while (driver.getCurrentUrl().equals("https://easywebdev.rwdoors.com/spr/Configuration/interface/edit")) {
           if (EasyWeb.getCurrentPage(driver).equals("Springs")) {
               wait.until(d -> driver.findElement(By.id("SPRINGS_ASS_COM_dataTable")).findElement(By.tagName("input"))
                       .isDisplayed());
           }
           ((JavascriptExecutor) driver).executeScript("nextPage()");
       }
       wait.until(d -> driver.findElement(By.id("bom-output")).isDisplayed());
       driver.manage().window().fullscreen();
      // driver.close();
    }   
    public static boolean isOnBOM(String partNumber, WebDriver driver){
        String bom = driver.findElement(By.id("bom-output")).getText();
        return bom.contains(partNumber);
    }
    public static String generateNewName(){
        return "AUTOMATED THERMATITE TEST " + System.currentTimeMillis();
    }
    public static void loadNewThermatiteConfig(WebDriver driver){
        loadNewThermatiteConfig(driver, generateNewName());
    }
    public static boolean isBeforePage(String page, WebDriver driver){
        try{
            driver.findElement(By.id("future-links")).findElement(By.name(page));
        }catch(NoSuchElementException e){
            return false;
        }
        return true;

    }
    public static void navigateToBOM(WebDriver driver){
        while(driver.getCurrentUrl().equals("https://easywebdev.rwdoors.com/spr/Configuration/interface/edit")){
            ((JavascriptExecutor)driver).executeScript("nextPage()");

        }
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(d -> driver.findElement(By.id("bom-output")).isDisplayed());
    }
    public static void navigateToPage(WebDriver driver, String pageName){
        while(!driver.findElement(By.cssSelector(".green-3-gradient.group-header")).getText().equals(pageName)){
            if(isBeforePage(pageName, driver)){
                ((JavascriptExecutor)driver).executeScript("nextPage()");
            }else{
                ((JavascriptExecutor)driver).executeScript("prevPage()");
            }
        }
    }
    public static String getCurrentPage(WebDriver driver){
        try{
            return driver.findElement(By.cssSelector(".green-3-gradient.group-header")).getText();

        }catch(NoSuchElementException e){
            return "";
        }
    }
    public static void loadNewThermatiteConfig(WebDriver driver, String name){
       
        driver.get("https://easywebdev.rwdoors.com/spr/Configuration/create/category/211539491?origin=");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));


        WebElement thermatiteForm = driver.findElement(By.id("configure_product_220739272"));
        thermatiteForm.submit();
        WebElement nameForm = driver.findElement(By.id("design_name"));
        nameForm.sendKeys(name);
       

        WebElement continueButton = driver.findElement(By.id("continue-design-button"));

        continueButton.click();
    }
    public static void login(WebDriver driver){
        driver.get("https://easywebdev.rwdoors.com/");
        WebElement username = driver.findElement(By.id("username"));
        WebElement password = driver.findElement(By.id("password"));
        username.sendKeys(Dotenv.load().get("ewusername"));
        password.sendKeys(Dotenv.load().get("ewpassword"));
        WebElement signIn = driver.findElement(By.name("login"));
        signIn.click();
         //Need to accept Cookies
         WebElement cookies = driver.findElement(By.id("gdpr-accept-button"));
         cookies.click();
    }
    public static void setDropdown(By by, String keys, String value, WebDriver driver){
        WebElement field = driver.findElement(by);
        while(!field.getAttribute("value").equals(value))
            field.sendKeys(keys);
       sleep(500);
    }
    public static void setDropdown(By by, String keys,  WebDriver driver){
        setDropdown(by, keys,keys, driver);
    }
    static void sleep(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void setDimensions(String model, int widthFeet, int widthInches, int heightFeet, int heightInches, WebDriver driver) {
        navigateToPage(driver, "Door Model and Dimensions");
        setDropdown(By.id("DOOR_MODEL"), model, driver);
        setDropdown(By.id("DOOR_WIDTH_FT"), "" + widthFeet, driver);
       setDropdown(By.id("DOOR_WIDTH_IN"), "" + widthInches, driver);
       setDropdown(By.id("DOOR_HEIGHT_FT"), "" + heightFeet, driver);
       setDropdown(By.id("DOOR_HEIGHT_IN"), "" + heightInches, driver);

    }
    public static void setDimensions(int widthFeet, int widthInches, int heightFeet, int heightInches, WebDriver driver) {
        navigateToPage(driver, "Door Model and Dimensions");
        setDropdown(By.id("DOOR_WIDTH_FT"), "" + widthFeet, driver);
       setDropdown(By.id("DOOR_WIDTH_IN"), "" + widthInches, driver);
       setDropdown(By.id("DOOR_HEIGHT_FT"), "" + heightFeet, driver);
       setDropdown(By.id("DOOR_HEIGHT_IN"), "" + heightInches, driver);

    }
}
