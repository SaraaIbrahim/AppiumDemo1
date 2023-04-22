package Demos;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class ChromeTestCase {

    AndroidDriver driver;
    @BeforeTest
    public void setUp() throws MalformedURLException {
        DesiredCapabilities caps= new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.BROWSER_NAME,"Chrome");
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME,"Android Emulator");
        caps.setCapability("unicodeKeyboard", "true");
        caps.setCapability("resetKeyboard", "true");
        caps.setCapability("chromedriverExecutable",System.getProperty("user.dir")+"/src/main/resources/chromedriver.exe");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),caps);
    }


    @Test
    public void SearchForAppium() {
        driver.get("https://www.google.com");
        WebElement ele = driver.findElement(By.name("q"));
        ele.sendKeys("appium");

        // Submit button
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        String ele2=driver.getCurrentUrl();
       System.out.println("URL "+ele2);
        Assert.assertTrue(ele2.contains("https://www.google.com/"));

    }

    @AfterTest
    public void tearDown()
    {
        driver.quit();
    }
}
