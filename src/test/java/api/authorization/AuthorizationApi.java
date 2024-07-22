package api.authorization;

import com.codeborne.selenide.WebDriverRunner;
import config.TestDataConfig;
import io.qameta.allure.Step;
import models.LoginRequestModel;
import models.LoginResponseModel;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;
import static specs.DemoqaSpec.*;

public class AuthorizationApi {
    static final TestDataConfig testDataConfig = ConfigFactory.create(TestDataConfig.class, System.getProperties());

    @Step("Login Авторизация на сайте")
    public static LoginResponseModel login() {
        LoginRequestModel loginData = new LoginRequestModel();
        loginData.setUserName(testDataConfig.userLogin());
        loginData.setPassword(testDataConfig.userPassword());

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