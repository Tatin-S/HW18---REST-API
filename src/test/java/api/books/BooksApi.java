package api.books;

import api.authorization.AuthorizationApi;
import io.qameta.allure.Step;
import models.AddBookRequestModel;

import java.util.List;

import static io.restassured.RestAssured.given;
import static specs.DemoqaSpec.*;

public class BooksApi {

    @Step("Добавление книги через API")
    public void addBookToProfile(String isbn) {
        AddBookRequestModel bookData = new AddBookRequestModel();
        AddBookRequestModel.Isbn isbnNum = new AddBookRequestModel.Isbn();
        bookData.setUserId(AuthorizationApi.getCookieByName(AuthorizationApi.USER_ID));
        bookData.setCollectionOfIsbns(List.of(isbnNum));

        given(requestSpec)
                .header("Authorization", "Bearer " + AuthorizationApi.getCookieByName(AuthorizationApi.TOKEN))
                .body(bookData)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .spec(responseSpecStatusCode201);
    }
}
