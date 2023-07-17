package BackOffice.Partners;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class PopUpPage extends PartnersPage {
    private final SelenideElement popUpHeader = $x("//div[@class='ant-modal-body']/form/h4");
    private final SelenideElement popUpSaveButton = $x("//div[@class='ant-modal-body']/form/button");
    private final SelenideElement jackpotPopUpClientField = $x("//div[@class='ant-modal-body']/form//div[@class='ant-select-selector']");
    private final ElementsCollection popUpItemLabels = $$x("//div[@class='ant-modal-body']/form//label");
    private final ElementsCollection popUpItemInputControls = $$x("//div[@class='ant-modal-body']/form//input");

    /**
     * Проверяет элементы поп-апа Джекпот
     *
     * @return найденный элемент
     */
    public PopUpPage jackpotPopUpIsVisible() {
        popUpHeader
                .shouldBe(visible)
                .shouldHave(exactText("Создать джекпот"));

        popUpSaveButton
                .shouldBe(visible)
                .shouldBe(disabled);

        popUpItemLabels
                .shouldHave(sizeGreaterThan(0))
                .shouldBe(exactTextsCaseSensitive("Название джекпота", "Клиенты"));

        popUpItemInputControls
                .shouldHave(sizeGreaterThan(0))
                .findBy(attribute("placeholder", "Название джекпота"))
                .shouldBe(visible)
                .shouldBe(editable);

        jackpotPopUpClientField
                .shouldBe(visible)
                .shouldBe(editable)
                .shouldHave(text("Клиенты (+"));
        return this;
    }

    /**
     * Создает узел Джекпот
     *
     * @param jackpotName имя узла
     * @return найденный элемент
     */
    public PopUpPage createJackpot(String jackpotName) {
        popUpItemInputControls
                .shouldHave(sizeGreaterThan(0))
                .findBy(attribute("placeholder", "Название джекпота"))
                .shouldBe(visible)
                .shouldBe(editable)
                .setValue(jackpotName)
                .shouldHave(value(jackpotName));

        popUpSaveButton
                .shouldBe(enabled)
                .click();

        return this;
    }
}
