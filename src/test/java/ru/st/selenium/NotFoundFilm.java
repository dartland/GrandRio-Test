package ru.st.selenium;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.st.selenium.model.Film;
import ru.st.selenium.model.User;

public class NotFoundFilm extends ru.st.selenium.pages.TestBase {


 
  @Test
  public void NotFoundFilm() throws Exception {
	 
	List<Film> FilmList;
	
	app.getFilmHelper().searchNull("Kadabra");
	assertTrue(app.getFilmHelper().isEmptySearchResult("No movies where found."));
	
	
	
  }  
 
  
  

}
