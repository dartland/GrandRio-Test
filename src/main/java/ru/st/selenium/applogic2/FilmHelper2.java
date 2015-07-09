package ru.st.selenium.applogic2;

import java.util.ArrayList;
import java.util.List;

import ru.st.selenium.applogic.FilmHelper;
import ru.st.selenium.model.Film;
import ru.st.selenium.pages.AddFilmPage;
import ru.st.selenium.pages.FilmContentPage;
import ru.st.selenium.pages.HomePage;

public class FilmHelper2 extends DriverBasedHelper implements FilmHelper {

  public FilmHelper2(ApplicationManager2 manager) {
    super(manager.getWebDriver());
  }

  @Override
  public boolean isSearchResultAs(ArrayList<Film> filmList, String searchString) {
  

	  return pages.homePage.isSearchResultAs(filmList,searchString);
	  //return true;
  }
  
  
  @Override
  public ArrayList<Film> search(String title) {
	  
	  return pages.homePage.ensureFilmsLoaded(title);
  }
  
  @Override
  public void searchNull(String title) {
    
	pages.homePage.ensurePageLoaded().setSearchFieldAndPressEnterButton(title);
	
  }
  
 
  @Override
  public boolean isEmptySearchResult(String stringText) {
    return pages.homePage.ensurePageLoaded().getEmptySearchText().equals(stringText);
  } 
  
  @Override
  public boolean isFullFilmNameEquals(Film film) {
	String FilmName = film.getTitle()+" ("+film.getYear()+")";   
    return pages.filmContentPage.ensurePageLoaded().getFullFilmName().equals(FilmName);
  }
  
  @Override
  public boolean isInputErrorYear() {
     return pages.addFilmPage.ensurePageLoaded().DisplaedLablelErrorYear();
   }
  
  @Override
  public boolean isInputErrorName() {
     return pages.addFilmPage.ensurePageLoaded().DisplaedLablelErrorName();
   }
  
  public int getSizeOfFilmList() {
	  return pages.homePage.getSize(); 
  }
  
 


}
