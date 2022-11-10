package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PaymentPage {

    private final String successMsg = "Успешно Операция одобрена Банком.";
    private final String bankFailureMsg = "Ошибка Ошибка! Банк отказал в проведении операции.";

    //    private SelenideElement heading = $(".heading").find(String.valueOf(text("Оплата по карте")));
//    private SelenideElement forPayButton = $("button").find(String.valueOf(text("Купить")));
    private SelenideElement cardNumberField = $x("//*[@id='root']/div/form/fieldset/div[1]/span/span/span[1]");
//    private SelenideElement cardNumberField = $("input__inner").find(String.valueOf(text("Номер карты")));
//    private SelenideElement cardNumberField = $("input").shouldHave(text("Номер карты"));
//    private SelenideElement cardNumberField = $(byText("Номер карты"));
    //    private SelenideElement cardNumberField = $(".input").find(String.valueOf(exactText("Номер карты")));
    private SelenideElement monthField = $(byText("Месяц"));
    //    private SelenideElement monthField = $(".form-field").find(String.valueOf(text("Месяц")));
    private SelenideElement yearField = $(byText("Год"));
    //    private SelenideElement yearField = $(".form-field").find(String.valueOf(text("Год")));
    private SelenideElement cardOwnerField = $(byText("Владелец"));
    //    private SelenideElement cardOwnerField = $(".form-field").find(String.valueOf(text("Владелец")));
    private SelenideElement cvcCodeField = $(byText("CVC/CVV"));
    //    private SelenideElement cvcCodeField = $(".form-field").find(String.valueOf(text("CVC/CVV")));
    private SelenideElement payButton = $(byText("Продолжить"));
    //    private SelenideElement payButton = $("button").find(String.valueOf(text("Продолжить")));
    private SelenideElement successNotification = $(byText(successMsg));
    //    private SelenideElement successNotification = $x(successMsg);
    private SelenideElement bankFailureNotification = $(byText(bankFailureMsg));
//    private SelenideElement bankFailureNotification = $x(bankFailureMsg);

//    public PaymentPage() {
//        heading.shouldBe(visible);
//    }

    public PaymentPage makeSuccessfulPayment(DataHelper.InfoForPayByCard info) {
        successfulPayment(info);
        return new PaymentPage();
    }


    public void successfulPayment(DataHelper.InfoForPayByCard info) {
//        forPayButton.click();
        cardNumberField.setValue(info.getCardNumber());
        monthField.setValue(info.getMonth());
        yearField.setValue(info.getYear());
        cardOwnerField.setValue(info.getCardOwner());
        cvcCodeField.setValue(info.getCvcCode());
        payButton.click();
        successNotification.shouldBe(visible);
//        return new PaymentPage();
    }

//    public VerificationPage validLogin(DataHelper.AuthInfo info) {
//        loginField.setValue(info.getLogin());
//        passwordField.setValue(info.getPassword());
//        loginButton.click();
//        return new VerificationPage();
//    }
//
//    public LoginPage invalidLogin(DataHelper.AuthInfo info) {
//        loginField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME, Keys.BACK_SPACE));
//        loginField.setValue(info.getLogin());
//        passwordField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME, Keys.BACK_SPACE));
//        passwordField.setValue(info.getPassword());
//        loginButton.click();
//        return new LoginPage();
//    }
//
//    public void errorNotificationWhenMore3EntryValidLoginAndInvalidPassword() {
//        errorNotification.shouldHave(text(errorNotificationWhenMore3EntryValidLoginAndInvalidPassword)).shouldBe(visible);
//    }
}
