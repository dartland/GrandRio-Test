package ru.st.selenium;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.testng.annotations.Test;

import ru.st.selenium.model.Film;

public class FoundFilm extends ru.st.selenium.pages.TestBase {


 
  @Test
  public void FoundFilm() throws Exception {
	 
	ArrayList<Film> filmList = new ArrayList<Film>();

	filmList = app.getFilmHelper().search("Best");
	//System.out.println(filmList.size());
	assertTrue(app.getFilmHelper().isSearchResultAs(filmList,"Best"));

	
  }  
 
  

}
