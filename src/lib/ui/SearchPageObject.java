package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject{
    private static final String
            SKIP_DISCLAIMER = "fragment_onboarding_skip_button",
    SEARCH_INIT_ELEMENT = "//*[@resource-id='org.wikipedia:id/search_container']//*[@text='Search Wikipedia']",
    SEARCH_INPUT ="search_src_text",
    SEARCH_RESULT_BY_SUBSTRING_TPL ="//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='{SUBSTRING']";


    public SearchPageObject(AppiumDriver driver){
        super(driver);
    }

    private static String getResultSearchElement(String substring){
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);

    }

    public void skipDisclaimer(){
        this.waitForElementAndClick(By.id(SKIP_DISCLAIMER),"cannot skip the disclaimer");
    }
    public void initSearchInput(){
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT),"cannot find element");
        this.waitForElementPresent(By.xpath(SEARCH_INIT_ELEMENT),"Cannot find input",5);
    }

    public void typeSearchLine(String search_line){
        this.waitForElementAndSendKeys(By.id(SEARCH_INPUT),search_line,"cannot find search line",5);
    }

    public void waitForSearchResult(String substring){
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent(By.xpath(search_result_xpath),"Cannot find search result with substring"+substring,5);
    }
}
