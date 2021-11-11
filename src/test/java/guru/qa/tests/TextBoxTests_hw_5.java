package guru.qa.tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import static com.codeborne.selenide.Selenide.*;

public class TextBoxTests_hw_5  extends TestBase {

    Faker faker = new Faker(new Locale("ru"));
    Faker faker2 = new Faker();
//    Faker faker = new Faker();

    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String email = faker2.internet().emailAddress();
    Random rand = new Random();
    String[] genders = new String[]{"Male", "Female", "Other"};
    int randomGender = rand.nextInt(3);
    String phone_number = faker.phoneNumber().subscriberNumber(10);
    String streetAddress = faker.address().streetAddress();
    String[] months = new String[]{"January", "February", "March", "April", "May", "June", "July", "August",
                                    "September", "October", "November", "December"};
    Date birthday = faker.date().birthday();
    int year = rand.nextInt(17);
    int START_YEAR = 2004;

    @Test
    void fillFormTest(){
        // first name last name
        automationPracticePage.openPage()
                .typeFirstName(firstName)
                .typeLastName(lastName);

        //e-mail
        automationPracticePage.typeEmail(email);
        //gender
        automationPracticePage.pickGender(genders[randomGender]);
        // phone number
        automationPracticePage.typePhoneNumber(phone_number);
        //birth date

        Calendar cal = Calendar.getInstance();
        cal.setTime(birthday);

        automationPracticePage.calendar.setDate(String.valueOf(cal.get(Calendar.DAY_OF_MONTH)),
                months[cal.get(Calendar.MONTH)], String.valueOf(START_YEAR + year));
        //some subjects
       $("#subjectsInput").scrollTo();
        automationPracticePage.pickSubjects(new String[]{"English", "Computer Science"});

        //some hobbies
        automationPracticePage.pickHobbies(new String[]{"Sports", "Reading", "Music"});
        // file upload
        automationPracticePage.uploadPicture();
        //address
        automationPracticePage.enterAdress(streetAddress);
        //state & city
        automationPracticePage.setStateAndCity("Rajasthan", "Jaipur");

        automationPracticePage.clickSubmit();

        // assertions
        automationPracticePage
                .checkResultsValue("Student Name", firstName + " " + lastName)
                .checkResultsValue("Student Email", email)
                .checkResultsValue("Gender", genders[randomGender])
                .checkResultsValue("Mobile", phone_number)
                .checkResultsValue("Date of Birth", cal.get(Calendar.DAY_OF_MONTH) + " " +
                        months[cal.get(Calendar.MONTH)] + "," + (START_YEAR + year))
                .checkResultsValue("Subjects", "English, Computer Science")
                .checkResultsValue("Hobbies", "Sports, Reading, Music")
                .checkResultsValue("Picture", "image.jpg")
                .checkResultsValue("Address", streetAddress)
                .checkResultsValue("State and City", "Rajasthan Jaipur")
        ;
    }
}
