package ru.st.selenium.pages;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ExternalPage extends AnyPage {

	public ExternalPage(PageManager pages) {
		super(pages);
	}

	public ExternalPage ensurePageLoaded() {
		super.ensurePageLoaded();
		wait.until(presenceOfElementLocated(By.id("language")));
		wait.until(presenceOfElementLocated(By.id("reg_btn")));
		return this;
	}

	@FindBy(id = "signin")
	private WebElement enterLink;

	@FindBy(id = "reg_btn")
	private WebElement registrationButton;

	@FindBy(id = "casino_btn")
	private WebElement microgamingLink;

	@FindBy(id = "casino_3d_btn")
	private WebElement netentLink;

	@FindBy(id = "live_casino_btn")
	private WebElement liveCasinoLink;

	public LoginPage clickEnterLink() {
		enterLink.click();
		// wait.until(alertIsPresent()).accept();
		return pages.loginPage;
	}
}
