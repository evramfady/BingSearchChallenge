package Pages;

import Utilities.DataUtils;
import Utilities.LogsUtils;
import Utilities.Utility;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.openqa.selenium.interactions.Actions;

import java.io.IOException;

import static Utilities.Utility.findWebElement;

public class P01_bingSearchPage {

    private final By searchBox = By.xpath("//textarea[@id='sb_form_q']");
    private final By searchBtn = By.xpath("//label[@id='search_icon']");

    private final WebDriver driver;
    String Keywords = DataUtils.getPropertyValue("searchKeywords", "KeyWord");

    public P01_bingSearchPage(WebDriver driver) throws IOException {
        this.driver = driver;
    }

    public P01_bingSearchPage EnterKeywords() {
        Utility.clickingOnElement(driver,searchBox);
        Utility.sendData(driver, searchBox, Keywords);
        return this;
    }

    public P01_bingSearchPage withCustomValidation() {
        String searchBoxText = driver.findElement(searchBox).getAttribute("value");
        Assert.assertFalse(searchBoxText.isEmpty(), "Search box should contain text");
        LogsUtils.info("Custom validation passed: search box contains '" + searchBoxText + "'");
        return this;
    }

    public P02_searchResultsPage PressEnter() {
        new Actions(driver).sendKeys(Keys.ENTER).perform();
        return new P02_searchResultsPage(driver);
    }

    public P02_searchResultsPage ClickOnSearchBtn() {
        WebElement searchIcon = driver.findElement(By.xpath("//label[@id='search_icon']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", searchIcon);
        return new P02_searchResultsPage(driver);
    }

    public boolean URL_Validation() {
        return driver.getCurrentUrl().contains(Keywords);
    }
}