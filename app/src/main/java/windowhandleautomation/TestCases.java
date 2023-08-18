package windowhandleautomation;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class TestCases {
    
    public void endTest(WebDriver driver)
    {
        System.out.println("End Test: TestCases");
       
        driver.quit();

    }

    public static void takeScreenShot(WebDriver driver, String screenshotType, String description){
        try {
            
            //Create folder if not exists
            File filDir = new File("./screenshots");
            if(!filDir.exists()){
                filDir.mkdirs();
            }

            //Generate a unique string using timestamp
            String timeStamp = String.valueOf(java.time.LocalDateTime.now()).replace(":", ".");
            
            //Generate filename
            String fileName = String.format("Screenshot_%s_%s_%s.png", timeStamp,screenshotType,description);

            //create object of TakeScreenshot
            TakesScreenshot screenshot = ((TakesScreenshot)driver);
            
            //Take screenshot
            File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);

            //Save screenshot in screenshots folder
            File destinationFile = new File("screenshots/", fileName);
            FileUtils.copyFile(sourceFile, destinationFile);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    public  void testCase01(WebDriver driver){

        try {
            
            System.out.println("Start Test case: testCase01");
            System.out.println("");

            //navigate to website
            driver.get("https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_win_open");

            //Store current windowHandle
            String parentWindow = driver.getWindowHandle();

            //switch to frame
            driver.switchTo().frame("iframeResult");

            

            //Click on Try it
            driver.findElement(By.xpath("//button[contains(text(),'Try it')]")).click();
            

            //Store windowHandles in a set
            Set<String> windows = new HashSet<>();
            windows = driver.getWindowHandles();

            

            for (String string : windows) {
                 if(!string.equals(parentWindow)){
                    driver.switchTo().window(string);
                 }
            }

           Thread.sleep(6000);

            //take screenshot
            takeScreenShot(driver, "CurrentWindow", "TestCase");
            

            //Get the url
            String url = driver.getCurrentUrl();

            //Get the title
            String title = driver.getTitle();
            
            

            //print url
            System.out.println("URL of website after clicking on 'Try it' " + url);
            System.out.println("");

            //print title
            System.out.println("Title of website after clicking on 'Try it' " + title);
            System.out.println(""); 

            //close the new window
            for (String string : windows) {
                   if(!string.equals(parentWindow)){
                    driver.close();
                   }
            }
            
            //switch to first window
            driver.switchTo().window(parentWindow);
            Thread.sleep(3000);

            //take screenshot
            takeScreenShot(driver, "ParentWindow", "TestCase");

            System.out.println("End Test case: testCase01");

        } catch (Exception e) {
           e.printStackTrace();
        }
        
    }


}
