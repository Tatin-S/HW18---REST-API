package api.authorization;

import io.qameta.allure.Step;
import models.LoginRequestModel;
import models.LoginResponseModel;
import org.openqa.selenium.Cookie;
import properties.SystemProperties;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;
import static specs.DemoqaSpec.*;

public class AuthorizationApi {

    private static final String login = SystemProperties.login,
            password = SystemProperties.password;

    public static final String USER_ID = "userID",
            TOKEN = "token",
            EXPIRES = "expires";

    @Step("Авторизация на сайте")
    public static LoginResponseModel login() {
        LoginRequestModel loginData = new LoginRequestModel();
        loginData.setUserName(login);
        loginData.setPassword(password);

        return given(requestSpec)
                .body(loginData)
                .when()
                .post("/Account/v1/Login")
                .then()
                .spec(responseSpecStatusCode200)
                .extract().as(LoginResponseModel.class);
    }

    @Step("Установка cookies")
    public static void addCookies() {
        open("/favicon.ico");
        LoginResponseModel loginResponseData = login();
        getWebDriver().manage().addCookie(new Cookie(USER_ID, loginResponseData.getUserId()));
        getWebDriver().manage().addCookie(new Cookie(TOKEN, loginResponseData.getToken()));
        getWebDriver().manage().addCookie(new Cookie(EXPIRES, loginResponseData.getExpires()));
    }

    @Step("Получение cookie")
    public static String getCookieByName(String cookie) {
        return getWebDriver().manage().getCookieNamed(cookie).getValue();
    }
}