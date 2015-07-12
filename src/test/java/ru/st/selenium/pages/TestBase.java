package ru.st.selenium.pages;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;

import ru.st.selenium.applogic.ApplicationManager;
import ru.st.selenium.applogic2.ApplicationManager2;

public class TestBase {

	protected ApplicationManager app;

	@BeforeClass
	public void init() {
		app = new ApplicationManager2();
		//тыкаю в какой-то элемент
		app.getNavigationHelper().hideBannerLink();
	}

	@AfterSuite
	public void stop() {
		app.stop();
		
	}

}
