/**
 * 
 */
package br.com.caelum.leilao.test;

import static org.junit.Assert.assertEquals;

import java.util.Collections;

import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.service.Avaliador;

/**
 * @author Ramon Vieira
 *
 */

public class ValidaMaiorEMenorValorLanceTest {

	@Test
	public void testaMaiorEMenorLance(){
	
	Double maiorValorEsperado = 500.0;
	Double menorValorEsperado = 50.0;
	Double mediaEsperada = 300.0;
	
	Usuario ramon = new Usuario("Ramon");
	Usuario maria = new Usuario("Maria");
	Usuario joao = new Usuario("João");
	
	Leilao leilao = new Leilao("Leilão do MotoX Play");
	leilao.propoe(new Lance(ramon, 500.0));
	leilao.propoe(new Lance(maria, 50.0));
	leilao.propoe(new Lance(joao, 350.0));
	
	Avaliador avaliador = new Avaliador();
	avaliador.avalia(leilao);
	avaliador.mediaDosLances(leilao);
	
	assertEquals(maiorValorEsperado, avaliador.getMaiorValor(), 0.00001);
	assertEquals(menorValorEsperado, avaliador.getMenorValor(), 0.00001);
	assertEquals(mediaEsperada, avaliador.mediaDosLances(leilao), 0.00001);
		
	}
	
	@Test
	public void possueApenasUmLance(){
		
		Usuario joao = new Usuario("Joao");
		
		Leilao leilao = new Leilao("Notebook Novo");
		leilao.propoe(new Lance(joao, 1000.0));
		
		Avaliador avaliador = new Avaliador();
		avaliador.avalia(leilao);
		
		assertEquals(1000.0, avaliador.getMaiorValor(), 0.00001);
		assertEquals(1000.0, avaliador.getMenorValor(), 0.00001);
		
	}
	
	@Test
	public void lancesRandomicos(){
		
		Usuario joao = new Usuario("Joao");
		Usuario ramon = new Usuario("Ramon");

		Leilao leilao = new Leilao("Carro 0km");
		leilao.propoe(new Lance(ramon, 200.0));
		leilao.propoe(new Lance(joao, 450.0));
		leilao.propoe(new Lance(ramon, 120.0));
		leilao.propoe(new Lance(joao, 700.0));
		leilao.propoe(new Lance(ramon, 630.0));
		leilao.propoe(new Lance(joao, 230.0));
		
		Avaliador avaliador = new Avaliador();
		avaliador.avalia(leilao);
		
		assertEquals(700.0, avaliador.getMaiorValor(),0.00001);
		assertEquals(120.0, avaliador.getMenorValor(),0.00001);
		
	}
	
	@Test
	public void lancesOrdemDecrescente(){
		
		Usuario joao = new Usuario("Joao");
		Usuario ramon = new Usuario("Ramon");

		Leilao leilao = new Leilao("Carro 0km");
		leilao.propoe(new Lance(ramon, 400.0));
		leilao.propoe(new Lance(joao, 300.0));
		leilao.propoe(new Lance(ramon, 200.0));
		leilao.propoe(new Lance(joao, 100.0));
		
		Avaliador avaliador = new Avaliador();
		avaliador.avalia(leilao);
		
		assertEquals(400.0, avaliador.getMaiorValor(),0.00001);
		assertEquals(100.0, avaliador.getMenorValor(),0.00001);
		
	}

	@Test
	public void verificaOsTresMaioresLances(){
		
		Leilao leilao = new Leilao("Leilão Fantasma");
		leilao.propoe(new Lance(null, 100.0));
		leilao.propoe(new Lance(null, 300.0));
		leilao.propoe(new Lance(null, 700.0));
		leilao.propoe(new Lance(null, 200.0));
		leilao.propoe(new Lance(null, 900.0));
		
		Avaliador avaliador = new Avaliador();
		avaliador.avalia(leilao);
		
		assertEquals(3, avaliador.getTresMaiores().size());
		assertEquals(900.0, avaliador.getTresMaiores().get(0).getValor(),0.00001);
		assertEquals(700.0, avaliador.getTresMaiores().get(1).getValor(),0.00001);
		assertEquals(300.0, avaliador.getTresMaiores().get(2).getValor(),0.00001);
		
	}
	
	@Test
	public void devolveDoisLancesExistentes(){
		
		Leilao leilao = new Leilao("Leilão Fantasma");
		leilao.propoe(new Lance(null, 100.0));
		leilao.propoe(new Lance(null, 200.0));
		
		Avaliador avaliador = new Avaliador();
		avaliador.avalia(leilao);
		
		assertEquals(100.0, leilao.getLances().get(0).getValor(),0.00001);
		assertEquals(200.0, leilao.getLances().get(1).getValor(),0.00001);
		assertEquals(2, leilao.getLances().size());
		
	}
	
	@Test
	public void leilaoVazio(){
		
		Leilao leilao = new Leilao("Leilão Fantasma");
		
		Avaliador avaliador = new Avaliador();
		avaliador.avalia(leilao);
		
		assertEquals(Collections.EMPTY_LIST, leilao.getLances());
		
	}
	
	
}

