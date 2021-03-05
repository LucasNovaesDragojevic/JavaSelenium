package br.com.alura.leilao.leiloes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.alura.leilao.login.LoginPage;

public class LeilaoTest {
	
	private LeiloesPage leiloesPage;
	private CadastroLeilaoPage cadastroLeilaoPage;
	
	@BeforeEach
	private void beforeEach() {
		LoginPage loginPage = new LoginPage();
		loginPage.preencheFormularioDeLogin("fulano", "pass");
		this.leiloesPage = loginPage.efetuaLogin();
		this.cadastroLeilaoPage = this.leiloesPage.carregarFormulario();		
	}
	
	@AfterEach
	private void afterEach() {
		leiloesPage.fechar();
	}
	
	@Test
	void deveCadastrarLeilao() {
		String hoje = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		String nome = "Leilao do dia " + hoje;
		String valor = "500.00";
		
		this.leiloesPage = cadastroLeilaoPage.cadastrarLeilao(nome, valor, hoje);
		
		Assert.assertTrue(leiloesPage.possuiLeilaoCadastrado(nome, valor, hoje));
	}
	
	@Test
	void deveValidarCadastroLeilao() {
		this.leiloesPage = cadastroLeilaoPage.cadastrarLeilao("", "", "");
		
		Assert.assertFalse(this.cadastroLeilaoPage.ehPaginaAtual());
		Assert.assertTrue(this.leiloesPage.ehPaginaAtual());
		Assert.assertTrue(this.cadastroLeilaoPage.mensagensDeValidacoesEstaoVisiveis());
	}
}
