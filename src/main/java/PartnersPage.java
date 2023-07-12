import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.apache.commons.lang3.StringUtils;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class PartnersPage extends BackofficePage {

    private final SelenideElement searchField = $("input#partner-search");
    private final SelenideElement selectedPartnerId = $x("//span[contains(@class, 'ant-tree-node-selected')]//span[@class='tree-item-id']");
    private final SelenideElement selectedPartnerName = $x("//span[contains(@class, 'ant-tree-node-selected')]//span[@class='tree-item-text']");
    private final SelenideElement searchResultId = $("span.search-id-by-type");
    private final SelenideElement searchResultName = $("span.search-result-name");
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
                .setValue(partnerId.toString())
                .shouldHave(value(partnerId.toString()))
                .pressEnter();

        String formattedPartnerId = formatPartnerId(partnerId.toString());

        searchResultId
                .shouldBe(visible)
                .shouldHave(text(formattedPartnerId))
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
                .setValue(name)
                .shouldHave(value(name))
                .pressEnter();

        searchResultName
                .shouldBe(visible)
                .shouldHave(text(name))
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
}
