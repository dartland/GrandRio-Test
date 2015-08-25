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

//	//��� ��� ����� ���������� ���
//	public Object[][] getListMicrogamingGame() {
//		String id=""; int gameCounter = 0; 
//		List<WebElement> GamesSlot = driver.findElements(By.className("game_cell"));
//		Object[][] gameArray = new String[GamesSlot.size()-1][1]; //-1 ������, ��� ����� game_cell ���� ������ ������
//		for (WebElement microgamingSlotCell : GamesSlot) {
//	    	id= String.format("%s",microgamingSlotCell.getAttribute("id")); 
//	    	if(!id.equals("")){
//	    		gameArray[gameCounter][0] = id;
//	    		gameCounter++;
//	    	}
//	    }
//		return gameArray;
//	}
	
	// ��� ����� ���������� 16-�� ���
	public Object[][] getListMicrogamingGame() {
	String id=""; int gameCounter = 0; 
	List<WebElement> GamesSlot = driver.findElements(By.className("game_cell"));
	int numberOfGame = 0;
	Object[][] gameArray = new String[15][1]; //-1 ������, ��� ����� game_cell ���� ������ ������
	for (WebElement microgamingSlotCell : GamesSlot) {
    	id= String.format("%s",microgamingSlotCell.getAttribute("id")); 
    	if(!id.equals("")){
    		gameArray[gameCounter][0] = id;
    		gameCounter++;
    	}
    	numberOfGame++;
    	if(numberOfGame==16)
    		break;
    }
	return gameArray;
}	

	public int getSizeOfMicrogamingGameList() {
		List<WebElement> GamesSlot = driver.findElements(By.className("game_cell"));
		return GamesSlot.size();
	}

	@Step("���������� ������ ��������� � ����")
	public boolean moveDraggerToGameAndCkickDemoButton(String game_id, int Dragger1, int Dragger2 ) {
        //Dragger1 �������� ��� ����������
		//Dragger2 �������� ����� ���������� �� ������ ���������
		Actions builder = new Actions(driver);
		builder.build();
		Boolean flag=false;
		String byXPathName = ".//*[@id='"+game_id+"']/b";
		System.out.println("������� ����� �������: "+byXPathName);
		while (!driver.findElement(By.xpath(byXPathName)).isDisplayed()) {
		    builder.dragAndDropBy(graggerBar,0,Dragger1).perform();//��������� ������� ������ ���������
			flag=true;
		};
		if(flag) {
			builder.dragAndDropBy(graggerBar,0,Dragger2).perform();// ��������� ������� ���, ����� �������� ����� ��������� ������
		}
		builder.dragAndDropBy(graggerBar,1,0).perform();
		
		driver.findElement(By.xpath(byXPathName)).click(); //������������� ������ ����� �������� �� ������� � �����
		
		return  moveCursorAndClickDemoButton(game_id, builder);

		
	}
	
	public boolean moveCursorAndClickDemoButton(String game_id, Actions builder) {
		String byXPathName = ".//*[@id='"+game_id+"']/b";
		String byXPathButtonDemo = ".//*[@id='"+game_id+"']/div[2]/a[2]";
		builder.moveToElement(    //�������� ��������� ���� � �������� ����, ������ ��������� ������, �������� ����
	            driver.findElement(By.xpath(byXPathName))).perform(); 
		
		if  (isElementPresent(By.xpath(byXPathButtonDemo))){
			WebElement buttonDemo = driver.findElement(By.xpath(byXPathButtonDemo));
			if(buttonDemo.isDisplayed()) {
				builder.moveToElement(buttonDemo).perform();
				buttonDemo.click();
				return true; //"Button_is_Displayed";
			}	
				else {	
					System.out.println("���������� ��� �������� ������ ����");
					makeScreenshot();
					return false;//"Button_isn't_Displayed";
			}
			
		}	
		System.out.println("����������� ������ ����");
		return false;//"Button_isn't_Present";
	}
	
	public boolean isSwitchToGameFrame() { //throws Exception {
		
		System.out.println("������� �� ����� � �����, ����� ���� �������� ������");
		
		try {
	    	driver.switchTo().frame("play_box"); // ������� �� ����� � �����, ����� ���� �������� ������
		} 	catch (Exception e) {
			makeScreenshot();
			System.out.println("��� �����?");
			return false;
		}
		
		//������������� �� ����� @class,'egamings_game_frame � ������� ����� ���� ����
		if(isElementPresent(By.xpath("//iframe[contains(@class,'egamings_game_frame')]"))) {
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@class,'egamings_game_frame')]"))); 	
		} else { 
			System.out.println("�� ������ iframe[contains(@class,'egamings_game_frame')]");
			return false;
		}
		// ���� ����� � ����� ��������, �� ���� ���� � ��������� �� �� 100% �������� 
		if(isElementPresent(By.xpath(".//*[@id='system']"))) {
			//System.out.println("�� ����� �� ����� � �����");	
			//FlashObjectWebDriver flashApp = new FlashObjectWebDriver(driver, "system");
			//System.out.println("���� ��������� ��:"+flashApp.callFlashObject("PercentLoaded")+"%, ���������� �������� �������");
		} else { 
			System.out.println("�� ������ ���� � id=system");
			noSuchElementExceptionAttachment("�� ������ ���� � id=system"); makeScreenshot();
			//return false; ���������� ��� ��� ����� � ����
			   }
		// ������� �� ������ � ������, ��� ����� ������ ������ close, � ��� ���������� �� ����� � �����
		driver.switchTo().defaultContent();
		if(isElementPresent(By.xpath("//*[@id='play_box']"))) {
			driver.switchTo().frame("play_box");
			//Thread.sleep(100L); �������� ���� throws Exception
			new Actions(driver).moveToElement(driver.findElement(By.xpath(".//*[@id='game_close']"))).perform();
			driver.findElement(By.xpath(".//*[@id='game_close']")).click(); 
			driver.switchTo().defaultContent();
			System.out.println("��� ������ ���������");
			return true;
			
		} else { 
			System.out.println("�� ������ iframe  id=play_box");
			return false;
		}  	
	}
		
	
	public boolean checkMicrogamingGame(String game) {
		String byXPathName = ".//*[@id='"+game+"']/b";
		System.out.println("��������� ���� id == '"+game+"'");
		isElementPresent(By.xpath(byXPathName));
		driver.findElements(By.className("game_cell"));
		WebElement element =  driver.findElement(By.xpath(byXPathName));
		//nameOfGameAttachment(driver.findElement(By.xpath(byXPathName)).getText());
		if(moveDraggerToGameAndCkickDemoButton(game, 1, 1))
			return isSwitchToGameFrame();
		else
			return false;
	}



	
}




//wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("game_cell"))); 
//System.out.println("+++++>"+microgamingSlotList.size());
//if (microgamingSlotList.size()>0)
//	return microgamingSlotList.size();
//else
