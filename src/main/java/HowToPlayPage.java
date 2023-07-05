import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition.*;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class HowToPlayPage extends BackofficePage {

    private final SelenideElement tabButton = $("div.ant-tabs-nav-operations > button");
    private final ElementsCollection additionalGameList = $$("div.ant-tabs-dropdown > ul > li");
    public HowToPlayPage hasTitle(String title){
        hasPageTitle(title);
        return this;
    }

    public HowToPlayPage hasGameInTabList(String game) {
        hasTabElementWithName(game);
        return this;
    }
}
