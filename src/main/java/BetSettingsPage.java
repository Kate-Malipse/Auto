import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class BetSettingsPage extends BackofficePage {
    private final SelenideElement systemBetsSwitcher = $x("//form[contains(@class, 'ant-form')]//div[@data-testid='setUpRates.form.allowSystem']/button[contains(@class, 'ant-switch')]");
    private final SelenideElement maxSystemBetCoefficientField = $x("//form[contains(@class, 'ant-form')]//div[@data-testid='setUpRates.form.maxSystemBetCoefficient']/input[@class='ant-input']");

    /**
     * Проверяет статус переключателя "Разрешить ставки типа Система"
     *
     * @param state статус переключателя (true, false)
     * @return найденный элемент
     */
    public BetSettingsPage switcherSystemBetsHasState(String state) {
        systemBetsSwitcher
                .shouldBe(visible)
                .shouldHave(attribute("aria-checked", state));
        return this;
    }

    /**
     * Устанавливает значение в поле "Максимальный коэффициент системы"
     *
     * @param value вводимое значение
     * @return найденный элемент
     */
    public BetSettingsPage maxSystemBetCoefficientFieldSetValue(String value) {
        maxSystemBetCoefficientField
                /* для очистки поля ввода т.к. clear() не работает */
                .press(Keys.chord(Keys.CONTROL, "a"))
                .press(Keys.BACK_SPACE)
                .shouldHave(exactValue(""));

        maxSystemBetCoefficientField
                .shouldBe(visible)
                .shouldBe(editable)
                .setValue(value)
                .shouldHave(exactValue(value));
        return this;
    }
}
