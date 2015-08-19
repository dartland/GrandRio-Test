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

	@Step("������� �� �������� ��� Microgaming")
	@Override
	public void gotoMicrogamingPage() {
		pages.internalPage.ensurePageLoaded().clickMicrogamingLink();
		
	}

	@Step("������� �� �������� ��� Microgaming � ������ �������� ����")
	@Override
	public void gotoMicrogamingSlotGames() {
		pages.microgamingPage.ensurePageLoaded().clickMicrogamingSlotLink();
		
	}

	@Step("������� �� �������� ��� Microgaming � ������ ���������� ����")
	@Override
	public void gotoMicrogamingTableGames() {
		pages.microgamingPage.ensurePageLoaded().clickMicrogamingTableLink();
	}

	@Step("������� �� �������� ��� NETENT")
	@Override
	public void gotoNetEntPage() {
		pages.internalPage.ensurePageLoaded().clickNetEntLink();
	}

	@Step("������� �� �������� ��� NETENT � ������ �������� ����")
	@Override
	public void gotoNetEntSlotGames() {
		pages.microgamingPage.ensurePageLoaded().clickNetEntSlotLink();
	}

}
