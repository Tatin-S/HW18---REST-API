package config;

import com.codeborne.selenide.Configuration;
import config.web.WebConfig;
import io.restassured.RestAssured;

public class ProjectConfiguration {

    private final WebConfig webConfig;

    public ProjectConfiguration(WebConfig webConfig) {
        this.webConfig = webConfig;
    }

    public void webConfig() {
        Configuration.baseUrl = webConfig.baseUrl();
        Configuration.browser = webConfig.browser();
        RestAssured.baseURI = webConfig.baseUrl();
        Configuration.browserSize = webConfig.browserSize();
        Configuration.browserVersion = webConfig.browserVersion();
        Configuration.pageLoadStrategy = "eager";
        if (webConfig.isRemote()) {
            Configuration.remote = webConfig.remoteUrl();
        }
    }
}