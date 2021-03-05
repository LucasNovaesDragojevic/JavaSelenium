package br.com.alura.leilao;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class HelloWrorldSelenium {

	@Test
	void helloTest() {
		System.setProperty("webdriver.gecko.driver", "drivers/geckodriver");
		WebDriver browser = new FirefoxDriver();
		browser.navigate().to("http://localhost:8080/leiloes");
		browser.quit();
	}
}
