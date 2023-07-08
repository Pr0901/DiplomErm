package page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class MainPage {

    private SelenideElement buyButton = $("//span[contains(text(), 'Купить')]");
    private SelenideElement buyInCreditButton = $("//span[contains(text(), 'Купить в кредит')]");
    private SelenideElement cardNumberField = $("//span[contains(text(), 'Номер карты')]");
    private SelenideElement monthField = $("//span[contains(text(), 'Месяц')]");
    private SelenideElement yearField = $("//span[contains(text(), 'Год')]");
    private SelenideElement ownerField = $("//span[contains(text(), 'Владелец')]");
    private SelenideElement cvvField = $("//span[contains(text(), 'CVC/CVV')]");






}
