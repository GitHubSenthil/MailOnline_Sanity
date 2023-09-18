package stepDefinition;

import commonUtil.commonUtil;
import commonUtil.waitUtil;
import driver.driverUtil;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;


public class mobileSteps {

    protected AndroidDriver driver = (AndroidDriver) driverUtil.getDriver();
    JavascriptExecutor js = (JavascriptExecutor) driver;

    @Given ( "Launch the app and go to Newspaper tab" )
    public void LaunchApp( ) {

        //Clicking the Notification Button
        if (waitUtil.waitForElement(driver.findElements ( By.id("com.android.permissioncontroller:id/permission_allow_button") ), 20))
            driver.findElement ( By.id("com.android.permissioncontroller:id/permission_allow_button") ).click ();

        //android.widget.TextView[@text='Failover']/../android.view.ViewGroup
        if (waitUtil.waitForElement(driver.findElements ( By.xpath("//android.widget.TextView[@text='Failover']/../android.view.ViewGroup") ), 20))
            driver.findElement ( By.xpath("//android.widget.TextView[@text='Failover']/../android.view.ViewGroup") ).click ();

        //Handle to Click the Welcome page - continue button
        String sStep = driver.findElement ( By.xpath("//android.widget.TextView[contains(@text,'Step')]") ).getAttribute ( "text" );
        sStep = sStep.substring ( sStep.length ()-1,sStep.length () );


        for (int i = 0; i < Integer.parseInt ( sStep ); i++) {
            System.out.println ( driver.findElement ( By.xpath("//android.widget.TextView[contains(@text,'Step')]") ).getAttribute ( "text" ));
            driver.findElement ( By.xpath("//android.widget.TextView[@text='Continue']") ).click ();
        }

        if (waitUtil.waitForElement(driver.findElements ( By.xpath ( "//android.widget.Button[@content-desc=\"Newspaper\"]" ) ), 20)){
            driver.findElement ( By.xpath ( "//android.widget.Button[@content-desc=\"Newspaper\"]" ) ).click ();
            Assert.assertTrue ("Verify NewsPaper Tab", true);
        }
        else
            Assert.assertTrue ("Verify NewsPaper Tab", false);


        //android.widget.TextView[@text='Recent issues']/following-sibling::android.widget.HorizontalScrollView[1]
    }

    @When  ("Newspaper tab scroll recent issues and scroll right tap see more")
    public void scrollToSeeMore() {
        String menuText = "Recent issues";
        //if (waitUtil.waitForElement(driver.findElements ( By.xpath ( "//android.widget.TextView[@text='Recent issues']/following-sibling::android.widget.HorizontalScrollView[1]" ) ), 20)){
            //js.executeScript("arguments[0].scrollIntoView(true);",driver.findElements ( By.xpath ( "//android.widget.TextView[@text='Recent issues']/following-sibling::android.widget.HorizontalScrollView[1]" )) );
//            WebElement el = driver.findElement(AppiumBy.ByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"YOUR_RECYCLERVIEW_ID\")).setAsVerticalList().scrollIntoView("
//                            + "new UiSelector().text(\""+menuText+"\"));"));

//            WebElement el1 = driver.findElement ( AppiumBy.androidUIAutomator ( "new UiScrollable(new UiSelector().resourceId(\"android.widget.ScrollView\")).setAsVerticalList().scrollIntoView("
//                            + "new UiSelector().text(\""+menuText+"\"));"));

        int iSwipleFlag = 0;
        do {
            commonUtil.scrollDown ( driver );
            if (waitUtil.waitForElement(driver.findElements ( By.xpath ( "//android.widget.TextView[@text='Recent issues']/following-sibling::android.widget.HorizontalScrollView[1]" ) ), 1))
                break;
            iSwipleFlag = iSwipleFlag + 1;
        } while (iSwipleFlag < 10);

        WebElement ele01 = driver.findElement( AppiumBy.xpath("//android.widget.TextView[@text='Recent issues']/following-sibling::android.widget.HorizontalScrollView[1]"));

        int centerY = ele01.getRect().y + (ele01.getSize().height/2);

        double startX = ele01.getRect().x + (ele01.getSize().width * 0.9);

        double endX = ele01.getRect().x + (ele01.getSize().width * 0.1);

        iSwipleFlag = 0;
        do {
            commonUtil.scrollHorizontal(driver, centerY, startX, endX);
            if (waitUtil.waitForElement(driver.findElements ( By.xpath ( "//android.widget.TextView[@text='Recent issues']/following-sibling::android.widget.HorizontalScrollView[1]//android.widget.TextView[@text='SEE MORE']" ) ), 1)) {
                driver.findElement ( By.xpath ( "//android.widget.TextView[@text='Recent issues']/following-sibling::android.widget.HorizontalScrollView[1]//android.widget.TextView[@text='SEE MORE']" ) ).click ();
                break;
            }
            iSwipleFlag = iSwipleFlag + 1;
        } while (iSwipleFlag < 10);

        if (iSwipleFlag >= 10) {
            Assert.assertTrue ("See more Recent Issues", false);
        }

    }

    @Then ("Tap to download 27 June edition")
    public void searchAndDownloadEdition() throws InterruptedException {

        WebElement ele01 = driver.findElement(AppiumBy.className ( "android.widget.ScrollView"));

        int centerX = ele01.getRect().x + (ele01.getSize().width/2);

        double startY = ele01.getRect().y + (ele01.getSize().height * 0.9);

        double endY = ele01.getRect().y + (ele01.getSize().height * 0.1);

        int iSwipleFlag = 0;
        do {
            commonUtil.verticalScroll ( driver, centerX,startY, endY);
            if (waitUtil.waitForElement(driver.findElements ( By.xpath ( "(//android.widget.TextView[contains(@text, '27 June')])[2]" ) ), 1)) {
                driver.findElement ( By.xpath ( "(//android.widget.TextView[contains(@text, '27 June')])[2]" ) ).click ();
                break;
            }
            iSwipleFlag = iSwipleFlag + 1;
        } while (iSwipleFlag < 10);

        if (iSwipleFlag >= 10) {
            Assert.assertTrue ("Not able to find 27th Edition", false);
        }

        if (waitUtil.waitForElement(driver.findElements ( By.xpath ( "//android.widget.TextView[@text='Sign in']" ) ), 20)) {
            driver.findElement ( By.xpath ( "//android.widget.TextView[@text='Sign in']" ) ).click ();
        }
        else
            Assert.assertTrue ("Sign - Click failed", false);


        //driver = commonUtil.switchToWebContext(driver);
//        Thread.sleep ( 2000 );
//        driver.context("WEBVIEW_com.anmedia.dailymail.kindlefire.uat");
//        Thread.sleep ( 2000 );
//
//        if (waitUtil.waitForElement(driver.findElements ( By.id ( "login.email" ) ), 5)) {
//
//            driver.findElement ( By.id ( "login.email" ) ).clear ();
//            driver.findElement ( By.id ( "login.email" ) ).sendKeys ( "twilightsp2708@gmail.comtwilightsp2708@gmail.com" );
//
//            driver.findElement ( By.id ( "login.email" ) ).sendKeys ( "National123!" );
//
//            driver.findElement ( By.className ( "android.widget.Button" ) ).click ();
//
//        }
//        else
//            Assert.assertTrue ("Login Page - failed", false);



        //(//android.widget.TextView[contains(@text, '27 June')])[2]
        //android.widget.TextView[@text='Sign in']
        //login.email
        //
        //login.password
        //
        //android.widget.Button

    }



}
