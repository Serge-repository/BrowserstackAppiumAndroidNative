package GeneralSetup;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.exceptions.UnirestException;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.json.JSONObject;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utils.HttpHelper;
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
    private String deviceNumber;
    private Map<String, Map<String, String>> deviceMaps;

    public HomeView homeView;
    public ViewsView viewsView;
    public TextFieldsView textFieldsView;

    public static String appPackage = "io.appium.android.apis";
    public static String appPath;

    ///////////// uncomment for local single device run //////////////////////
    @BeforeClass(alwaysRun = true)
    public void beforeClassSingleDeviceRun() throws IOException, UnirestException {

    ///////////// uncomment for parallel device run via xml suite //////////////////////
//    @Parameters({"device"})
//    @BeforeClass(alwaysRun = true)
//    public void beforeClassSingleDeviceRun(String device) throws IOException {

        String userName = PropertiesLoader.decodeString(true);
        String accessKey = PropertiesLoader.decodeString(false);

        // выбор .apk или .ipa файла (можно реализовать через switch)
        JSONObject appUrl = new JSONObject(HttpHelper.uploadApp("/C:/Java/Projects/AppiumProjects/BrowserstackAppiumAndroidNative/src/main/resources/ApiDemos-debug.apk"));

        ///////////// uncomment for parallel device run via xml suite ///////////////////
//        deviceNumber = System.getProperty("device", device);

        ///////////// uncomment for local single device run //////////////////////
        deviceNumber = System.getProperty("device", "1");

        initDeviceMaps();
        deviceSettings = deviceMaps.get(deviceNumber);

        capabilities.setCapability("device", deviceSettings.get("device"));
        capabilities.setCapability("os_version", deviceSettings.get("os_version"));
        capabilities.setCapability("project", "Browserstack Project");
        capabilities.setCapability("build", "First build");
        capabilities.setCapability("name", "Bstack-[Java] Sample Test");
        capabilities.setCapability("app", appUrl.getString("app_url"));
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

    private void initDeviceMaps() throws IOException {
        InputStream stream = TestBasisMobile.class.getResourceAsStream("/Devices.json");
        if (stream == null) {
            throw new RuntimeException("Could not find Devices.json");
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        deviceMaps = new ObjectMapper().enable(JsonParser.Feature.ALLOW_COMMENTS).readValue(reader, HashMap.class);
        stream.close();
    }
}
