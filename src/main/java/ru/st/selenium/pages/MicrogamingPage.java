package ru.st.selenium.pages;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

public class MicrogamingPage extends InternalPage  { //тут должно быть расширение другого класса

	public MicrogamingPage(PageManager pages) {
		super(pages);
	}

	public MicrogamingPage ensurePageLoaded() {
		super.ensurePageLoaded();
		wait.until(presenceOfElementLocated(By.xpath("//a[@onclick='RIO.getGames(this, 997);'])[1]")));
		//wait.until(presenceOfElementLocated(By.id("???")));
		return this;
	}
	
	@FindBy(xpath = "//a[@onclick='RIO.getGames(this, 997);'])[2]")
	private WebElement microgamingSlotLink;
	
	@FindBys({@FindBy(className = "game_cell")})
	private List<WebElement> microgamingSlotList;

	public MicrogamingPage clickMicrogamingSlotLink() {
		microgamingSlotLink.click();
		return pages.microgamingPage;
		
	}

	public Object[][] getListMicrogamingSlotGame() {
		String id=""; int gameCounter = 0;
		String[][] gameArray = new String[microgamingSlotList.size()][];
		for (WebElement microgamingSlotCell : microgamingSlotList) {
	    	id= String.format("%s",microgamingSlotCell.getAttribute("id")); 
	    	if(!id.equals("")){
	        //System.out.println("Значение 'id="+id+"'");
	    	gameArray[gameCounter][0] = id;
	    	gameCounter++;
	    	}
	    }
		return gameArray;
	}	
}
