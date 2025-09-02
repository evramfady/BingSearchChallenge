package Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static Utilities.Utility.findWebElements;

public class P04_BingThirdResultsPage {
    private final WebDriver driver;

    private final By searchResultPage = By.xpath("//strong[contains(text(),'Vodafone')]");


    public P04_BingThirdResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Count search results on the Third page")
    public int CountNumOfThirdSearchResult()
    {
        return (findWebElements(driver,searchResultPage).size());
    }

}
