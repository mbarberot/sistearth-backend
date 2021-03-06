package com.alterrae.game.auth;

import com.alterrae.db.api.manager.ModelException;
import com.alterrae.db.api.entity.User;
import com.alterrae.test.utils.TestUserManager;
import org.junit.Test;

import static com.alterrae.game.auth.Authenticator.Result.ACCEPTED;
import static com.alterrae.game.auth.Authenticator.Result.REJECTED;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AuthenticatorTest {

    @Test
    public void testAuthenticateAccepted() throws Exception {
        TestUserManager userManager = mock(TestUserManager.class);
        when(userManager.getBy("username", "foo")).thenReturn(new User("57596105fc13ae0f3a001507", "foo", "bar", "foo@dot.com"));
        assertEquals(ACCEPTED, new Authenticator(userManager).authenticate("foo", "bar"));
    }

    @Test
    public void testAuthenticateRejected() throws Exception {
        TestUserManager userManager = mock(TestUserManager.class);
        when(userManager.getBy("username", "foo")).thenReturn(new User("57596105fc13ae0f3a001507", "foo", "bar", "foo@dot.com"));
        assertEquals(REJECTED, new Authenticator(userManager).authenticate("foo", "wrongpassword"));
    }

    @Test
    public void testAuthenticateRejected_ModelException() throws Exception {
        TestUserManager userManager = mock(TestUserManager.class);
        when(userManager.getBy("username", "foo")).thenThrow(new ModelException("whatever"));
        assertEquals(REJECTED, new Authenticator(userManager).authenticate("foo", "whatever"));
    }

    @Test
    public void testIsAuthenticatedAccepted() throws Exception {
        TestUserManager userManager = mock(TestUserManager.class);
        when(userManager.getBy("username", "foo")).thenReturn(new User("57596105fc13ae0f3a001507", "foo", "bar", "foo@dot.com"));
        Authenticator auth = new Authenticator(userManager);
        auth.authenticate("foo", "bar");
        assertTrue(auth.isAuthenticated());
    }

    @Test
    public void testIsAuthenticatedRejected() throws Exception {
        TestUserManager userManager = mock(TestUserManager.class);
        when(userManager.getBy("username", "foo")).thenReturn(new User("57596105fc13ae0f3a001507", "foo", "bar", "foo@dot.com"));
        Authenticator auth = new Authenticator(userManager);
        auth.authenticate("foo", "wrong password");
        assertFalse(auth.isAuthenticated());
    }

    @Test(expected = AuthenticationException.class)
    public void testIsAuthenticatedWithoutAuthentication() throws Exception {
        new Authenticator(mock(TestUserManager.class)).isAuthenticated();
    }

    @Test
    public void testGetAuthenticatedUserAccepted() throws Exception {
        User user = new User("57596105fc13ae0f3a001507", "foo", "bar", "foo@dot.com");

        TestUserManager userManager = mock(TestUserManager.class);
        when(userManager.getBy("username", "foo")).thenReturn(user);

        Authenticator auth = new Authenticator(userManager);
        auth.authenticate("foo", "bar");

        assertEquals(user, auth.getAuthenticatedUser());
    }

    @Test(expected = AuthenticationException.class)
    public void testGetAuthenticatedUserRejected() throws Exception {
        TestUserManager userManager = mock(TestUserManager.class);
        when(userManager.getBy("username", "foo")).thenReturn(new User("57596105fc13ae0f3a001507", "foo", "bar", "foo@dot.com"));

        Authenticator auth = new Authenticator(userManager);
        auth.authenticate("foo", "wrong password");
        auth.getAuthenticatedUser();
    }

    @Test(expected = AuthenticationException.class)
    public void testGetAuthenticatedUserWithoutAuthentication() throws Exception {
        Authenticator auth = new Authenticator(mock(TestUserManager.class));
        auth.getAuthenticatedUser();
    }
}