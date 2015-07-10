package ru.st.selenium.pages;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends AnyPage {

	public RegistrationPage(PageManager pages) {
		super(pages);
	}

	@FindBy(xpath = "//form[@id='reg_form']//input[@name='email']")
	private WebElement emailField;
	// xpath = "//form[@id='reg_form']//input[@name='email']"

	@FindBy(id = "reg_psw")
	private WebElement passwordField;

	@FindBy(id = "uniform-currency_RUB")
	private WebElement currencyRUBRadio;
	
	@FindBy(id = "uniform-i_agree")
	private WebElement iAgreeRulesBox;
	
	@FindBy(id = "uniform-i_agree_2")
	private WebElement iAgree18Box;
	
	@FindBy(css = ".rg_in_btn>input")
	private WebElement submitButton;	
	
	@FindBy(id = "reg_modal_close")
	private WebElement registrationPageCloseButton;
	
	

	// private Select permissionDropdown() {
	// return new Select(driver.findElement(By.name("permission")));
	// }
	
	public void clickRegistrationPageCloseButton() {
		registrationPageCloseButton.click();
	}
	
	public void clickRegisrationSubmitButton() {
		submitButton.click();
	}

	public RegistrationPage setCurrencyRUBRadio() {
		currencyRUBRadio.click();
		return this;
	}
	
	public RegistrationPage setiAgreeRulesBox() {
		iAgreeRulesBox.click();
		return this;
	}	
	
	public RegistrationPage setIAgree18Box() {
		iAgree18Box.click();
		return this;
	}
	
	public RegistrationPage setEmailField(String text) {
		emailField.clear();
		emailField.sendKeys(text);
		return this;
	}

	public RegistrationPage setPasswordField(String text) {
		passwordField.clear();
		passwordField.sendKeys(text);
		return this;
	}

	public RegistrationPage ensurePageLoaded() {
		super.ensurePageLoaded();
		wait.until(presenceOfElementLocated(By.xpath(".//*[@id='reg_form']/h4"))); // надпись "регистрация"
		return this;
	}
}
