package ru.st.selenium.applogic;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import ru.st.selenium.model.User;

public interface UserHelper {

	void loginAs(User user);

	void logout();

	boolean isLoggedIn();

	boolean isLoggedInAs(User user);

	boolean isNotLoggedIn();

	boolean isNotLoggedInInception();

	void addNewUserAs(User user);

	boolean isNotRegisteredIn(); 

	boolean isRegisteredIn();

	void takeScreenShot(ITestResult result);

	WebDriver getWebDriver();

}
