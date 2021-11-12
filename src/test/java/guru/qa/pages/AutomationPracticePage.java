package guru.qa.pages;

import com.codeborne.selenide.SelenideElement;
import guru.qa.pages.components.CalendarComponent;

import java.util.Objects;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AutomationPracticePage  {
    // locators & elements
    private final String FORM_TITLE = "Student Registration Form";
    private SelenideElement
            formTitle = $(".practice-form-wrapper"),
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            userEmailInput = $("#userEmail"),
            genderRadioM = $("[for='gender-radio-1']"),
            genderRadioF = $("[for='gender-radio-2']"),
            genderRadioO = $("[for='gender-radio-3']"),
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
    public AutomationPracticePage openPage() {
        open("https://demoqa.com/automation-practice-form");
        formTitle.shouldHave(text(FORM_TITLE));

        return this;
    }

    public AutomationPracticePage  typeFirstName(String firstName) {
        firstNameInput.setValue(firstName);
//        firstNameInput.shouldHave(Condition.value(value));

        return this;
    }

    public void typeLastName(String lastName) {
        lastNameInput.setValue(lastName);
    }

    public void typeEmail(String eMail) {
        userEmailInput.setValue(eMail);
    }

    public void pickGender(String value) {
        if (Objects.equals(value, "Male")) {
            genderRadioM.click();
        }
        if (Objects.equals(value, "Female")) {
            genderRadioF.click();
        }
        if (Objects.equals(value, "Other")) {
            genderRadioO.click();
        }
    }

    public void typePhoneNumber(String number) {
        phoneNumberInput.setValue(number);
    }

    public void pickSubjects(String[] value) {
        subjects.click();

        for (String s : value) {
            subjectsInput.setValue(s).pressEnter();
        }
    }

    public void pickHobbies(String[] value) {
        for (String s : value) {
            if (Objects.equals(s, "Sports")) {
                hobbies_checkbox_1.click();
            }
            if (Objects.equals(s, "Reading")) {
                hobbies_checkbox_2.click();
            }
            if (Objects.equals(s, "Music")) {
                hobbies_checkbox_3.click();
            }
        }
    }

    public void uploadPicture(String path) {
        uploadPicture.uploadFromClasspath(path);
    }

    public void enterAdress(String address){
        currentAddress.setValue(address);
    }

    public void setStateAndCity(String stateValue, String cityValue){
        state.click();
        state_input.setValue(stateValue).pressEnter();
        city.click();
        city_input.setValue(cityValue).pressEnter();
    }

    public void clickSubmit(){
        submit_button.click();
    }

    public AutomationPracticePage  checkResultsValue(String key, String value) {
        resultsTable.$(byText(key))
                .parent().shouldHave(text(value));

        return this;
    }
}
