package Pages;

import Utilities.Utility;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static Utilities.Utility.findWebElements;

public class P02_searchResultsPage {

    private final WebDriver driver;
//    private final By firstSearchResult = By.xpath("(//a[contains(text(),'Vodafone')])[1]");
//    private final By secondSearchResult = By.xpath("(//a[contains(text(),'Vodafone')])[2]");
    private final By searchResultFirstPage = By.xpath("(//a[contains(text(),'Vodafone')])");
    private final By nextPageButton = By.xpath("//a[@title='Next page']");


    public P02_searchResultsPage(WebDriver driver) {
        this.driver = driver;
    }


    public int CountNumOfFirstSearchRes()
    {
        return (findWebElements(driver,searchResultFirstPage).size());
    }

    public P02_searchResultsPage validateRelatedSearches(WebDriver driver)
    {
        List<WebElement> relatedSections = findWebElements(driver, searchResultFirstPage);

        // Condition 1: At least 2 sections
        if (relatedSections.size() >= 2) {
            System.out.println("✅ Found " + relatedSections.size() + " 'Related searches for Vodafone' sections.");
        } else {
            throw new AssertionError("❌ Expected at least 2 related searches sections, but found: " + relatedSections.size());
        }
        // Condition 2 : Validate the keyword
        for (WebElement item : relatedSections) {
            String text = item.getText().toLowerCase();
            if (text.contains("vodafone")) {
                System.out.println("✅ Item contains Vodafone: " + text);
            } else {
                throw new AssertionError("❌ Item does not contain Vodafone: " + text);
            }
        }
        return this;
    }

    @Step("Navigate to second results page")
    public P03_BingSecondResultsPage goToNextPage() {
        Utility.scrolling(driver,nextPageButton);
        Utility.clickingOnElement(driver,nextPageButton);
        return new P03_BingSecondResultsPage(driver);
    }

}
