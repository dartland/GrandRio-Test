package ru.st.selenium.pages;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class AddFilmPage extends InternalPage {

	public AddFilmPage(PageManager pages) {
		super(pages);
	}
	
	@FindBy(name = "name")
	private WebElement titleField;
	
  @FindBy(name = "year")
  private WebElement yearField;
  
  @FindBy(name = "aka")
  private WebElement alsoKnownAs;
  
  @FindBy(name = "submit")
  private WebElement submitButton; 
  
  @FindBy(xpath = ".//label[@for='year']")
  private WebElement labelErrorYear;
  
  @FindBy(xpath = ".//label[@for='name']")
  private WebElement labelErrorName;
  
 
  public String getFilmName() {
    return titleField.getAttribute("value");
  }

  public AddFilmPage setFilmName(String text) {
	  titleField.clear();
	  titleField.sendKeys(text);
    return this;
  }

  public String getYear() {
    return yearField.getAttribute("value");
  }

  public AddFilmPage setYear(String text) {
	  yearField.clear();
	  yearField.sendKeys(text);
    return this;
  }

  public String getAlsoKnownAs() {
	    return alsoKnownAs.getAttribute("value");
	  } 
  
  public AddFilmPage setAlsoKnownAs(String text) {
	  alsoKnownAs.sendKeys(text);
    return this;
  }


  public void clickSubmitButton() {
		
	submitButton.click();
  }

  public boolean DisplaedLablelErrorYear() {
  
	return labelErrorYear.isDisplayed();
  }
  
  public boolean DisplaedLablelErrorName() {
		
	  return labelErrorName.isDisplayed();
  } 
  

  public AddFilmPage ensurePageLoaded() {
    super.ensurePageLoaded();
    wait.until(presenceOfElementLocated(By.xpath(".//*[@id='imdbsearch']")));
    wait.until(presenceOfElementLocated(By.cssSelector("div.content h2")));
    return this;
  }


  
}
