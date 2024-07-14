package pages;
import api.authorization.AuthorizationApi;
import com.codeborne.selenide.SelenideElement;
import data.TestData;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class ProfilePage {
    private final SelenideElement booksListTitle = $("#userName-label"),
            userName = $("#userName-value"),
            deleteButton = $("#delete-record-undefined"),
            confirmButton = $("#closeSmallModal-ok"),
            listEmptyLabel = $(".rt-noData");
            TestData data = new TestData();

    @Step("Открытие профиля")
    public ProfilePage openPage() {
        String userID = AuthorizationApi.extactValueFromCookieString("userID");
        open("/profile");
        booksListTitle.shouldHave(text("Books : "));
        userName.shouldHave(text(data.login));
        return this;
    }

    @Step("Проверем, что книга есть в корзине")
    public ProfilePage checkBookIsInProfile() {
        $("a[href*='/profile?book=" + data.isbn + "']").should(exist);
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
