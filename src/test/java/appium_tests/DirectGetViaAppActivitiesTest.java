package appium_tests;

import GeneralSetup.AppActivities;
import GeneralSetup.TestBasisMobile;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DirectGetViaAppActivitiesTest extends TestBasisMobile {
    @BeforeMethod
    public void beforeMethod(){
        ((AndroidDriver) appiumDriver).startActivity(new Activity(appPackage, AppActivities.TEXT_FIELDS_VIEW.getActivityPath()));
    }

    @Test
    public void lockDeviceDemo(){
        textFieldsView.getActionsWithDeviceAndApp().lockDevice(5);
        textFieldsView.enterTextIntoHintField();
        textFieldsView.getActionsWithDeviceAndApp().lockDeviceForever();
        textFieldsView.getActionsWithDeviceAndApp().unlockDevice();
    }

    @Test
    public void typeTextUsingAndroidKeyboard(){
        textFieldsView.enterKeyUsingKeyboard(AndroidKey.A, AndroidKey.B, AndroidKey.C);
        textFieldsView.hideKeyboard();
    }
}
