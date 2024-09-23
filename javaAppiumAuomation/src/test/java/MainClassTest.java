import org.junit.Test;

//import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainClassTest {
    @Test
    public void testGetLocalNumber() {
        MainClass mainClass = new MainClass();
        int expected = 14;
        int actual = mainClass.getLocalNumber();
        assertEquals(expected, actual, "Numbers are not equal");

    }


}
