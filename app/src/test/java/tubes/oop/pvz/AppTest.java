package tubes.oop.pvz;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    void mainHasOutput() {
        Main classUnderTest = new Main();
        assertNotNull(classUnderTest, "main class should not be null");
    }
}