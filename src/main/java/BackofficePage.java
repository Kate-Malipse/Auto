import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.util.ResourceBundle;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class BackofficePage {
    private final ResourceBundle rb = ResourceBundle.getBundle("constant");
    private final SelenideElement loginButton = $x("//span[text()= 'LOG IN']");
    private final SelenideElement partnersSection = $("a[href=\"/partners\"");
    private final SelenideElement rulesAndHintsList = $x("//aside//span[text()='Правила и подсказки']");
    private final SelenideElement howToPlaySection = $("a[href=\"/rules-and-tooltips/how-to-play\"]");
    protected final SelenideElement pageTitle = $("h3");
    private final SelenideElement tabButton = $x("//div[@class='ant-tabs-nav-operations']/button");
    protected final ElementsCollection tabsElements = $$x("//div[@class= 'ant-tabs-nav-list']/div[@class='ant-tabs-tab']");
    protected final ElementsCollection additionalTabsElements = $$x("//ul[@aria-label='expanded dropdown']/li");

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

    /**
     * Открытие раздела Партнеры
     */
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
    protected BackofficePage hasPageTitle(String title) {
        pageTitle
                .shouldBe(visible)
                .shouldHave(exactText(title));
        return this;
    }

    /**
     * Проверяет виден ли текст элемента в таб списке
     *
     * @param tabName текст элемента
     */
    public BackofficePage hasTabWithName(String tabName) {
        SelenideElement element = findTabElementInCollection(tabsElements, tabName);

        if (!element.exists()) {
            tabButton
                    .shouldBe(enabled)
                    .click();

            element = findTabElementInCollection(additionalTabsElements, tabName);
        }

        element.shouldBe(visible);

        return this;
    }

    /**
     * Ищет текст элемента в коллекции элементов
     *
     * @param collection коллекция элементов
     * @param itemName   текст внутри элемента
     * @return найденый элемент
     */
    private SelenideElement findTabElementInCollection(ElementsCollection collection, String itemName) {
        return collection
                .shouldHave(sizeGreaterThan(0))
                .findBy(exactText(itemName));
    }
}
