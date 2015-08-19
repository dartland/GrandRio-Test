package ru.st.selenium.pages;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends AnyPage {

	public LoginPage(PageManager pages) {
		super(pages);
	}

	@FindBy(id = "lgin")
	private WebElement usernameField;

	@FindBy(id = "psw")
	private WebElement passwordField;

	@FindBy(id = "lg_in_btn")
	private WebElement submitButton;

	@FindBy(id = "lim_close")
	private WebElement loginPageCloseButton;

	public LoginPage setUsernameField(String text) {
		usernameField.clear();
		usernameField.sendKeys(text);
		return this;
	}

	public LoginPage setPasswordField(String text) {
		passwordField.clear();
		passwordField.sendKeys(text);
		return this;
	}

	public void clickLoginPageCloseButton() {
		loginPageCloseButton.click();
	}

	public void clickSubmitButton() {
		submitButton.click();
	}

	@Override
	public LoginPage ensurePageLoaded() {
		super.ensurePageLoaded();
		wait.until(presenceOfElementLocated(By.id("frgt_psw"))); // Забыли пароль?
		//wait.until(visibilityOfElementLocated(By.id("frgt_psw")));
		return this;
	}
}
