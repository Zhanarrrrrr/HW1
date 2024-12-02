package suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tests.ArticleTests;
import tests.MyListsTest;
@RunWith(Suite.class)
@Suite.SuiteClasses({
        ArticleTests.class,
        MyListsTest.class
})
public class TestSuite {
}
