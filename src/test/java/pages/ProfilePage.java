package pages;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import properties.SystemProperties;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class ProfilePage {
    private final SelenideElement booksListTitle = $("#userName-label"),
            userName = $("#userName-value"),
            deleteButton = $("#delete-record-undefined"),
            confirmButton = $("#closeSmallModal-ok"),
            listEmptyLabel = $(".rt-noData");

    @Step("Открытие профиля")
    public ProfilePage openPage() {
        open("/profile");
        booksListTitle.shouldHave(text("Books : "));
        userName.shouldHave(text(SystemProperties.login));
        return this;
    }

    @Step("Проверем, что книга есть в корзине")
    public ProfilePage checkBookIsInProfile(String isbn) {
        $("a[href*='/profile?book=" + isbn + "']").should(exist);
        return this;
    }

    @Step("Удаление книги")
    public ProfilePage deleteBook() {
        deleteButton.click();
        confirmButton.click();
        switchTo().alert().accept();
        return this;
    }

    @Step("Проверем, что книга удалена")
    public void checkBookIsDeleted() {
        listEmptyLabel.should(exist);
    }
}
