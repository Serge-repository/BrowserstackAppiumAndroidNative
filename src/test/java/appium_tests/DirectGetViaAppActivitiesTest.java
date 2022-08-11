package appium_tests;

import GeneralSetup.TestBasisMobile;
import io.appium.java_client.android.nativekey.AndroidKey;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DirectGetViaAppActivitiesTest extends TestBasisMobile {
    @BeforeMethod
    public void beforeMethod(){
        homeView.selectViewsOption();
        viewsView.getTouchAction().scrollAction(448, 1752, 551, 389);
        viewsView.getTouchAction().scrollAction(448, 1752, 551, 389);
        viewsView.getTouchAction().scrollAction(448, 1752, 551, 389);
        viewsView.selectTextFieldsOption();
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
