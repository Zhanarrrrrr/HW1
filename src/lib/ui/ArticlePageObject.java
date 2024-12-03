package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class ArticlePageObject extends MainPageObject {
    private static final String
            ARTICLE_TITLE_FIRST = "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Musical theatre']",
            ARTICLE_SAVE_BUTTON = "page_save",
            ARTICLE_SAVE_FOLDER_BUTTON = "snackbar_action",
            LIST_NAME_INPUT = "text_input",
            LIST_NAME_RENAME = "android:id/button1",
            ARTICLE_TITLE_SECOND = "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='MusicBrainz']",
            FAV_LIST = "//*[@text='Fav']",
            ARTICLE_TITLE = "//*[@text='Musical theatre']";


    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public void addFirstArticleToSaved(String name_of_folder) {
        this.waitForElementAndClick(
                By.xpath(ARTICLE_TITLE_FIRST),
                "Cannot be clicked to this article"
        );
        this.waitForElementAndClick(
                By.id(ARTICLE_SAVE_BUTTON),
                "Cannot be saved"
        );
        this.waitForElementAndClick(
                By.id(ARTICLE_SAVE_FOLDER_BUTTON),
                "Cannot be added to folder"
        );
        this.waitForElementAndSendKeys(
                By.id(LIST_NAME_INPUT),
                name_of_folder,
                "cannot rename the folder",
                10
        );
        this.waitForElementAndClick(
                By.id(LIST_NAME_RENAME),
                "Rename is not saved"
        );
    }

    public void addSecondArticleToSaved() {
        this.waitForElementAndClick(
                By.xpath(ARTICLE_TITLE_SECOND),
                "Cannot be clicked to this article"
        );
        this.waitForElementAndClick(
                By.id(ARTICLE_SAVE_BUTTON),
                "Cannot be saved"
        );
        this.waitForElementAndClick(
                By.id(ARTICLE_SAVE_FOLDER_BUTTON),
                "Cannot be added to folder"
        );
        this.waitForElementAndClick(
                By.xpath(FAV_LIST),
                "The second article cannot be added to Fav"
        );
    }

    public void swipeArticle() {

        this.swipeElemenToLeft(
                By.xpath(
                        ARTICLE_TITLE),
                "Cannot be deleted"
        );
        this.waitForElementPresent(By.xpath(
                        ARTICLE_TITLE),
                "The article is not in folder", 10);


        this.waitForElementAndClick(By.xpath(
                        ARTICLE_TITLE),
                "cannot be clicked");

        this.clickToCheckTitle(By.xpath(
                        ARTICLE_TITLE),
                "The article is not in folder");
    }

}

