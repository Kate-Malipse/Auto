import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
public class BackofficePage {
    private final SelenideElement loginButton = $$("button > span").findBy(text("LOG IN"));
    public void open() {
        Selenide.open("https://backoffice.tvbet.bet/");
        loginButton.shouldBe(visible);
    }

    public void login() {
        loginButton.click();
    }
}
