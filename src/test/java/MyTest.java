import org.testng.annotations.*;

public class MyTest {
    @BeforeClass
    public void setUp(){
        BackofficePage backofficePage = new BackofficePage();
        LoginPage loginPage = new LoginPage();

        backofficePage.open();
        backofficePage.login();
        loginPage.signIn("login", "password");
    }
    @Test
    public void AuthorizationTest(){
            System.out.print("Hello");
    }

}
