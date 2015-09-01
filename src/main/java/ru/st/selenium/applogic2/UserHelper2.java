package ru.st.selenium.applogic2;


import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import ru.st.selenium.applogic.UserHelper;
import ru.st.selenium.model.User;
import ru.st.selenium.pages.UserProfilePage;

public class UserHelper2 extends DriverBasedHelper implements UserHelper {

	public UserHelper2(ApplicationManager2 manager) {
		super(manager.getWebDriver());
	}

	@Override
	public void loginAs(User user) {
		pages.loginPage.ensurePageLoaded()
			.setUsernameField(user.getLogin())
			.setPasswordField(user.getPassword())
			.clickSubmitButton();
	}
	
	@Override
	public void addNewUserAs(User user) {
		
		//на этом этапе появляется возможность появления информационного окна
		//поэтому нужно проверить его существовние (видимость)
		if(pages.registrationPage.isAlertPresent()) {closeAlertPage();}
		pages.registrationPage.ensurePageLoaded()
		.setEmailField(user.getEmail())
		.setPasswordField(user.getPassword())
		.setCurrencyRUBRadio()
		.setiAgreeRulesBox()
		.setIAgree18Box()
		.clickRegisrationSubmitButton();
	}
	
	
	public void closeAlertPage() {
		boolean alertIs = pages.alertPage.waitPageLoaded();
		if (alertIs) pages.alertPage.clickAlertPageCloseButton();
	}

	@Override
	public void logout() {
		pages.internalPage.ensurePageLoaded().clickExitButton();
	}

	@Override
	public boolean isLoggedIn() {
		return pages.internalPage.waitPageLoaded();
	}

	@Override
	public boolean isLoggedInAs(User user) {
		return isLoggedIn() && getLoggedUser().getEmail().equals(user.getLogin()); // у нас логин = email
	}

	@Override
	public boolean isLoginPageNotLoggedIn() {
		boolean notLogged = pages.loginPage.waitPageLoaded();
		if (notLogged) {
			pages.loginPage.clickLoginPageCloseButton();
		}

		return notLogged;
	}

	@Override
	//нифига не понимаю как это работает!!!!
	//догадка такая, что мeтод waitPageLoaded() вызывается из Page (LoginPage extends Page),
	//а он в свою очередь вызывает метод ensurePageLoaded() не из Page, а из LoginPage, который переопределен
	public boolean isNotLoggedIn() {
		return pages.externalPage.waitPageLoaded();
	}

	private User getLoggedUser() {
		UserProfilePage userProfile = pages.internalPage.ensurePageLoaded().clickMyProfileButton().ensurePageLoaded();
		// System.out.println(userProfile.getEmail());
		return new User()
				// .setLogin(userProfile.getFirstName())
				.setEmail(userProfile.getEmail());
	}
	
	@Override
	public boolean isNotRegisteredIn() {
		boolean notRegistered = pages.registrationPage.waitPageLoaded();
		if (notRegistered) {
			//закрываем информационное окошко, если оно появляется
			if(pages.registrationPage.isAlertPresent()) {closeAlertPage();}
			pages.registrationPage.clickRegistrationPageCloseButton();
		}
		return notRegistered;
	}

	@Override
	public boolean isRegisteredIn() {

		boolean alertIs = pages.alertPage.waitPageLoaded();
		
		if (alertIs) {
			String alertString = pages.alertPage.getAlertText();
			CharSequence checkMail = "Пожалуйста, проверьте почту";
			CharSequence maxRegistration = "Достигнут максимум регистраций";
			pages.alertPage.clickAlertPageCloseButton(); //в любом случае закрываем окно алерта
			//System.out.println(alertString);
			//тут нужно заделать проверку, какой алерт выскочил: если достигнут максимум, то нажать кнопку
			if(alertString.contains(maxRegistration)){
				pages.registrationPage.clickRegistrationPageCloseButton();
				return alertIs;
			}
			// а если успешная регистрация, которая доступна раз в сутки, то... пока не понятно, что делать
			if(alertString.contains(checkMail)) 
			return pages.externalPage.waitPageLoaded();
		}
		
		return alertIs;
	}

	@Override
	public void takeScreenShot(ITestResult result) {
		
		//result нам необходима для формирования имени файла скриншота из методов этого класса
		String fileSeperator = System.getProperty("file.separator");
		try {
			System.out.println("Имя проваленного теста:"+result.getName()
				+"  Имя класса:"+result.getInstanceName()); 
			File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			File targetFile = new File("C:"+fileSeperator
					+"Screenshoots"+fileSeperator+"screenshot.jpg");
			FileUtils.copyFile(screenshotFile, targetFile);
		} catch (Exception e) {
			System.out.println("====>>> An exception occured while taking screenshot " + e.getCause());
		}
	}
	
	@Override
	//моя фунция, которая возвращает текущий драйвер
	public WebDriver getWebDriver() {
		return driver;
	}

	@Override
	public boolean isMicrogamingSlotGamesPresent() {
		if (pages.microgamingPage.getSizeOfMicrogamingGameList()>0)
			return true;
		else
			return false;
	}
	
	@Override
	public boolean isNetEntSlotGamesPresent() {
		if (pages.microgamingPage.getSizeOfMicrogamingGameList()>0) //одна и та же функция
			return true;
		else
			return false;
	}
	
	@Override
	public boolean isMicrogamingTableGamesPresent() {
		if (pages.microgamingPage.getSizeOfMicrogamingGameList()>0)
			return true;
		else
			return false;
	}

	@Override
	public Object[][] getListMicrogamingGame() {
        Object[][] gameArray= pages.microgamingPage.getListMicrogamingGame();
        return(gameArray);
	}

	@Override
	public boolean isMicrogamingGameRun(String game) {
		return pages.microgamingPage.checkMicrogamingGame(game);
	}



}
