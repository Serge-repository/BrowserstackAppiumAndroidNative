package appium_tests;

import GeneralSetup.TestBasisMobile;
import org.testng.annotations.Test;

public class DemoApkTests extends TestBasisMobile {

    @Test
    public void firstTest(){
        homeView.differentSelectorsStrategies();
    }

    @Test
    public void secondTest(){
        homeView.selectorsUsingUiAutomator2();
    }

    @Test
    public void hybridAppByTest(){
        homeView.byExampleForHybridApp();
    }

    @Test
    public void scrollsAndTextInput(){
        homeView.selectViewsOption();
        viewsView.getTouchAction().scrollAction(448, 1752, 551, 389);
        viewsView.getTouchAction().scrollAction(448, 1752, 551, 389);
        viewsView.getTouchAction().scrollAction(448, 1752, 551, 389);
        viewsView.selectTextFieldsOption();
        textFieldsView.enterTextIntoHintField();
    }

    @Test
    public void scrollsTestUsingElements(){
        homeView.selectViewsOption();
        viewsView.scrollFromGalleryToAnimation();
    }

    @Test
    public void runAppInBackground(){
        homeView.selectViewsOption();
        viewsView.getActionsWithDeviceAndApp().runAppInBackground();
        viewsView.scrollFromGalleryToAnimation();
    }

    @Test
    public void workingWithAttributes(){
        homeView.validateAccessibilityElementByAttribute();
    }

    @Test
    public void clickOnMyOwnElement(){
        homeView.clickOnMyElement();
    }

    @Test
    public void exploringWaits(){
        homeView.getWaitUtils().implicitWait();
        homeView.clickOnMyElement();
    }

    @Test
    public void tapOnElement(){
        homeView.tapOnElement();
    }

    @Test
    public void tapOnElementByCoordinates(){
        homeView.tapOnElementByCoordinates();
    }

    @Test(groups = {"smoke"})
    public void tapOnElementByExactCoordinates(){
        homeView.tapOnElementByExactCoordinates(538, 416);
    }
}