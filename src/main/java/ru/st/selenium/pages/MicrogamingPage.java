package ru.st.selenium.pages;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import ru.yandex.qatools.allure.annotations.Step;

public class MicrogamingPage extends InternalPage  { 

	public MicrogamingPage(PageManager pages) {
		super(pages);
	}

	@Override
	public MicrogamingPage ensurePageLoaded() {
		super.ensurePageLoaded();
		wait.until(presenceOfElementLocated(By.xpath("(//a[@onclick='RIO.getGames(this, 997);'])[1]")));
		//wait.until(presenceOfElementLocated(By.id("???")));
		return this;
	}
	
	@FindBy(xpath = "(//a[@onclick='RIO.getGames(this, 997);'])[2]")
	private WebElement microgamingSlotLink;
	
	@FindBy(xpath = "(//a[@onclick='RIO.getGames(this, 997);'])[3]")
	private WebElement microgamingTableLink;
	
	@FindBy(xpath = "(//a[@onclick='RIO.getGames(this, 992);'])[2]")
	private WebElement netEntSlotLink;	
	
	@FindBys({@FindBy(className = "game_cell")})
	private List<WebElement> microgamingSlotList;
	
	@FindBy(css = "div.mCSB_dragger_bar")
	private WebElement graggerBar;

	public MicrogamingPage clickMicrogamingSlotLink() {
		microgamingSlotLink.click();
		return pages.microgamingPage;
	}
	
	public MicrogamingPage clickNetEntSlotLink() {
		netEntSlotLink.click();
		return pages.microgamingPage;
	}
	
	public MicrogamingPage clickMicrogamingTableLink() {
		microgamingTableLink.click();
		return pages.microgamingPage;
	}	

	public Object[][] getListMicrogamingGame() {
		String id=""; int gameCounter = 0; 
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

	public int getSizeOfMicrogamingGameList() {
		List<WebElement> GamesSlot = driver.findElements(By.className("game_cell"));
		return GamesSlot.size();
	}

	@Step("Перемещаем полосу прокрутки к игре")
	public boolean moveDraggerToGameAndCkickDemoButton(String game_id, int Dragger1, int Dragger2 ) {
        //Dragger1 смещение при скроллинге
		//Dragger2 смещение после скроллинга до полной видимости
		Actions builder = new Actions(driver);
		builder.build();
		Boolean flag=false;
		String byXPathName = ".//*[@id='"+game_id+"']/b";
		while (!driver.findElement(By.xpath(byXPathName)).isDisplayed()) {
		    builder.dragAndDropBy(graggerBar,0,Dragger1).perform();//понемногу двигаем полосу прокрутки
			flag=true;
		};
		if(flag) {
			builder.dragAndDropBy(graggerBar,0,Dragger2).perform();// продвинем немного ещё, чтобы сработал метод подсветки кнопок
		}
		builder.dragAndDropBy(graggerBar,1,0).perform();
		
		driver.findElement(By.xpath(byXPathName)).click(); //позиционируем кликом фокус браузера на элемент с игрой
		
		return  moveCursorAndClickDemoButton(game_id, builder);

		
	}
	
	public boolean moveCursorAndClickDemoButton(String game_id, Actions builder) {
		String byXPathName = ".//*[@id='"+game_id+"']/b";
		String byXPathButtonDemo = ".//*[@id='"+game_id+"']/div[2]/a[2]";
		builder.moveToElement(    //подводим указатель мыши к названию игры, должны появиться кнопки, нажимаем демо
	            driver.findElement(By.xpath(byXPathName))).perform(); 
		
		if  (isElementPresent(By.xpath(byXPathButtonDemo))){
			WebElement buttonDemo = driver.findElement(By.xpath(byXPathButtonDemo));
			if(buttonDemo.isDisplayed()) {
				builder.moveToElement(buttonDemo).perform();
				buttonDemo.click();
				return true; //"Button_is_Displayed";
			}	
				else {	
					System.out.println("недоступна для кликанья кнопка Демо");
					makeScreenshot();
					return false;//"Button_isn't_Displayed";
			}
			
		}	
		System.out.println("отсутствует кнопка Демо");
		return false;//"Button_isn't_Present";
	}
	
	public boolean isSwitchToGameFrame() { //throws Exception {
		
		System.out.println("заходим во фрэйм с игрой, чтобы были доступны кнопки");
		
		try {
	    	driver.switchTo().frame("play_box"); // заходим во фрэйм с игрой, чтобы были доступны кнопки
		} 	catch (Exception e) {
			makeScreenshot();
			System.out.println("где фрэйм?");
			return false;
		}
		
		//переключаемся на фрэйм @class,'egamings_game_frame в котором лежит наша флэш
		if(isElementPresent(By.xpath("//iframe[contains(@class,'egamings_game_frame')]"))) {
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@class,'egamings_game_frame')]"))); 	
		} else { 
			System.out.println("Не найден iframe[contains(@class,'egamings_game_frame')]");
			return false;
		}
		// если фрейм с игрой открылся, то ищем флэш и тестируем ее на 100% загрузку 
		if(isElementPresent(By.xpath(".//*[@id='system']"))) {
			//System.out.println("Мы вошли во фрэйм с игрой");	
			//FlashObjectWebDriver flashApp = new FlashObjectWebDriver(driver, "system");
			//System.out.println("Игра загружена на:"+flashApp.callFlashObject("PercentLoaded")+"%, управление передано плагину");
		} else { 
			System.out.println("Не найден флэш с id=system");
			noSuchElementExceptionAttachment("Не найден флэш с id=system"); makeScreenshot();
			//return false; попытаться ещё раз войти в игру
			   }
		// выходим из фрэйма в корень, нам нужно нажать кнопку close, а она недоступна из фрэма с игрой
		driver.switchTo().defaultContent();
		if(isElementPresent(By.xpath("//*[@id='play_box']"))) {
			driver.switchTo().frame("play_box");
			//Thread.sleep(100L); работает если throws Exception
			new Actions(driver).moveToElement(driver.findElement(By.xpath(".//*[@id='game_close']"))).perform();
			driver.findElement(By.xpath(".//*[@id='game_close']")).click(); 
			driver.switchTo().defaultContent();
			System.out.println("все прошло нормально");
			return true;
			
		} else { 
			System.out.println("НЕ найден iframe  id=play_box");
			return false;
		}  	
	}
		
	
	public boolean checkMicrogamingGame(String game) {
		System.out.println("id == '"+game+"'");
		nameOfGameAttachment(driver.findElement(By.xpath(".//*[@id='"+game+"']/b")).getText());
		if(moveDraggerToGameAndCkickDemoButton(game, 1, 1))
			{return isSwitchToGameFrame();}
		else
			{return false;}
	}



	
}




//wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("game_cell"))); 
//System.out.println("+++++>"+microgamingSlotList.size());
//if (microgamingSlotList.size()>0)
//	return microgamingSlotList.size();
//else
