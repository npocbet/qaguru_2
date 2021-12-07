package guru.qa.tests;

import com.codeborne.selenide.Configuration;
import guru.qa.config.CredentialsConfig;
import guru.qa.helpers.Attach;
import guru.qa.pages.AutomationPracticePage;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import static java.lang.String.format;

public class TestBase_hw_11 {

    AutomationPracticePage automationPP = new AutomationPracticePage();
    public static CredentialsConfig credentials =
            ConfigFactory.create(CredentialsConfig.class);

    @BeforeAll
    static void setup(){

        String login = credentials.login();
        String password = credentials.password();
        String url = System.getProperty("url", "selenoid.autotests.cloud/wd/hub/");

        Configuration.remote = format("https://%s:%s@%s",login, password, url);
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
        Attach.addVideo();
    }
}