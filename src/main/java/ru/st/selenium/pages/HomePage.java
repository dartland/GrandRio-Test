package ru.st.selenium.pages;

import static org.junit.Assert.fail;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import ru.st.selenium.model.Film;


public class HomePage extends InternalPage {

	public HomePage(PageManager pages) {
		super(pages);
	}
	
  @FindBy(xpath = "//img[@alt='Add movie']")
  private WebElement addMovieButton;
 
  @FindBy(id = "q")
  private WebElement searchField;  
  
  @FindBy(css = "div.content")
  private WebElement noMoviesWhereFound;  
  
  @FindBy(id = "results")
  private WebElement filmContainer;  
  
  
  @FindBys({@FindBy(id = "results"),
      @FindBy(tagName = "a")})
  private List<WebElement> filmList;
  
    
  public boolean isSearchResultAs(ArrayList<Film> filmModelList, String searchString) {
		
		boolean isAs = true; 
		// ежели, хоть один фильм не содержит вхождения строки поиска, возвращаем false
	    for (int i = 0; i < filmModelList.size(); i++) {
			
	    	CharSequence cs1 = searchString.toLowerCase();
			if (filmModelList.get(i).getTitle().toLowerCase().contains(cs1) == false) 
				{isAs = false;}
	    	//System.out.println(filmModelList.get(i).getTitle().toLowerCase().contains(cs1));
		  }
		
		return isAs;
	}
  
  public ArrayList<Film> ensureFilmsLoaded(String title) {
	  
	  ArrayList<Film> movieList = new ArrayList<Film>();
	  //searchField.clear(); searchField.sendKeys(""+Keys.RETURN);
	  setSearchFieldAndPressEnterButton("");
	  //System.out.println(filmList.size());
	  //setSearchFieldAndPressEnterButton(title);
	  searchField.clear(); searchField.sendKeys(title+Keys.RETURN);
	  

	  // ожидание исчезновения списка
	  for (int count = 0;; count ++) {
  	    if (count >= 30)
  	        throw new TimeoutException();
	  try {
		    filmList.size();
	    } catch (StaleElementReferenceException e) 
	  		{break;}
	  }

	  //System.out.println(filmList.size());
	  
      String NameFilm;

      for (int i = 0; i < filmList.size(); i++) {
    	  
		WebElement film_cell = filmList.get(i);
		NameFilm = film_cell.findElement(By.className("title")).getText();
		//System.out.println("film name ="+NameFilm);
		//теперь заполним отсюда список film
		Film film = new Film().setTitle(NameFilm);
		movieList.add(i, film);
		//System.out.println("film name ="+film.getTitle());
	  }
	  
    return movieList;
    //  return null;
    
  }

  
  public String getEmptySearchText() {
	 
	 //возможно стоит добавить ожидание видимости 
	 return noMoviesWhereFound.getText(); 
	  
  }
  
  
  public HomePage setSearchFieldAndPressEnterButton(String searchString) {
	    searchField.clear();
	    searchField.sendKeys(searchString+Keys.RETURN);
	    
	    return pages.homePage;
	  } 
  
 
  public AddFilmPage clickMovieButton() {
	    addMovieButton.click();
	    return pages.addFilmPage;
	  }   
  
  public FilmContentPage getFilmContent(String nameFilm) {
	    
	  List<WebElement> Films = filmContainer.findElements(By.tagName("a"));
	  int FilmsSize = Films.size();
	  if(FilmsSize>0) {
		Films.get(6).click();
		}	  
	 return pages.filmContentPage;
	 } 

  public int getSize() {
	 List<WebElement> Films = filmContainer.findElements(By.tagName("a"));
	 return Films.size();
  }
  
  
  public HomePage ensurePageLoaded() {
    super.ensurePageLoaded();
    wait.until(presenceOfElementLocated(By.xpath("//img[@alt='Add movie']")));
    //wait.until(presenceOfElementLocated(By.cssSelector("div.content h2")));
    return this;
  }



}
