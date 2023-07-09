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

public class BuyTest {

    private SelenideElement buyButton = $x("//span[contains(text(), 'Купить')]");
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
        buyButton.click();
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
    void validBuy() {
        cardNumberField.setValue(DataHelper.getApprovedCard());
        monthField.setValue(DataHelper.getRightMonth("en"));
        yearField.setValue(DataHelper.getYear(3));
        ownerField.setValue(DataHelper.getOwner("en"));
        cvvField.setValue(DataHelper.getCode("en"));
        continueButton.click();
        approvalNotification.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    void buyOwnerWithHyphen() {
        cardNumberField.setValue(DataHelper.getApprovedCard());
        monthField.setValue(DataHelper.getRightMonth("en"));
        yearField.setValue(DataHelper.getYear(3));
        ownerField.setValue(DataHelper.getOwnerWithHyphen("en"));
        cvvField.setValue(DataHelper.getCode("en"));
        continueButton.click();
        approvalNotification.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    void buyOwnerBorderLine() {
        cardNumberField.setValue(DataHelper.getApprovedCard());
        monthField.setValue(DataHelper.getRightMonth("en"));
        yearField.setValue(DataHelper.getYear(3));
        ownerField.setValue(DataHelper.getBorderlineOwner("en"));
        cvvField.setValue(DataHelper.getCode("en"));
        continueButton.click();
        approvalNotification.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    void buyOwnerWithApostrophe() {
        cardNumberField.setValue(DataHelper.getApprovedCard());
        monthField.setValue(DataHelper.getRightMonth("en"));
        yearField.setValue(DataHelper.getYear(3));
        ownerField.setValue(DataHelper.getOwnerWithApostrophe("en"));
        cvvField.setValue(DataHelper.getCode("en"));
        continueButton.click();
        approvalNotification.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    void buySmallCardNumber() {
        cardNumberField.setValue(DataHelper.getSmallCard("en"));
        monthField.setValue(DataHelper.getRightMonth("en"));
        yearField.setValue(DataHelper.getYear(3));
        ownerField.setValue(DataHelper.getOwner("en"));
        cvvField.setValue(DataHelper.getCode("en"));
        continueButton.click();
        wrongFormatNotification.shouldBe(Condition.visible);
    }

    @Test
    void buyOneDigitCardNumber() {
        cardNumberField.setValue(DataHelper.getDigit("en"));
        monthField.setValue(DataHelper.getRightMonth("en"));
        yearField.setValue(DataHelper.getYear(3));
        ownerField.setValue(DataHelper.getOwner("en"));
        cvvField.setValue(DataHelper.getCode("en"));
        continueButton.click();
        wrongFormatNotification.shouldBe(Condition.visible);
    }
    @Test
    void buyBigCardNumber() {
        cardNumberField.setValue(DataHelper.getBigCard("en"));
        monthField.setValue(DataHelper.getRightMonth("en"));
        yearField.setValue(DataHelper.getYear(3));
        ownerField.setValue(DataHelper.getOwner("en"));
        cvvField.setValue(DataHelper.getCode("en"));
        continueButton.click();
        disapprovalNotification.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    void buyDeclinedCard() {
        cardNumberField.setValue(DataHelper.getDeclinedCard());
        monthField.setValue(DataHelper.getRightMonth("en"));
        yearField.setValue(DataHelper.getYear(3));
        ownerField.setValue(DataHelper.getOwner("en"));
        cvvField.setValue(DataHelper.getCode("en"));
        continueButton.click();
        disapprovalNotification.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    void buyLetterCardNumber() {
        cardNumberField.setValue(DataHelper.getLetters("en"));
        monthField.setValue(DataHelper.getRightMonth("en"));
        yearField.setValue(DataHelper.getYear(3));
        ownerField.setValue(DataHelper.getOwner("en"));
        cvvField.setValue(DataHelper.getCode("en"));
        continueButton.click();
        notFilledNotification.shouldBe(Condition.visible);
    }

    @Test
    void buySymbolsCardNumber() {
        cardNumberField.setValue(DataHelper.getSymbols());
        monthField.setValue(DataHelper.getRightMonth("en"));
        yearField.setValue(DataHelper.getYear(3));
        ownerField.setValue(DataHelper.getOwner("en"));
        cvvField.setValue(DataHelper.getCode("en"));
        continueButton.click();
        notFilledNotification.shouldBe(Condition.visible);
    }

    @Test
    void buyEmptyCardNumber() {
        monthField.setValue(DataHelper.getRightMonth("en"));
        yearField.setValue(DataHelper.getYear(3));
        ownerField.setValue(DataHelper.getOwner("en"));
        cvvField.setValue(DataHelper.getCode("en"));
        continueButton.click();
        notFilledNotification.shouldBe(Condition.visible);
    }

    @Test
    void buyZeroCardNumber() {
        cardNumberField.setValue(DataHelper.getZero());
        monthField.setValue(DataHelper.getRightMonth("en"));
        yearField.setValue(DataHelper.getYear(3));
        ownerField.setValue(DataHelper.getOwner("en"));
        cvvField.setValue(DataHelper.getCode("en"));
        continueButton.click();
        disapprovalNotification.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    void buyZeroMonth() {
        cardNumberField.setValue(DataHelper.getApprovedCard());
        monthField.setValue(DataHelper.getZero());
        yearField.setValue(DataHelper.getYear(3));
        ownerField.setValue(DataHelper.getOwner("en"));
        cvvField.setValue(DataHelper.getCode("en"));
        continueButton.click();
        wrongFormatNotification.shouldBe(Condition.visible);
    }

    @Test
    void buyNonexistentMonth() {
        cardNumberField.setValue(DataHelper.getApprovedCard());
        monthField.setValue(DataHelper.getWrongMonth("en"));
        yearField.setValue(DataHelper.getYear(3));
        ownerField.setValue(DataHelper.getOwner("en"));
        cvvField.setValue(DataHelper.getCode("en"));
        continueButton.click();
        incorrectDateNotification.shouldBe(Condition.visible);
    }

    @Test
    void buySmallMonth() {
        cardNumberField.setValue(DataHelper.getApprovedCard());
        monthField.setValue(DataHelper.getDigit("en"));
        yearField.setValue(DataHelper.getYear(3));
        ownerField.setValue(DataHelper.getOwner("en"));
        cvvField.setValue(DataHelper.getCode("en"));
        continueButton.click();
        wrongFormatNotification.shouldBe(Condition.visible);
    }

    @Test
    void buyBigMonth() {
        cardNumberField.setValue(DataHelper.getApprovedCard());
        monthField.setValue(DataHelper.getThreeDigits("en"));
        yearField.setValue(DataHelper.getYear(3));
        ownerField.setValue(DataHelper.getOwner("en"));
        cvvField.setValue(DataHelper.getCode("en"));
        continueButton.click();
        incorrectDateNotification.shouldBe(Condition.visible);
    }

    @Test
    void buyLettersMonth() {
        cardNumberField.setValue(DataHelper.getApprovedCard());
        monthField.setValue(DataHelper.getLetters("en"));
        yearField.setValue(DataHelper.getYear(3));
        ownerField.setValue(DataHelper.getOwner("en"));
        cvvField.setValue(DataHelper.getCode("en"));
        continueButton.click();
        notFilledNotification.shouldBe(Condition.visible);
    }

    @Test
    void buySymbolsMonth() {
        cardNumberField.setValue(DataHelper.getApprovedCard());
        monthField.setValue(DataHelper.getSymbols());
        yearField.setValue(DataHelper.getYear(3));
        ownerField.setValue(DataHelper.getOwner("en"));
        cvvField.setValue(DataHelper.getCode("en"));
        continueButton.click();
        notFilledNotification.shouldBe(Condition.visible);
    }

    @Test
    void buyNotFilledMonth() {
        cardNumberField.setValue(DataHelper.getApprovedCard());
        yearField.setValue(DataHelper.getYear(3));
        ownerField.setValue(DataHelper.getOwner("en"));
        cvvField.setValue(DataHelper.getCode("en"));
        continueButton.click();
        notFilledNotification.shouldBe(Condition.visible);
    }

    @Test
    void buyZeroYear() {
        cardNumberField.setValue(DataHelper.getApprovedCard());
        monthField.setValue(DataHelper.getRightMonth("en"));
        yearField.setValue(DataHelper.getZero());
        ownerField.setValue(DataHelper.getOwner("en"));
        cvvField.setValue(DataHelper.getCode("en"));
        continueButton.click();
        expiredNotification.shouldBe(Condition.visible);
    }

    @Test
    void buyLastYear() {
        cardNumberField.setValue(DataHelper.getApprovedCard());
        monthField.setValue(DataHelper.getRightMonth("en"));
        yearField.setValue(DataHelper.getYear(-1));
        ownerField.setValue(DataHelper.getOwner("en"));
        cvvField.setValue(DataHelper.getCode("en"));
        continueButton.click();
        expiredNotification.shouldBe(Condition.visible);
    }

    @Test
    void buyFarYear() {
        cardNumberField.setValue(DataHelper.getApprovedCard());
        monthField.setValue(DataHelper.getRightMonth("en"));
        yearField.setValue(DataHelper.getYear(6));
        ownerField.setValue(DataHelper.getOwner("en"));
        cvvField.setValue(DataHelper.getCode("en"));
        continueButton.click();
        incorrectDateNotification.shouldBe(Condition.visible);
    }

    @Test
    void buyDigitYear() {
        cardNumberField.setValue(DataHelper.getApprovedCard());
        monthField.setValue(DataHelper.getRightMonth("en"));
        yearField.setValue(DataHelper.getDigit("en"));
        ownerField.setValue(DataHelper.getOwner("en"));
        cvvField.setValue(DataHelper.getCode("en"));
        continueButton.click();
        wrongFormatNotification.shouldBe(Condition.visible);
    }

    @Test
    void buyBigYear() {
        cardNumberField.setValue(DataHelper.getApprovedCard());
        monthField.setValue(DataHelper.getRightMonth("en"));
        yearField.setValue(DataHelper.getThreeDigits("en"));
        ownerField.setValue(DataHelper.getOwner("en"));
        cvvField.setValue(DataHelper.getCode("en"));
        continueButton.click();
        incorrectDateNotification.shouldBe(Condition.visible);
    }

    @Test
    void buyLettersYear() {
        cardNumberField.setValue(DataHelper.getApprovedCard());
        monthField.setValue(DataHelper.getRightMonth("en"));
        yearField.setValue(DataHelper.getLetters("en"));
        ownerField.setValue(DataHelper.getOwner("en"));
        cvvField.setValue(DataHelper.getCode("en"));
        continueButton.click();
        notFilledNotification.shouldBe(Condition.visible);
    }

    @Test
    void buySymbolsYear() {
        cardNumberField.setValue(DataHelper.getApprovedCard());
        monthField.setValue(DataHelper.getRightMonth("en"));
        yearField.setValue(DataHelper.getSymbols());
        ownerField.setValue(DataHelper.getOwner("en"));
        cvvField.setValue(DataHelper.getCode("en"));
        continueButton.click();
        notFilledNotification.shouldBe(Condition.visible);
    }

    @Test
    void buyNotFilledYear() {
        cardNumberField.setValue(DataHelper.getApprovedCard());
        monthField.setValue(DataHelper.getRightMonth("en"));
        ownerField.setValue(DataHelper.getOwner("en"));
        cvvField.setValue(DataHelper.getCode("en"));
        continueButton.click();
        notFilledNotification.shouldBe(Condition.visible);
    }


    @Test
    void buyBigOwner() {
        cardNumberField.setValue(DataHelper.getApprovedCard());
        monthField.setValue(DataHelper.getRightMonth("en"));
        yearField.setValue(DataHelper.getYear(2));
        ownerField.setValue(DataHelper.getBigOwner("en"));
        cvvField.setValue(DataHelper.getCode("en"));
        continueButton.click();
        overLimitNotification.shouldBe(Condition.visible);
    }

    @Test
    void buyDigitOwner() {
        cardNumberField.setValue(DataHelper.getApprovedCard());
        monthField.setValue(DataHelper.getRightMonth("en"));
        yearField.setValue(DataHelper.getYear(2));
        ownerField.setValue(DataHelper.getDigit("en"));
        cvvField.setValue(DataHelper.getCode("en"));
        continueButton.click();
        notFilledNotification.shouldBe(Condition.visible);
    }

    @Test
    void buySymbolsOwner() {
        cardNumberField.setValue(DataHelper.getApprovedCard());
        monthField.setValue(DataHelper.getRightMonth("en"));
        yearField.setValue(DataHelper.getYear(2));
        ownerField.setValue(DataHelper.getSymbols());
        cvvField.setValue(DataHelper.getCode("en"));
        continueButton.click();
        notFilledNotification.shouldBe(Condition.visible);
    }

    @Test
    void buyOneLetterOwner() {
        cardNumberField.setValue(DataHelper.getApprovedCard());
        monthField.setValue(DataHelper.getRightMonth("en"));
        yearField.setValue(DataHelper.getYear(2));
        ownerField.setValue(DataHelper.getLetters("en"));
        cvvField.setValue(DataHelper.getCode("en"));
        continueButton.click();
        wrongFormatNotification.shouldBe(Condition.visible);
    }

    @Test
    void buyOnlyNameOwner() {
        cardNumberField.setValue(DataHelper.getApprovedCard());
        monthField.setValue(DataHelper.getRightMonth("en"));
        yearField.setValue(DataHelper.getYear(2));
        ownerField.setValue(DataHelper.getName("en"));
        cvvField.setValue(DataHelper.getCode("en"));
        continueButton.click();
        noLastNameNotification.shouldBe(Condition.visible);
    }

    @Test
    void buyRussianOwner() {
        cardNumberField.setValue(DataHelper.getApprovedCard());
        monthField.setValue(DataHelper.getRightMonth("en"));
        yearField.setValue(DataHelper.getYear(2));
        ownerField.setValue(DataHelper.getOwner("ru"));
        cvvField.setValue(DataHelper.getCode("en"));
        continueButton.click();
        wrongFormatNotification.shouldBe(Condition.visible);
    }

    @Test
    void buyNotFilledOwner() {
        cardNumberField.setValue(DataHelper.getApprovedCard());
        monthField.setValue(DataHelper.getRightMonth("en"));
        yearField.setValue(DataHelper.getYear(2));
        cvvField.setValue(DataHelper.getCode("en"));
        continueButton.click();
        notFilledNotification.shouldBe(Condition.visible);
    }

    @Test
    void buySmallCode() {
        cardNumberField.setValue(DataHelper.getApprovedCard());
        monthField.setValue(DataHelper.getRightMonth("en"));
        yearField.setValue(DataHelper.getYear(2));
        ownerField.setValue(DataHelper.getOwner("en"));
        cvvField.setValue(DataHelper.getDigit("en"));
        continueButton.click();
        wrongFormatNotification.shouldBe(Condition.visible);
    }

    @Test
    void buyBigCode() {
        cardNumberField.setValue(DataHelper.getApprovedCard());
        monthField.setValue(DataHelper.getRightMonth("en"));
        yearField.setValue(DataHelper.getYear(2));
        ownerField.setValue(DataHelper.getOwner("en"));
        cvvField.setValue(DataHelper.getBigCode("en"));
        continueButton.click();
        disapprovalNotification.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }

    @Test
    void buyLettersCode() {
        cardNumberField.setValue(DataHelper.getApprovedCard());
        monthField.setValue(DataHelper.getRightMonth("en"));
        yearField.setValue(DataHelper.getYear(2));
        ownerField.setValue(DataHelper.getOwner("en"));
        cvvField.setValue(DataHelper.getLetters("en"));
        continueButton.click();
        notFilledNotification.shouldBe(Condition.visible);
    }

    @Test
    void buySymbolsCode() {
        cardNumberField.setValue(DataHelper.getApprovedCard());
        monthField.setValue(DataHelper.getRightMonth("en"));
        yearField.setValue(DataHelper.getYear(2));
        ownerField.setValue(DataHelper.getOwner("en"));
        cvvField.setValue(DataHelper.getSymbols());
        continueButton.click();
        notFilledNotification.shouldBe(Condition.visible);
    }

    @Test
    void buyNotFilledCode() {
        cardNumberField.setValue(DataHelper.getApprovedCard());
        monthField.setValue(DataHelper.getRightMonth("en"));
        yearField.setValue(DataHelper.getYear(2));
        ownerField.setValue(DataHelper.getOwner("en"));
        continueButton.click();
        notFilledNotification.shouldBe(Condition.visible);
    }
}