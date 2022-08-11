package utils;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.ElementOption;

import static GeneralSetup.TestBasisMobile.appiumDriver;

public class Actions {
    private final TouchAction touchAction = new TouchAction(appiumDriver);

    public void tap(MobileBy mobileBy) {
        touchAction.tap(ElementOption.element(appiumDriver.findElement(mobileBy)));
    }
}
