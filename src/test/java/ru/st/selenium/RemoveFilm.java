package ru.st.selenium;

import org.testng.Assert;
import org.testng.annotations.Test;

import ru.st.selenium.model.Film;

public class RemoveFilm extends ru.st.selenium.pages.TestBase {

 
  @Test
  public void RemoveFilm() throws Exception {
	
    Film film = new Film().setTitle("Selenium");
	int SizeBeforDelete = app.getFilmHelper().getSizeOfFilmList();
	app.getFilmHelper().delete(film);
	int SizeAfterDelete = app.getFilmHelper().getSizeOfFilmList();
	Assert.assertEquals((SizeBeforDelete-SizeAfterDelete),1);
	
	
  }  
 

}
