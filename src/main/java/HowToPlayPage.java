import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.*;

public class HowToPlayPage {
    private final SelenideElement pageTitleElement = $("h3");
    private final ElementsCollection gamesListElements = $$("div.ant-tabs-nav-list > div.ant-tabs-tab");
    public final String pageTitle = pageTitleElement.innerText();

    /**
     * Список игр, доступных для выбора на странице
     *
     * @return список игр
     */
    public ArrayList<String> availableGames() {
        ArrayList<String> gamesList = new ArrayList<>();

        gamesListElements.shouldHave(CollectionCondition.sizeGreaterThan(0));

        for (SelenideElement i : gamesListElements) {
            gamesList.add(i.innerText().trim());
        }
        return gamesList;
    }
}
