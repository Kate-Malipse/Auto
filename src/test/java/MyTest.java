import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class MyTest {

    @Test
    public void AuthorizationTest(){
        open("/login");
        $("#submit").click();
        $(".message").shouldHave(text("Привет"));

    }

}
