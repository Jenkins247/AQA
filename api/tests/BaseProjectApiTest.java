package com.leroy.magmobile.api.tests;

import com.google.inject.Inject;
import com.leroy.constants.EnvConstants;
import com.leroy.core.UserSessionData;
import com.leroy.core.api.Module;
import com.leroy.core.configuration.BaseTest;
import com.leroy.umbrella_extension.authorization.AuthClient;
import org.testng.annotations.Guice;
import ru.leroymerlin.qa.core.clients.base.Response;

import static com.leroy.core.matchers.Matchers.successful;
import static org.hamcrest.MatcherAssert.assertThat;

@Guice(modules = {Module.class})
public abstract class BaseProjectApiTest extends BaseTest {

    @Inject
    private AuthClient authClient;

    @Override
    protected UserSessionData initTestClassUserSessionDataTemplate() {
        UserSessionData userSessionData = new UserSessionData();
        userSessionData.setUserLdap(EnvConstants.BASIC_USER_LDAP);
        userSessionData.setUserShopId(EnvConstants.SHOP_WITH_NEW_INTERFACE);
        userSessionData.setUserDepartmentId("1");
        if (isNeedAccessToken()) {
            userSessionData.setAccessToken(getAccessToken());
        }
        return userSessionData;
    }

    protected String getAccessToken() {
        return authClient.getAccessToken(EnvConstants.BASIC_USER_LDAP,
                EnvConstants.BASIC_USER_PASS);
    }

    protected boolean isNeedAccessToken() {
        return true;
    }

    protected void isResponseOk(Response<?> response) {
        assertThat(response, successful());
    }

}
