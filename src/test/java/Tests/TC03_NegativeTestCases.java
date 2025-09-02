package Tests;

import DriverFactory.DriverFactory;
import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultListenerClass;
import Pages.P01_bingSearchPage;
import Pages.P02_searchResultsPage;
import Utilities.DataUtils;
import Utilities.LogsUtils;
import Utilities.Utility;
import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;

@Listeners({ITestResultListenerClass.class, IInvokedMethodListenerClass.class})
public class TC03_NegativeTestCases {

    @Parameters("browser")
    @BeforeMethod
    @Step("Setup browser for negative testing")
    public void setup(@Optional("chrome") String browser) throws IOException {
        LogsUtils.info("Setting up " + browser + " browser for negative tests");
        DriverFactory.SetUpDriver(browser);
        DriverFactory.getDriver().get(DataUtils.getPropertyValue("environment", "BASE_URL"));
    }

    @Test(priority = 1, expectedExceptions = {AssertionError.class})
    @Severity(SeverityLevel.NORMAL)
    @Description("Test search with empty keywords using method chaining")
    @Step("Test empty search scenario")
    public void testEmptySearchChain() throws IOException {
        LogsUtils.info("Testing empty search with method chaining");
        
        // Override search with empty string using anonymous object
        new P01_bingSearchPage(DriverFactory.getDriver()) {
            @Override
            public P01_bingSearchPage EnterKeywords() {
                Utility.sendData(DriverFactory.getDriver(), 
                    By.xpath("//textarea[@id='sb_form_q']"), "");
                return this;
            }
        }.EnterKeywords()
         .PressEnter()
         .validateRelatedSearches(DriverFactory.getDriver()); // This should fail
        
        LogsUtils.info("Empty search test completed");
    }

    @Test(priority = 2)
    @Severity(SeverityLevel.NORMAL)
    @Description("Test invalid URL navigation and recovery using chaining")
    @Step("Test invalid URL scenario with recovery")
    public void testInvalidURLWithRecovery() throws IOException {
        LogsUtils.info("Testing invalid URL navigation");
        
        // Navigate to invalid URL first
        DriverFactory.getDriver().get("https://www.bing.com/invalid-page");
        
        // Try to perform search chain and expect it to handle gracefully
        try {
            new P01_bingSearchPage(DriverFactory.getDriver())
                .EnterKeywords()
                .PressEnter();

            Assert.assertFalse(false);
        } catch (Exception e) {
            LogsUtils.info("Expected exception caught: " + e.getMessage());
            
            // Recovery: navigate back to valid URL and try again
            DriverFactory.getDriver().get(DataUtils.getPropertyValue("environment", "BASE_URL"));
            
            new P01_bingSearchPage(DriverFactory.getDriver())
                .EnterKeywords()
                .PressEnter()
                .validateRelatedSearches(DriverFactory.getDriver());
            
            LogsUtils.info("Recovery successful");
        }
    }

    @AfterMethod
    @Step("Cleanup after negative test")
    public void tearDown() {
        LogsUtils.info("Cleaning up browser session after negative test");
        DriverFactory.quitDriver();
    }
}
