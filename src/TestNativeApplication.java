

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;



public class TestNativeApplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new TestNativeApplication().testNativeAppWithInstallation();
	}
	
	public void testNativeAppWithInstallation()
	{
		AppiumDriver driver = null;
		AppiumDriverLocalService service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
										.usingDriverExecutable(new File("C:/Program Files/nodejs/node.exe"))
										.withAppiumJS(new File("C:/Program Files (x86)/Appium/node_modules/appium/bin/appium.js"))
										.withLogFile(new File("C:/text.txt")));
	
	service.start();
		System.out.println(service.getUrl());
		File app = new File("D:\\WorkingDirectory\\Automation_CoE\\App\\android\\lufthansa_latest.apk"); 	
		
		
		DesiredCapabilities capabilities = new DesiredCapabilities();			
		capabilities.setCapability("app", app.getAbsolutePath());		
		//capabilities.setCapability("avd", "TestLufthansa");
		capabilities.setCapability("deviceName", "");
		capabilities.setCapability("automationName", "Appium");
		capabilities.setCapability(MobileCapabilityType.UDID, "TA934007HG");
		
		capabilities.setCapability("app-package", "com.lufthansa.android.lufthansa");
		capabilities.setCapability("app-activity", "com.lufthansa.android.lufthansa.ui.activity.HomeActivity");
	  
		try {			
			 driver =new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);			
		//	driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		AndroidDriver android = ((AndroidDriver)driver);
		
		android.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
		android.findElementByXPath("//*[@text='Continue as guest']").click();
		
		android.findElementById("com.lufthansa.android.lufthansa:id/ac_home_drawertoggleview").click();
		MobileElement element = (MobileElement) driver.scrollTo("More");
		
	}
	
	public void testNativeAlreadyInstalled()
	{
		//WebDriver driver = null;
		AppiumDriverLocalService service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
											.usingDriverExecutable(new File("C:/Program Files (x86)/nodejs/node.exe"))
											.withAppiumJS(new File("C:/Appium/node_modules/appium/bin/appium.js"))
											.withLogFile(new File("C:/text.txt")));
		
		service.start();
	
		DesiredCapabilities capabilities = new DesiredCapabilities();	
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "");
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
		capabilities.setCapability(MobileCapabilityType.APP_PACKAGE, "com.android.settings");
		capabilities.setCapability(MobileCapabilityType.APP_ACTIVITY, "com.android.settings.Settings");
		capabilities.setCapability("udid", "TA934007HG");
		capabilities.setCapability(MobileCapabilityType.PLATFORM, "WINDOWS");
		capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 180);
		AppiumDriver driver;
		try {			
			
			driver =new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);	
		//	driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
			
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);		
		
			MobileElement element = (MobileElement) driver.scrollTo("About");
			driver.tap(1,element,500);

			
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
	}

}
