package guru.qa.tests;

import com.codeborne.selenide.Configuration;
import guru.qa.helpers.Attach;
import guru.qa.pages.AutomationPracticePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

public class TestBase {

    AutomationPracticePage automationPP = new AutomationPracticePage();

    @BeforeAll
    static void setup(){

        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub/";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);

        Configuration.browserCapabilities = capabilities;
    }

    @AfterEach
    public void tearDown(){
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
    }
}