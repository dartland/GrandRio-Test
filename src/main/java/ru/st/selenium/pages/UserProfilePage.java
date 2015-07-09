package ru.st.selenium.pages;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserProfilePage extends InternalPage {

	public UserProfilePage(PageManager pages) {
		super(pages);
	}

	@FindBy(id = "firstName")
	private WebElement firstNameField;

	@FindBy(id = "email")
	private WebElement emailField;

	@FindBy(id = "newpass")
	private WebElement newPasswordField;

	@FindBy(name = "reppass")
	private WebElement repeatPasswordField;

	// private Select permissionDropdown() {
	// return new Select(driver.findElement(By.name("permission")));
	// }

	public String getFirstName() {
		return firstNameField.getAttribute("value");
	}

	public String getEmail() {
		return emailField.getAttribute("value");
	}

	public UserProfilePage setEmailField(String text) {
		emailField.sendKeys(text);
		return this;
	}

	public UserProfilePage setNewPassword(String text) {
		newPasswordField.sendKeys(text);
		return this;
	}

	public UserProfilePage setRepeatPassword(String text) {
		repeatPasswordField.sendKeys(text);
		return this;
	}

	// public String getRole() {
	// return permissionDropdown().getFirstSelectedOption().getText();
	// }

	public UserProfilePage ensurePageLoaded() {
		super.ensurePageLoaded();
		wait.until(presenceOfElementLocated(By.id("save_data"))); // кнопка
																	// "сохранить
																	// данные"
		return this;
	}
}
