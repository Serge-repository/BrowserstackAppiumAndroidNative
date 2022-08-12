package GeneralSetup;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import utils.PropertiesLoader;
import views.HomeView;
import views.TextFieldsView;
import views.ViewsView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class TestBasisMobile {
    public static AppiumDriver<MobileElement> appiumDriver;
    public static WebDriverWait wait;

    private DesiredCapabilities capabilities = new DesiredCapabilities();
    private Map<String, String> deviceSettings;
    private URL serverAddress;

    public HomeView homeView;
    public ViewsView viewsView;
    public TextFieldsView textFieldsView;

    public static String appPackage = "io.appium.android.apis";
    public static String appPath;

    ///////////// uncomment for local single device run //////////////////////
//    @BeforeClass(alwaysRun = true)
//    public void beforeClassSingleDeviceRun() throws IOException {

    ///////////// uncomment for parallel device run via xml suite //////////////////////
    @Parameters({"device"})
    @BeforeClass(alwaysRun = true)
    public void beforeClassSingleDeviceRun(String device) throws IOException {

        String userName = PropertiesLoader.getCredentials("src/main/resources/credentials.properties", "userName");
        String accessKey = PropertiesLoader.getCredentials("src/main/resources/credentials.properties", "accessKey");

        // For Browserstack + Jenkins integration
//        String userName = System.getenv("BROWSERSTACK_USERNAME");
//        String accessKey = System.getenv("BROWSERSTACK_ACCESS_KEY");
//        String browserstackLocal = System.getenv("BROWSERSTACK_LOCAL");
//        String buildName = System.getenv("BROWSERSTACK_BUILD_NAME");
//        String browserstackLocalIdentifier = System.getenv("BROWSERSTACK_LOCAL_IDENTIFIER");
//        String app = System.getenv("BROWSERSTACK_APP_ID");

        ///////////// uncomment for parallel device run via xml suite ///////////////////
        String deviceNumber = System.getProperty("device", device);

        ///////////// uncomment for local single device run //////////////////////
//        String deviceNumber = System.getProperty("device", "1");

        InputStream stream = TestBasisMobile.class.getResourceAsStream("/Devices.json");
        if (stream == null) {
            throw new RuntimeException("Could not find Devices.json");
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        Map<String, Map<String, String>> deviceMaps = new ObjectMapper().enable(JsonParser.Feature.ALLOW_COMMENTS).readValue(reader, HashMap.class);
        deviceSettings = deviceMaps.get(deviceNumber);

        capabilities.setCapability("device", deviceSettings.get("device"));
        capabilities.setCapability("os_version", deviceSettings.get("os_version"));
        capabilities.setCapability("project", "Browserstack Project");
        capabilities.setCapability("build", "First Build");
        capabilities.setCapability("name", "Bstack-[Java] Sample Test");
        capabilities.setCapability("app", deviceSettings.get("app_url"));
        capabilities.setCapability("newCommandTimeout", 300);
        serverAddress = new URL("https://" + userName + ":" + accessKey + "@hub-cloud.browserstack.com/wd/hub");

        initializeDriver();
    }

    @AfterClass(alwaysRun = true)
    public void afterClassSingleDeviceRun() {
        appiumDriver.quit();
    }

    private void initializeDriver() {
        appiumDriver = new AndroidDriver<>(serverAddress, capabilities);
        wait = new WebDriverWait(appiumDriver, 10);
        initializeClasses();
    }

    private void initializeClasses() {
        homeView = new HomeView(appiumDriver, wait);
        viewsView = new ViewsView(appiumDriver, wait);
        textFieldsView = new TextFieldsView(appiumDriver, wait);
    }
}
