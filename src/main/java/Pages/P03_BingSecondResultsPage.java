package Pages;

import Utilities.Utility;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static Utilities.Utility.findWebElements;

public class P03_BingSecondResultsPage {

    private final WebDriver driver;

    private final By searchResultPage = By.xpath("//strong[contains(text(),'Vodafone')]");
    private final By nextPageButton = By.xpath("//a[@title='Next page']");

    public P03_BingSecondResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Count search results on the second page")
    public int CountNumOfSecondSearchResult()
    {
        return (findWebElements(driver, searchResultPage).size());
    }

    @Step("Navigate to Third results page")
    public P04_BingThirdResultsPage goToNextPage() {
        Utility.scrolling(driver,nextPageButton);
        Utility.clickingOnElement(driver,nextPageButton);
        return new P04_BingThirdResultsPage(driver);
    }






}
