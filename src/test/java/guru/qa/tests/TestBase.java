package guru.qa.tests;

import com.codeborne.selenide.Configuration;
import guru.qa.pages.AutomationPracticePage;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    AutomationPracticePage automationPP = new AutomationPracticePage();
//      selenide:5.25.0 only
//    @BeforeAll
//    static void beforeAll() {
//        Configuration.startMaximized = true;
//    }
}