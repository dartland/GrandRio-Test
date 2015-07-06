package ru.st.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PageManager {

  private WebDriver driver;

  public HomePage homePage;
  public LoginPage loginPage;
  public AddFilmPage addFilmPage;
  public InternalPage internalPage;
  public UserProfilePage userProfilePage;
  public FilmContentPage filmContentPage;
  public UserManagementPage userManagementPage;

  public PageManager(WebDriver driver) {
    this.driver = driver;
    homePage = initElements(new HomePage(this));
    loginPage = initElements(new LoginPage(this));
    addFilmPage = initElements(new AddFilmPage(this));
    internalPage = initElements(new InternalPage(this));
    userProfilePage = initElements(new UserProfilePage(this));
    filmContentPage = initElements(new FilmContentPage(this));
    userManagementPage = initElements(new UserManagementPage(this));
    
  }
  
  private <T extends Page> T initElements(T page) {
    //PageFactory.initElements(driver, page);
    //PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), page);
    PageFactory.initElements(new DisplayedElementLocatorFactory(driver, 10), page);
    return page;
  }
  
  public WebDriver getWebDriver() {
    return driver;
  }

}
