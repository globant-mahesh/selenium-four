package org.example;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.apache.log4j.PropertyConfigurator;


import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    private static WebDriver driver = null;
    private static Logger logger;


    @BeforeClass
    public static void setup() {
//        logger = LogManager.getLogger(AppTest.class);
        logger = LoggerFactory.getLogger(AppTest.class);
//        PropertyConfigurator.configure("src/main/resources/log4j.properties");
        logger.info("Setting Test Case");
        System.setProperty("webdriver.chrome.driver","./src/test/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Before
    public void logBeforeTest() {
        System.out.println("Here!!!!!!!!!!!!!!");
        logger.info("Running Test Case");
    }

    @After
    public void logAfterTest() {
        logger.debug("Done with test execution");
    }

    @Test
    public void shouldAnswerWithTrue()
    {
        driver.get("https://www.google.com");
//        WebElement searchBtn = new WebDriverWait(driver, Duration.ofSeconds(10))
//                .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='CqAVzb lJ9FBc']//input[@name='btnK']")));
        WebElement ele = driver.findElement(By.xpath("//input[@name='q']"));
        ele.sendKeys("Amazon");
        driver.findElement(By.xpath("//input[@name='q']")).submit();
//        searchBtn.click();
        WebElement resultLnk = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='tF2Cxc']//h3[@class='LC20lb MBeuO DKV0Md']/parent::a")));
//        driver.findElement(By.xpath("//div[@class='tF2Cxc']//h3[@class='LC20lb MBeuO DKV0Md']/parent::a")).click();
        resultLnk.click();
        String titleTxt = driver.getTitle();
        assertThat(titleTxt, equalToIgnoringCase("Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in"));
    }

//    @Test
    public void testAmazon() {
        WebElement searchTxt = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
        new Actions(driver).click(searchTxt).keyDown(Keys.SHIFT).sendKeys("f").keyUp(Keys.SHIFT).sendKeys("ootwear").keyDown(Keys.ENTER).keyUp(Keys.ENTER).build().perform();
        WebElement resultTxt = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='RESULTS']")));
        List<WebElement> resultTabs = driver.findElements(By.xpath("//span[@class='a-size-base a-color-base a-text-normal']"));
        int count = resultTabs.size();
        WebElement wb = resultTabs.get(1);
//            int count = driver.findElements(By.xpath("//span[@class='a-size-base a-color-base a-text-normal']")).size();
        assertThat(count, lessThanOrEqualTo(48));
    }

//    @Test
    public void testProduct() {
        WebElement resultTxt = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[contains(@class,'a-color-base a-text-normal')]/parent::a)[1]")));
//        (//span[@class='a-size-base-plus a-color-base a-text-normal']/parent::a)[1]
        List<WebElement> results = driver.findElements(By.xpath("//span[contains(@class,'a-color-base a-text-normal')]/parent::a"));
        results.get(0).click();
        Set<String> tabs = driver.getWindowHandles();
        Iterator itr = tabs.iterator();
        String newWindowHandle= null;
        while(itr.hasNext()) {
            newWindowHandle = itr.next().toString();
        }
        driver.switchTo().window(newWindowHandle);
        new WebDriverWait(driver, Duration.ofSeconds(60))
                .until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//label[text()='Size:']"))));
        Select sizeSelect = new Select(driver.findElement(By.xpath("//Select[@name='dropdown_selected_size_name']")));
        sizeSelect.selectByIndex(1);
//        sizeSelect.selectByValue("3 Little Kid");
//        sizeSelect.selectByVisibleText("11 Little Kid");
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String priceTxt = driver.findElement(By.xpath("//span[contains(@class,'reinventPricePriceToPayMargin')]//span[@class='a-price-symbol']")).getText();
        assertThat(priceTxt, startsWith("₹"));
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }

}
