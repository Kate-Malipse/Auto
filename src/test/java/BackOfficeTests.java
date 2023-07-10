import jdk.jfr.Description;
import org.testng.annotations.*;

import java.util.ResourceBundle;

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

    @Test
    @Description("Как играть (настройки). Отображение новой игры")
    public void gameIsAvailableInHowToPlaySection() {
        backofficePage.openHowToPlaySection();

        new HowToPlayPage()
                .hasPageTitle("Как играть (настройки)")
                .hasTabWithName("Футбол");
    }

}
