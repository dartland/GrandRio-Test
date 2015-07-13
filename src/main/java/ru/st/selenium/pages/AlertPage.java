package ru.st.selenium.pages;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AlertPage extends AnyPage {

	public AlertPage(PageManager pages) {
		super(pages);
	}

	@FindBy(id = "alert-message-holder")
	private WebElement messageHolder;


	@FindBy(id = "close-alert")
	private WebElement alertPageCloseButton;



	public void clickAlertPageCloseButton() {
		alertPageCloseButton.click();
	}



	public AlertPage ensurePageLoaded() {
		super.ensurePageLoaded();
		wait.until(presenceOfElementLocated(By.id("alert-message-holder"))); // Забыли пароль?
		return this;
	}



	public String getAlertText() {
		// TODO Auto-generated method stub
		return messageHolder.getText();
	}


}
