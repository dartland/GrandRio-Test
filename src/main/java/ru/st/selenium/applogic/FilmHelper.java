package ru.st.selenium.applogic;

import java.util.ArrayList;
import java.util.List;

import ru.st.selenium.model.Film;

public interface FilmHelper {


	ArrayList<Film> search(String title);
	boolean isFullFilmNameEquals(Film film);
	boolean isInputErrorYear();
	boolean isInputErrorName();
	int getSizeOfFilmList();
	boolean isEmptySearchResult(String string);
	void searchNull(String title);
	
	boolean isSearchResultAs(ArrayList<Film> filmList, String string);

}
