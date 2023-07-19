package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$x;

public class CardPayment {

    private SelenideElement buyButton = $x("//span[contains(text(), 'Купить')]");
    private SelenideElement buyText = $x("//h3[contains(text(), 'Оплата по карте')]");
    private SelenideElement cardNumberField = $x("//input[contains (@placeholder, '0000 0000 0000 0000')]");
    private SelenideElement monthField = $x("//input[contains (@placeholder, '08')]");
    private SelenideElement yearField = $x("//input[contains (@placeholder, '22')]");
    private SelenideElement ownerField = $$("[type=text]").get(3);
    private SelenideElement cvvField = $x("//input[contains (@placeholder, '999')]");
    private SelenideElement continueButton = $$("button[class='button button_view_extra button_size_m button_theme_alfa-on-white']").get(1);
    private SelenideElement approvalNotification = $x("//div[contains(@class, 'notification notification_visible notification_status_ok')]");
    private SelenideElement disapprovalNotification = $x("//div[contains(@class, 'notification notification_visible notification_status_error ')]");
    private SelenideElement wrongFormatNotification = $x("//span[contains(text(), 'Неверный формат')]");
    private SelenideElement notFilledNotification = $x("//span[contains(text(), 'Поле обязательно для заполнения')]");
    private SelenideElement expiredNotification = $x("//span[contains(text(), 'Истёк срок действия карты')]");
    private SelenideElement overLimitNotification = $x("//span[contains(text(), 'Поле не может содержать более 50 символов')]");
    private SelenideElement noLastNameNotification = $x("//span[contains(text(), 'Укажите фамилию')]");
    private SelenideElement incorrectDateNotification = $x("//span[contains(text(), 'Неверно указан срок действия карты')]");


    public CardPayment() {
        buyButton.click();
    }

    public void everythingFilled(String card, String month, String year, String owner, String code) {
        buyText.shouldBe(Condition.visible);
        cardNumberField.setValue(card);
        monthField.setValue(month);
        yearField.setValue(year);
        ownerField.setValue(owner);
        cvvField.setValue(code);
        continueButton.click();
    }

    public void missingCard(String month, String year, String owner, String code) {
        buyText.shouldBe(Condition.visible);
        monthField.setValue(month);
        yearField.setValue(year);
        ownerField.setValue(owner);
        cvvField.setValue(code);
        continueButton.click();
    }

    public void missingMonth(String card, String year, String owner, String code) {
        buyText.shouldBe(Condition.visible);
        cardNumberField.setValue(card);
        yearField.setValue(year);
        ownerField.setValue(owner);
        cvvField.setValue(code);
        continueButton.click();
    }

    public void missingYear(String card, String month, String owner, String code) {
        buyText.shouldBe(Condition.visible);
        cardNumberField.setValue(card);
        monthField.setValue(month);
        ownerField.setValue(owner);
        cvvField.setValue(code);
        continueButton.click();
    }

    public void missingOwner(String card, String month, String year, String code) {
        buyText.shouldBe(Condition.visible);
        cardNumberField.setValue(card);
        monthField.setValue(month);
        yearField.setValue(year);
        cvvField.setValue(code);
        continueButton.click();
    }

    public void missingCode(String card, String month, String year, String owner) {
        buyText.shouldBe(Condition.visible);
        cardNumberField.setValue(card);
        monthField.setValue(month);
        yearField.setValue(year);
        ownerField.setValue(owner);
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

