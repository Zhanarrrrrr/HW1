package main.tests;

import lib.CoreTestClass;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class MyListsTest extends CoreTestClass {
    @Test
    public void testSwipeArticle() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.skipDisclaimer();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("music");
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
        public void removeArticleFromList(int articleIndex) {
            this.swipeElementToLeft(
                    getArticleElementByIndex(articleIndex),
                    "Не удалось удалить статью по индексу: " + articleIndex
            );
        }

        public void checkIfOnlyOneArticleIsSaved() {
            int articlesCount = this.getSavedArticlesCount();
            if (articlesCount != 1) {
                throw new AssertionError("В списке должно остаться только одна статья, но их " + articlesCount);
            }
        }

        private int getSavedArticlesCount() {
            return this.waitForElements(
                    By.xpath("//android.widget.TextView[@resource-id='org.wikipedia:id/page_list_item_title']")
            ).size();
        }

    }


}
