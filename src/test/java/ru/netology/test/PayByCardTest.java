package ru.netology.test;

import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.pages.ChoiceOfPaymentVariantPage;
import ru.netology.pages.PaymentPage;

import static com.codeborne.selenide.Selenide.*;

public class PayByCardTest {

    @Test
    void successfulPayment() {
        var choiceOfPaymentVariantPage = open("http://localhost:8080/",
                ChoiceOfPaymentVariantPage.class);
        choiceOfPaymentVariantPage.payByCard();
        PaymentPage make = new PaymentPage();
        make.makeSuccessfulPayment(DataHelper.getValidInfoForPayByCard());
    }

    @Test
    void declinedPayment() { //падает, issue
        var choiceOfPaymentVariantPage = open("http://localhost:8080/",
                ChoiceOfPaymentVariantPage.class);
        choiceOfPaymentVariantPage.payByCard();
        PaymentPage make = new PaymentPage();
        DataHelper.InfoForPayByCard info = new DataHelper.InfoForPayByCard(DataHelper.getDeclinedCardNumber(),
                DataHelper.getCurrentMonth(), DataHelper.getCurrentYear(), DataHelper.getValidCardOwner(),
                DataHelper.getRandomCvcCode());
        make.makeDeclinedPayment(info);
    }

    @Test
    void randomCardNumberValidFormat() {
        var choiceOfPaymentVariantPage = open("http://localhost:8080/",
                ChoiceOfPaymentVariantPage.class);
        choiceOfPaymentVariantPage.payByCard();
        PaymentPage make = new PaymentPage();
        DataHelper.InfoForPayByCard info = new DataHelper.InfoForPayByCard(DataHelper.getRandomCardNumberValidFormat(),
                DataHelper.getCurrentMonth(), DataHelper.getCurrentYear(), DataHelper.getValidCardOwner(),
                DataHelper.getRandomCvcCode());
        make.makeDeclinedPayment(info);
    }

    @Test
    void randomCardNumberInvalidFormat() {
        var choiceOfPaymentVariantPage = open("http://localhost:8080/",
                ChoiceOfPaymentVariantPage.class);
        choiceOfPaymentVariantPage.payByCard();
        PaymentPage make = new PaymentPage();
        DataHelper.InfoForPayByCard info = new DataHelper.InfoForPayByCard(DataHelper.getRandomCardNumberInvalidFormat(),
                DataHelper.getCurrentMonth(), DataHelper.getCurrentYear(), DataHelper.getValidCardOwner(),
                DataHelper.getRandomCvcCode());
        make.makeInvalidPayment(info);
    }

    @Test
    void emptyCardNumber() {
        var choiceOfPaymentVariantPage = open("http://localhost:8080/",
                ChoiceOfPaymentVariantPage.class);
        choiceOfPaymentVariantPage.payByCard();
        PaymentPage make = new PaymentPage();
        DataHelper.InfoForPayByCard info = new DataHelper.InfoForPayByCard(DataHelper.getEmptyCardNumber(),
                DataHelper.getCurrentMonth(), DataHelper.getCurrentYear(), DataHelper.getValidCardOwner(),
                DataHelper.getRandomCvcCode());
        make.makeInvalidPayment(info);
    }

    @Test
    void lastMonth() {
        var choiceOfPaymentVariantPage = open("http://localhost:8080/",
                ChoiceOfPaymentVariantPage.class);
        choiceOfPaymentVariantPage.payByCard();
        PaymentPage make = new PaymentPage();
        String year = DataHelper.getCurrentYear();
        if (Integer.valueOf(DataHelper.getCurrentMonth()) == 1) {
            year = String.valueOf((Integer.valueOf(DataHelper.getCurrentYear()) - 1));
        }
        DataHelper.InfoForPayByCard info = new DataHelper.InfoForPayByCard(DataHelper.getApprovedCardNumber(),
                DataHelper.getPastMonth(), year, DataHelper.getValidCardOwner(), DataHelper.getRandomCvcCode());
        make.makePaymentInvalidMonth(info);
    }

    @Test
    void monthPlus12() {
        var choiceOfPaymentVariantPage = open("http://localhost:8080/",
                ChoiceOfPaymentVariantPage.class);
        choiceOfPaymentVariantPage.payByCard();
        PaymentPage make = new PaymentPage();
        String invalidMonth = String.valueOf(Integer.valueOf(DataHelper.getCurrentMonth()) + 12);
        DataHelper.InfoForPayByCard info = new DataHelper.InfoForPayByCard(DataHelper.getApprovedCardNumber(),
                invalidMonth, DataHelper.getCurrentYear(), DataHelper.getValidCardOwner(), DataHelper.getRandomCvcCode());
        make.makePaymentInvalidMonth(info);
    }

    @Test
    void emptyMonth() {
        var choiceOfPaymentVariantPage = open("http://localhost:8080/",
                ChoiceOfPaymentVariantPage.class);
        choiceOfPaymentVariantPage.payByCard();
        PaymentPage make = new PaymentPage();
        DataHelper.InfoForPayByCard info = new DataHelper.InfoForPayByCard(DataHelper.getApprovedCardNumber(),
                DataHelper.getEmptyMonth(), DataHelper.getCurrentYear(), DataHelper.getValidCardOwner(),
                DataHelper.getRandomCvcCode());
        make.makePaymentEmptyMonth(info);
    }

    @Test
    void pastYear() {
        var choiceOfPaymentVariantPage = open("http://localhost:8080/",
                ChoiceOfPaymentVariantPage.class);
        choiceOfPaymentVariantPage.payByCard();
        PaymentPage make = new PaymentPage();
        DataHelper.InfoForPayByCard info = new DataHelper.InfoForPayByCard(DataHelper.getApprovedCardNumber(),
                DataHelper.getCurrentMonth(), DataHelper.getPastYear(), DataHelper.getValidCardOwner(),
                DataHelper.getRandomCvcCode());
        make.makePaymentInvalidYear(info);
    }
    @Test
    void emptyYear() {
        var choiceOfPaymentVariantPage = open("http://localhost:8080/",
                ChoiceOfPaymentVariantPage.class);
        choiceOfPaymentVariantPage.payByCard();
        PaymentPage make = new PaymentPage();
        DataHelper.InfoForPayByCard info = new DataHelper.InfoForPayByCard(DataHelper.getApprovedCardNumber(),
                DataHelper.getCurrentMonth(), DataHelper.getEmptyYear(), DataHelper.getValidCardOwner(),
                DataHelper.getRandomCvcCode());
        make.makePaymentEmptyYear(info);
    }

    @Test
    void cardOwnerPlusSpecSymbol() { //падает, issue
        var choiceOfPaymentVariantPage = open("http://localhost:8080/",
                ChoiceOfPaymentVariantPage.class);
        choiceOfPaymentVariantPage.payByCard();
        PaymentPage make = new PaymentPage();
        DataHelper.InfoForPayByCard info = new DataHelper.InfoForPayByCard(DataHelper.getApprovedCardNumber(),
                DataHelper.getCurrentMonth(), DataHelper.getCurrentYear(), DataHelper.getCardOwnerSpecSymbol(),
                DataHelper.getRandomCvcCode());
        make.makePaymentInvalidCardOwner(info);
    }

    @Test
    void cardOwnerPlusNumber() { //падает, issue
        var choiceOfPaymentVariantPage = open("http://localhost:8080/",
                ChoiceOfPaymentVariantPage.class);
        choiceOfPaymentVariantPage.payByCard();
        PaymentPage make = new PaymentPage();
        DataHelper.InfoForPayByCard info = new DataHelper.InfoForPayByCard(DataHelper.getApprovedCardNumber(),
                DataHelper.getCurrentMonth(), DataHelper.getCurrentYear(), DataHelper.getInvalidCardOwnerByNumber(),
                DataHelper.getRandomCvcCode());
        make.makePaymentInvalidCardOwner(info);
    }

    @Test
    void cardOwnerByRus() { //падает, issue
        var choiceOfPaymentVariantPage = open("http://localhost:8080/",
                ChoiceOfPaymentVariantPage.class);
        choiceOfPaymentVariantPage.payByCard();
        PaymentPage make = new PaymentPage();
        DataHelper.InfoForPayByCard info = new DataHelper.InfoForPayByCard(DataHelper.getApprovedCardNumber(),
                DataHelper.getCurrentMonth(), DataHelper.getCurrentYear(), DataHelper.getInvalidCardOwnerByRus(),
                DataHelper.getRandomCvcCode());
        make.makePaymentInvalidCardOwner(info);
    }

    @Test
    void emptyCardOwner() {
        var choiceOfPaymentVariantPage = open("http://localhost:8080/",
                ChoiceOfPaymentVariantPage.class);
        choiceOfPaymentVariantPage.payByCard();
        PaymentPage make = new PaymentPage();
        DataHelper.InfoForPayByCard info = new DataHelper.InfoForPayByCard(DataHelper.getApprovedCardNumber(),
                DataHelper.getCurrentMonth(), DataHelper.getCurrentYear(), DataHelper.getEmptyCardOwner(),
                DataHelper.getRandomCvcCode());
        make.makePaymentEmptyCardOwner(info);
    }

    @Test
    void invalidCvcCode() {
        var choiceOfPaymentVariantPage = open("http://localhost:8080/",
                ChoiceOfPaymentVariantPage.class);
        choiceOfPaymentVariantPage.payByCard();
        PaymentPage make = new PaymentPage();
        DataHelper.InfoForPayByCard info = new DataHelper.InfoForPayByCard(DataHelper.getApprovedCardNumber(),
                DataHelper.getCurrentMonth(), DataHelper.getCurrentYear(), DataHelper.getValidCardOwner(),
                DataHelper.getInvalidCvcCode());
        make.makePaymentInvalidCvcCode(info);
    }

    @Test
    void emptyCvcCode() { //падает, issue
        var choiceOfPaymentVariantPage = open("http://localhost:8080/",
                ChoiceOfPaymentVariantPage.class);
        choiceOfPaymentVariantPage.payByCard();
        PaymentPage make = new PaymentPage();
        DataHelper.InfoForPayByCard info = new DataHelper.InfoForPayByCard(DataHelper.getApprovedCardNumber(),
                DataHelper.getCurrentMonth(), DataHelper.getCurrentYear(), DataHelper.getValidCardOwner(),
                DataHelper.getEmptyCvcCode());
        make.makePaymentEmptyCvcCode(info);
    }
}
