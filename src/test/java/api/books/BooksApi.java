package api.books;

import api.authorization.AuthorizationApi;
import data.TestData;
import io.qameta.allure.Step;
import models.AddBookRequestModel;

import static io.restassured.RestAssured.given;
import static specs.DemoqaSpec.*;

public class BooksApi {
    TestData data = new TestData();

    @Step("Добавление книги через API")
    public void addBookToProfile() {
        String userID = AuthorizationApi.extactValueFromCookieString("userID");
        String token = AuthorizationApi.extactValueFromCookieString("token");
        AddBookRequestModel bookData = new AddBookRequestModel();
        bookData.setUserId(userID);
        bookData.setIsbn(data.isbn);

        given(requestSpec)
                .header("Authorization", "Bearer " + token)
                .body(bookData)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .spec(responseSpecStatusCode201);
    }
}
