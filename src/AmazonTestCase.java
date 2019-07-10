import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

public class AmazonTestCase {

public static void main(String[] args) throws Exception{
	//AndroidDriver driver = null;
	AppiumDriver<MobileElement> driver = null;
	AppiumDriverLocalService service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
										.usingDriverExecutable(new File("C:/Program Files/nodejs/node.exe"))
										.withAppiumJS(new File("C:/Program Files (x86)/Appium/node_modules/appium/bin/appium.js"))
										.withLogFile(new File("C:/text.txt")));
	service.start();
	
	File app = new File("C:\\Users\\ssi151\\workspace\\Appium\\APK\\amazon.apk");
	DesiredCapabilities capabilities = new DesiredCapabilities();
	capabilities.setCapability("app", app.getAbsolutePath());
	capabilities.setCapability("deviceName", "ANDROID");
	capabilities.setCapability("automationName", "Appium");
	capabilities.setCapability("52003d2e3f901100" , MobileCapabilityType.UDID);
	
	capabilities.setCapability("app-package", "com.amazon.mShop.android");
	capabilities.setCapability("app-activity", "com.amazon.mShop.splashscreen.StartupActivity");
	
	try {
		 driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub") ,capabilities);
	}
	catch (MalformedURLException e) {
		e.printStackTrace();
	}
	driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
//Application is installed.
	
	driver.findElement(By.id("com.amazon.mShop.android:id/search_edit_text")).click();
	driver.findElement(By.id("com.amazon.mShop.android:id/rs_search_src_text")).click();
	driver.findElement(By.id("com.amazon.mShop.android:id/rs_search_src_text")).clear();

//Searched for Device.
	driver.findElement(By.id("com.amazon.mShop.android:id/rs_search_src_text")).sendKeys("iphone 6");

//Selected top device from the suggestion.
	List<MobileElement> searchResult = driver.findElements(By.id("com.amazon.mShop.android:id/rs_search_dropdown_item_suggestions"));
	searchResult.get(0).click();	  

//Selected the device from the search result page.	
	List<MobileElement> searchList = driver.findElements(By.id("com.amazon.mShop.android:id/list_product_linear_layout"));
	searchList.get(0).click();
	Thread.sleep(5000);
	driver.findElement(By.xpath(".//android.widget.Button[@index='6']")).tap(1, 10);
	driver.findElement(By.xpath(".//android.widget.Button[@index='6']")).click();

//Changing the capacity of device.
	driver.findElement(By.xpath("//android.view.View[@index='2']")).tap(1, 10);
	Thread.sleep(5000);

//Tap on Buy now button.	
	driver.findElement(By.xpath("//android.widget.Image[@index='22']")).tap(1, 10);
	
//Validating Invalid credential Scenario.
	driver.findElement(By.xpath("//android.widget.EditText[@index='0']")).tap(1, 10);
	driver.findElement(By.xpath("//android.widget.EditText[@index='0']")).clear();
	driver.findElement(By.xpath("//android.widget.EditText[@index='0']")).sendKeys("sahil@gmail.com");
	
	driver.findElement(By.xpath("//android.widget.EditText[@index='1']")).tap(1, 10);
	driver.findElement(By.xpath("//android.widget.EditText[@index='1']")).clear();
	driver.findElement(By.xpath("//android.widget.EditText[@index='1']")).sendKeys("password");

	driver.findElement(By.xpath("//android.widget.Button[@index='7']")).tap(1, 10);
	
	boolean errorMessage = driver.findElement(By.xpath("//android.widget.ListView[@index='1']")).isDisplayed();
	System.out.println(errorMessage);
	try {
		Assert.assertTrue(errorMessage);
		System.out.println("Invalid Credentials");
	}
	catch(Exception e) {
		System.out.println(e);
	}
}
}
