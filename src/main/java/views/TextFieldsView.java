package views;

import GeneralSetup.DataManagement;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TextFieldsView extends DataManagement {

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\")")
    private MobileElement hintTextBox;

    public TextFieldsView(AppiumDriver<MobileElement> appiumDriver, WebDriverWait wait) {
        super(appiumDriver, wait);
    }

    public void enterTextIntoHintField() {
        hintTextBox.click();
        hintTextBox.sendKeys("my text");
    }

    public void enterTextIntoHintFieldUsingKeyboard(AndroidKey androidKey) {
        hintTextBox.click();
        isKeyboardShown();
        enterKeyUsingKeyboard(androidKey);
    }
}
