package steps;

import Base.baseUtil;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;


public class hooks extends baseUtil {


    @Before
    public void initializeTest() throws MalformedURLException {

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        /*chromeOptions.setCapability("browserVersion", "116.0");
        chromeOptions.setCapability("platformName", "Windows 10");*/
        chromeOptions.setCapability("browserName", "chrome"); // Specify browser name, not browserVersion
        chromeOptions.setCapability("browserVersion", "116.0"); // Specify browser version here if needed
//        chromeOptions.setCapability("platformName", "Linux"); // Specify the platform (e.g., Linux)

        chromeOptions.setCapability("selenoid:options", new HashMap<String, Object>() {{
            /* How to add test badge */
            put("name", "Test badge...");

            /* How to set session timeout */
            put("sessionTimeout", "15m");

            /* How to set timezone */
            put("env", new ArrayList<String>() {{
                add("TZ=UTC");
            }});

            /* How to add "trash" button */
            put("labels", new HashMap<String, Object>() {{
                put("manual", "true");
            }});

            /* How to enable video recording */
            put("enableVideo", true);
        }});
        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub/"), chromeOptions);

    }

    @After
    public void teardownTest() {

        System.out.println("\nclosing the browser: Chrome");
        driver.quit();
    }

}