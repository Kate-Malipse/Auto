import jdk.jfr.Description;
import org.testng.annotations.*;

public class MyTest {
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
    public void GameIsAvailableInHowToPlaySection() {
        HowToPlayPage howToPlayPage = new HowToPlayPage();
        backofficePage.openHowToPlaySection();

        assert howToPlayPage.pageTitle.equals("Как играть (настройки)");
        assert howToPlayPage.availableGames().stream()
                .anyMatch(s -> s.equals("Футбол"));


    }
}
