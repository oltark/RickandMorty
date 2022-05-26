package Hooks;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;

public class Hooks {

    @BeforeAll
    public static void allureSubThread() {


        String listenerName = "allureSelenide";
        if(!(SelenideLogger.hasListener(listenerName)))
            SelenideLogger.addListener(listenerName, new AllureSelenide().screenshots(true).savePageSource(false));

    }
}
