package ru.st.selenium;

import static org.junit.Assert.assertTrue;
import static org.testng.Assert.fail;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import ru.st.selenium.model.User;

public class AddNewUser extends ru.st.selenium.pages.TestBase {

	@BeforeMethod
	public void mayBeLogout() {
				       		
		if (app.getUserHelper().isNotLoggedInInception()) {
			return;
		}
		app.getUserHelper().logout();
	
	}

	@AfterTest
	public void checkStatusTest() {
		// не работает с параметром checkStatusTest(ITestResult result)
		// то есть, если тест упал когда не нашли элемент, а не при проверке assert, то хрен знает как скриншот сделать 
		//System.out.println(">>> Error "  + " <<<<");
		
	}	
	
	@AfterMethod
	public void checkStatusMethod(ITestResult result) {
		if (result.isSuccess()) {
			return;
		} else {
			// test failed!!! do whatever you want
			app.getUserHelper().takeScreenShot(result);
			
		}
	}
	
	
	@Test(priority = 1)
	public void AddNewUserOK() throws Exception {
		User user = new User().setEmail("dartland3@rambler.ru").setPassword("123456");
		app.getNavigationHelper().gotoRegistationPage();
		app.getUserHelper().addNewUserAs(user);
		assertTrue(app.getUserHelper().isRegisteredIn());
		
		//ѕожалуйста, проверьте почту и завершите процесс регистрации
		//ƒостигнут максимум регистраций с адреса 109.229.68.160.

	}

	@Test(priority = 2, enabled = true) // приоритет исполнени€ первый, тест будет проигнорирован
	public void AddNewUserFailed() throws Exception {

		//app.getNavigationHelper().hideBannerLink();
		
		User user = new User().setEmail("dartland@rambler-ru").setPassword("123456");
		app.getNavigationHelper().gotoRegistationPage();
		app.getUserHelper().addNewUserAs(user);
		//assertTrue(false);
		assertTrue(app.getUserHelper().isNotRegisteredIn()); 
		
	}	
    
	@Test
    public void simpleTest() throws Exception {
	       assertThat(2, is(2));
	}	

    @Test
    public void failedTest() {
        fail("This test should be failed");
    }

    @Test(dependsOnMethods = "failedTest")
    public void skippedByDependencyTest() {
    }	
	
	

}
