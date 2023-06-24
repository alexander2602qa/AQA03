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
}
