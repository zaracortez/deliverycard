package ru.netology;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static java.time.Duration.ofSeconds;

public class DeliveryTest {
    @BeforeAll
    static void setUp(){
        SelenideLogger.addListener("allure", new AllureSelenide());
    }
    @AfterAll
    static void tearDownAll(){
        SelenideLogger.removeListener("allure");
    }
    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }


    @Test
    @DisplayName("Should successful plan and replan meeting")
    void shouldSuccessfulPlanAndReplanMeeting() {
        var validUser = DataGenerator.Registration.generateUser("ru");
        var daysToAddForFirstMeeting = 4;
        var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
        var daysToAddForSecondMeeting = 7;
        var secondMeetingDate = DataGenerator.generateDate(daysToAddForSecondMeeting);
        $("[data-test-id='city'] input").setValue(validUser.getCity());
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT,Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(firstMeetingDate);
        $("[data-test-id='name'] input").setValue(validUser.getName());
        $("[data-test-id='phone'] input").setValue(validUser.getPhone());
        $("[data-test-id= 'agreement']").click();
        $$("button").find(exactText("Запланировать")).click();
        $("[data-test-id='success-notification']  .notification__title").shouldBe(visible, ofSeconds(10)).shouldHave(exactText("Успешно!"));
        $("[data-test-id='success-notification']  .notification__content").shouldBe(visible, ofSeconds(10)).shouldHave(exactText("Встреча успешно запланирована на " + firstMeetingDate));
        $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(secondMeetingDate);
        $$("button").find(exactText("Запланировать")).click();
        $("[data-test-id='replan-notification']  .notification__title").shouldBe(visible, ofSeconds(10)).shouldHave(exactText("Необходимо подтверждение"));
        $("[data-test-id='replan-notification']  .notification__content").shouldBe(visible, ofSeconds(10)).shouldHave(text("У вас уже запланирована встреча на другую дату. Перепланировать?"));
        $$("[data-test-id='replan-notification'] button").find(exactText("Перепланировать")).click();
        $("[data-test-id='success-notification']  .notification__title").shouldBe(visible, ofSeconds(10)).shouldHave(exactText("Успешно!"));
        $("[data-test-id='success-notification']  .notification__content").shouldBe(visible, ofSeconds(10)).shouldHave(exactText("Встреча успешно запланирована на " + secondMeetingDate));
    }
}
