package ru.st.selenium.pages;

import static org.junit.Assert.assertTrue;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import ru.st.selenium.applogic.ApplicationManager;
import ru.st.selenium.applogic2.ApplicationManager2;
import ru.st.selenium.model.User;

public class TestBase {

	protected ApplicationManager app;

	@BeforeClass
	public void init() {
		app = new ApplicationManager2();
		//тыкаю в какой-то элемент , чтобы свалил баннер
		app.getNavigationHelper().hideBannerLink();
	}

	@AfterSuite
	public void stop() {
		app.stop(); 
		
	}
	
}