package ru.st.selenium.pages;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
    usernameField.sendKeys(text);
    return this;
  }

  public LoginPage setPasswordField(String text) {
    passwordField.sendKeys(text);
    return this;
  }

  public void clickLoginPageCloseButton() {
	  loginPageCloseButton.click();
  }
  
  public void clickSubmitButton() {
	    submitButton.click();
	  }
	   
  

  public LoginPage ensurePageLoaded() {
    super.ensurePageLoaded();
    wait.until(presenceOfElementLocated(By.id("frgt_psw"))); //Забыли пароль?
    return this;
  }
}
