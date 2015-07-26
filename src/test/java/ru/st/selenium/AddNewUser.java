package ru.st.selenium;

import static org.junit.Assert.assertTrue;


import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
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

	@AfterTest
	public void checkStatusTest() {
		// �� �������� � ���������� checkStatusTest(ITestResult result)
		// �� ����, ���� ���� ���� ����� �� ����� �������, � �� ��� �������� assert, �� ���� ����� ��� �������� ������� 
		System.out.println(">>> Error "  + " <<<<");
		
	}	
	
//	@AfterTest(alwaysRun = true)
//	public void checkStatusTest(ITestResult result) {
//		if (result.isSuccess()) {
//			return;
//		} else {
//			// test failed!!! do whatever you want
//			app.getUserHelper().takeScreenShot(result);
//			
//		}
//		System.out.println(">>> Error " + result.getName() + " <<<<");
//	}
	
	@AfterMethod
	public void checkStatusMethod(ITestResult result) {
		if (result.isSuccess()) {
			return;
		} else {
			// test failed!!! do whatever you want
			app.getUserHelper().takeScreenShot(result);
			
		}
	}
	
	
	@Test(priority = 2)
	public void AddNewUserOK() throws Exception {
		User user = new User().setEmail("dartland3@rambler.ru").setPassword("123456");
		app.getNavigationHelper().gotoRegistationPage();
		app.getUserHelper().addNewUserAs(user);
		assertTrue(app.getUserHelper().isRegisteredIn());
		
		//����������, ��������� ����� � ��������� ������� �����������
		//��������� �������� ����������� � ������ 109.229.68.160.

	}

	@Test(priority = 1, enabled = false) // ��������� ���������� ������, ���� ����� ��������������
	public void AddNewUserFailed() throws Exception {

		//app.getNavigationHelper().hideBannerLink();
		
		User user = new User().setEmail("dartland@rambler-ru").setPassword("123456");
		app.getNavigationHelper().gotoRegistationPage();
		app.getUserHelper().addNewUserAs(user);
		//assertTrue(false);
		assertTrue(app.getUserHelper().isNotRegisteredIn());

	}
	
	

}
