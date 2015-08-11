package ru.st.selenium.pages;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import ru.yandex.qatools.allure.annotations.Step;

public class MicrogamingPage extends InternalPage  { 

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
	
	@FindBy(css = "div.mCSB_dragger_bar")
	private WebElement graggerBar;

	public MicrogamingPage clickMicrogamingSlotLink() {
		microgamingSlotLink.click();
		return pages.microgamingPage;
	}

	public Object[][] getListMicrogamingSlotGame() {
		String id=""; int gameCounter = 0;
		//Object[][] gameArray = new String[microgamingSlotList.size()][1];
		List<WebElement> GamesSlot = driver.findElements(By.className("game_cell"));
		Object[][] gameArray = new String[GamesSlot.size()-1][1]; //-1 потому, что среди game_cell одна ячейка пустая
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
		return GamesSlot.size();
	}

	@Step("Перемещаем полосу прокрутки к игре")
	public void moveDraggerToGame(String game_id, int Dragger1, int Dragger2 ) {
        //Dragger1 смещение при скроллинге
		//Dragger2 смещение после скроллинга до полной видимости
		Actions builder = new Actions(driver);
		builder.build();
		Boolean flag=false;
		String byXPathName = ".//*[@id='"+game_id+"']/b";
		while (!driver.findElement(By.xpath(byXPathName)).isDisplayed())
		{
		    builder.dragAndDropBy(graggerBar,0,Dragger1).perform();//понемногу двигаем полосу прокрутки
			flag=true;
		};
		if(flag) {
			builder.dragAndDropBy(graggerBar,0,Dragger2).perform();// продвинем немного ещё, чтобы сработал метод подсветки кнопок
		}
		builder.dragAndDropBy(graggerBar,1,0).perform(); 
	}
	
	public void moveCursorAndClickDemoButton(String game_id) {
		String byXPathName = ".//*[@id='"+game_id+"']/b";
		String byXPathButtonDemo = ".//*[@id='"+game_id+"']/div[2]/a[2]";
		new Actions(driver).moveToElement(    //подводим указатель мыши к названию игры, должны появиться кнопки, нажимаем демо
	            driver.findElement(By
	            		.xpath(byXPathName)))
	                    .perform(); 
		
		if  (isElementPresent(By.xpath(byXPathButtonDemo))){
			WebElement buttonDemo = driver.findElement(By.xpath(byXPathButtonDemo));
			new Actions(driver).moveToElement(buttonDemo).perform();
			buttonDemo.click();
			
		}	else {System.out.println("Кнопка Demo отсутствует");}
	}
	
	public void switchToGameFrame() {
		
	}
		
	
	public boolean checkMicrogamingGame(String game) {
		System.out.println("id == '"+game+"'");
		moveDraggerToGame(game, 1, 1);
		moveCursorAndClickDemoButton(game);
		switchToGameFrame();
		return true;
	}	
	
	private boolean isElementPresent(By by) {
	    try {
		     driver.findElement(by);
		     return true;
		    } catch (NoSuchElementException e) {
		     return false;
		    }
	}
		
}



//wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("game_cell"))); 
//System.out.println("+++++>"+microgamingSlotList.size());
//if (microgamingSlotList.size()>0)
//	return microgamingSlotList.size();
//else
