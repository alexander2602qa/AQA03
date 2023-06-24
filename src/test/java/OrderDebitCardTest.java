import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class OrderDebitCardTest {
    @Test
    void shouldOrderCardIfDateCorrect() {
        open("http://localhost:9999/");
        $("[data-test-id='name'] input").setValue("Иван Антонов");
        $("[data-test-id='phone'] input").setValue("+79131345555");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $(withText("Ваша заявка успешно отправлена!")).shouldBe(visible, Duration.ofMillis(5000));
    }

    @Test
    void shouldShowTipIfNameIsEmpty() {
        open("http://localhost:9999/");
        $("[data-test-id='name'] input").setValue("");
        $("[data-test-id='phone'] input").setValue("+79131345555");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id='name'] .input__sub").shouldHave(text("Поле обязательно для заполнения"));
    }

    @Test
    void shouldShowTipIfNameIsNotCorrect() {
        open("http://localhost:9999/");
        $("[data-test-id='name'] input").setValue("Ivan Ivanov");
        $("[data-test-id='phone'] input").setValue("+79131345555");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id='name'] .input__sub").shouldHave(text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }


    @Test
    void shouldShowTipIfPhoneIsEmpty() {
        open("http://localhost:9999/");
        $("[data-test-id='name'] input").setValue("Иван Иванов");
        $("[data-test-id='phone'] input").setValue("");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id='phone'] .input__sub").shouldHave(text("Поле обязательно для заполнения"));
    }

    @Test
    void shouldShowTipIfPhoneIsNotCorrect() {
        open("http://localhost:9999/");
        $("[data-test-id='name'] input").setValue("Иван Иванов");
        $("[data-test-id='phone'] input").setValue("88005553535"); // без плюса в начале
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id='phone'] .input__sub").shouldHave(text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldShowTipIfAgreementIsNotClicked() {
        open("http://localhost:9999/");
        $("[data-test-id='name'] input").setValue("Иван Иванов");
        $("[data-test-id='phone'] input").setValue("+79131345555");
        $(".button").click();
        $("[data-test-id='agreement']").shouldHave(cssClass("input_invalid"));
    }
}
