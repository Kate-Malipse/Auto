import BackOffice.BackofficePage;
import BackOffice.HowToPlayPage;
import BackOffice.Partners.BetSettingsPage;
import BackOffice.Partners.PartnersPage;
import BackOffice.Partners.PopUpPage;
import org.testng.annotations.*;

import java.util.ResourceBundle;
import java.util.UUID;

public class BackOfficeTests {
    private final BackofficePage backofficePage = new BackofficePage();
    private final ResourceBundle rb = ResourceBundle.getBundle("constant");
    private final String login = rb.getString("backOfficeLogin");
    private final String password = rb.getString("backOfficePassword");

    @BeforeClass
    public void setUp() {
        backofficePage.open();
        backofficePage.login();
        new LoginPage().signIn(login, password);
    }

    @Test(description = "Как играть (настройки). Отображение новой игры")
    public void footballIsAvailableInHowToPlaySection() {
        backofficePage.openHowToPlaySection();

        new HowToPlayPage()
                .hasPageTitle("Как играть (настройки)")
                .selectTabWithName("Футбол");
    }

    @Test(description = "Создать джекпот. ППС")
    public void jackpotIsCreatedForPPS() {
        backofficePage.openPartnersSection();
        String jackpotName = UUID.randomUUID().toString().replace("-", "");

        new PartnersPage()
                .hasPageTitle("Партнёры")
                .searchPartner("Test_pps_jp")
                .openPartnerContextMenu()
                .contextMenuHasItems(new String[]{"Создать кассу", "Джекпот", "Набор джекпотов", "Редактировать", "Клонировать", "Удалить"})
                .selectContextItem("Джекпот");

        new PopUpPage()
                .jackpotPopUpIsVisible()
                .createJackpot(jackpotName)
                .successAlertHasAppeared()
                .searchPartner(jackpotName);
    }

    @Test(description = "Максимальный коэффициент Системы. Доступность ввода")
    public void maxSystemBetCoefficientFieldIsEditable() {
        backofficePage.openPartnersSection();

        new PartnersPage()
                .hasPageTitle("Партнёры")
                .searchPartner(6026)
                .selectTabWithName("Настройки ставок");

        new BetSettingsPage()
                .switcherSystemBetsHasState(true)
                .maxSystemBetCoefficientFieldSetValue(10.5);
    }
}
