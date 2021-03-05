package br.com.alura.leilao.leiloes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import br.com.alura.leilao.PageObject;

public class LeiloesPage extends PageObject {
	
	private static final String URL_LEILAO_CADASTRO = "http://localhost:8080/leiloes/new";
	private static final String URL_LEILOES = "http://localhost:8080/leiloes";
	
	public LeiloesPage(WebDriver browser) {
		super(browser);
	}

	public CadastroLeilaoPage carregarFormulario() {
		this.browser.navigate().to(URL_LEILAO_CADASTRO);
		return new CadastroLeilaoPage(browser);
	}

	public boolean possuiLeilaoCadastrado(String nome, String valorInicial, String dataAbertura) {
		WebElement ultimaLinhaTabela = this.browser.findElement(By.cssSelector("#tabela-leiloes tbody tr:last-child"));
		WebElement colunaNome = ultimaLinhaTabela.findElement(By.cssSelector("td:nth-child(1)"));
		WebElement colunaDataAbertura = ultimaLinhaTabela.findElement(By.cssSelector("td:nth-child(2)"));
		WebElement colunaValorInicial = ultimaLinhaTabela.findElement(By.cssSelector("td:nth-child(3)"));
		
		return colunaNome.getText().equals(nome) 
				&& colunaValorInicial.getText().equals(valorInicial)
				&& colunaDataAbertura.getText().equals(dataAbertura); 
	}

	public boolean ehPaginaAtual() {
		return this.browser.getCurrentUrl().equals(URL_LEILOES);
	}
}
