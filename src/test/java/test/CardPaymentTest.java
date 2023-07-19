package test;


import com.codeborne.selenide.logevents.SelenideLogger;
import data.DataBase;
import data.DataHelper;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import page.CardPayment;

import static com.codeborne.selenide.Selenide.*;

public class CardPaymentTest {


    @BeforeEach
    void setUp() {
        open("http://localhost:8080");

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
        var cardPayment = new CardPayment();
        cardPayment.everythingFilled(DataHelper.getApprovedCard(), DataHelper.getRightMonth("en"), DataHelper.getYear(3), DataHelper.getOwner("en"), DataHelper.getCode("en"));
        cardPayment.approval();
        Assertions.assertEquals("[APPROVED]", DataBase.getStatusForPayment());
        Assertions.assertEquals("[45000]", DataBase.getAmountForPayment());
    }

    @Test
    void buyOwnerWithHyphen() {
        var cardPayment = new CardPayment();
        cardPayment.everythingFilled(DataHelper.getApprovedCard(), DataHelper.getRightMonth("en"), DataHelper.getYear(3), DataHelper.getOwnerWithHyphen("en"), DataHelper.getCode("en"));
        cardPayment.approval();
        Assertions.assertEquals("[APPROVED]", DataBase.getStatusForPayment());
        Assertions.assertEquals("[45000]", DataBase.getAmountForPayment());
    }

    @Test
    void buyOwnerBorderLine() {
        var cardPayment = new CardPayment();
        cardPayment.everythingFilled(DataHelper.getApprovedCard(), DataHelper.getRightMonth("en"), DataHelper.getYear(3), DataHelper.getBorderlineOwner("en"), DataHelper.getCode("en"));
        cardPayment.approval();
        Assertions.assertEquals("[APPROVED]", DataBase.getStatusForPayment());
        Assertions.assertEquals("[45000]", DataBase.getAmountForPayment());
    }

    @Test
    void buyOwnerWithApostrophe() {
        var cardPayment = new CardPayment();
        cardPayment.everythingFilled(DataHelper.getApprovedCard(), DataHelper.getRightMonth("en"), DataHelper.getYear(3), DataHelper.getOwnerWithApostrophe("en"), DataHelper.getCode("en"));
        cardPayment.approval();
        Assertions.assertEquals("[APPROVED]", DataBase.getStatusForPayment());
        Assertions.assertEquals("[45000]", DataBase.getAmountForPayment());
    }

    @Test
    void buySmallCardNumber() {
        var cardPayment = new CardPayment();
        cardPayment.everythingFilled(DataHelper.getSmallCard("en"), DataHelper.getRightMonth("en"), DataHelper.getYear(3), DataHelper.getOwner("en"), DataHelper.getCode("en"));
        cardPayment.wrongFormat();
    }

    @Test
    void buyOneDigitCardNumber() {
        var cardPayment = new CardPayment();
        cardPayment.everythingFilled(DataHelper.getDigit("en"), DataHelper.getRightMonth("en"), DataHelper.getYear(3), DataHelper.getOwner("en"), DataHelper.getCode("en"));
        cardPayment.wrongFormat();
    }

    @Test
    void buyBigCardNumber() {
        var cardPayment = new CardPayment();
        cardPayment.everythingFilled(DataHelper.getBigCard("en"), DataHelper.getRightMonth("en"), DataHelper.getYear(3), DataHelper.getOwner("en"), DataHelper.getCode("en"));
        cardPayment.disapproval();
        Assertions.assertEquals("[DECLINED]", DataBase.getStatusForPayment());
        Assertions.assertEquals("[45000]", DataBase.getAmountForPayment());
    }

    @Test
    void buyDeclinedCard() {
        var cardPayment = new CardPayment();
        cardPayment.everythingFilled(DataHelper.getDeclinedCard(), DataHelper.getRightMonth("en"), DataHelper.getYear(3), DataHelper.getOwner("en"), DataHelper.getCode("en"));
        cardPayment.disapproval();
        Assertions.assertEquals("[DECLINED]", DataBase.getStatusForPayment());
        Assertions.assertEquals("[45000]", DataBase.getAmountForPayment());
    }

    @Test
    void buyLetterCardNumber() {
        var cardPayment = new CardPayment();
        cardPayment.everythingFilled(DataHelper.getLetters("en"), DataHelper.getRightMonth("en"), DataHelper.getYear(3), DataHelper.getOwner("en"), DataHelper.getCode("en"));
        cardPayment.notFilled();
    }

    @Test
    void buySymbolsCardNumber() {
        var cardPayment = new CardPayment();
        cardPayment.everythingFilled(DataHelper.getSymbols(), DataHelper.getRightMonth("en"), DataHelper.getYear(3), DataHelper.getOwner("en"), DataHelper.getCode("en"));
        cardPayment.notFilled();
    }

    @Test
    void buyEmptyCardNumber() {
        var cardPayment = new CardPayment();
        cardPayment.missingCard(DataHelper.getRightMonth("en"), DataHelper.getYear(3), DataHelper.getOwner("en"), DataHelper.getCode("en"));
        cardPayment.notFilled();
    }

    @Test
    void buyZeroCardNumber() {
        var cardPayment = new CardPayment();
        cardPayment.everythingFilled(DataHelper.getZero(), DataHelper.getRightMonth("en"), DataHelper.getYear(3), DataHelper.getOwner("en"), DataHelper.getCode("en"));
        cardPayment.disapproval();
        Assertions.assertEquals("[DECLINED]", DataBase.getStatusForPayment());
        Assertions.assertEquals("[45000]", DataBase.getAmountForPayment());
    }

    @Test
    void buyZeroMonth() {
        var cardPayment = new CardPayment();
        cardPayment.everythingFilled(DataHelper.getApprovedCard(), DataHelper.getZero(), DataHelper.getYear(3), DataHelper.getOwner("en"), DataHelper.getCode("en"));
        cardPayment.wrongFormat();
    }

    @Test
    void buyNonexistentMonth() {
        var cardPayment = new CardPayment();
        cardPayment.everythingFilled(DataHelper.getApprovedCard(), DataHelper.getWrongMonth("en"), DataHelper.getYear(3), DataHelper.getOwner("en"), DataHelper.getCode("en"));
        cardPayment.incorrectDate();
    }

    @Test
    void buySmallMonth() {
        var cardPayment = new CardPayment();
        cardPayment.everythingFilled(DataHelper.getApprovedCard(), DataHelper.getDigit("en"), DataHelper.getYear(3), DataHelper.getOwner("en"), DataHelper.getCode("en"));
        cardPayment.wrongFormat();
    }

    @Test
    void buyBigMonth() {
        var cardPayment = new CardPayment();
        cardPayment.everythingFilled(DataHelper.getApprovedCard(), DataHelper.getThreeDigits("en"), DataHelper.getYear(3), DataHelper.getOwner("en"), DataHelper.getCode("en"));
        cardPayment.incorrectDate();
    }

    @Test
    void buyLettersMonth() {
        var cardPayment = new CardPayment();
        cardPayment.everythingFilled(DataHelper.getApprovedCard(), DataHelper.getRightMonth("en"), DataHelper.getYear(3), DataHelper.getOwner("en"), DataHelper.getCode("en"));
        cardPayment.notFilled();
    }

    @Test
    void buySymbolsMonth() {
        var cardPayment = new CardPayment();
        cardPayment.everythingFilled(DataHelper.getApprovedCard(), DataHelper.getSymbols(), DataHelper.getYear(3), DataHelper.getOwner("en"), DataHelper.getCode("en"));
        cardPayment.notFilled();
    }

    @Test
    void buyNotFilledMonth() {
        var cardPayment = new CardPayment();
        cardPayment.missingMonth(DataHelper.getApprovedCard(), DataHelper.getYear(3), DataHelper.getOwner("en"), DataHelper.getCode("en"));
        cardPayment.notFilled();
    }

    @Test
    void buyZeroYear() {
        var cardPayment = new CardPayment();
        cardPayment.everythingFilled(DataHelper.getApprovedCard(), DataHelper.getRightMonth("en"), DataHelper.getZero(), DataHelper.getOwner("en"), DataHelper.getCode("en"));
        cardPayment.expired();
    }

    @Test
    void buyLastYear() {
        var cardPayment = new CardPayment();
        cardPayment.everythingFilled(DataHelper.getApprovedCard(), DataHelper.getRightMonth("en"), DataHelper.getYear(-1), DataHelper.getOwner("en"), DataHelper.getCode("en"));
        cardPayment.expired();
    }

    @Test
    void buyFarYear() {
        var cardPayment = new CardPayment();
        cardPayment.everythingFilled(DataHelper.getApprovedCard(), DataHelper.getRightMonth("en"), DataHelper.getYear(6), DataHelper.getOwner("en"), DataHelper.getCode("en"));
        cardPayment.incorrectDate();
    }

    @Test
    void buyDigitYear() {
        var cardPayment = new CardPayment();
        cardPayment.everythingFilled(DataHelper.getApprovedCard(), DataHelper.getRightMonth("en"), DataHelper.getDigit("en"), DataHelper.getOwner("en"), DataHelper.getCode("en"));
        cardPayment.wrongFormat();
    }

    @Test
    void buyBigYear() {
        var cardPayment = new CardPayment();
        cardPayment.everythingFilled(DataHelper.getApprovedCard(), DataHelper.getRightMonth("en"), DataHelper.getThreeDigits("en"), DataHelper.getOwner("en"), DataHelper.getCode("en"));
        cardPayment.incorrectDate();
    }

    @Test
    void buyLettersYear() {
        var cardPayment = new CardPayment();
        cardPayment.everythingFilled(DataHelper.getApprovedCard(), DataHelper.getRightMonth("en"), DataHelper.getLetters("en"), DataHelper.getOwner("en"), DataHelper.getCode("en"));
        cardPayment.notFilled();
    }

    @Test
    void buySymbolsYear() {
        var cardPayment = new CardPayment();
        cardPayment.everythingFilled(DataHelper.getApprovedCard(), DataHelper.getRightMonth("en"), DataHelper.getSymbols(), DataHelper.getOwner("en"), DataHelper.getCode("en"));
        cardPayment.notFilled();
    }

    @Test
    void buyNotFilledYear() {
        var cardPayment = new CardPayment();
        cardPayment.missingYear(DataHelper.getApprovedCard(), DataHelper.getRightMonth("en"), DataHelper.getOwner("en"), DataHelper.getCode("en"));
        cardPayment.notFilled();
    }


    @Test
    void buyBigOwner() {
        var cardPayment = new CardPayment();
        cardPayment.everythingFilled(DataHelper.getApprovedCard(), DataHelper.getRightMonth("en"), DataHelper.getYear(3), DataHelper.getBigOwner("en"), DataHelper.getCode("en"));
        cardPayment.overLimit();
    }

    @Test
    void buyDigitOwner() {
        var cardPayment = new CardPayment();
        cardPayment.everythingFilled(DataHelper.getApprovedCard(), DataHelper.getRightMonth("en"), DataHelper.getYear(3), DataHelper.getDigit("en"), DataHelper.getCode("en"));
        cardPayment.notFilled();
    }

    @Test
    void buySymbolsOwner() {
        var cardPayment = new CardPayment();
        cardPayment.everythingFilled(DataHelper.getApprovedCard(), DataHelper.getRightMonth("en"), DataHelper.getYear(3), DataHelper.getSymbols(), DataHelper.getCode("en"));
        cardPayment.notFilled();
    }

    @Test
    void buyOneLetterOwner() {
        var cardPayment = new CardPayment();
        cardPayment.everythingFilled(DataHelper.getApprovedCard(), DataHelper.getRightMonth("en"), DataHelper.getYear(3), DataHelper.getLetters("en"), DataHelper.getCode("en"));
        cardPayment.wrongFormat();
    }

    @Test
    void buyOnlyNameOwner() {
        var cardPayment = new CardPayment();
        cardPayment.everythingFilled(DataHelper.getApprovedCard(), DataHelper.getRightMonth("en"), DataHelper.getYear(3), DataHelper.getName("en"), DataHelper.getCode("en"));
        cardPayment.noLastName();
    }

    @Test
    void buyRussianOwner() {
        var cardPayment = new CardPayment();
        cardPayment.everythingFilled(DataHelper.getApprovedCard(), DataHelper.getRightMonth("en"), DataHelper.getYear(3), DataHelper.getOwner("ru"), DataHelper.getCode("en"));
        cardPayment.wrongFormat();
    }

    @Test
    void buyNotFilledOwner() {
        var cardPayment = new CardPayment();
        cardPayment.missingOwner(DataHelper.getApprovedCard(), DataHelper.getRightMonth("en"), DataHelper.getYear(3), DataHelper.getCode("en"));
        cardPayment.notFilled();
    }

    @Test
    void buySmallCode() {
        var cardPayment = new CardPayment();
        cardPayment.everythingFilled(DataHelper.getApprovedCard(), DataHelper.getRightMonth("en"), DataHelper.getYear(3), DataHelper.getOwner("en"), DataHelper.getDigit("en"));
        cardPayment.wrongFormat();
    }

    @Test
    void buyBigCode() {
        var cardPayment = new CardPayment();
        cardPayment.everythingFilled(DataHelper.getApprovedCard(), DataHelper.getRightMonth("en"), DataHelper.getYear(3), DataHelper.getOwner("en"), DataHelper.getBigCode("en"));
        cardPayment.disapproval();
        Assertions.assertEquals("[DECLINED]", DataBase.getStatusForPayment());
        Assertions.assertEquals("[45000]", DataBase.getAmountForPayment());
    }

    @Test
    void buyLettersCode() {
        var cardPayment = new CardPayment();
        cardPayment.everythingFilled(DataHelper.getApprovedCard(), DataHelper.getRightMonth("en"), DataHelper.getYear(3), DataHelper.getOwner("en"), DataHelper.getLetters("en"));
        cardPayment.notFilled();
    }

    @Test
    void buySymbolsCode() {
        var cardPayment = new CardPayment();
        cardPayment.everythingFilled(DataHelper.getApprovedCard(), DataHelper.getRightMonth("en"), DataHelper.getYear(3), DataHelper.getOwner("en"), DataHelper.getSymbols());
        cardPayment.notFilled();
    }

    @Test
    void buyNotFilledCode() {
        var cardPayment = new CardPayment();
        cardPayment.missingCode(DataHelper.getApprovedCard(), DataHelper.getRightMonth("en"), DataHelper.getYear(3), DataHelper.getOwner("en"));
        cardPayment.notFilled();
    }
}