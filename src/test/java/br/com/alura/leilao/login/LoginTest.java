package br.com.alura.leilao.login;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginTest {
	
	private LoginPage loginPage;
	
	@BeforeEach
	private void beforeEach( ) {
		loginPage = new LoginPage();
	}
	
	@AfterEach
	private void afterEach() {
		loginPage.fechar();
	}

	@Test
	void deveEfetuarLogin() {
		loginPage.preencheFormularioDeLogin("fulano", "pass");
		loginPage.efetuaLogin();
		
		Assert.assertFalse(loginPage.ehPaginaDeLogin());
		Assert.assertEquals("fulano", loginPage.nomeUsuarioLogado());
	}
	
	@Test
	void naoDeveEfeutarLogin() {
		loginPage.preencheFormularioDeLogin("aaa", "aaa");
		loginPage.efetuaLogin();
		
		Assert.assertTrue(loginPage.ehPaginaDeLoginComDadosInvalidos());
		Assert.assertNull("fulano", loginPage.nomeUsuarioLogado());
		Assert.assertTrue(loginPage.contemTexto("Usuário e senha inválidos."));
	}
	
	@Test
	void naoDeveAcessarPaginaRestritaSemEstarLogado() {
		loginPage.navegaParaPaginaRestrita();

		Assert.assertTrue(loginPage.ehPaginaDeLogin());
		Assert.assertFalse(loginPage.contemTexto("Dados do leilão"));
	}
}
