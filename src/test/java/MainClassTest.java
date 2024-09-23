import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class MainClassTest {
    @Test
    public void testGetClassNumber() {
        MainClass mainClass = new MainClass();
        int actual = mainClass.getClassNumber();
        assertFalse(actual < 45, "УПС! Число меньше 45. Было введено "+ actual);
    }
}

