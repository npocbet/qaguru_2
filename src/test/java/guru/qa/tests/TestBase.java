package guru.qa.tests;

import com.codeborne.selenide.Configuration;
import guru.qa.pages.Automation_practice_page;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    Automation_practice_page automationPracticePage = new Automation_practice_page();
//      selenide:5.25.0 only
//    @BeforeAll
//    static void beforeAll() {
//        Configuration.startMaximized = true;
//    }
}