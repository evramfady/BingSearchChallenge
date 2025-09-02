package Tests;

import DriverFactory.DriverFactory;
import Pages.P01_bingSearchPage;
import Utilities.DataUtils;
import Utilities.LogsUtils;
import org.testng.annotations.*;

import java.io.IOException;

public class BaseTestClass {

    @Parameters("browser")
    @BeforeMethod
    public void setup(@Optional("chrome") String browser) throws IOException {
        LogsUtils.info("Setting up " + browser + " browser");
        DriverFactory.SetUpDriver(browser);
        String baseUrl = DataUtils.getPropertyValue("environment", "BASE_URL");
        DriverFactory.getDriver().get(baseUrl);
        LogsUtils.info("Navigated to: " + baseUrl);
    }
    @AfterMethod
    public void tearDown() {
        LogsUtils.info("Cleaning up browser session");
        if (DriverFactory.getDriver() != null) {
            DriverFactory.quitDriver();
        }
    }
}
