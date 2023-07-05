package data;

import lombok.Value;

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

    public static CardInfo getApprovedCard() {
        return new CardInfo("4444 4444 4444 4441", )
    }

}
