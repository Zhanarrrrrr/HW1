import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainClassTest {

    @Test
    public void testGetClassString() {
        MainClass MainClass = new MainClass();
        String actual = MainClass.getClassString();
        assertTrue(actual.contains("hello") || actual.contains("Hello"), "Yes, answer includes necessary word");


    }
}
