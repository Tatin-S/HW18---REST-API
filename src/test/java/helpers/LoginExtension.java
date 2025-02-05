package helpers;

import api.authorization.AuthorizationApi;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class LoginExtension implements BeforeEachCallback {

    @Override
    public void beforeEach(ExtensionContext context) {
        AuthorizationApi.setCookiesInBrowser(AuthorizationApi.login());
    }
}