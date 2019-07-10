

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

public class TestHybridApplication {

public static void main(String[] args) throws Exception{
		
	AppiumDriver driver = null;
	AppiumDriverLocalService service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
									.usingDriverExecutable(new File("C:/Program Files/nodejs/node.exe"))
									.withAppiumJS(new File("C:/Program Files (x86)/Appium/node_modules/appium/bin/appium.js"))
									.withLogFile(new File("C:/text.txt")));

service.start();
		System.out.println(service.getUrl());
		File app = new File("D:\\WorkingDirectory\\Automation_CoE\\App\\android\\HelloGappium-android.apk"); 	
		
		
		DesiredCapabilities capabilities = new DesiredCapabilities();			
		capabilities.setCapability("app", app.getAbsolutePath());		
		//capabilities.setCapability("avd", "TestLufthansa");
		capabilities.setCapability("deviceName", "");
		capabilities.setCapability("automationName", "Appium");
		capabilities.setCapability(MobileCapabilityType.UDID, "TA934007HG");
		
		capabilities.setCapability("app-package", "io.appium.gappium.sampleapp");
		capabilities.setCapability("app-activity", "HelloGappium");
	  
		try {			
			 driver =new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);			
		//	driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		AndroidDriver androidDriver = ((AndroidDriver)driver);
		
		androidDriver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

		Set<String> contexts = androidDriver.getContextHandles();
        // => ['NATIVE_APP', 'WEBVIEW_1', ...]
        // make sure we have something other than the native context
        System.out.println(contexts.size());
        for (String context : contexts) {
            if (!context.equals("NATIVE_APP")) {
                androidDriver.context(context);
                break;
            }
        }
		
		
		System.out.println(androidDriver.getPageSource());
		
		System.out.println("Done");
		
		WebElement search = androidDriver.findElement(By.cssSelector(".search-key"));
        search.sendKeys("j");
        List<WebElement> employees = androidDriver.findElements(By.cssSelector(".topcoat-list a"));
        System.out.println(employees.size() == 5);
        employees.get(3).click();
        List<WebElement> options = androidDriver.findElements(By.cssSelector(".actions a"));
        System.out.println(options.size()== 6);
        options.get(3).click();
        Thread.sleep(2000);
		
		
	}


}
