package ru.st.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import ru.yandex.qatools.allure.annotations.Attachment;

public abstract class AnyPage extends Page {

	public AnyPage(PageManager pages) {
		super(pages);
	}

    public boolean isElementPresent(By by) {
	    try {
		     driver.findElement(by);
		     return true;
		    } catch (NoSuchElementException e) {
		    	noSuchElementExceptionAttachment("�� ������ ������� �� ��������: "+by.toString()); 	
		    	makeScreenshot();
		    	return false;
		    }
	}
	
    @Attachment("�� ������ ������� �� ��������")
    public String noSuchElementExceptionAttachment(String attacheString) {
        return attacheString;
    }
    
    @Attachment("���� ������� ����")
    public String nameOfGameAttachment(String attacheString) {
        return attacheString;
    }
    
    @Attachment
    public byte[] makeScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
    
    
}
