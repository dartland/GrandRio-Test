package ru.st.selenium;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.testng.Assert.fail;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.st.selenium.model.User;
import ru.yandex.qatools.allure.annotations.Attachment;

public class AddNewUser extends ru.st.selenium.pages.TestBase {

	@BeforeMethod
	public void mayBeLogout() {
				       		
		if (app.getUserHelper().isNotLoggedInInception()) {
			return;
		}
		app.getUserHelper().logout();
	
	}

   //********Тесты*************

	@Test(priority = 1, enabled = false)
	public void AddNewUserOK() throws Exception {
		User user = new User().setEmail("dartland3@rambler.ru").setPassword("123456");
		app.getNavigationHelper().gotoRegistationPage();
		app.getUserHelper().addNewUserAs(user);
		assertTrue(app.getUserHelper().isRegisteredIn());
		//Пожалуйста, проверьте почту и завершите процесс регистрации
		//Достигнут максимум регистраций с адреса 109.229.68.160.
	}
	
	@Test(priority = 2, enabled = true) 
	public void AddNewUserFailed() throws Exception {

		User user = new User().setEmail("dartland@rambler-ru").setPassword("123456");
		app.getNavigationHelper().gotoRegistationPage();
		app.getUserHelper().addNewUserAs(user);
		//заделаем аттач из скриншота
		//createAttachment("Заполнили поля рег. формы"); 	makeScreenshot();
		//assertTrue(false);
		assertTrue(app.getUserHelper().isNotRegisteredIn()); 

	}	
    
	@Test(priority = 3)
    public void simpleTest() throws Exception {
	       assertThat(2, is(2));
	}	

    @Test(priority = 4)
    public void failedTest() {
    	assertTrue(false);
        fail("This test should be failed");
    }

    @Test(priority = 5, dependsOnMethods = "failedTest")
    public void skippedByDependencyTest() {
    }	
	
    //********Конец тестов*************
    
    
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
			//app.getUserHelper().takeScreenShot(result);
			createAttachment("Имя проваленного теста:"+result.getName()); 	makeScreenshot();
			//тест фэйлим, а обработка срабатывает после применения метода, то есть после след теста.
		}
	}    
    
    @Attachment("Приложение тесту AddNewUser")
    public String createAttachment(String attacheString) {
        return attacheString;
    }
	
    @Attachment
    public byte[] makeScreenshot() {
        return ((TakesScreenshot) app.getUserHelper().getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }	

}
