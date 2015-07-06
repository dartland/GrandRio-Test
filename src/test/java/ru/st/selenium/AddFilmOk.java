package ru.st.selenium;

import static org.junit.Assert.assertTrue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.st.selenium.model.Film;
import ru.st.selenium.model.User;

public class AddFilmOk extends ru.st.selenium.pages.TestBase {

  @BeforeMethod
  public void mayBeLogout() {
    if (app.getUserHelper().isNotLoggedIn()) {
      return;
    }
    app.getUserHelper().logout();
  }
 
  @Test
  public void addFilmOk() throws Exception {
	 
	User user = new User().setLogin("admin").setPassword("admin");
	app.getUserHelper().loginAs(user);  
	
	Film film = new Film().setTitle("The best movie").setYear("2015").setNotes("WebDriver"); 
	app.getFilmHelper().create(film);
	assertTrue(app.getFilmHelper().isFullFilmNameEquals(film));
	//app.getUserHelper().logout();
  }  
 
  
  

}
