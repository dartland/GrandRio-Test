package ru.st.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PageManager {

	private WebDriver driver;

	public AlertPage alertPage;
	public LoginPage loginPage;
    public InternalPage internalPage;
	public ExternalPage externalPage;
	public UserProfilePage userProfilePage;
	public RegistrationPage registrationPage;

	public PageManager(WebDriver driver) {
		this.driver = driver;

		alertPage = initElements(new AlertPage(this));
		loginPage = initElements(new LoginPage(this));
		internalPage = initElements(new InternalPage(this));
		externalPage = initElements(new ExternalPage(this));
		userProfilePage = initElements(new UserProfilePage(this));
		registrationPage = initElements(new RegistrationPage(this));

	}

	private <T extends Page> T initElements(T page) {
		// PageFactory.initElements(driver, page);
		// PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10),
		// page); 
		PageFactory.initElements(new DisplayedElementLocatorFactory(driver, 10), page);
		return page;
	}

	public WebDriver getWebDriver() {
		return driver;
	}

}
