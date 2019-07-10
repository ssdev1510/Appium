import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;


public class TestWebApplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		AppiumDriver driver;
			AppiumDriverLocalService service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
											.usingDriverExecutable(new File("C:/Program Files/nodejs/node.exe"))
											.withAppiumJS(new File("C:/Program Files (x86)/Appium/node_modules/appium/bin/appium.js"))
											.withLogFile(new File("C:/text.txt")));
		
		service.start();

		DesiredCapabilities capabilities = new DesiredCapabilities();			
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "");
		//The kind of mobile device or emulator to use
		//iPhone Simulator, iPad Simulator, iPhone Retina 4-inch, Android Emulator, Galaxy S4, etc…. 
		//On iOS, this should be one of the valid devices returned by instruments with instruments -s devices. 
		//On Android this capability is currently ignored. 
		//But we have to value to this variable (dummy value or may be "")
		
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
		//Determines which automation engine to use
		//Appium is the default value. Otherwise Selendroid can be given)
		
		capabilities.setCapability(MobileCapabilityType.UDID, "52003d2e3f901100");
		//Unique device identifier of the connected physical device.	
		
		capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
		//Name of mobile web browser to automate. Should be an empty string if automating an app instead.
		//‘Safari’ for iOS and ‘Chrome’, ‘Chromium’, or ‘Browser’ for Android
		
		capabilities.setCapability(MobileCapabilityType.PLATFORM, "WINDOWS");
		//Which mobile OS platform to use  E.g., iOS, Android, or FirefoxOS		
		
		capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 180);		
		try {			
			
			 driver =new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);	
			//OR
			//driver = (AppiumDriver) new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			
			driver.get("http://www.rediffmail.com");
			driver.findElement(By.xpath("//a[.='Rediffmail']")).click();
			driver.findElement(By.name("login")).sendKeys("Test");;
			driver.findElement(By.name("passwd")).sendKeys("Test");			
			driver.findElement(By.xpath("//input[contains(@value,'Sign in')]")).click();	
			
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

	}

}
