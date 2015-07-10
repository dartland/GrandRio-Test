package ru.st.selenium.applogic2;

import ru.st.selenium.applogic.NavigationHelper;

public class NavigationHelper2 extends DriverBasedHelper implements NavigationHelper {

	private String baseUrl;

	public NavigationHelper2(ApplicationManager2 manager) {
		super(manager.getWebDriver());
		this.baseUrl = manager.getBaseUrl();
	}

	@Override
	public void openMainPage() {
		driver.get(baseUrl);
	}

	@Override
	public void openRelativeUrl(String url) {
		driver.get(baseUrl + url);
	}

	@Override
	public void gotoLoginPage() {
		pages.externalPage.ensurePageLoaded().clickEnterLink();

	}

	@Override
	public void gotoRegistationPage() {
		pages.externalPage.ensurePageLoaded().clickRegistrationLink();
		
	}

}
