package Hooks;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.jupiter.api.BeforeAll;

public class Hooks {

    @BeforeAll
    public static void allureSubThread() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

        String listenerName = "allureSelenide";
        if(!(SelenideLogger.hasListener(listenerName)))
            SelenideLogger.addListener(listenerName, new AllureSelenide().screenshots(true).savePageSource(false));

    }
}
