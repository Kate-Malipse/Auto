import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.apache.commons.lang3.StringUtils;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class PartnersPage extends BackofficePage {

    private final SelenideElement searchField = $("input#partner-search");
    private final SelenideElement selectedPartner = $(byXpath("//span[@class='ant-tree-node-selected']/span/div/a/span/span[@class='tree-item-id']"));
    private final SelenideElement searchResult = $("span.search-id-by-type");
    private final ElementsCollection contextItems = $$(byXpath("//div[@class='react-contexify__item']/div[@class='react-contexify__item__content']"));

    /**
     * Поиск партнера в дереве по id партнера
     *
     * @param partnerId id партнера
     * @return найденный элемент
     */
    public PartnersPage searchPartner(String partnerId) {
        searchField
                .shouldBe(visible)
                .setValue(partnerId)
                .pressEnter();

        partnerId = formatPartnerId(partnerId);

        searchResult
                .shouldBe(visible)
                .shouldHave(text(partnerId))
                .click();

        selectedPartner
                .shouldBe(visible)
                .shouldHave(text(partnerId));

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
}
