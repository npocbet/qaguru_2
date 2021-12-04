package guru.qa.tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import static com.codeborne.selenide.Selenide.*;

public class TextBoxTests_hw_5  extends TestBase {

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
    void fillFormTest(){
        // first name last name
        automationPP.openPage()
                .typeFirstName(firstName)
                .typeLastName(lastName);

        //e-mail
        automationPP.typeEmail(email);
        //gender
        automationPP.pickGender(genders[randomGender]);
        // phone number
        automationPP.typePhoneNumber(phone_number);
        //birth date

        Calendar cal = Calendar.getInstance();
        cal.setTime(birthday);

        automationPP.calendar.setDate(String.valueOf(cal.get(Calendar.DAY_OF_MONTH)),
                months[cal.get(Calendar.MONTH)], String.valueOf(startYear + stepYear));
        //some subjects
       $("#subjectsInput").scrollTo();
        automationPP.pickSubjects(new String[]{"English", "Computer Science"});

        //some hobbies
        automationPP.pickHobbies(new String[]{"Sports", "Reading", "Music"});
        // file upload
        automationPP.uploadPicture("img/image.png");
        //address
        automationPP.enterAdress(streetAddress);
        //state & city
        automationPP.setStateAndCity("Rajasthan", "Jaipur");

        automationPP.clickSubmit();

        // assertions
        automationPP
                .checkResultsValue("Student Name", firstName + " " + lastName)
                .checkResultsValue("Student Email", email)
                .checkResultsValue("Gender", genders[randomGender])
                .checkResultsValue("Mobile", phone_number)
                .checkResultsValue("Date of Birth", cal.get(Calendar.DAY_OF_MONTH) + " " +
                        months[cal.get(Calendar.MONTH)] + "," + (startYear + stepYear))
                .checkResultsValue("Subjects", "English, Computer Science")
                .checkResultsValue("Hobbies", "Sports, Reading, Music")
                .checkResultsValue("Picture", "image.png")
                .checkResultsValue("Address", streetAddress)
                .checkResultsValue("State and City", "Rajasthan Jaipur")
        ;
    }
}
