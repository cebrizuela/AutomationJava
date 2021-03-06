package CourseAppium.myCode;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;

public class CreateDriverSession {

    public static AppiumDriver initializeDriver(String platformName) throws Exception {

        //Driver Session for Android
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
        caps.setCapability("newCommandTimeout", 300);

        // Es necesario crear la url por donde corre   el servidor de appium
        URL url = new URL("http://0.0.0.0:4723/wd/hub/");


        switch (platformName) {
            case "Android":
                caps.setCapability(MobileCapabilityType.DEVICE_NAME, "pixel_3");
                caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
                caps.setCapability(MobileCapabilityType.UDID, "emulator-5554");

                //Para iniciar el emulador automaticamente
                caps.setCapability("avd", "AndroidCurso");
                caps.setCapability("avdLauchTimeout", 118000);


                // Para instalar la App

                String appUrl = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator
                        + "resources" + File.separator + "ApiDemos-debug.apk";
                 caps.setCapability(MobileCapabilityType.APP, appUrl);


                 // Para abrir la app usamos la opcion appPackage de appActivity
                caps.setCapability("appPackage", "io.appium.android.apis");
                caps.setCapability("appActivity", "io.appium.android.apis.ApiDemos");
                return new AndroidDriver(url, caps);


            case "iOS":
                //Driver Session for iOS
                caps.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone_11");
                caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
                caps.setCapability(MobileCapabilityType.UDID, "nombre del dispositivo");

            /*
                String appUrl = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator
                +"resources" + File.separator + "ApiDemos-debug.apk";
                caps.setCapability(MobileCapabilityType.APP, appUrl);
                */

                return new IOSDriver(url, caps);

            default:
                throw new Exception("invalid platform");
        }
    }
}
