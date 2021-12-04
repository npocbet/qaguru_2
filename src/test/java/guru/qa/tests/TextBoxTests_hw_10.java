package guru.qa.tests;

import com.github.javafaker.Faker;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class TextBoxTests_hw_10 extends TestBase {

    private Faker faker = new Faker(new Locale("ru"));
    private Faker faker2 = new Faker();

    private String firstName = faker.name().firstName();
    private String lastName = faker.name().lastName();
    private String email = faker2.internet().emailAddress();
    private Random rand = new Random();
    private String[] genders = new String[]{"Male", "Female", "Other"};
    private int randomGender = rand.nextInt(genders.length);
    private String phone_number = faker.phoneNumber().subscriberNumber(10);
    private String streetAddress = faker.address().streetAddress();
    private String[] months = new String[]{"January", "February", "March", "April", "May", "June", "July", "August",
                                    "September", "October", "November", "December"};
    private Date birthday = faker.date().birthday();
    private int stepYear = rand.nextInt(17);
    private int startYear = 2004;

    @Test
    @Owner("npocbet(kravtsovsv)")
    @Feature("Регистрация")
    @Story("Регистриция пользователя, базовые анкетные данные")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Тест demoqa, заполнение анкеты. Лямбда шаги через step (name, () -> {})")
    @Link(name = "demoqa", url = "https://demoqa.com/automation-practice-form")
    void fillFormTest(){
        // first name last name
        step("Открываем страницу формы", () -> {
            open("https://demoqa.com/automation-practice-form");
        });

        step("Проверяем заголовок формы", () -> {
            $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        });

        step("Вводим имя и фамилию", () -> {
            $("#firstName").setValue(firstName);
            $("#lastName").setValue(lastName);
        });

        step("Вводим email", () -> {
            $("#userEmail").setValue(email);
        });

        step("Вводим пол", () -> {
            String value = genders[randomGender];
            if (Objects.equals(value, "Male")) {
                $("[for='gender-radio-1']").click();
            }
            if (Objects.equals(value, "Female")) {
                $("[for='gender-radio-2']").click();
            }
            if (Objects.equals(value, "Other")) {
                $("[for='gender-radio-3']").click();
            }
        });

        step("Вводим номер телефона", () -> {
            $("#userNumber").setValue(phone_number);
        });

        Calendar cal = Calendar.getInstance();
        cal.setTime(birthday);

        step("Вводим дату рождения", () -> {
            $("#dateOfBirthInput").click();
            $(".react-datepicker__month-select").selectOption(months[cal.get(Calendar.MONTH)]);
            $(".react-datepicker__year-select").selectOption(String.valueOf(startYear + stepYear));
            $(".react-datepicker__day--0" + cal.get(Calendar.DAY_OF_MONTH) +
                    ":not(.react-datepicker__day--outside-month)").click();
        });

        step("Вводим интересы", () -> {
            $("#subjectsInput").scrollTo();
            $(".subjects-auto-complete__value-container").click();
            $("#subjectsInput").setValue("English").pressEnter();
            $("#subjectsInput").setValue("Computer Science").pressEnter();
        });

        step("Вводим хобби", () -> {
            $("[for=hobbies-checkbox-1]").click();
            $("[for=hobbies-checkbox-2]").click();
            $("[for=hobbies-checkbox-3]").click();
        });

            step("Загружаем картинку", () -> {
            $("#uploadPicture").uploadFromClasspath("img/image.png");
        });

        step("Вводим адрес", () -> {
            $("#currentAddress").setValue(streetAddress);
        });

        step("Вводим регион/город", () -> {
            $("#state").click();
            $("#react-select-3-input").setValue("Rajasthan").pressEnter();
            $("#city").click();
            $("#react-select-4-input").setValue("Jaipur").pressEnter();
        });

        step("Жмем отправить", () -> {
            $("#submit").click();
        });

        step("Проверяем, что введенные данные отобразились в результирующем окне", () -> {
            $(".table-responsive").$(byText("Student Name"))
                    .parent().shouldHave(text(firstName + " " + lastName));
            $(".table-responsive").$(byText("Student Email"))
                    .parent().shouldHave(text(email));
            $(".table-responsive").$(byText("Gender"))
                        .parent().shouldHave(text(genders[randomGender]));
            $(".table-responsive").$(byText("Mobile"))
                        .parent().shouldHave(text(phone_number));
            $(".table-responsive").$(byText("Date of Birth"))
                        .parent().shouldHave(text(cal.get(Calendar.DAY_OF_MONTH) + " " +
                            months[cal.get(Calendar.MONTH)] + "," + (startYear + stepYear)));
            $(".table-responsive").$(byText("Subjects"))
                        .parent().shouldHave(text("English, Computer Science"));
            $(".table-responsive").$(byText("Hobbies"))
                        .parent().shouldHave(text("Sports, Reading, Music"));
            $(".table-responsive").$(byText("Picture"))
                        .parent().shouldHave(text("img/image.png"));
            $(".table-responsive").$(byText("Address"))
                        .parent().shouldHave(text(streetAddress));
            $(".table-responsive").$(byText("State and City"))
                        .parent().shouldHave(text("Rajasthan Jaipur"));
        });

    }
}
