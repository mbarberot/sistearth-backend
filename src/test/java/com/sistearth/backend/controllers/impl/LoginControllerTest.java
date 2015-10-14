package com.sistearth.backend.controllers.impl;

import com.sistearth.backend.controllers.Answer;
import com.sistearth.backend.controllers.payloads.extractors.impl.LoginPayloadExtractor;
import com.sistearth.backend.controllers.payloads.impl.LoginPayload;
import com.sistearth.backend.models.beans.User;
import com.sistearth.backend.models.managers.ModelManager;
import com.sistearth.backend.utils.TestUserManager;
import com.sistearth.backend.utils.TokenManager;
import com.sistearth.backend.views.impl.LoginView;
import org.junit.Test;

import static com.sistearth.backend.utils.TestUtils.createUser;
import static java.util.Collections.emptyMap;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

public class LoginControllerTest {
    @Test
    public void testLoginSuccess() throws Exception {
        User payloadUser = new User();
        payloadUser.setUsername("Jon");
        payloadUser.setPassword("winterfell");

        User databaseUser = createUser(1, "Jon", "winterfell", "jon@snow.com");

        LoginPayload payload = mock(LoginPayload.class);
        doReturn(true).when(payload).isValid();
        doReturn(payloadUser.getUsername()).when(payload).getUsername();

        ModelManager<User> userManager = mock(TestUserManager.class);
        doReturn(databaseUser).when(userManager).getBy(eq("username"), eq(payloadUser.getUsername()));

        TokenManager tokenManager = mock(TokenManager.class);
        doReturn("my-token").when(tokenManager).createToken(anyObject());

        LoginView exptectedView = new LoginView();
        exptectedView.setUser(databaseUser);
        exptectedView.setToken("my-token");

        assertEquals(
                new Answer(200, exptectedView.render()),
                new LoginController(userManager, tokenManager, new LoginPayloadExtractor(), new LoginView())
                        .process(payload, emptyMap())
        );

        verify(userManager, atLeastOnce()).getBy(eq("username"), eq(payloadUser.getUsername()));
    }
}