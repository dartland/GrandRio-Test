package ru.st.selenium;

import static org.junit.Assert.assertTrue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.st.selenium.model.Film;

public class AddFilmFailure extends ru.st.selenium.pages.TestBase {


  @Test
  public void AddFilmFailure() throws Exception {
	
	Film film = new Film().setTitle("Selenium 2.0 + Java").setYear("").setNotes("WebDriver"); 
	app.getFilmHelper().create(film);
	assertTrue(app.getFilmHelper().isInputErrorYear());
	film.setTitle("").setYear("2003").setNotes("WebDriver"); 
	app.getFilmHelper().create(film);
	assertTrue(app.getFilmHelper().isInputErrorName());
	
	
  }  
 
  
  

}
