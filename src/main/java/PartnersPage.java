import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.apache.commons.lang3.StringUtils;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class PartnersPage extends BackofficePage {

    private final SelenideElement searchField = $("input#partner-search");
    private final SelenideElement selectedPartner = $x("//span[contains(@class, 'ant-tree-node-selected')]//span[@class='tree-item-id']");
    private final SelenideElement searchResult = $("span.search-id-by-type");
    private final SelenideElement popUpHeader = $x("//div[@class='ant-modal-body']/form/h4");
    private final SelenideElement popUpSaveButton = $x("//div[@class='ant-modal-body']/form/button");
    private final ElementsCollection contextItemsElement = $$x("//div[@class='react-contexify__item']/div[@class='react-contexify__item__content']");
    private final ElementsCollection partnersTree = $$("div.ant-tree-treenode");
    private final ElementsCollection popUpItemLabels = $$x("//div[@class='ant-modal-body']/form//label");
    private final ElementsCollection popUpItemInputControls = $$x("//div[@class='ant-modal-body']/form//input");

    /**
     * Поиск партнера в дереве по id партнера
     *
     * @param partnerId id партнера
     * @return найденный элемент
     */
    public PartnersPage searchPartner(String partnerId) {
        partnersTree.shouldHave(sizeGreaterThan(0));
        searchField
                .shouldBe(visible)
                .setValue(partnerId)
                .shouldHave(value(partnerId))
                .pressEnter();

        String formattedPartnerId = formatPartnerId(partnerId);

        searchResult
                .shouldBe(visible)
                .shouldHave(text(formattedPartnerId))
                .click();

        selectedPartner
                .shouldBe(visible)
                .shouldHave(text(formattedPartnerId));

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
    private String formatPartnerId(String partnerId)
    {
        return "[" + StringUtils.leftPad(partnerId, 6, '0') + "]";
    }

    public PartnersPage contextMenuHasItems(String[] contextItems) {
        contextItemsElement
                .shouldBe(exactTextsCaseSensitive(contextItems));
        return this;
    }

    public PartnersPage openPartnerContextMenu() {
        selectedPartner
                .shouldBe(visible)
                .contextClick();
        return this;
    }

    public PartnersPage selectContextItem(String contextItem) {
        contextItemsElement
                .shouldHave(sizeGreaterThan(0))
                .findBy(exactText(contextItem))
                .click();
        return this;
    }

    public PartnersPage jackpotPopUpHasAppeared() {
        popUpHeader
                .shouldBe(visible)
                .shouldHave(exactText("Создать джекпот"));

        popUpSaveButton
                .shouldBe(visible)
                .shouldBe(enabled);

        popUpItemLabels
                .shouldHave(sizeGreaterThan(0))
                .shouldBe(exactTextsCaseSensitive("Название джекпота", "Клиенты"));
//                .findBy(exactText("Название джекпота"))
//                .shouldBe(visible);

//        popUpItemLabels
//                .shouldHave(sizeGreaterThan(0))
//                .findBy(exactText("Клиенты"))
//                .shouldBe(visible);

        popUpItemInputControls
                .shouldHave(sizeGreaterThan(0))
                .findBy(attribute("placeholder", "Название джекпота"))
                .shouldBe(visible)
                .shouldBe(editable);

        return this;
    }
}
