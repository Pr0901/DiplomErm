package test;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.DataBase;
import data.DataHelper;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import page.BuyingOnCredit;


import static com.codeborne.selenide.Selenide.*;

public class BuyingOnCreditTest {


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
    void validBuyInCredit() {
        var buyingOnCredit = new BuyingOnCredit();
        buyingOnCredit.fillingInformation(DataHelper.getApprovedCard(), DataHelper.getRightMonth("en"), DataHelper.getYear(3), DataHelper.getOwner("en"), DataHelper.getCode("en"));
        buyingOnCredit.approval();
        Assertions.assertEquals("[APPROVED]", DataBase.getStatusForCredit());
    }


    @Test
    void creditOwnerWithHyphen() {
        var buyingOnCredit = new BuyingOnCredit();
        buyingOnCredit.fillingInformation(DataHelper.getApprovedCard(), DataHelper.getRightMonth("en"), DataHelper.getYear(3), DataHelper.getOwnerWithHyphen("en"), DataHelper.getCode("en"));
        buyingOnCredit.approval();
        Assertions.assertEquals("[APPROVED]", DataBase.getStatusForCredit());
    }

    @Test
    void creditOwnerBorderLine() {
        var buyingOnCredit = new BuyingOnCredit();
        buyingOnCredit.fillingInformation(DataHelper.getApprovedCard(), DataHelper.getRightMonth("en"), DataHelper.getYear(3), DataHelper.getBorderlineOwner("en"), DataHelper.getCode("en"));
        buyingOnCredit.approval();
    }


    @Test
    void creditOwnerWithApostrophe() {
        var buyingOnCredit = new BuyingOnCredit();
        buyingOnCredit.fillingInformation(DataHelper.getApprovedCard(), DataHelper.getRightMonth("en"), DataHelper.getYear(3), DataHelper.getOwnerWithApostrophe("en"), DataHelper.getCode("en"));
        buyingOnCredit.approval();
        Assertions.assertEquals("[APPROVED]", DataBase.getStatusForCredit());
    }


    @Test
    void creditSmallCardNumber() {
        var buyingOnCredit = new BuyingOnCredit();
        buyingOnCredit.fillingInformation(DataHelper.getSmallCard("en"), DataHelper.getRightMonth("en"), DataHelper.getYear(3), DataHelper.getOwner("en"), DataHelper.getCode("en"));
        buyingOnCredit.wrongFormat();
    }


    @Test
    void creditOneDigitCardNumber() {
        var buyingOnCredit = new BuyingOnCredit();
        buyingOnCredit.fillingInformation(DataHelper.getDigit("en"), DataHelper.getRightMonth("en"), DataHelper.getYear(3), DataHelper.getOwner("en"), DataHelper.getCode("en"));
        buyingOnCredit.wrongFormat();
    }


    @Test
    void creditBigCardNumber() {
        var buyingOnCredit = new BuyingOnCredit();
        buyingOnCredit.fillingInformation(DataHelper.getBigCard("en"), DataHelper.getRightMonth("en"), DataHelper.getYear(3), DataHelper.getOwner("en"), DataHelper.getCode("en"));
        buyingOnCredit.disapproval();
    }


    @Test
    void creditDeclinedCard() {
        var buyingOnCredit = new BuyingOnCredit();
        buyingOnCredit.fillingInformation(DataHelper.getDeclinedCard(), DataHelper.getRightMonth("en"), DataHelper.getYear(3), DataHelper.getOwner("en"), DataHelper.getCode("en"));
        buyingOnCredit.disapproval();
        Assertions.assertEquals("[DECLINED]", DataBase.getStatusForCredit());
    }


    @Test
    void creditLetterCardNumber() {
        var buyingOnCredit = new BuyingOnCredit();
        buyingOnCredit.fillingInformation(DataHelper.getLetters("en"), DataHelper.getRightMonth("en"), DataHelper.getYear(3), DataHelper.getOwner("en"), DataHelper.getCode("en"));
        buyingOnCredit.notFilled();
    }


    @Test
    void creditSymbolsCardNumber() {
        var buyingOnCredit = new BuyingOnCredit();
        buyingOnCredit.fillingInformation(DataHelper.getSymbols(), DataHelper.getRightMonth("en"), DataHelper.getYear(3), DataHelper.getOwner("en"), DataHelper.getCode("en"));
        buyingOnCredit.notFilled();
    }


    @Test
    void creditEmptyCardNumber() {
        var buyingOnCredit = new BuyingOnCredit();
        buyingOnCredit.fillingInformation("", DataHelper.getRightMonth("en"), DataHelper.getYear(3), DataHelper.getOwner("en"), DataHelper.getCode("en"));
        buyingOnCredit.notFilled();
    }


    @Test
    void creditZeroCardNumber() {
        var buyingOnCredit = new BuyingOnCredit();
        buyingOnCredit.fillingInformation(DataHelper.getZero(), DataHelper.getRightMonth("en"), DataHelper.getYear(3), DataHelper.getOwner("en"), DataHelper.getCode("en"));
        buyingOnCredit.disapproval();
        Assertions.assertEquals("[DECLINED]", DataBase.getStatusForCredit());
    }


    @Test
    void creditZeroMonth() {
        var buyingOnCredit = new BuyingOnCredit();
        buyingOnCredit.fillingInformation(DataHelper.getApprovedCard(), DataHelper.getZero(), DataHelper.getYear(3), DataHelper.getOwner("en"), DataHelper.getCode("en"));
        buyingOnCredit.wrongFormat();
    }


    @Test
    void creditNonexistentMonth() {
        var buyingOnCredit = new BuyingOnCredit();
        buyingOnCredit.fillingInformation(DataHelper.getApprovedCard(), DataHelper.getWrongMonth("en"), DataHelper.getYear(3), DataHelper.getOwner("en"), DataHelper.getCode("en"));
        buyingOnCredit.incorrectDate();
    }


    @Test
    void creditSmallMonth() {
        var buyingOnCredit = new BuyingOnCredit();
        buyingOnCredit.fillingInformation(DataHelper.getApprovedCard(), DataHelper.getDigit("en"), DataHelper.getYear(3), DataHelper.getOwner("en"), DataHelper.getCode("en"));
        buyingOnCredit.wrongFormat();
    }


    @Test
    void creditBigMonth() {
        var buyingOnCredit = new BuyingOnCredit();
        buyingOnCredit.fillingInformation(DataHelper.getApprovedCard(), DataHelper.getThreeDigits("en"), DataHelper.getYear(3), DataHelper.getOwner("en"), DataHelper.getCode("en"));
        buyingOnCredit.incorrectDate();
    }


    @Test
    void creditLettersMonth() {
        var buyingOnCredit = new BuyingOnCredit();
        buyingOnCredit.fillingInformation(DataHelper.getApprovedCard(), DataHelper.getLetters("en"), DataHelper.getYear(3), DataHelper.getOwner("en"), DataHelper.getCode("en"));
        buyingOnCredit.notFilled();
    }


    @Test
    void creditSymbolsMonth() {
        var buyingOnCredit = new BuyingOnCredit();
        buyingOnCredit.fillingInformation(DataHelper.getApprovedCard(), DataHelper.getSymbols(), DataHelper.getYear(3), DataHelper.getOwner("en"), DataHelper.getCode("en"));
        buyingOnCredit.notFilled();
    }


    @Test
    void creditNotFilledMonth() {
        var buyingOnCredit = new BuyingOnCredit();
        buyingOnCredit.fillingInformation(DataHelper.getApprovedCard(), "", DataHelper.getYear(3), DataHelper.getOwner("en"), DataHelper.getCode("en"));
        buyingOnCredit.notFilled();
    }


    @Test
    void creditZeroYear() {
        var buyingOnCredit = new BuyingOnCredit();
        buyingOnCredit.fillingInformation(DataHelper.getApprovedCard(), DataHelper.getRightMonth("en"), DataHelper.getZero(), DataHelper.getOwner("en"), DataHelper.getCode("en"));
        buyingOnCredit.expired();
    }


    @Test
    void creditLastYear() {
        var buyingOnCredit = new BuyingOnCredit();
        buyingOnCredit.fillingInformation(DataHelper.getApprovedCard(), DataHelper.getRightMonth("en"), DataHelper.getYear(-1), DataHelper.getOwner("en"), DataHelper.getCode("en"));
        buyingOnCredit.expired();
    }


    @Test
    void creditFarYear() {
        var buyingOnCredit = new BuyingOnCredit();
        buyingOnCredit.fillingInformation(DataHelper.getApprovedCard(), DataHelper.getRightMonth("en"), DataHelper.getYear(6), DataHelper.getOwner("en"), DataHelper.getCode("en"));
        buyingOnCredit.incorrectDate();
    }


    @Test
    void creditDigitYear() {
        var buyingOnCredit = new BuyingOnCredit();
        buyingOnCredit.fillingInformation(DataHelper.getApprovedCard(), DataHelper.getRightMonth("en"), DataHelper.getDigit("en"), DataHelper.getOwner("en"), DataHelper.getCode("en"));
        buyingOnCredit.wrongFormat();
    }


    @Test
    void creditBigYear() {
        var buyingOnCredit = new BuyingOnCredit();
        buyingOnCredit.fillingInformation(DataHelper.getApprovedCard(), DataHelper.getRightMonth("en"), DataHelper.getThreeDigits("en"), DataHelper.getOwner("en"), DataHelper.getCode("en"));
        buyingOnCredit.incorrectDate();
    }

    @Test
    void creditLettersYear() {
        var buyingOnCredit = new BuyingOnCredit();
        buyingOnCredit.fillingInformation(DataHelper.getApprovedCard(), DataHelper.getRightMonth("en"), DataHelper.getLetters("en"), DataHelper.getOwner("en"), DataHelper.getCode("en"));
        buyingOnCredit.notFilled();
    }


    @Test
    void creditSymbolsYear() {
        var buyingOnCredit = new BuyingOnCredit();
        buyingOnCredit.fillingInformation(DataHelper.getApprovedCard(), DataHelper.getRightMonth("en"), DataHelper.getSymbols(), DataHelper.getOwner("en"), DataHelper.getCode("en"));
        buyingOnCredit.notFilled();
    }


    @Test
    void creditNotFilledYear() {
        var buyingOnCredit = new BuyingOnCredit();
        buyingOnCredit.fillingInformation(DataHelper.getApprovedCard(), DataHelper.getRightMonth("en"), "", DataHelper.getOwner("en"), DataHelper.getCode("en"));
        buyingOnCredit.notFilled();
    }


    @Test
    void creditBigOwner() {
        var buyingOnCredit = new BuyingOnCredit();
        buyingOnCredit.fillingInformation(DataHelper.getApprovedCard(), DataHelper.getRightMonth("en"), DataHelper.getYear(3), DataHelper.getBigOwner("en"), DataHelper.getCode("en"));
        buyingOnCredit.overLimit();
    }


    @Test
    void creditDigitOwner() {
        var buyingOnCredit = new BuyingOnCredit();
        buyingOnCredit.fillingInformation(DataHelper.getApprovedCard(), DataHelper.getRightMonth("en"), DataHelper.getYear(3), DataHelper.getDigit("en"), DataHelper.getCode("en"));
        buyingOnCredit.notFilled();
    }


    @Test
    void creditSymbolsOwner() {
        var buyingOnCredit = new BuyingOnCredit();
        buyingOnCredit.fillingInformation(DataHelper.getApprovedCard(), DataHelper.getRightMonth("en"), DataHelper.getYear(3), DataHelper.getSymbols(), DataHelper.getCode("en"));
        buyingOnCredit.notFilled();
    }


    @Test
    void creditOneLetterOwner() {
        var buyingOnCredit = new BuyingOnCredit();
        buyingOnCredit.fillingInformation(DataHelper.getApprovedCard(), DataHelper.getRightMonth("en"), DataHelper.getYear(3), DataHelper.getLetters("en"), DataHelper.getCode("en"));
        buyingOnCredit.wrongFormat();
    }


    @Test
    void creditOnlyNameOwner() {
        var buyingOnCredit = new BuyingOnCredit();
        buyingOnCredit.fillingInformation(DataHelper.getApprovedCard(), DataHelper.getRightMonth("en"), DataHelper.getYear(3), DataHelper.getName("en"), DataHelper.getCode("en"));
        buyingOnCredit.noLastName();
    }


    @Test
    void creditRussianOwner() {
        var buyingOnCredit = new BuyingOnCredit();
        buyingOnCredit.fillingInformation(DataHelper.getApprovedCard(), DataHelper.getRightMonth("en"), DataHelper.getYear(3), DataHelper.getOwner("ru"), DataHelper.getCode("en"));
        buyingOnCredit.wrongFormat();
    }


    @Test
    void creditNotFilledOwner() {
        var buyingOnCredit = new BuyingOnCredit();
        buyingOnCredit.fillingInformation(DataHelper.getApprovedCard(), DataHelper.getRightMonth("en"), DataHelper.getYear(3), "", DataHelper.getCode("en"));
        buyingOnCredit.notFilled();
    }


    @Test
    void creditSmallCode() {
        var buyingOnCredit = new BuyingOnCredit();
        buyingOnCredit.fillingInformation(DataHelper.getApprovedCard(), DataHelper.getRightMonth("en"), DataHelper.getYear(3), DataHelper.getOwner("en"), DataHelper.getDigit("en"));
        buyingOnCredit.wrongFormat();
    }


    @Test
    void creditBigCode() {
        var buyingOnCredit = new BuyingOnCredit();
        buyingOnCredit.fillingInformation(DataHelper.getApprovedCard(), DataHelper.getRightMonth("en"), DataHelper.getYear(3), DataHelper.getOwner("en"), DataHelper.getBigCode("en"));
        buyingOnCredit.disapproval();
    }


    @Test
    void creditLettersCode() {
        var buyingOnCredit = new BuyingOnCredit();
        buyingOnCredit.fillingInformation(DataHelper.getApprovedCard(), DataHelper.getRightMonth("en"), DataHelper.getYear(3), DataHelper.getOwner("en"), DataHelper.getLetters("en"));
        buyingOnCredit.notFilled();
    }


    @Test
    void creditSymbolsCode() {
        var buyingOnCredit = new BuyingOnCredit();
        buyingOnCredit.fillingInformation(DataHelper.getApprovedCard(), DataHelper.getRightMonth("en"), DataHelper.getYear(3), DataHelper.getOwner("en"), DataHelper.getSymbols());
        buyingOnCredit.notFilled();
    }


    @Test
    void creditNotFilledCode() {
        var buyingOnCredit = new BuyingOnCredit();
        buyingOnCredit.fillingInformation(DataHelper.getApprovedCard(), DataHelper.getRightMonth("en"), DataHelper.getYear(3), DataHelper.getOwner("en"), "");
        buyingOnCredit.notFilled();
    }
}