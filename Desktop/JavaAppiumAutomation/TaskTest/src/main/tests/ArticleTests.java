package main.tests;

import lib.CoreTestClass;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class ArticleTests extends CoreTestClass {

    @Test
    public void testFillAndSearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.skipDisclaimer();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("music");
    }

    @Test
    public void testSaveArticletoFav() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.skipDisclaimer();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("music");

        String name_of_folder = "Fav";
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        // Сохраняем первую статью в избранное
        ArticlePageObject.addFirstArticleToSaved(name_of_folder);

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyList();

        ArticlePageObject.addSecondArticleToSaved();


        NavigationUI.clickMyList();
        MyListsPageObject myListsPageObject = new MyListsPageObject(driver);
        myListsPageObject.navigateToMyList();


        myListsPageObject.removeArticleFromList(1);


        myListsPageObject.checkIfOnlyOneArticleIsSaved();
    }
}
