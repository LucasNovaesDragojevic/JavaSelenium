package br.com.alura.leilao;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class PageObject {

	protected WebDriver browser;

	public PageObject(WebDriver browser) {
		System.setProperty("webdriver.gecko.driver", "drivers/geckodriver");
		
		if (browser == null)
			this.browser = new FirefoxDriver();
		else
			this.browser = browser;
		
		this.browser.manage()
					.timeouts()
					.implicitlyWait(2, TimeUnit.SECONDS)
					.pageLoadTimeout(10, TimeUnit.SECONDS);
	}
	
	public void fechar() {
		browser.quit();
	}
}
