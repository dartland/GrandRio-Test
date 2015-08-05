package ru.st.selenium.pages;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

import org.openqa.selenium.By;

public class MicrogamingPage extends InternalPage  { //��� ������ ���� ���������� ������� ������

	public MicrogamingPage(PageManager pages) {
		super(pages);
	}

	public MicrogamingPage ensurePageLoaded() {
		super.ensurePageLoaded();
		wait.until(presenceOfElementLocated(By.id("???")));
		wait.until(presenceOfElementLocated(By.id("???")));
		return this;
	}	
}
