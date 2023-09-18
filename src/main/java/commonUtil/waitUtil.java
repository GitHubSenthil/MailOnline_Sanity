package commonUtil;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class waitUtil {

    //Method : Wait for the Element to Exist
    //Param1 : WebElement List item - FindElements
    //Param2 : Time in seconds - Wait to find the element
    public static boolean waitForElement(List<WebElement> waitElement, int waitTime) {

        int waitFlag = 0;
        try {
            do {

                if (waitElement.size () > 0){
                    return true;
                }
                waitFlag = waitFlag + 1;
                Thread.sleep ( 1000 );

            } while(waitFlag <= waitTime);
            return false;
        }
        catch (Exception e){
            return false;
        }

    }

    //Method : Wait for the Element to Exist
    //Param1 : WebElement List item - FindElements
    //Param2 : Time in seconds - Wait to find the element
    public static boolean waitUntilElementExist(WebDriver driver, String waitElement, int waitTime) {

        int waitFlag = 0;
        try {
            do {
                Thread.sleep ( 1000 );
                waitFlag = waitFlag + 1;
                if (waitFlag >= waitTime){
                    return false;
                }
            } while (driver.findElements ( By.xpath ( waitElement ) ).size () >= 1);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

}
