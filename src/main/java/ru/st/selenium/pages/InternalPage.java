package ru.st.selenium.pages;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InternalPage extends AnyPage {

	public InternalPage(PageManager pages) {
		super(pages);
	}

	@Override
	public InternalPage ensurePageLoaded() {
		super.ensurePageLoaded();
		wait.until(presenceOfElementLocated(By.id("language")));
		wait.until(presenceOfElementLocated(By.id("balance")));
		wait.until(visibilityOfElementLocated(By.id("balance")));
		return this;
	}

	@FindBy(id = "casino_btn")
	private WebElement microgamingLink;

	@FindBy(id = "casino_3d_btn")
	private WebElement netEntLink;

	@FindBy(id = "live_casino_btn")
	private WebElement liveCasinoLink;

	@FindBy(id = "logout_btn")
	private WebElement exitButton;

	@FindBy(id = "my_profile")
	private WebElement myProfileButton;

	public ExternalPage clickExitButton() {
		exitButton.click();
		return pages.externalPage;

	}

	public UserProfilePage clickMyProfileButton() {
		myProfileButton.click();
		return pages.userProfilePage;

	}

	public MicrogamingPage clickMicrogamingLink() {
		microgamingLink.click();
		return pages.microgamingPage;
	}

	public MicrogamingPage clickNetEntLink() {
		netEntLink.click();
		return pages.microgamingPage;
	}

}
