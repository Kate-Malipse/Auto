import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.util.ResourceBundle;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class BackofficePage {
    ResourceBundle rb = ResourceBundle.getBundle("constant");
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
        Selenide.open(rb.getString("backOfficeUrl"));
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

    /**
     * Проверяет отображение заголовка страницы
     *
     * @param title текст заголовка
     */
    protected void hasPageTitle(String title) {
        pageTitle
                .shouldBe(visible)
                .shouldHave(exactText(title));
    }

    /**
     * Проверяет виден ли текст элемента в таб списке
     *
     * @param tabName текст элемента
     */
    protected void hasTabElementWithName(String tabName) {
        SelenideElement element = findTabElementInCollection(tabsElements, tabName);
        if(element.exists()) {
            element.shouldBe(visible);
            return;
        }
        tabButton
                .shouldBe(enabled)
                .click();
        element = findTabElementInCollection(additionalTabsElements, tabName);
        element.shouldBe(visible);
    }

    /**
     * Ищет текст элемента в коллекции элементов
     *
     * @param collection коллекция элементов
     * @param itemName текст внутри элемента
     * @return найденый элемент
     */
    private SelenideElement findTabElementInCollection(ElementsCollection collection, String itemName) {
        return collection
                .shouldHave(sizeGreaterThan(0))
                .findBy(exactText(itemName));
    }
}
