package ru.st.selenium.pages;

import static org.openqa.selenium.support.ui.ExpectedConditions.alertIsPresent;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class FilmContentPage extends InternalPage {

	public FilmContentPage(PageManager pages) {
		super(pages);
	}
	

  
  @FindBy(xpath = "//img[@alt='Remove']")
  private WebElement removeButton; 
  

  @FindBy(xpath = "//h2")
  private WebElement fullFilmName; 
  
  
  public HomePage clickRemoveButton() {
		
	  removeButton.click();
	  wait.until(alertIsPresent()).accept();
	  return pages.homePage; 
  }
    
 
  public String getFullFilmName() {
		
	  return fullFilmName.getText();
  }
    




  public FilmContentPage ensurePageLoaded() {
    super.ensurePageLoaded();
    wait.until(presenceOfElementLocated(By.xpath("//img[@alt='Own']")));
    wait.until(presenceOfElementLocated(By.cssSelector("div.content h2")));
    return this;
  }


  
}
