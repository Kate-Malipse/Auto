import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.util.ArrayList;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class BackofficePage {
    private final SelenideElement loginButton = $$("button > span").findBy(text("LOG IN"));
    private final SelenideElement partnersSection = $("a[href=\"/partners\"");
    private final SelenideElement rulesAndHintsList = $$("aside > div > ul > li > div > span").findBy(text("Правила и подсказки"));
    private final SelenideElement howToPlaySection = $("a[href=\"/rules-and-tooltips/how-to-play\"]");
    protected final SelenideElement pageTitle = $("h3");
    protected final ElementsCollection tabsElements = $$("div.ant-tabs-nav-list > div.ant-tabs-tab > div");
    protected final ElementsCollection additionalTabsElements = $$("div.ant-tabs-dropdown > ul > li");
    private final SelenideElement tabButton = $("div.ant-tabs-nav-operations > button");

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
        pageTitle.shouldBe(visible);
    }

    protected void hasPageTitle(String title){
        pageTitle
                .shouldBe(visible)
                .shouldHave(exactText(title));
    }

    protected void hasTabElementWithName(String tabName) {
        var elem = tabsElements
                .shouldHave(sizeGreaterThan(0))
                .findBy(exactText(tabName));

        if (elem.exists()) {
            elem.shouldBe(visible);
            return;
        }

        tabButton.shouldBe(enabled).click();
        additionalTabsElements
                .shouldHave(sizeGreaterThan(0))
                .findBy(exactText(tabName))
                .shouldBe(visible);
    }
}
