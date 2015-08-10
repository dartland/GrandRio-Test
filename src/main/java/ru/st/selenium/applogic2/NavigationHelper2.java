package ru.st.selenium.applogic2;

import ru.st.selenium.applogic.NavigationHelper;  
import ru.yandex.qatools.allure.annotations.Step;

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

	@Override
	public void hideBannerLink() {
		pages.externalPage.toHideBanner();
		
	}

	@Step("Переход на страницу игр Microgaming")
	@Override
	public void gotoMicrogamingPage() {
		pages.externalPage.ensurePageLoaded().clickMicrogamingLink();
		
	}

	@Step("Переход на страницу игр Microgaming в раздел слотовые игры")
	@Override
	public void gotoSlotGames() {
		pages.microgamingPage.ensurePageLoaded().clickMicrogamingSlotLink();
		
	}

}
