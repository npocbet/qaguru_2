package guru.qa.pages;

//import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import guru.qa.pages.components.CalendarComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class Automation_practice_page {
    // locators & elements
    private final String FORM_TITLE = "Student Registration Form";
    private SelenideElement
            formTitle = $(".practice-form-wrapper"),
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            userEmailInput = $("#userEmail"),
            genderRadio = $("[for='gender-radio-1']"),
            subjects = $(".subjects-auto-complete__value-container"),
            subjectsInput = $("#subjectsInput"),
            phoneNumberInput = $("#userNumber"),
            hobbies_checkbox_1 = $("[for=hobbies-checkbox-1]"),
            hobbies_checkbox_2 = $("[for=hobbies-checkbox-2]"),
            hobbies_checkbox_3 = $("[for=hobbies-checkbox-3]"),
            uploadPicture = $("#uploadPicture"),
            currentAddress = $("#currentAddress"),
            state = $("#state"),
            state_input = $("#react-select-3-input"),
            city = $("#city"),
            city_input = $("#react-select-4-input"),
            submit_button = $("#submit"),
            resultsTable = $(".table-responsive");
    public CalendarComponent calendar = new CalendarComponent();

    // actions
    public Automation_practice_page openPage() {
        open("https://demoqa.com/automation-practice-form");
        formTitle.shouldHave(text(FORM_TITLE));

        return this;
    }

    public Automation_practice_page typeFirstName(String value) {
        firstNameInput.setValue(value);
//        firstNameInput.shouldHave(Condition.value(value));

        return this;
    }

    public void typeLastName(String value) {
        lastNameInput.setValue(value);
    }

    public void typeEmail(String value) {
        userEmailInput.setValue(value);
    }

    public void pickGender(String value) {
        genderRadio.click();
    }

    public void typePhoneNumber(String value) { phoneNumberInput.setValue(value); }


    public Automation_practice_page checkResultsValue(String key, String value) {
        resultsTable.$(byText(key))
                .parent().shouldHave(text(value));

        return this;
    }
}
