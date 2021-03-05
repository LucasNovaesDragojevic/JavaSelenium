package br.com.alura.leilao.login;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LoginPage {

	private static final String URL_LOGIN = "http://localhost:8080/login";
	private WebDriver browser;
	
	LoginPage() {
		System.setProperty("webdriver.gecko.driver", "drivers/geckodriver");
		browser = new FirefoxDriver();
		browser.navigate().to(URL_LOGIN);
	}

	public void fechar() {
		browser.quit();
	}

	public void preencheFormularioDeLogin(String username, String password) {
		browser.findElement(By.id("username")).sendKeys(username);
		browser.findElement(By.id("password")).sendKeys(password);
	}

	public void efetuaLogin() {
		browser.findElement(By.id("submit")).click();
	}

	public boolean ehPaginaDeLogin() {
		return browser.getCurrentUrl().equals(URL_LOGIN);
	}
	
	public boolean ehPaginaDeLoginComDadosInvalidos() {
		return browser.getCurrentUrl().equals(URL_LOGIN + "?error");
	}

	public Object nomeUsuarioLogado() {
		try {
			return browser.findElement(By.id("usuario-logado")).getText();
		} catch (NoSuchElementException e) {
			return null;
		}
	}

	public void navegaParaPaginaRestrita() {
		browser.navigate().to("http://localhost:8080/leiloes/1");	
	}

	public boolean contemTexto(String texto) {
		return browser.getPageSource().contains(texto);
	}
}
