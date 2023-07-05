import com.beust.jcommander.Parameter;
import jdk.jfr.Description;
import org.testng.annotations.*;

public class BackOfficeTests {
    BackofficePage backofficePage = new BackofficePage();
    LoginPage loginPage = new LoginPage();

    @BeforeClass
    public void setUp() {
        backofficePage.open();
        backofficePage.login();
        loginPage.signIn("login", "password");
    }

    @Test
    @Description("Как играть (настройки). Отображение новой игры")
    public void gameIsAvailableInHowToPlaySection() {
        HowToPlayPage howToPlayPage = new HowToPlayPage();
        backofficePage.openHowToPlaySection();

        howToPlayPage
                .hasTitle("Как играть (настройки)")
                .hasGameInTabList("Футбол");
                //.hasGameInTabList("1БЕТ");
    }

}
