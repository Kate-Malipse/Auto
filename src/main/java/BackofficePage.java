import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class BackofficePage {
    private final SelenideElement loginButton = $$("button > span").findBy(text("LOG IN"));
    private final SelenideElement partnersSection = $("a[href=\"/partners\"");
    private final SelenideElement rulesAndHintsList = $$("aside > div > ul > li > div > span").findBy(text("Правила и подсказки"));
    private final SelenideElement howToPlaySection = $("a[href=\"/rules-and-tooltips/how-to-play\"]");

    /**
     * Переход на страницу бэкофиса
     */
    public void open() {
        Selenide.open("https://backoffice.tvbet.bet/");
    }

    /**
     * Нажатие на кнопку Login при первом открытии страницы
     */
    public void login() {
        loginButton.shouldBe(visible).click();
    }

    public void openPartnersSection() {
        partnersSection.shouldBe(visible).click();
    }

    /**
     * Открытие раздела Как играть
     */
    public void openHowToPlaySection() {
        rulesAndHintsList.shouldBe(visible).click();
        howToPlaySection.shouldBe(visible).click();
    }
}
