/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package windowhandleautomation;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class App {
    public void getGreeting(WebDriver driver) {

        TestCases tests = new TestCases(); // Initialize your test class

        

        tests.testCase01(driver);

        //END Tests
        tests.endTest(driver); // End your test by clearning connections and closing browser

        
    }

    public static void main(String[] args) {

        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        new App().getGreeting(driver);
    }
}
