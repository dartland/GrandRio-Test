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
		
		//�� ���� ����� ���������� ����������� ��������� ��������������� ����
		//������� ����� ��������� ��� ������������ (���������)
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
		return isLoggedIn() && getLoggedUser().getEmail().equals(user.getLogin()); // � ��� ����� = email
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
	//������ �� ������� ��� ��� ��������!!!!
	//������� �����, ��� �e��� waitPageLoaded() ���������� �� Page (LoginPage extends Page),
	//� �� � ���� ������� �������� ����� ensurePageLoaded() �� �� Page, � �� LoginPage, ������� �������������
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
			//��������� �������������� ������, ���� ��� ����������
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
			CharSequence checkMail = "����������, ��������� �����";
			CharSequence maxRegistration = "��������� �������� �����������";
			pages.alertPage.clickAlertPageCloseButton(); //� ����� ������ ��������� ���� ������
			//System.out.println(alertString);
			//��� ����� �������� ��������, ����� ����� ��������: ���� ��������� ��������, �� ������ ������
			if(alertString.contains(maxRegistration)){
				pages.registrationPage.clickRegistrationPageCloseButton();
				return alertIs;
			}
			// � ���� �������� �����������, ������� �������� ��� � �����, ��... ���� �� �������, ��� ������
			if(alertString.contains(checkMail)) 
			return pages.externalPage.waitPageLoaded();
		}
		
		return alertIs;
	}

	@Override
	public void takeScreenShot(ITestResult result) {
		
		//result ��� ���������� ��� ������������ ����� ����� ��������� �� ������� ����� ������
		String fileSeperator = System.getProperty("file.separator");
		try {
			System.out.println("��� ������������ �����:"+result.getName()
				+"  ��� ������:"+result.getInstanceName()); 
			File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			File targetFile = new File("C:"+fileSeperator
					+"Screenshoots"+fileSeperator+"screenshot.jpg");
			FileUtils.copyFile(screenshotFile, targetFile);
		} catch (Exception e) {
			System.out.println("====>>> An exception occured while taking screenshot " + e.getCause());
		}
	}
	
	@Override
	//��� ������, ������� ���������� ������� �������
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
		if (pages.microgamingPage.getSizeOfMicrogamingGameList()>0) //���� � �� �� �������
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
