package ru.st.selenium;

import static org.junit.Assert.assertTrue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.st.selenium.model.User;

public class NetEntTest extends ru.st.selenium.pages.TestBase {
	@BeforeMethod
	public void mayBeLogout() {
		if (app.getUserHelper().isNotLoggedInInception()) {
			System.out.println("**********isNotLoggedInInception**********");
			return;
		}
		app.getUserHelper().logout(); 
	}
	
	@Test(priority = 1, enabled = true)
	public void testNetEntSlotGame() throws Exception {
		User user = new User().setLogin("dartland@rambler.ru").setPassword("123456");
		app.getNavigationHelper().gotoLoginPage();
		app.getUserHelper().loginAs(user);
		//assertTrue(app.getUserHelper().isLoggedInAs(user));
		app.getNavigationHelper().gotoNetEntPage();
		app.getNavigationHelper().gotoNetEntSlotGames();
		assertTrue(app.getUserHelper().isNetEntSlotGamesPresent());
	}	

}
