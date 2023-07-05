import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class LoginPage {
    private final SelenideElement usernameField = $("input#Username");
    private final SelenideElement passwordField = $("input#Password");
    private final SelenideElement captcha = $("iframe[title=\"reCAPTCHA\"]");
    private final SelenideElement loginButton = $("button#recaptcha");

    /**
     * Авторизация в БО
     *
     * @param login    логин пользователя
     * @param password пароль пользователя
     */
    public void signIn(String login, String password) {
        usernameField.shouldBe(visible).setValue(login);
        passwordField.shouldBe(visible).setValue(password);

        captcha.shouldBe(visible).click();

        loginButton.shouldBe(enabled).click();
    }
}
