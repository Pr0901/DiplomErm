package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class CardPayment {

    private SelenideElement buyButton = $(byText("Купить"));
    private SelenideElement buyText = $(byText("Оплата по карте"));
    private SelenideElement cardNumberField = $x("//input[contains (@placeholder, '0000 0000 0000 0000')]");
    private SelenideElement monthField = $x("//input[contains (@placeholder, '08')]");
    private SelenideElement yearField = $x("//input[contains (@placeholder, '22')]");
    private SelenideElement ownerField = $$("[type=text]").get(3);
    private SelenideElement cvvField = $x("//input[contains (@placeholder, '999')]");
    private SelenideElement continueButton = $(byText("Продолжить"));
    private SelenideElement approvalNotification = $x("//div[contains(@class, 'notification_status_ok')]");
    private SelenideElement disapprovalNotification = $x("//div[contains(@class, 'notification_status_error')]");
    private SelenideElement wrongFormatNotification = $(byText("Неверный формат"));
    private SelenideElement notFilledNotification = $(byText("Поле обязательно для заполнения"));
    private SelenideElement expiredNotification = $(byText("Истёк срок действия карты"));
    private SelenideElement overLimitNotification = $(byText("Поле не может содержать более 50 символов"));
    private SelenideElement noLastNameNotification = $(byText("Укажите фамилию"));
    private SelenideElement incorrectDateNotification = $(byText("Неверно указан срок действия карты"));


    public CardPayment() {
        buyButton.click();
    }

    public void fillingInformation(String card, String month, String year, String owner, String code) {
        buyText.shouldBe(Condition.visible);
        cardNumberField.setValue(card);
        monthField.setValue(month);
        yearField.setValue(year);
        ownerField.setValue(owner);
        cvvField.setValue(code);
        continueButton.click();
    }

    public void approval() {
        approvalNotification.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    public void disapproval() {
        disapprovalNotification.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    public void wrongFormat() {
        wrongFormatNotification.shouldBe(Condition.visible);
    }

    public void notFilled() {
        notFilledNotification.shouldBe(Condition.visible);
    }

    public void expired() {
        expiredNotification.shouldBe(Condition.visible);
    }

    public void overLimit() {
        overLimitNotification.shouldBe(Condition.visible);
    }

    public void noLastName() {
        noLastNameNotification.shouldBe(Condition.visible);
    }

    public void incorrectDate() {
        incorrectDateNotification.shouldBe(Condition.visible);
    }
}

