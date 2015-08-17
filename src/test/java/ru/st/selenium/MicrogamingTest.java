package ru.st.selenium;

import static org.junit.Assert.assertTrue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ru.st.selenium.model.User;

public class MicrogamingTest extends ru.st.selenium.pages.TestBase {
	
	@BeforeMethod
	public void mayBeLogout() {
		if (app.getUserHelper().isNotLoggedInInception()) {
			return;
		}
		app.getUserHelper().logout(); 
	}
	
	@Test(priority = 1, enabled = true)
	public void testMicrogamingSlotGame() throws Exception {
		User user = new User().setLogin("dartland@rambler.ru").setPassword("123456");
		app.getNavigationHelper().gotoLoginPage();
		app.getUserHelper().loginAs(user);
		//assertTrue(app.getUserHelper().isLoggedInAs(user));
		app.getNavigationHelper().gotoMicrogamingPage();
		app.getNavigationHelper().gotoSlotGames();
		assertTrue(app.getUserHelper().isMicrogamingSlotGamesPresent());
	}	
	
//	@DataProvider      //(name = "MicrogamingSlotGame")
//    public Object[][] MicrogamingSlotGame() {
//        Object[][] gameArray = app.getUserHelper().getListMicrogamingGame();
//        return gameArray;
//    }
//	
//	@Test(dataProvider = "MicrogamingSlotGame", priority = 2, enabled = true)
//	 public void microgamingSlotGameTest(String game){
//		assertTrue(app.getUserHelper().isMicrogamingGameRun(game));
//	}
	
	@Test(priority = 3, enabled = true)
	public void testMicrogamingTableGame() throws Exception {
		app.getNavigationHelper().gotoTableGames();
		assertTrue(app.getUserHelper().isMicrogamingTableGamesPresent());
	}		
	
	@DataProvider      //(name = "MicrogamingTableGame")
    public Object[][] MicrogamingTableGame() {
        Object[][] gameArray = app.getUserHelper().getListMicrogamingGame();
        return gameArray;
    }
	
	@Test(dataProvider = "MicrogamingTableGame", priority = 4, enabled = true)
	 public void microgamingTableGameTest(String game){
		assertTrue(app.getUserHelper().isMicrogamingGameRun(game));
	}
	
	
}
