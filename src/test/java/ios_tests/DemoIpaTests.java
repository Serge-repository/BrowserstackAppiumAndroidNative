package ios_tests;

import GeneralSetup.TestBasisMobile;
import io.appium.java_client.MobileElement;
import org.testng.annotations.Test;

public class DemoIpaTests extends TestBasisMobile {

    @Test
    public void firstTest(){
        MobileElement textButton = appiumDriver.findElementByAccessibilityId("Text Button");
        textButton.click();
    }

}