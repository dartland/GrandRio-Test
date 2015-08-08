package ru.st.selenium.pages;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MicrogamingPage extends InternalPage  { //тут должно быть расширение другого класса

	public MicrogamingPage(PageManager pages) {
		super(pages);
	}

	public MicrogamingPage ensurePageLoaded() {
		super.ensurePageLoaded();
		wait.until(presenceOfElementLocated(By.xpath("(//a[@onclick='RIO.getGames(this, 997);'])[1]")));
		//wait.until(presenceOfElementLocated(By.id("???")));
		return this;
	}
	
	@FindBy(xpath = "(//a[@onclick='RIO.getGames(this, 997);'])[2]")
	private WebElement microgamingSlotLink;
	
	@FindBys({@FindBy(className = "game_cell")})
	private List<WebElement> microgamingSlotList;

	public MicrogamingPage clickMicrogamingSlotLink() {
		microgamingSlotLink.click();
		return pages.microgamingPage;
		
	}

	public Object[][] getListMicrogamingSlotGame() {
		String id=""; int gameCounter = 0;
		//Object[][] gameArray = new String[microgamingSlotList.size()][1];
		List<WebElement> GamesSlot = driver.findElements(By.className("game_cell"));
		Object[][] gameArray = new String[GamesSlot.size()-1][1]; //-1 потому, что среди game_cell одна €чейка пуста€
		for (WebElement microgamingSlotCell : GamesSlot) {
	    	id= String.format("%s",microgamingSlotCell.getAttribute("id")); 
	    	if(!id.equals("")){
	    		gameArray[gameCounter][0] = id;
	    		gameCounter++;
	    	}
	    }
		return gameArray;
	}

	public int getSizeOfMicrogamingSlotGameList() {
		
		List<WebElement> GamesSlot = driver.findElements(By.className("game_cell"));
//      wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("game_cell"))); 
//		System.out.println("+++++>"+microgamingSlotList.size());
		if (microgamingSlotList.size()>0)
			return microgamingSlotList.size();
		else
			return GamesSlot.size();
	}	
}
