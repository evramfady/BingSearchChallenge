# Comprehensive Test Cases for Bing Search Validation

## Project Overview
This is a Selenium WebDriver automation project that validates Bing search functionality across multiple browsers using **anonymous objects** and **method chaining** patterns for elegant, fluent test code.

## Test Architecture
- **Framework**: Selenium + TestNG + Maven + Allure
- **Pattern**: Page Object Model with Fluent API
- **Browsers**: Chrome, Firefox, Edge (parallel execution)
- **Reporting**: Allure with screenshots and logs

## Test Cases Created

### 1. TC01_EnterKeyWordSearch (Basic Search Tests)
**Purpose**: Core search functionality validation using method chaining

**Test Methods**:
```java
// Anonymous object with method chaining
new P01_bingSearchPage(DriverFactory.getDriver())
    .EnterKeywords()
    .PressEnter()
    .validateRelatedSearches(DriverFactory.getDriver())
    .goToNextPage()
    .goToNextPage();
```

**Tests Include**:
- `testSearchFlowWithChaining()` - Complete search flow using Enter key
- `testSearchButtonWithChaining()` - Search using click button
- `testSearchResultsCountWithChaining()` - Count results across pages
- `testFluentNavigationChain()` - Single mega-chain navigation

### 2. TC02_AdvancedSearchTests (Advanced Scenarios)
**Purpose**: Complex navigation and validation scenarios

**Key Features**:
```java
// Cross-page validation in single chain
int resultsCount = new P01_bingSearchPage(DriverFactory.getDriver())
    .EnterKeywords()
    .PressEnter()
    .validateRelatedSearches(DriverFactory.getDriver())
    .goToNextPage()
    .CountNumOfSearchRes();
```

**Tests Include**:
- `testCrossPageValidationChain()` - Single chain across all pages
- `testURLValidationThroughoutChain()` - URL validation at each step
- `testMixedNavigationMethods()` - Both Enter and click methods
- `testMegaChainWithValidations()` - Complete mega-chain execution

### 3. TC03_NegativeTestCases (Error Scenarios)
**Purpose**: Handle edge cases and error conditions gracefully

**Key Features**:
```java
// Anonymous object overriding behavior for negative testing
new P01_bingSearchPage(DriverFactory.getDriver()) {
    @Override
    public P01_bingSearchPage EnterKeywords() {
        Utility.sendData(DriverFactory.getDriver(), 
            By.xpath("//textarea[@id='sb_form_q']"), "");
        return this;
    }
}.EnterKeywords()
 .PressEnter()
 .validateRelatedSearches(DriverFactory.getDriver());
```

**Tests Include**:
- `testEmptySearchChain()` - Empty search keyword handling
- `testInvalidURLWithRecovery()` - Invalid URL navigation and recovery
- `testOverNavigationChain()` - Navigation beyond available pages
- `testBrowserNavigationImpact()` - Back/forward button impact

### 4. TC04_DataDrivenTests (Multiple Data Sets)
**Purpose**: Test with various search keywords and methods

**Key Features**:
```java
@DataProvider(name = "searchKeywords")
public Object[][] provideSearchKeywords() {
    return new Object[][]{
        {"Vodafone"}, {"Technology"}, {"Microsoft"}, 
        {"Java Programming"}, {"Selenium WebDriver"}
    };
}

// Dynamic keyword override using anonymous objects
new P01_bingSearchPage(DriverFactory.getDriver()) {
    @Override
    public P01_bingSearchPage EnterKeywords() {
        Utility.sendData(DriverFactory.getDriver(), 
            By.xpath("//textarea[@id='sb_form_q']"), keyword);
        return this;
    }
}.EnterKeywords().PressEnter();
```

**Tests Include**:
- `testSearchWithDifferentKeywords()` - Multiple search terms
- `testFullNavigationChainWithKeywords()` - Complete navigation per keyword
- `testDifferentSearchMethods()` - Enter vs Click button methods
- `testMultipleSequentialChains()` - Sequential search chains

### 5. TC05_UtilityAndPerformanceTests (Infrastructure Tests)
**Purpose**: Test utility methods and performance metrics

**Key Features**:
```java
// Performance timing with anonymous objects
new P01_bingSearchPage(DriverFactory.getDriver()) {
    private Instant searchStartTime;
    
    @Override
    public P01_bingSearchPage EnterKeywords() {
        searchStartTime = Instant.now();
        super.EnterKeywords();
        return this;
    }
    
    public P01_bingSearchPage logSearchTime() {
        Duration searchDuration = Duration.between(searchStartTime, Instant.now());
        LogsUtils.info("Search input took: " + searchDuration.toMillis() + "ms");
        return this;
    }
}.EnterKeywords().PressEnter();
```

**Tests Include**:
- `testDriverFactoryWithChaining()` - DriverFactory validation
- `testDataUtilsWithChaining()` - Property file reading
- `testSearchPerformanceWithChaining()` - Performance measurement
- `testUtilityMethodsWithChaining()` - Screenshot and utility methods
- `testCrossBrowserCompatibilityChain()` - Browser feature detection

### 6. TC06_FluentAPITests (Advanced Patterns)
**Purpose**: Demonstrate advanced anonymous object patterns

**Key Features**:
```java
// Builder pattern with anonymous objects
new Object() {
    private String keyword = "Vodafone";
    private boolean useEnterKey = true;
    private int targetPage = 2;
    
    public Object withKeyword(String kw) {
        this.keyword = kw;
        return this;
    }
    
    public Object withClickButton() {
        this.useEnterKey = false;
        return this;
    }
    
    public void execute() throws IOException {
        // Implementation
    }
}.withKeyword("Technology")
 .withClickButton()
 .toPage(2)
 .execute();
```

**Tests Include**:
- `testFluentSearchWithCustomValidation()` - Custom validation in chain
- `testConditionalChaining()` - Conditional logic execution
- `testBuilderPatternChaining()` - Builder pattern implementation
- `testLambdaStyleChaining()` - Lambda expressions with anonymous objects
- `testCallbackPatternChaining()` - Callback pattern execution

## Key Design Patterns Used

### 1. Anonymous Objects for Behavior Override
```java
new P01_bingSearchPage(DriverFactory.getDriver()) {
    @Override
    public P01_bingSearchPage EnterKeywords() {
        // Custom implementation
        return this;
    }
}
```

### 2. Method Chaining for Fluent API
```java
searchPage.EnterKeywords()
          .PressEnter()
          .validateRelatedSearches(driver)
          .goToNextPage()
          .CountNumOfSearchRes();
```

### 3. Lambda Expressions with Anonymous Objects
```java
Function<String, Boolean> searchAndValidate = (keyword) -> {
    // Search implementation
    return validationResult;
};
```

### 4. Builder Pattern Integration
```java
new SearchBuilder()
    .withKeyword("Technology")
    .withClickButton()
    .toPage(2)
    .execute();
```

## Running the Tests

### Run Specific Test Suite:
```bash
mvn test -Dsurefire.suiteXmlFiles=TestRunner/bingSearchValidation.xml
```

### Run Comprehensive Suite:
```bash
mvn test -Dsurefire.suiteXmlFiles=TestRunner/comprehensiveTestSuite.xml
```

### Run with Specific Browser:
```bash
mvn test -Dbrowser=chrome
```

## Test Coverage

### Functional Testing:
- ✅ Basic search functionality
- ✅ Navigation between result pages
- ✅ URL validation
- ✅ Search result counting
- ✅ Related searches validation

### Cross-Browser Testing:
- ✅ Chrome compatibility
- ✅ Firefox compatibility  
- ✅ Edge compatibility
- ✅ Parallel execution

### Data-Driven Testing:
- ✅ Multiple search keywords
- ✅ Different search methods (Enter/Click)
- ✅ Various navigation patterns

### Negative Testing:
- ✅ Empty search handling
- ✅ Invalid URL recovery
- ✅ Over-navigation scenarios
- ✅ Browser navigation impact

### Performance Testing:
- ✅ Search timing measurement
- ✅ Chain execution performance
- ✅ Resource cleanup validation

### Utility Testing:
- ✅ DriverFactory operations
- ✅ DataUtils property reading
- ✅ Screenshot functionality
- ✅ Logging mechanisms

## Benefits of Anonymous Objects + Method Chaining

1. **Readability**: Tests read like natural language
2. **Maintainability**: Less boilerplate code
3. **Flexibility**: Easy behavior customization
4. **Reusability**: Chainable methods promote reuse
5. **Testing**: Easy to test different scenarios
6. **Debugging**: Clear execution flow

## Advanced Patterns Demonstrated

1. **Conditional Chaining**: Logic-based execution paths
2. **Builder Pattern**: Configurable test execution
3. **Lambda Integration**: Functional programming approach
4. **Callback Pattern**: Event-driven test execution
5. **Performance Monitoring**: Built-in timing measurement
6. **Dynamic Behavior**: Runtime method overriding

This comprehensive test suite provides thorough coverage of the Bing search functionality while demonstrating modern Java testing patterns with anonymous objects and method chaining.
