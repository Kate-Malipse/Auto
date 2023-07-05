import jdk.jfr.Description;
import org.testng.annotations.*;
import java.util.ResourceBundle;

public class BackOfficeTests {
    BackofficePage backofficePage = new BackofficePage();
    LoginPage loginPage = new LoginPage();
    ResourceBundle rb = ResourceBundle.getBundle("constant");
    String login = rb.getString("backOfficeLogin");
    String password = rb.getString("backOfficePassword");


    @BeforeClass
    public void setUp() {
        backofficePage.open();
        backofficePage.login();
        loginPage.signIn(login, password);
    }

    @Test
    @Description("Как играть (настройки). Отображение новой игры")
    public void gameIsAvailableInHowToPlaySection() {
        HowToPlayPage howToPlayPage = new HowToPlayPage();
        backofficePage.openHowToPlaySection();

        howToPlayPage
                .hasTitle("Как играть (настройки)")
                .hasGameInTabList("Футбол");
    }

}
