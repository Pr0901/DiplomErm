package test;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class BuyTest {

    private SelenideElement buyButton = $x("//span[contains(text(), 'Купить')]");
    private SelenideElement cardNumberField = $x("//span[contains(text(), 'Номер карты')]");
    private SelenideElement monthField = $x("//span[contains(text(), 'Месяц')]");
    private SelenideElement yearField = $x("//span[contains(text(), 'Год')]");
    private SelenideElement ownerField = $x("//span[contains(text(), 'Владелец')]");
    private SelenideElement cvvField = $x("//span[contains(text(), 'CVC/CVV')]");
}
