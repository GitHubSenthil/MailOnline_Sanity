package stepDefinition;



import commonUtil.waitUtil;
import driver.driverUtil;
import io.cucumber.java.After;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.cucumber.junit.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class webSteps {

    protected WebDriver driver = driverUtil.getDriver();
    JavascriptExecutor js = (JavascriptExecutor) driver;

    @Given ( "Navigate to Daily mail video page URL" )
    public void navigateToDailyMailVideoPageURL( ) throws InterruptedException {
        //Launching the browser
        driver.get ( "https://www.dailymail.co.uk/video/index.html" );
        Thread.sleep ( 3000 );
        //Accepting the Cookies
        if (waitUtil.waitForElement(driver.findElements ( By.xpath ("//button[text()='Got it']") ), 20)) {
            Assert.assertTrue ( driver.getTitle ().contains ( "Mail Online" ) );
            driver.findElement ( By.xpath ( "//button[text()='Got it']" ) ).click ();
        }

        if (waitUtil.waitForElement(driver.findElements ( By.xpath ("//div[@id='outerWrapper']/div[@id='closeButton']") ), 30))
            driver.findElement ( By.xpath ("//div[@id='outerWrapper']/div[@id='closeButton']")).click ();
    }

    @When ( "Click the video to begin playback" )
    public void clickTheVideoToBeginPlayback( ) {
        //Scrolling to the Video
        WebElement element = driver.findElement ( By.xpath ( "//div[@id='vjs_video_3']" ) );

        js.executeScript("arguments[0].scrollIntoView(true);", element);

        //Click the Play button
        if (waitUtil.waitForElement(driver.findElements ( By.xpath ("//div[@class='vjs-big-play-button']") ), 30))
            js.executeScript("arguments[0].click();", driver.findElement ( By.xpath ( "//div[@class='vjs-big-play-button']" ) ));
        else
            Assert.assertTrue ( false );

        //Wait Until the Advertisement to run
        waitUtil.waitForElement(driver.findElements ( By.xpath ("//div[@class='video-ad-label vjs-control']") ), 10);
        waitUtil.waitUntilElementExist(driver, "//div[@class='video-ad-label vjs-control']", 100);

        //Verify Video is playing
        if (waitUtil.waitForElement(driver.findElements ( By.xpath ("//div[@class='vjs-play-control vjs-control  vjs-playing']") ), 10))
            Assert.assertTrue ( true );
        else
            Assert.assertTrue ( false );

        //video-ad-label vjs-control

    }

    @Then ("^Click Video again for pause playback$" )
    public void clickVideoAgainForPausePlayback( ) {
        //Clicking the Pause button
        driver.findElement ( By.xpath ("//div[@class='vjs-control-bar']//div[@class='vjs-play-control vjs-control  vjs-playing']")).click ();

        //Verify Video is paused
        if (waitUtil.waitForElement(driver.findElements ( By.xpath ("//div[@class='vjs-control-bar']//div[@class='vjs-play-control vjs-control  vjs-paused']") ), 10))
            Assert.assertTrue ( true );
        else
            Assert.assertTrue ( false );
    }

    @And ("Click Forward button to change the video")
    public void clickVideoForwardButton() {
        String sVideoName, sNextVideo;

        //Get the Video Name
        sVideoName = driver.findElement ( By.xpath ("//div[@class='vjs-title-text']/div")).getText ();
        System.out.println ( sVideoName );


        //Verify the Previous/Forward button and click
        if (waitUtil.waitForElement(driver.findElements ( By.xpath ("//div[@class='mol-skip-control vjs-control']") ), 10))
            driver.findElement(By.xpath ("//div[@class='mol-skip-control vjs-control']")).click ();
        else
            Assert.assertTrue ( false );


        //Wait Until the Advertisement to run
        waitUtil.waitForElement(driver.findElements ( By.xpath ("//div[@class='video-ad-label vjs-control']") ), 10);
        waitUtil.waitUntilElementExist(driver, "//div[@class='video-ad-label vjs-control']", 100);

        sNextVideo = driver.findElement ( By.xpath ("//div[@class='vjs-title-text']/div")).getText ();
        System.out.println ( sNextVideo );
        if (!(sVideoName.equalsIgnoreCase ( sNextVideo )))
            Assert.assertTrue ( "Verify previous/forward video played",true );
        else
            Assert.assertTrue ( "Verify previous/forward video played",false );

    }

    @And ("Click Back button to change the video")
    public void clickVideoBackButton() throws InterruptedException {
        String sVideoName, sNextVideo;

        //Get the Video Name
        sVideoName = driver.findElement ( By.xpath ("//div[@class='vjs-title-text']/div")).getText ();
        System.out.println ( sVideoName );


        //Verify the Previous/Forward button and click
        if (waitUtil.waitForElement(driver.findElements ( By.xpath ("//div[@class='mol-previous-control vjs-control']") ), 10)) {
            js.executeScript("arguments[0].click();", driver.findElement ( By.xpath ( "//div[@class='mol-previous-control vjs-control']" ) ));
            Thread.sleep ( 1000 );
            js.executeScript("arguments[0].click();", driver.findElement ( By.xpath ( "//div[@class='mol-previous-control vjs-control']" ) ));
        }
        else
            Assert.assertTrue ( false );


        //Wait Until the Advertisement to run
        waitUtil.waitForElement(driver.findElements ( By.xpath ("//div[@class='video-ad-label vjs-control']") ), 10);
        waitUtil.waitUntilElementExist(driver, "//div[@class='video-ad-label vjs-control']", 100);

        sNextVideo = driver.findElement ( By.xpath ("//div[@class='vjs-title-text']/div")).getText ();
        System.out.println ( sNextVideo );
        if (!(sVideoName.equalsIgnoreCase ( sNextVideo )))
            Assert.assertTrue ( "Verify previous/forward video played",true );
        else
            Assert.assertTrue ( "Verify previous/forward video played",false );

    }

    @Then ("Click Speaker icon to mute the video")
    public void clickSpeakerIconToMute() {


        if (waitUtil.waitForElement(driver.findElements ( By.xpath ("//div[contains(@class,'vjs-volume-menu-button')]") ), 10)) {
            driver.findElement ( By.xpath ( "//div[contains(@class,'vjs-volume-menu-button')]" ) ).click ();
        }
        else
            Assert.assertTrue ( "Click Speaker Icon to Mute",false );


        if (waitUtil.waitForElement(driver.findElements ( By.xpath ("//div[contains(@class,'vjs-volume-menu-button') and contains(@class,'vjs-vol-0') ]") ), 10)) {
            Assert.assertTrue ( "Click Speaker Icon to Mute",true );
        }
        else
            Assert.assertTrue ( "Click Speaker Icon to Mute",false );
    }

    @And ("Click Speaker icon to unmute the video")
    public void clickSpeakerIconToUnMute() {
        //driver.findElement ( By.xpath ( "//div[contains(@class,'vjs-volume-menu-button') and contains(@class,'vjs-vol-0') ]" ) ).click ();
        js.executeScript("arguments[0].click();", driver.findElement ( By.xpath ( "//div[contains(@class,'vjs-volume-menu-button') and contains(@class,'vjs-vol-0') ]" ) ));

        if (waitUtil.waitForElement(driver.findElements ( By.xpath ("//div[contains(@class,'vjs-volume-menu-button')]") ), 10)) {
            Assert.assertTrue ( "Click Speaker Icon to UnMute",true );
        }
        else
            Assert.assertTrue ( "Click Speaker Icon to UnMute",false );
    }


    @Then ("Verify Autoplay is working when a video finished")
    public void VerifyAutoPlayVideo() {

        String sVideoName, sNextVideo;

        //Get the Video Name
        sVideoName = driver.findElement ( By.xpath ("//div[@class='vjs-title-text']/div")).getText ();
        System.out.println ( sVideoName );

        WebElement ele = driver.findElement ( By.xpath ("//div[@class='vjs-progress-holder vjs-slider']") );
        Actions action = new Actions(driver);
        action.dragAndDropBy(ele, 300, 0).perform();
        float fVal;
        do {
            String val = driver.findElement ( By.xpath ( "//div[@class='vjs-seek-handle vjs-slider-handle']" ) ).getAttribute ( "style" );
            val = val.replace ( "left: ", "" ).replace ( "%;", "" );
            System.out.println ( "val" + val);
            fVal = Float.valueOf ( val );
        }while (fVal >= 30);

        sNextVideo = driver.findElement ( By.xpath ("//div[@class='vjs-title-text']/div")).getText ();
        System.out.println ( sNextVideo );
        if (!(sVideoName.equalsIgnoreCase ( sNextVideo )))
            Assert.assertTrue ( "Verify previous/forward video played",true );
        else
            Assert.assertTrue ( "Verify previous/forward video played",false );
    }

    @Given ( "Navigate to Home Daily mail page URL" )
    public void navigateToHomeDailyMailVideoPageURL( ) throws InterruptedException {
        //Launching the browser
        driver.navigate().to ( "https://www.dailymail.co.uk/" );
        Thread.sleep ( 3000 );
        //Accepting the Cookies
        if (waitUtil.waitForElement(driver.findElements ( By.xpath ("//button[text()='Got it']") ), 5)) {
            Assert.assertTrue ( driver.getTitle ().contains ( "Mail Online" ) );
            driver.findElement ( By.xpath ( "//button[text()='Got it']" ) ).click ();
        }


        if (waitUtil.waitForElement(driver.findElements ( By.xpath ("//button[@class='close-1_Ex1']") ), 5))
            driver.findElement ( By.xpath ("//button[@class='close-1_Ex1']") ).click ();
    }

    @When("Click the Sports menu page")
    public void clickSportsMenu( ) {
        if (waitUtil.waitForElement(driver.findElements ( By.xpath ("//ul[@class='nav-primary cleared bdrgr3 cnr5']//a[text()='Sport']") ), 10))
            js.executeScript("arguments[0].click();", driver.findElement ( By.xpath ( "//ul[@class='nav-primary cleared bdrgr3 cnr5']//a[text()='Sport']" ) ));
        else
            Assert.assertTrue ( false );

        Assert.assertTrue ( driver.getTitle ().contains ( "Sports News" ) );
    }

    @Then("Scroll to Premier League table and Click View tables")
    public void scrollToTable( ) {

        if (waitUtil.waitForElement(driver.findElements ( By.xpath ("//div[contains(@class,'competitionTable')]") ), 10)) {
            WebElement element = driver.findElement ( By.xpath ( "//div[contains(@class,'competitionTable')]" ) );
            js.executeScript ( "arguments[0].scrollIntoView(true);", element );
        }
        else
            Assert.assertTrue ( false );


        driver.findElement ( By.xpath ( "//div[contains(@class,'competitionTable')]" ) ).click ();

        driver.switchTo ().defaultContent ();
        //driver.findElement ( By.xpath ( "//a[text()='Tables']" ) ).click ();
        js.executeScript("arguments[0].click();", driver.findElement ( By.xpath ( "//a[text()='Tables']" ) ));


        if (waitUtil.waitForElement(driver.findElements ( By.xpath ("//div[contains(@class,'competitionTable')]") ), 10)) {
            Assert.assertTrue ( "Table View Navigated",true );
        }
        else
            Assert.assertTrue ( "Table View Navigated",false );
    }

    @And("Verify the position of the team Man Utd")
    public void pickPos( ) {
        String pos;
        int iLoopFlag = 0;
        List<WebElement> eleRows = driver.findElements ( By.xpath ( "//div[contains(@class,'competitionTable')]/table/tbody/tr" ) );

        for (WebElement eleRow: eleRows) {

            List<WebElement> eleCells = eleRow.findElements ( By.xpath("td") );
            pos = eleCells.get(0).getText ();
            for (WebElement eleCell: eleCells){

                if (eleCell.getText ().equalsIgnoreCase ( "Man Utd" )){
                    System.out.println ( "Position of the Team: Man UTD"+  pos);
                    iLoopFlag = 1;
                    break;
                }

            }
            if (iLoopFlag == 1){
                break;
            }

        }

    }


    public void closedriver(){
        //driver.close ();
        //driver=null;
        //driver.get("D:\\MailOnline\\MailOnline_Sanity\\target\\cucumberHtmlReport.html");

    }
}
