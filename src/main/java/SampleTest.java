import lib.CoreTestClass;
import lib.ui.*;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SampleTest extends CoreTestClass {
    private MainPageObject MainPageObject;

    protected void setUp() throws Exception {
        super.setUp();
        MainPageObject = new MainPageObject(driver);
    }

    @Test
    @org.junit.jupiter.api.Order(1)
    public void testFillAndSearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.skipDisclaimer();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("music");
//        SearchPageObject.waitForSearchResult("Musical theatre");
    }

    @Test
    public void testSaveArticletoFav() {
        testFillAndSearch();
        String name_of_folder = "Fav";
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.addFirstArticleToSaved(name_of_folder);
        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyList();
        ArticlePageObject.addSecondArticleToSaved();
        NavigationUI.clickMyList();
        NavigationUI.clickMyList();
        MyListsPageObject myListsPageObject = new MyListsPageObject(driver);
        myListsPageObject.navigateToMyList();
        myListsPageObject.displayList();
//
//        MainPageObject.waitForElementAndClick(
//                By.xpath(
//                        "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='Musical theatre']"),
//                "Cannot be clicked to this article"
//        );
//        MainPageObject.waitForElementAndClick(
//                By.id("org.wikipedia:id/page_save"),
//                "Cannot be saved"
//        );
//        MainPageObject.waitForElementAndClick(
//                By.id("org.wikipedia:id/snackbar_action"),
//                "Cannot be added to folder"
//        );
//        MainPageObject.waitForElementAndSendKeys(
//                By.id("org.wikipedia:id/text_input"),
//                "Fav",
//                "cannot rename the folder",
//                10
//        );
//        MainPageObject.waitForElementAndClick(
//                By.id("android:id/button1"),
//                "Rename is not saved"
//        );
//        MainPageObject.waitForElementAndClick(
//                By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]"),
//                "Cannot go back to the article list"
//        );
//        MainPageObject.waitForElementAndClick(
//                By.xpath(
//                        "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@text='MusicBrainz']"),
//                "Cannot be clicked to this article"
//        );
//        MainPageObject.waitForElementAndClick(
//                By.id("org.wikipedia:id/page_save"),
//                "Cannot be saved"
//        );
//        MainPageObject.waitForElementAndClick(
//                By.id("org.wikipedia:id/snackbar_action"),
//                "Cannot be added to folder"
//        );
//        MainPageObject.waitForElementAndClick(
//                By.xpath("//*[@text='Fav']"),
//                "The second article cannot be added to Fav"
//        );
//        MainPageObject.waitForElementAndClick(
//                By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]"),
//                "Cannot go back to the article list"
//        );
//        MainPageObject.waitForElementAndClick(
//                By.xpath("//android.widget.ImageButton[@content-desc=\"Navigate up\"]"),
//                "Cannot go back to the main menu"
//        );
//        MainPageObject.waitForElementAndClick(
//                By.id("org.wikipedia:id/nav_tab_reading_lists"),
//                "Cannot enter to the list of Saved"
//        );
//        MainPageObject.waitForElementAndClick(
//                By.id("org.wikipedia:id/negativeButton"),
//                "Cannot enter to the list of Saved"
//        );

//        MainPageObject.waitForElementAndClick(
//                By.xpath(
//
//                        "//*[@text='Fav']"),
//                "The second article cannot be added to Fav"
//        );
    }


    @Test
    public void testSwipeArticle() {
        testFillAndSearch();
        testSaveArticletoFav();
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.swipeArticle();
        MainPageObject.swipeElemenToLeft(
                By.xpath(
                        "//*[@text='Musical theatre']"),
                "Cannot be deleted"
        );
        MainPageObject.waitForElementPresent(By.xpath(
                        "//*[@text='MusicBrainz']"),
                "The article is not in folder", 10);


        MainPageObject.waitForElementAndClick(By.xpath(
                        "//*[@text='MusicBrainz']"),
                "cannot be clicked");

        MainPageObject.clickToCheckTitle(By.xpath(
                        "//*[@text='MusicBrainz']"),
                "The article is not in folder");
    }


}