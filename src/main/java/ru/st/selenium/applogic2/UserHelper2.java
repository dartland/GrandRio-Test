package ru.st.selenium.applogic2;

import ru.st.selenium.applogic.UserHelper;
import ru.st.selenium.model.User;
import ru.st.selenium.pages.UserProfilePage;

public class UserHelper2 extends DriverBasedHelper implements UserHelper {

	public UserHelper2(ApplicationManager2 manager) {
		super(manager.getWebDriver());
	}

	@Override
	public void loginAs(User user) {
		pages.loginPage.ensurePageLoaded()
			.setUsernameField(user.getLogin())
			.setPasswordField(user.getPassword())
			.clickSubmitButton();
	}
	
	@Override
	public void addNewUserAs(User user) {
		pages.registrationPage.ensurePageLoaded()//;
//		.setEmailField(user.getEmail())
//		.setPasswordField(user.getPassword())
//		.setCurrencyRUBRadio()
//		.setiAgreeRulesBox()
//		.setIAgree18Box()
		.clickRegisrationSubmitButton();
		
	}

	@Override
	public void logout() {
		pages.internalPage.ensurePageLoaded().clickExitButton();
	}

	@Override
	public boolean isLoggedIn() {
		return pages.internalPage.waitPageLoaded();
	}

	@Override
	public boolean isLoggedInAs(User user) {
		return isLoggedIn() && getLoggedUser().getEmail().equals(user.getLogin()); // у нас логин = email
	}

	@Override
	public boolean isNotLoggedIn() {
		boolean notLogged = pages.loginPage.waitPageLoaded();
		if (notLogged) {
			pages.loginPage.clickLoginPageCloseButton();
		}

		return notLogged;
	}

	@Override
	public boolean isNotLoggedInInception() {
		return pages.loginPage.waitPageLoaded();
	}

	private User getLoggedUser() {
		UserProfilePage userProfile = pages.internalPage.ensurePageLoaded().clickMyProfileButton().ensurePageLoaded();
		// System.out.println(userProfile.getEmail());
		return new User()
				// .setLogin(userProfile.getFirstName())
				.setEmail(userProfile.getEmail());

	}

	
	@Override
	public boolean isNotLoggedInRegistrationPage() {
		boolean notLogged = pages.registrationPage.waitPageLoaded();
		if (notLogged) {
			pages.registrationPage.clickRegistrationPageCloseButton();
		}

		return notLogged;
	}

}
