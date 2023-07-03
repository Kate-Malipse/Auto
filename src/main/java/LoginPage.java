import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class LoginPage {
    private final SelenideElement usernameField = $("input#Username");
    private final SelenideElement passwordField = $("#Password");
    private final SelenideElement captchaCheckbox = $("span#recaptcha-anchor > div");
    private final SelenideElement loginButton = $("button#recaptcha");
    public void signIn(String login, String password){
        usernameField.shouldBe(visible);
        passwordField.shouldBe(visible);

        usernameField.setValue(login);
        passwordField.setValue(password);

        captchaCheckbox.click();

        loginButton.shouldBe(enabled);
        loginButton.click();
    }
}
