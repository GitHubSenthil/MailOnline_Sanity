package driver;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class driverUtil {
    public static long DEFAULT_WAIT = 20;
    protected static WebDriver driver = null;
    static String systemPath = System.getProperty ( "user.dir" );
    static Properties property = new Properties ();
    static DesiredCapabilities cap = null;

    public static WebDriver getDriver( ) {

        if (driver != null) {
           return driver;
         }
        InputStream input = null;
        try {
            property.load (new FileInputStream (systemPath+"/src/main/resources/Config.properties"));
        } catch (IOException e) {
            e.printStackTrace ();
        }
        String sEnvironment = property.getProperty("Environment");
        switch (sEnvironment) {
            case "Windows_Local":
//                //capabilities.setJavascriptEnabled(true);
//                //capabilities.setCapability("takesScreenshot", true);
//                driver = new ChromeDriver ();
//                //driver.manage ().timeouts ().setScriptTimeout ( DEFAULT_WAIT, TimeUnit.SECONDS );
//                driver.manage ().window ().maximize ();

                Map<String, Object> prefs = new HashMap<String, Object>();
                prefs.put("profile.default_content_setting_values.notifications", 2);
                ChromeOptions options = new ChromeOptions ();
                options.addArguments ( "start-maximized" );
                options.setExperimentalOption("prefs", prefs);
                driver = new ChromeDriver ( options );
                //driver.manage ().timeouts ().implicitlyWait ( Duration.ofSeconds ( 30 ) );
                break;

            case "Android_Local":
                String port = "4723";

                try {
                    input = new FileInputStream (systemPath+"/src/main/resources/deviceConfig/"+sEnvironment+".properties");
                    cap = getCapability(input);
                    //driver = (AndroidDriver) new AndroidDriver(new URL ("http://127.0.0.1:"+port+"/wd/hub"),cap);
                    driver = (AndroidDriver) new AndroidDriver(new URL ("http://127.0.0.1:"+port),cap);
                    driver.manage ().timeouts ().implicitlyWait ( Duration.ofSeconds ( 0 ) );
                } catch (FileNotFoundException e) {
                    e.printStackTrace ();
                }
                catch (MalformedURLException e) {
                    e.printStackTrace ();
                    System.exit ( 0 );
                }
                break;

            case "BrowserStack_Android": case "BrowserStack_Web":
                URL remoteDriverURL = null;
                try {
                    input = new FileInputStream ( systemPath + "/src/main/resources/deviceConfig/" + sEnvironment + ".properties" );
                    Properties prop = new Properties ();
                    prop.load ( input );

                    String url = property.getProperty("protocol")+
                            "://"+
                            property.getProperty("username")+
                            ":"+
                            property.getProperty("access_key")+
                            property.getProperty("url");

                    remoteDriverURL = new URL(url);

                }
                catch (Exception e) {
                    e.printStackTrace ();
                    System.exit ( 0 );
                }
                driver = new RemoteWebDriver (remoteDriverURL, getCapability ( input ));
                break;


            default:
                System.out.println ( "Please provide valid platform Name: " + sEnvironment );
                System.exit ( 0 );
        }
        return driver;
    }

    public static DesiredCapabilities getCapability(InputStream input) {
        DesiredCapabilities capability = new DesiredCapabilities();
        Properties prop = new Properties ();
        try {
            prop.load(input);
            if(prop.containsKey("app")) {
                String appName = prop.getProperty("app");
                String appPath = systemPath+"/src/main/resources/appUnderTest/"+appName;
                prop.setProperty("app", appPath);
            }

            // set capabilities
            Enumeration<Object> enuKeys = prop.keys();
            while (enuKeys.hasMoreElements()) {
                String key = (String) enuKeys.nextElement();
                String value = prop.getProperty(key);
                capability.setCapability(key, value);
            }
            input.close();
        }catch(Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        return capability;
    }
}
