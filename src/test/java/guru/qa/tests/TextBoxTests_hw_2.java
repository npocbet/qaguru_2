package guru.qa.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

// import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TextBoxTests_hw_2 extends TestBase{

//    @BeforeAll
//    static void beforeAll() {
//        Configuration.startMaximized = true;
//    }

    @Test
    void fillFormTest(){
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        open("https://demoqa.com/automation-practice-form");
        // first name
        $("[id=firstName]").setValue("Svjato");
        //last name
        $("[id=lastName]").setValue("Kravts");
        //e-mail
        $("#userEmail").setValue("aaa@aa.aa");
        //gender
        $("[for='gender-radio-1']").click();
        // phone number
        $("#userNumber").setValue("89990999999");

        //birth date
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOptionByValue("8");
        $(".react-datepicker__year-select").selectOptionByValue("1988");
        $(".react-datepicker__day--013").click();

        //some subjects
        $(".subjects-auto-complete__value-container").click();
        $("#subjectsInput").setValue("English").pressEnter();
        $("#subjectsInput").setValue("Computer Science").pressEnter();
        //some hobbies
        $("[for=hobbies-checkbox-1]").click();
        $("[for=hobbies-checkbox-2]").click();
        $("[for=hobbies-checkbox-3]").click();
        // file upload
        // $("#uploadPicture").uploadFile(new File("src/test/resources/image.png"));
        // another way to upload the file (нужно указывать только имя файла относительно resources)
        $("#uploadPicture").uploadFromClasspath("img/image.png");
        //address
        $("#currentAddress").setValue("The Earth");
        //state
        $("#state").click();
        $("#react-select-3-input").setValue("Rajasthan").pressEnter();
        //city
        $("#city").click();
        $("#react-select-4-input").setValue("Jaipur").pressEnter();

        $("#submit").scrollTo().click();

        // assertions

//        $(".table > tbody> :nth-child(1) > :nth-child(2)").shouldHave(text("Svjato Kravts"));
//        $(".table > tbody> :nth-child(2) > :nth-child(2)").shouldHave(text("aaa@aa.aa"));
//        $(".table > tbody> :nth-child(3) > :nth-child(2)").shouldHave(text("Male"));
//        $(".table > tbody> :nth-child(4) > :nth-child(2)").shouldHave(text("8999099999"));
//        $(".table > tbody> :nth-child(5) > :nth-child(2)").shouldHave(text("13 September,1988"));
//        $(".table > tbody> :nth-child(6) > :nth-child(2)").shouldHave(text("English, Computer Science"));
//        $(".table > tbody> :nth-child(7) > :nth-child(2)").shouldHave(text("Sports, Reading, Music"));
//        $(".table > tbody> :nth-child(8) > :nth-child(2)").shouldHave(text("image.png"));
//        $(".table > tbody> :nth-child(9) > :nth-child(2)").shouldHave(text("The Earth"));
//        $(".table > tbody> :nth-child(10) > :nth-child(2)").shouldHave(text("Rajasthan Jaipur"));
        // более простой путь
        $(".table").shouldHave(text("Svjato Kravts"), text("aaa@aa.aa"), text("Male"), text("8999099999"),
                text("13 September,1988"), text("English, Computer Science"), text("Sports, Reading, Music"),
                text("image.png"), text("The Earth"), text("Rajasthan Jaipur"));
    }
}
