/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package cloud.test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ServerTest {
    @Test void appHasAGreeting() {
        Server classUnderTest = new Server();
        assertNotNull(classUnderTest.getGreeting(), "app should have a greeting");
    }
}