package api.authorization;

import com.codeborne.selenide.WebDriverRunner;
import data.TestData;
import io.qameta.allure.Step;
import models.LoginRequestModel;
import models.LoginResponseModel;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;
import static specs.DemoqaSpec.*;

public class AuthorizationApi {

    @Step("Авторизация на сайте")
    public static LoginResponseModel login() {
        TestData authData = new TestData();
        LoginRequestModel loginData = new LoginRequestModel();
        loginData.setUserName(authData.login);
        loginData.setPassword(authData.pass);

        return given(requestSpec)
                .body(loginData)
                .when()
                .post("/Account/v1/Login")
                .then()
                .spec(responseSpecStatusCode200)
                .extract().as(LoginResponseModel.class);
    }

    @Step("Авторизация пользователя")
    public static void setCookiesInBrowser(LoginResponseModel loginResponse) {
        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("userID", loginResponse.getUserId()));
        getWebDriver().manage().addCookie(new Cookie("expires", loginResponse.getExpires()));
        getWebDriver().manage().addCookie(new Cookie("token", loginResponse.getToken()));
        open("");
    }

    public static String extactValueFromCookieString(String value) {
        String cookieValue = String.valueOf(WebDriverRunner.getWebDriver().manage().getCookieNamed(value));
        return cookieValue.substring(cookieValue.indexOf("=") + 1, cookieValue.indexOf(";"));
    }
}