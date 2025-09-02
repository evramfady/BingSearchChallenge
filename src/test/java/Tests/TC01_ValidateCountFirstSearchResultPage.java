package Tests;

import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultListenerClass;
import Pages.P01_bingSearchPage;
import Pages.P02_searchResultsPage;
import Utilities.DataUtils;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;

import static DriverFactory.DriverFactory.getDriver;

@Listeners({ITestResultListenerClass.class, IInvokedMethodListenerClass.class})
public class TC01_ValidateCountFirstSearchResultPage extends BaseTestClass {

    @Test(priority = 1)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test complete search flow using method chaining and anonymous objects")
    @Step("Execute Bing search validation with method chaining")
    public void testSearchFlowWithChaining() throws IOException {
        int MinimumSearchResults = Integer.parseInt(DataUtils.getPropertyValue("searchKeywords", "MinimumSearchResults"));

        try {
            new P01_bingSearchPage(getDriver())
                    .EnterKeywords()
                    .PressEnter();
        }
        catch (Exception e) {
            new P01_bingSearchPage(getDriver()).EnterKeywords().PressEnter();
        }
        Assert.assertTrue(MinimumSearchResults < new P02_searchResultsPage(getDriver()).CountNumOfFirstSearchRes(),
                "The number of search results should be greater than " + MinimumSearchResults);
    }
}
