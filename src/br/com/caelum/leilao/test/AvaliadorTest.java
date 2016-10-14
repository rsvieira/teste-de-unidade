/**
 * 
 */
package br.com.caelum.leilao.test;

import static org.junit.Assert.assertEquals;

import java.util.Collections;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.caelum.leilao.builder.LeilaoBuilder;
import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.service.Avaliador;

/**
 * @author Ramon Vieira
 *
 */

public class AvaliadorTest {

	private Avaliador avaliador;
	private Usuario joao;
	private Usuario ramon;
	private Usuario maria;

	@Before
	public void setUp() {
		avaliador = new Avaliador();
		joao = new Usuario("Joao");
		ramon = new Usuario("Ramon");
		maria = new Usuario("Maria");
	}
	
	@BeforeClass
	public static void testandoBeforeClass() {
	  System.out.println("Inicializando... classe de teste.");
	}

	@AfterClass
	public static void testandoAfterClass() {
	  System.out.println("Finalizando... classe de teste.");
	}

	@Test
	public void testaMaiorEMenorLance() {

		Double maiorValorEsperado = 550.0;
		Double menorValorEsperado = 50.0;
		Double mediaEsperada = 300.0;

		Leilao leilao = new LeilaoBuilder()
				.criaLeilao("Leilão do MotoX Play")
				.crialance(new Lance(ramon, 550.0))
				.crialance(new Lance(maria, 50.0))
				.crialance(new Lance(joao, 300.0))
				.builder();

		avaliador.avalia(leilao);	
		avaliador.mediaDosLances(leilao);

		assertEquals(maiorValorEsperado, avaliador.getMaiorValor(), 0.00001);
		assertEquals(menorValorEsperado, avaliador.getMenorValor(), 0.00001);
		assertEquals(mediaEsperada, avaliador.mediaDosLances(leilao), 0.00001);

	}

	@Test
	public void possueApenasUmLance() {

		Leilao leilao = new LeilaoBuilder()
				.criaLeilao("Notebook Novo")
				.crialance(new Lance(joao, 1000.0))
				.builder();

		avaliador.avalia(leilao);

		assertEquals(1000.0, avaliador.getMaiorValor(), 0.00001);
		assertEquals(1000.0, avaliador.getMenorValor(), 0.00001);

	}

	@Test
	public void lancesRandomicos() {

		Leilao leilao = new LeilaoBuilder()
				.criaLeilao("Carro 0km")
				.crialance(new Lance(ramon, 200.0))
				.crialance(new Lance(joao, 450.0))
				.crialance(new Lance(ramon, 120.0))
				.crialance(new Lance(joao, 700.0))
				.crialance(new Lance(ramon, 630.0))
				.crialance(new Lance(joao, 230.0))
				.builder();

		avaliador.avalia(leilao);

		assertEquals(700.0, avaliador.getMaiorValor(), 0.00001);
		assertEquals(120.0, avaliador.getMenorValor(), 0.00001);

	}

	@Test
	public void lancesOrdemDecrescente() {

		Leilao leilao = new LeilaoBuilder()
				.criaLeilao("Carro 0km")
				.crialance(new Lance(ramon, 400.0))
				.crialance(new Lance(joao, 300.0))
				.crialance(new Lance(ramon, 200.0))
				.crialance(new Lance(joao, 100.0))
				.builder();

		avaliador.avalia(leilao);

		assertEquals(400.0, avaliador.getMaiorValor(), 0.00001);
		assertEquals(100.0, avaliador.getMenorValor(), 0.00001);

	}

	@Test
	public void verificaOsTresMaioresLances() {

		Leilao leilao = new LeilaoBuilder().criaLeilao("Leilão Fantasma")
				.crialance(new Lance(ramon, 100.0))
				.crialance(new Lance(joao, 300.0))
				.crialance(new Lance(ramon, 700.0))
				.crialance(new Lance(joao, 200.0))
				.crialance(new Lance(ramon, 900.0)).builder();

		avaliador.avalia(leilao);

		assertEquals(3, avaliador.getTresMaiores().size());
		assertEquals(900.0, avaliador.getTresMaiores().get(0).getValor(), 0.00001);
		assertEquals(700.0, avaliador.getTresMaiores().get(1).getValor(), 0.00001);
		assertEquals(300.0, avaliador.getTresMaiores().get(2).getValor(), 0.00001);

	}

	@Test
	public void devolveDoisLancesExistentes() {

		Leilao leilao = new LeilaoBuilder()
				.criaLeilao("Leilão Fantasma")
				.crialance(new Lance(ramon, 100.0))
				.crialance(new Lance(joao, 200.0))
				.builder();

		avaliador.avalia(leilao);

		assertEquals(100.0, leilao.getLances().get(0).getValor(), 0.00001);
		assertEquals(200.0, leilao.getLances().get(1).getValor(), 0.00001);
		assertEquals(2, leilao.getLances().size());

	}

	@Test
	public void leilaoVazio() {

		Leilao leilao = new LeilaoBuilder()
				.criaLeilao("Leilão Fantasma")
				.builder();

		avaliador.avalia(leilao);

		assertEquals(Collections.EMPTY_LIST, leilao.getLances());

	}

}
