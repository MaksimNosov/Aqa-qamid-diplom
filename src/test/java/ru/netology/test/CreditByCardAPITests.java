package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.APIHelper;
import ru.netology.data.DataHelper;
import ru.netology.pages.ChoiceOfPaymentVariantPage;

import static com.codeborne.selenide.Selenide.*;

public class CreditByCardAPITests {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setUp() {
        var choiceOfPaymentVariantPage = open("http://localhost:8080/",
                ChoiceOfPaymentVariantPage.class);
        choiceOfPaymentVariantPage.payByCard();
    }

    @Test
    void successfulCredit() {
        APIHelper.shouldCreditByApprovedCard(DataHelper.getValidInfoForPayByCard());
    }

    @Test
    void declinedCredit() {
        DataHelper.InfoForPayByCard info = new DataHelper.InfoForPayByCard(DataHelper.getDeclinedCardNumber(),
                DataHelper.getCurrentMonth(), DataHelper.getCurrentYear(), DataHelper.getValidCardOwner(),
                DataHelper.getRandomCvcCode());
        APIHelper.shouldCreditByDeclinedCard(info);
    }

    @Test
    void randomCardNumberValidFormatCredit() {
        DataHelper.InfoForPayByCard info = new DataHelper.InfoForPayByCard(DataHelper.getRandomCardNumberValidFormat(),
                DataHelper.getCurrentMonth(), DataHelper.getCurrentYear(), DataHelper.getValidCardOwner(),
                DataHelper.getRandomCvcCode());
        APIHelper.shouldCreditByRandomCard(info);
    }
}