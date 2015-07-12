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
		User user = new User().setEmail("dartland2@rambler.ru").setPassword("123456");
		app.getNavigationHelper().gotoRegistationPage();
		app.getUserHelper().addNewUserAs(user);
		assertTrue(app.getUserHelper().isRegisteredIn());
		
		//Пожалуйста, проверьте почту и завершите процесс регистрации
		//Достигнут максимум регистраций с адреса 109.229.68.160.

	}

	@Test
	public void AddNewUserFailed() throws Exception {

		User user = new User().setEmail("dartland@rambler-ru").setPassword("123456");
		app.getNavigationHelper().gotoRegistationPage();
		app.getUserHelper().addNewUserAs(user);
		assertTrue(app.getUserHelper().isNotRegisteredIn());

	}

}
