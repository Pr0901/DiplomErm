package data;

import lombok.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.github.javafaker.Faker;

import java.util.Locale;


public class DataHelper {


    private DataHelper() {
    }

    @Value
    public static class CardInfo {
        String cardNumber;
        String month;
        String year;
        String owner;
        String cvc;
    }

    @Value
    public static class AmountSum {
        String amount;
    }

    public static String getApprovedCard() {
        return "4444 4444 4444 4441";
    }

    public static String getDeclinedCard() {
        return "4444 4444 4444 4442";
    }

    public static String getBigCard(String locale) {
        var faker = new Faker(new Locale(locale));
        return faker.numerify("#################");
    }

    public static String getSmallCard(String locale) {
        var faker = new Faker(new Locale(locale));
        return faker.numerify("###############");
    }

    public static String getRightMonth(String locale) {
        var faker = new Faker(new Locale(locale));
        var plusMonth = faker.numerify("#");
        return LocalDate.now().plusMonths(Long.parseLong(plusMonth)).format(DateTimeFormatter.ofPattern("MM"));
    }

    ;

    public static String getWrongMonth(String locale) {
        var faker = new Faker(new Locale(locale));
        return faker.numerify("2#");
    }

    ;


    public static String getYear(int shift) {
        return LocalDate.now().plusYears(shift).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getOwner(String locale) {
        var faker = new Faker(new Locale(locale));
        return faker.name().firstName() + " " + faker.name().lastName();
    }

    public static String getOwnerWithApostrophe(String locale) {
        var faker = new Faker(new Locale(locale));
        return faker.name().firstName() + " " + faker.letterify("?") + "'" + faker.name().lastName();
    }

    public static String getOwnerWithHyphen(String locale) {
        var faker = new Faker(new Locale(locale));
        return faker.name().firstName() + "-" + faker.name().firstName() + " " + faker.name().lastName();
    }

    public static String getBorderlineOwner(String locale) {
        var faker = new Faker(new Locale(locale));
        return faker.letterify("B???????????????????????? I???????????????????????");
    }

    public static String getBigOwner(String locale) {
        var faker = new Faker(new Locale(locale));
        return faker.letterify("?????????????????????????? ????????????????????????");
    }

    public static String getName(String locale) {
        var faker = new Faker(new Locale(locale));
        return faker.name().firstName();
    }


    public static String getCode(String locale) {
        var faker = new Faker(new Locale(locale));
        return faker.numerify("###");
    }

    public static String getBigCode(String locale) {
        var faker = new Faker(new Locale(locale));
        return faker.numerify("####");
    }

    public static String getLetters(String locale) {
        var faker = new Faker(new Locale(locale));
        return faker.letterify("?");
    }

    ;

    public static String getDigit(String locale) {
        var faker = new Faker(new Locale(locale));
        return faker.numerify("#");
    }

    ;

    public static String getSymbols() {
        return "+*";
    }

    ;

    public static String getThreeDigits(String locale) {
        var faker = new Faker(new Locale(locale));
        return faker.numerify("4##");
    }

    ;

    public static String getZero() {
        return ("0000000000000000");
    }

    ;

    public static String getSum() {
        return "45 000";
    }


}
