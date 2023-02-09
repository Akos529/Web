import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardApplicationTest {
    @BeforeEach
    void openBrowser(){
        open("http://localhost:9999");
    }
    @Test
    void shouldSubmitRequest() {
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Иванов Иван");
        form.$("[data-test-id=phone] input").setValue("+79210000000");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }
    @Test
    void shouldRequireNameField() {
        SelenideElement form = $(".form");
        form.$("[data-test-id=phone] input").setValue("+79210000000");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }
    @Test
    void shouldRequirePhoneField() {
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Иванов Иван");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }
}
