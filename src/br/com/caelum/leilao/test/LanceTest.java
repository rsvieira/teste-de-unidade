/**
 * 
 */
package br.com.caelum.leilao.test;

import static org.junit.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.*;
import static br.com.caelum.leilao.matcher.LeilaoMatcher.temUmLance;


import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;

/**
 * @author Ramon Vieira
 *
 */

public class LanceTest {
	
	@Test
	public void naoAceitarLancesEmSequenciaMesmoUsuario(){
		Leilao leilao = new Leilao("Viagem São Paulo");
		
		Usuario ramon = new Usuario("Ramon");
		
		leilao.propoe(new Lance(ramon, 2000));
		leilao.propoe(new Lance(ramon, 2000));
		
		assertEquals(1,leilao.getLances().size());
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoAceitarLancesNegativosEIguaisAZero(){
		Leilao leilao = new Leilao("Viagem São Paulo");
		
		Usuario ramon = new Usuario("Ramon");
		
		leilao.propoe(new Lance(ramon, 0));
		leilao.propoe(new Lance(ramon, -1));
		
		assertEquals(1,leilao.getLances().size());
		
	}
	
	@Test
	public void naoAceitarMaisdeCincoLancesDoMesmoUsuario(){
		Leilao leilao = new Leilao("Viagem São Paulo");
		
		Usuario ramon = new Usuario("Ramon");
		Usuario jenkhy = new Usuario("Jenkhy");
		
		leilao.propoe(new Lance(ramon, 10));
		leilao.propoe(new Lance(jenkhy, 20));
		
		leilao.propoe(new Lance(ramon, 200));
		leilao.propoe(new Lance(jenkhy, 300));
		
		leilao.propoe(new Lance(ramon, 400));
		leilao.propoe(new Lance(jenkhy, 500));
		
		leilao.propoe(new Lance(ramon, 600));
		leilao.propoe(new Lance(jenkhy, 700));
		
		leilao.propoe(new Lance(ramon, 800));
		leilao.propoe(new Lance(jenkhy, 900));

		leilao.propoe(new Lance(ramon, 12000));
		
		assertEquals(10,leilao.getLances().size());
		assertEquals(900, leilao.getLances().get(10 - 1).getValor(), 0.00001);
		
	}
	
	@Test
	public void dobraValorLance(){
		Leilao leilao = new Leilao("Viagem São Paulo");
		
		Usuario ramon = new Usuario("Ramon");
		Usuario jenkhy = new Usuario("Jenkhy");
		
		leilao.dobraLance(ramon);
		
		assertEquals(0, leilao.getLances().size());
		
		leilao.propoe(new Lance(ramon, 10));
		leilao.propoe(new Lance(jenkhy, 20));
		
		leilao.dobraLance(ramon);
		
		assertEquals(20, leilao.getLances().get(2).getValor(), 0.00001);
		
	}
	
	@Test
	public void verificaUmlance(){
		Leilao leilao = new Leilao("Viagem São Paulo");
		
		Usuario ramon = new Usuario("Ramon");
		
		leilao.propoe(new Lance(ramon, 10));
		
		assertThat(leilao, temUmLance(leilao.getLances().get(0)));
		
	}
	

}
