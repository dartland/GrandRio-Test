package ru.st.selenium;

import static org.junit.Assert.assertTrue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.st.selenium.model.User;

public class AddNewUser extends ru.st.selenium.pages.TestBase {

	@BeforeMethod
	public void mayBeLogout() {
		if (app.getUserHelper().isNotLoggedInInception()) {
			return;
		}
		app.getUserHelper().logout();
	}

	@Test
	public void AddNewUserOK() throws Exception {
		User user = new User().setLogin("dartland@rambler.ru").setPassword("123456");
		app.getNavigationHelper().gotoLoginPage();
		app.getUserHelper().loginAs(user);
		assertTrue(app.getUserHelper().isLoggedInAs(user));
		app.getUserHelper().logout();

	}

//	@Test
//	public void AddNewUserFailed() throws Exception {
//
//		User user = new User().setLogin("dartland@rambler.ru").setPassword("wrong");
//		app.getNavigationHelper().gotoLoginPage();
//		app.getUserHelper().loginAs(user);
//		assertTrue(app.getUserHelper().isNotLoggedIn());
//
//	}

}
