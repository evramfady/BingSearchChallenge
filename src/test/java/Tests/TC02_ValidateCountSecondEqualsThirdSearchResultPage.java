package Tests;

import Listeners.IInvokedMethodListenerClass;
import Listeners.ITestResultListenerClass;
import Pages.P01_bingSearchPage;
import Pages.P02_searchResultsPage;
import Pages.P03_BingSecondResultsPage;
import Pages.P04_BingThirdResultsPage;
import Utilities.DataUtils;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;

import static DriverFactory.DriverFactory.getDriver;

@Listeners({ITestResultListenerClass.class, IInvokedMethodListenerClass.class})
public class TC02_ValidateCountSecondEqualsThirdSearchResultPage extends BaseTestClass {

    @Test(priority = 1)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Test complete search flow using method chaining and anonymous objects")
    @Step("Execute Bing search validation with method chaining")
    public void testSearchFlowWithChaining() throws IOException {
        try {
            new P01_bingSearchPage(getDriver())
                    .EnterKeywords()
                    .PressEnter()
                    .goToNextPage();
            int SecondPageResults = new P03_BingSecondResultsPage(getDriver()).CountNumOfSecondSearchResult();
            new P02_searchResultsPage(getDriver()).goToNextPage();
            int ThirdPageResults = new P04_BingThirdResultsPage(getDriver()).CountNumOfThirdSearchResult();
            Assert.assertEquals(SecondPageResults, ThirdPageResults,
                    "The number of search results on the second and third pages should be equal");
        }
        catch (Exception e) {
            new P01_bingSearchPage(getDriver())
                    .EnterKeywords()
                    .PressEnter()
                    .goToNextPage();
            int SecondPageResults = new P03_BingSecondResultsPage(getDriver()).CountNumOfSecondSearchResult();
            new P02_searchResultsPage(getDriver()).goToNextPage();
            int ThirdPageResults = new P04_BingThirdResultsPage(getDriver()).CountNumOfThirdSearchResult();
            Assert.assertEquals(SecondPageResults, ThirdPageResults,
                    "The number of search results on the second and third pages should be equal");
        }
    }
}
