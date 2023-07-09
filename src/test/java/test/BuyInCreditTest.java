package test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import data.DataHelper;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class BuyInCreditTest {

    private SelenideElement buyInCreditButton = $x("//span[contains (text(), 'Купить в кредит')]");
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



    @BeforeEach
    void setUp() {
        open("http://localhost:8080");
        buyInCreditButton.click();
    }

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }



    @Test
    void validBuyInCredit() {
        cardNumberField.setValue(DataHelper.getApprovedCard());
        monthField.setValue(DataHelper.getRightMonth("en"));
        yearField.setValue(DataHelper.getYear(3));
        ownerField.setValue(DataHelper.getOwner("en"));
        cvvField.setValue(DataHelper.getCode("en"));
        continueButton.click();
        approvalNotification.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    void creditOwnerWithHyphen() {
        cardNumberField.setValue(DataHelper.getApprovedCard());
        monthField.setValue(DataHelper.getRightMonth("en"));
        yearField.setValue(DataHelper.getYear(3));
        ownerField.setValue(DataHelper.getOwnerWithHyphen("en"));
        cvvField.setValue(DataHelper.getCode("en"));
        continueButton.click();
        approvalNotification.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    void creditOwnerBorderLine() {
        cardNumberField.setValue(DataHelper.getApprovedCard());
        monthField.setValue(DataHelper.getRightMonth("en"));
        yearField.setValue(DataHelper.getYear(3));
        ownerField.setValue(DataHelper.getBorderlineOwner("en"));
        cvvField.setValue(DataHelper.getCode("en"));
        continueButton.click();
        approvalNotification.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    void creditOwnerWithApostrophe() {
        cardNumberField.setValue(DataHelper.getApprovedCard());
        monthField.setValue(DataHelper.getRightMonth("en"));
        yearField.setValue(DataHelper.getYear(3));
        ownerField.setValue(DataHelper.getOwnerWithApostrophe("en"));
        cvvField.setValue(DataHelper.getCode("en"));
        continueButton.click();
        approvalNotification.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    void creditSmallCardNumber() {
        cardNumberField.setValue(DataHelper.getSmallCard("en"));
        monthField.setValue(DataHelper.getRightMonth("en"));
        yearField.setValue(DataHelper.getYear(3));
        ownerField.setValue(DataHelper.getOwner("en"));
        cvvField.setValue(DataHelper.getCode("en"));
        continueButton.click();
        wrongFormatNotification.shouldBe(Condition.visible);
    }

    @Test
    void creditOneDigitCardNumber() {
        cardNumberField.setValue(DataHelper.getDigit("en"));
        monthField.setValue(DataHelper.getRightMonth("en"));
        yearField.setValue(DataHelper.getYear(3));
        ownerField.setValue(DataHelper.getOwner("en"));
        cvvField.setValue(DataHelper.getCode("en"));
        continueButton.click();
        wrongFormatNotification.shouldBe(Condition.visible);
    }
    @Test
    void creditBigCardNumber() {
        cardNumberField.setValue(DataHelper.getBigCard("en"));
        monthField.setValue(DataHelper.getRightMonth("en"));
        yearField.setValue(DataHelper.getYear(3));
        ownerField.setValue(DataHelper.getOwner("en"));
        cvvField.setValue(DataHelper.getCode("en"));
        continueButton.click();
        disapprovalNotification.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    void creditDeclinedCard() {
        cardNumberField.setValue(DataHelper.getDeclinedCard());
        monthField.setValue(DataHelper.getRightMonth("en"));
        yearField.setValue(DataHelper.getYear(3));
        ownerField.setValue(DataHelper.getOwner("en"));
        cvvField.setValue(DataHelper.getCode("en"));
        continueButton.click();
        disapprovalNotification.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    void creditLetterCardNumber() {
        cardNumberField.setValue(DataHelper.getLetters("en"));
        monthField.setValue(DataHelper.getRightMonth("en"));
        yearField.setValue(DataHelper.getYear(3));
        ownerField.setValue(DataHelper.getOwner("en"));
        cvvField.setValue(DataHelper.getCode("en"));
        continueButton.click();
        notFilledNotification.shouldBe(Condition.visible);
    }

    @Test
    void creditSymbolsCardNumber() {
        cardNumberField.setValue(DataHelper.getSymbols());
        monthField.setValue(DataHelper.getRightMonth("en"));
        yearField.setValue(DataHelper.getYear(3));
        ownerField.setValue(DataHelper.getOwner("en"));
        cvvField.setValue(DataHelper.getCode("en"));
        continueButton.click();
        notFilledNotification.shouldBe(Condition.visible);
    }

    @Test
    void creditEmptyCardNumber() {
        monthField.setValue(DataHelper.getRightMonth("en"));
        yearField.setValue(DataHelper.getYear(3));
        ownerField.setValue(DataHelper.getOwner("en"));
        cvvField.setValue(DataHelper.getCode("en"));
        continueButton.click();
        notFilledNotification.shouldBe(Condition.visible);
    }

    @Test
    void creditZeroCardNumber() {
        cardNumberField.setValue(DataHelper.getZero());
        monthField.setValue(DataHelper.getRightMonth("en"));
        yearField.setValue(DataHelper.getYear(3));
        ownerField.setValue(DataHelper.getOwner("en"));
        cvvField.setValue(DataHelper.getCode("en"));
        continueButton.click();
        disapprovalNotification.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    void creditZeroMonth() {
        cardNumberField.setValue(DataHelper.getApprovedCard());
        monthField.setValue(DataHelper.getZero());
        yearField.setValue(DataHelper.getYear(3));
        ownerField.setValue(DataHelper.getOwner("en"));
        cvvField.setValue(DataHelper.getCode("en"));
        continueButton.click();
        wrongFormatNotification.shouldBe(Condition.visible);
    }

    @Test
    void creditNonexistentMonth() {
        cardNumberField.setValue(DataHelper.getApprovedCard());
        monthField.setValue(DataHelper.getWrongMonth("en"));
        yearField.setValue(DataHelper.getYear(3));
        ownerField.setValue(DataHelper.getOwner("en"));
        cvvField.setValue(DataHelper.getCode("en"));
        continueButton.click();
        incorrectDateNotification.shouldBe(Condition.visible);
    }

    @Test
    void creditSmallMonth() {
        cardNumberField.setValue(DataHelper.getApprovedCard());
        monthField.setValue(DataHelper.getDigit("en"));
        yearField.setValue(DataHelper.getYear(3));
        ownerField.setValue(DataHelper.getOwner("en"));
        cvvField.setValue(DataHelper.getCode("en"));
        continueButton.click();
        wrongFormatNotification.shouldBe(Condition.visible);
    }

    @Test
    void creditBigMonth() {
        cardNumberField.setValue(DataHelper.getApprovedCard());
        monthField.setValue(DataHelper.getThreeDigits("en"));
        yearField.setValue(DataHelper.getYear(3));
        ownerField.setValue(DataHelper.getOwner("en"));
        cvvField.setValue(DataHelper.getCode("en"));
        continueButton.click();
        incorrectDateNotification.shouldBe(Condition.visible);
    }

    @Test
    void creditLettersMonth() {
        cardNumberField.setValue(DataHelper.getApprovedCard());
        monthField.setValue(DataHelper.getLetters("en"));
        yearField.setValue(DataHelper.getYear(3));
        ownerField.setValue(DataHelper.getOwner("en"));
        cvvField.setValue(DataHelper.getCode("en"));
        continueButton.click();
        notFilledNotification.shouldBe(Condition.visible);
    }

    @Test
    void creditSymbolsMonth() {
        cardNumberField.setValue(DataHelper.getApprovedCard());
        monthField.setValue(DataHelper.getSymbols());
        yearField.setValue(DataHelper.getYear(3));
        ownerField.setValue(DataHelper.getOwner("en"));
        cvvField.setValue(DataHelper.getCode("en"));
        continueButton.click();
        notFilledNotification.shouldBe(Condition.visible);
    }

    @Test
    void creditNotFilledMonth() {
        cardNumberField.setValue(DataHelper.getApprovedCard());
        yearField.setValue(DataHelper.getYear(3));
        ownerField.setValue(DataHelper.getOwner("en"));
        cvvField.setValue(DataHelper.getCode("en"));
        continueButton.click();
        notFilledNotification.shouldBe(Condition.visible);
    }

    @Test
    void creditZeroYear() {
        cardNumberField.setValue(DataHelper.getApprovedCard());
        monthField.setValue(DataHelper.getRightMonth("en"));
        yearField.setValue(DataHelper.getZero());
        ownerField.setValue(DataHelper.getOwner("en"));
        cvvField.setValue(DataHelper.getCode("en"));
        continueButton.click();
        expiredNotification.shouldBe(Condition.visible);
    }

    @Test
    void creditLastYear() {
        cardNumberField.setValue(DataHelper.getApprovedCard());
        monthField.setValue(DataHelper.getRightMonth("en"));
        yearField.setValue(DataHelper.getYear(-1));
        ownerField.setValue(DataHelper.getOwner("en"));
        cvvField.setValue(DataHelper.getCode("en"));
        continueButton.click();
        expiredNotification.shouldBe(Condition.visible);
    }

    @Test
    void creditFarYear() {
        cardNumberField.setValue(DataHelper.getApprovedCard());
        monthField.setValue(DataHelper.getRightMonth("en"));
        yearField.setValue(DataHelper.getYear(6));
        ownerField.setValue(DataHelper.getOwner("en"));
        cvvField.setValue(DataHelper.getCode("en"));
        continueButton.click();
        incorrectDateNotification.shouldBe(Condition.visible);
    }

    @Test
    void creditDigitYear() {
        cardNumberField.setValue(DataHelper.getApprovedCard());
        monthField.setValue(DataHelper.getRightMonth("en"));
        yearField.setValue(DataHelper.getDigit("en"));
        ownerField.setValue(DataHelper.getOwner("en"));
        cvvField.setValue(DataHelper.getCode("en"));
        continueButton.click();
        wrongFormatNotification.shouldBe(Condition.visible);
    }

    @Test
    void creditBigYear() {
        cardNumberField.setValue(DataHelper.getApprovedCard());
        monthField.setValue(DataHelper.getRightMonth("en"));
        yearField.setValue(DataHelper.getThreeDigits("en"));
        ownerField.setValue(DataHelper.getOwner("en"));
        cvvField.setValue(DataHelper.getCode("en"));
        continueButton.click();
        incorrectDateNotification.shouldBe(Condition.visible);
    }

    @Test
    void creditLettersYear() {
        cardNumberField.setValue(DataHelper.getApprovedCard());
        monthField.setValue(DataHelper.getRightMonth("en"));
        yearField.setValue(DataHelper.getLetters("en"));
        ownerField.setValue(DataHelper.getOwner("en"));
        cvvField.setValue(DataHelper.getCode("en"));
        continueButton.click();
        notFilledNotification.shouldBe(Condition.visible);
    }

    @Test
    void creditSymbolsYear() {
        cardNumberField.setValue(DataHelper.getApprovedCard());
        monthField.setValue(DataHelper.getRightMonth("en"));
        yearField.setValue(DataHelper.getSymbols());
        ownerField.setValue(DataHelper.getOwner("en"));
        cvvField.setValue(DataHelper.getCode("en"));
        continueButton.click();
        notFilledNotification.shouldBe(Condition.visible);
    }

    @Test
    void creditNotFilledYear() {
        cardNumberField.setValue(DataHelper.getApprovedCard());
        monthField.setValue(DataHelper.getRightMonth("en"));
        ownerField.setValue(DataHelper.getOwner("en"));
        cvvField.setValue(DataHelper.getCode("en"));
        continueButton.click();
        notFilledNotification.shouldBe(Condition.visible);
    }


    @Test
    void creditBigOwner() {
        cardNumberField.setValue(DataHelper.getApprovedCard());
        monthField.setValue(DataHelper.getRightMonth("en"));
        yearField.setValue(DataHelper.getYear(2));
        ownerField.setValue(DataHelper.getBigOwner("en"));
        cvvField.setValue(DataHelper.getCode("en"));
        continueButton.click();
        overLimitNotification.shouldBe(Condition.visible);
    }

    @Test
    void creditDigitOwner() {
        cardNumberField.setValue(DataHelper.getApprovedCard());
        monthField.setValue(DataHelper.getRightMonth("en"));
        yearField.setValue(DataHelper.getYear(2));
        ownerField.setValue(DataHelper.getDigit("en"));
        cvvField.setValue(DataHelper.getCode("en"));
        continueButton.click();
        notFilledNotification.shouldBe(Condition.visible);
    }

    @Test
    void creditSymbolsOwner() {
        cardNumberField.setValue(DataHelper.getApprovedCard());
        monthField.setValue(DataHelper.getRightMonth("en"));
        yearField.setValue(DataHelper.getYear(2));
        ownerField.setValue(DataHelper.getSymbols());
        cvvField.setValue(DataHelper.getCode("en"));
        continueButton.click();
        notFilledNotification.shouldBe(Condition.visible);
    }

    @Test
    void creditOneLetterOwner() {
        cardNumberField.setValue(DataHelper.getApprovedCard());
        monthField.setValue(DataHelper.getRightMonth("en"));
        yearField.setValue(DataHelper.getYear(2));
        ownerField.setValue(DataHelper.getLetters("en"));
        cvvField.setValue(DataHelper.getCode("en"));
        continueButton.click();
        wrongFormatNotification.shouldBe(Condition.visible);
    }

    @Test
    void creditOnlyNameOwner() {
        cardNumberField.setValue(DataHelper.getApprovedCard());
        monthField.setValue(DataHelper.getRightMonth("en"));
        yearField.setValue(DataHelper.getYear(2));
        ownerField.setValue(DataHelper.getName("en"));
        cvvField.setValue(DataHelper.getCode("en"));
        continueButton.click();
        noLastNameNotification.shouldBe(Condition.visible);
    }

    @Test
    void creditRussianOwner() {
        cardNumberField.setValue(DataHelper.getApprovedCard());
        monthField.setValue(DataHelper.getRightMonth("en"));
        yearField.setValue(DataHelper.getYear(2));
        ownerField.setValue(DataHelper.getOwner("ru"));
        cvvField.setValue(DataHelper.getCode("en"));
        continueButton.click();
        wrongFormatNotification.shouldBe(Condition.visible);
    }

    @Test
    void creditNotFilledOwner() {
        cardNumberField.setValue(DataHelper.getApprovedCard());
        monthField.setValue(DataHelper.getRightMonth("en"));
        yearField.setValue(DataHelper.getYear(2));
        cvvField.setValue(DataHelper.getCode("en"));
        continueButton.click();
        notFilledNotification.shouldBe(Condition.visible);
    }

    @Test
    void creditSmallCode() {
        cardNumberField.setValue(DataHelper.getApprovedCard());
        monthField.setValue(DataHelper.getRightMonth("en"));
        yearField.setValue(DataHelper.getYear(2));
        ownerField.setValue(DataHelper.getOwner("en"));
        cvvField.setValue(DataHelper.getDigit("en"));
        continueButton.click();
        wrongFormatNotification.shouldBe(Condition.visible);
    }

    @Test
    void creditBigCode() {
        cardNumberField.setValue(DataHelper.getApprovedCard());
        monthField.setValue(DataHelper.getRightMonth("en"));
        yearField.setValue(DataHelper.getYear(2));
        ownerField.setValue(DataHelper.getOwner("en"));
        cvvField.setValue(DataHelper.getBigCode("en"));
        continueButton.click();
        disapprovalNotification.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    void creditLettersCode() {
        cardNumberField.setValue(DataHelper.getApprovedCard());
        monthField.setValue(DataHelper.getRightMonth("en"));
        yearField.setValue(DataHelper.getYear(2));
        ownerField.setValue(DataHelper.getOwner("en"));
        cvvField.setValue(DataHelper.getLetters("en"));
        continueButton.click();
        notFilledNotification.shouldBe(Condition.visible);
    }

    @Test
    void creditSymbolsCode() {
        cardNumberField.setValue(DataHelper.getApprovedCard());
        monthField.setValue(DataHelper.getRightMonth("en"));
        yearField.setValue(DataHelper.getYear(2));
        ownerField.setValue(DataHelper.getOwner("en"));
        cvvField.setValue(DataHelper.getSymbols());
        continueButton.click();
        notFilledNotification.shouldBe(Condition.visible);
    }

    @Test
    void creditNotFilledCode() {
        cardNumberField.setValue(DataHelper.getApprovedCard());
        monthField.setValue(DataHelper.getRightMonth("en"));
        yearField.setValue(DataHelper.getYear(2));
        ownerField.setValue(DataHelper.getOwner("en"));
        continueButton.click();
        notFilledNotification.shouldBe(Condition.visible);
    }


}
