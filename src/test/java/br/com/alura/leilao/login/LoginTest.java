package br.com.alura.leilao.login;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LoginTest {

	private static final String URL_LOGIN = "http://localhost:8080/login";
	private WebDriver browser;
	
	@BeforeAll
	private static void beforeAll() {
		System.setProperty("webdriver.gecko.driver", "drivers/geckodriver");
	}
	
	@BeforeEach
	private void beforEach() {
		browser = new FirefoxDriver();
		browser.navigate().to(URL_LOGIN);
	}
	
	@AfterEach
	private void afterEach() {
		browser.quit();
	}

	@Test
	void deveEfetuarLogin() {
		browser.findElement(By.id("username")).sendKeys("fulano");
		browser.findElement(By.id("password")).sendKeys("pass");
		browser.findElement(By.id("submit")).click();
		
		Assert.assertFalse(browser.getCurrentUrl().equals(URL_LOGIN));
		Assert.assertEquals("fulano", browser.findElement(By.id("usuario-logado")).getText());
	}
	
	@Test
	void naoDeveEfeutarLogin() {
		browser.findElement(By.id("username")).sendKeys("aaaa");
		browser.findElement(By.id("password")).sendKeys("aaaa");
		browser.findElement(By.id("submit")).click();
		
		Assert.assertTrue(browser.getCurrentUrl().equals(URL_LOGIN + "?error"));
		Assert.assertTrue(browser.getPageSource().contains("Usuário e senha inválidos."));
		Assert.assertThrows(NoSuchElementException.class, () -> browser.findElement(By.id("usuario-logado")).getText());
	}
	
	@Test
	void naoDeveAcessarPaginaRestritaSemEstarLogado() {
		browser.navigate().to("http://localhost:8080/leiloes/1");
		
		Assert.assertTrue(browser.getCurrentUrl().equals(URL_LOGIN));
		Assert.assertFalse(browser.getPageSource().contains("Dados do leilão"));
	}
}
