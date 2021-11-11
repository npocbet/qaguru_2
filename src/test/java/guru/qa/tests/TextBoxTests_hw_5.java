package guru.qa.tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

public class TextBoxTests_hw_5  extends TestBase {

    @Test
    void fillFormTest(){
        // first name last name
        automationPracticePage.openPage()
                .typeFirstName("Svjato")
                .typeLastName("Kravts");

        //e-mail
        automationPracticePage.typeEmail("aaa@aa.aa");
        //gender
        automationPracticePage.pickGender("Male");
        // phone number
        automationPracticePage.typePhoneNumber("89990999999");
        //birth date
        automationPracticePage.calendar.setDate("13", "September", "1988");
        //some subjects
       $("#subjectsInput").scrollTo();
        automationPracticePage.pickSubjects(new String[]{"English", "Computer Science"});

        //some hobbies
        automationPracticePage.pickHobbies(new String[]{"Sports", "Reading", "Music"});
        // file upload
        automationPracticePage.uploadPicture();
        //address
        automationPracticePage.enterAdress("The Earth");
        //state & city
        automationPracticePage.setStateAndCity("Rajasthan", "Jaipur");
        automationPracticePage.clickSubmit();
        // assertions
        automationPracticePage
                .checkResultsValue("Student Name", "Svjato Kravts")
                .checkResultsValue("Student Email", "aaa@aa.aa")
                .checkResultsValue("Gender", "Male")
                .checkResultsValue("Mobile", "8999099999")
                .checkResultsValue("Date of Birth", "13 September,1988")
                .checkResultsValue("Subjects", "English, Computer Science")
                .checkResultsValue("Hobbies", "Sports, Reading, Music")
                .checkResultsValue("Picture", "image.jpg")
                .checkResultsValue("Address", "The Earth")
                .checkResultsValue("State and City", "Rajasthan Jaipur")
        ;
    }
}
