package BackOffice.Partners;

import BackOffice.BackofficePage;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class PartnersPage extends BackofficePage {

    private final SelenideElement searchField = $("input#partner-search");
    private final SelenideElement selectedPartnerId = $x("//span[contains(@class, 'ant-tree-node-selected')]//span[@class='tree-item-id']");
    private final SelenideElement selectedPartnerName = $x("//span[contains(@class, 'ant-tree-node-selected')]//span[@class='tree-item-text']");
    private final ElementsCollection searchResultsId = $$("span.search-id-by-type");
    private final ElementsCollection searchResultsName = $$("span.search-result-name");
    private final SelenideElement alertNotificationMessage = $x("//div[@role='alert']/div[@class='ant-notification-notice-message']");
    private final SelenideElement alertNotificationDescription = $x("//div[@role='alert']/div[@class='ant-notification-notice-description']");
    private final ElementsCollection contextItemsElement = $$x("//div[@class='react-contexify__item']/div[@class='react-contexify__item__content']");
    private final ElementsCollection partnersTree = $$("div.ant-tree-treenode");

    /**
     * Поиск партнера в дереве по id партнера
     *
     * @param partnerId id партнера
     * @return найденный элемент
     */
    public PartnersPage searchPartner(Integer partnerId) {
        partnersTree.shouldHave(sizeGreaterThan(0));
        searchField
                .shouldBe(visible)
                /* для очистки поля ввода т.к. clear() не работает */
                .press(Keys.chord(Keys.CONTROL, "a"))
                .press(Keys.BACK_SPACE)
                .shouldHave(exactValue(""));

        searchField
                .setValue(partnerId.toString())
                .shouldHave(exactValue(partnerId.toString()))
                .pressEnter();

        String formattedPartnerId = formatPartnerId(partnerId.toString());

        searchResultsId
                .shouldHave(sizeGreaterThan(0))
                .findBy(exactText(formattedPartnerId))
                .click();

        selectedPartnerId
                .shouldBe(visible)
                .shouldHave(text(formattedPartnerId));

        return this;
    }

    /**
     * Поиск партнера в дереве по имени партнера
     *
     * @param name имя партнера
     * @return найденный элемент
     */
    public PartnersPage searchPartner(String name) {
        partnersTree.shouldHave(sizeGreaterThan(0));
        searchField
                .shouldBe(visible)
                /* для очистки поля ввода т.к. clear() не работает */
                .press(Keys.chord(Keys.CONTROL, "a"))
                .press(Keys.BACK_SPACE)
                .shouldHave(value(""));

        searchField
                .setValue(name)
                .shouldHave(value(name))
                .pressEnter();

        searchResultsName
                .shouldHave(sizeGreaterThan(0))
                .findBy(exactText(name))
                .click();

        selectedPartnerName
                .shouldBe(visible)
                .shouldHave(text(name));

        return this;
    }

    @Override
    public PartnersPage hasPageTitle(String title) {
        return (PartnersPage) super.hasPageTitle(title);
    }

    /**
     * Приведение к формату [000000] значения partnerId
     *
     * @param partnerId id партнера
     * @return отформатированный id
     */
    private String formatPartnerId(String partnerId) {
        return "[" + StringUtils.leftPad(partnerId, 6, '0') + "]";
    }

    /**
     * Проверяет пункты в контекстном меню.
     *
     * @param contextItems список пунктов меню
     * @return найденные элементы
     */
    public PartnersPage contextMenuHasItems(String[] contextItems) {
        contextItemsElement
                .shouldBe(exactTextsCaseSensitive(contextItems));
        return this;
    }

    /**
     * Открытие контекстного меню через нажатие ПКМ на партнера в дереве
     *
     * @return найденный элемент
     */
    public PartnersPage openPartnerContextMenu() {
        selectedPartnerId
                .shouldBe(visible)
                .contextClick();
        return this;
    }

    /**
     * Выбор пункта из контекстного меню партнера
     *
     * @param contextItem пункт меню
     * @return найденный элемент
     */
    public PartnersPage selectContextItem(String contextItem) {
        contextItemsElement
                .shouldHave(sizeGreaterThan(0))
                .findBy(exactText(contextItem))
                .click();
        return this;
    }

    /**
     * Проверяет появление и исчезание нотификации об успехе действия
     *
     * @return найденный элемент
     */
    public PartnersPage successAlertHasAppeared() {
        alertNotificationMessage
                .shouldBe(visible)
                .shouldHave(exactText("Успех"));

        alertNotificationDescription
                .shouldBe(visible)
                .shouldHave(exactText("Операция прошла успешно"));

        alertNotificationMessage
                .shouldNotBe(visible, Duration.ofSeconds(5));
        return this;
    }
}
