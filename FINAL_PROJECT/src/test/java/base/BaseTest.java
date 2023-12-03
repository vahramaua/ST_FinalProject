package base;

import com.google.common.io.Files;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.HomePage;
import pages.ProductPage;

import java.io.File;
import java.io.IOException;

public class BaseTest {
    public static final String WEBDRIVER = "webdriver.chrome.driver";
    public static final String DRIVER_PATH = "src/drivers/chromedriver";
    public static final String baseURL = "https://onlinearmenianstore.com/";

    private WebDriver driver;

    protected HomePage homePage;

    protected ProductPage productPage;

    @BeforeClass
    public void setUp()
    {
        System.setProperty(WEBDRIVER,DRIVER_PATH);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        goHome();
    }

    @BeforeMethod
    public void goHome()
    {
        driver.get(baseURL);
        homePage = new HomePage(driver);
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }


    @AfterMethod
    public void recordFailure(ITestResult result)
    {
        if((ITestResult.FAILURE == result.getStatus())) {
            var camera = (TakesScreenshot)driver;
            File screenshot = camera.getScreenshotAs(OutputType.FILE);
            try{
                Files.move(screenshot, new File("src/screenshots" +  result.getName() + ".png"));
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
}
