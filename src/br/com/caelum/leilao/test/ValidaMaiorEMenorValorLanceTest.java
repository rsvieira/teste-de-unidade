/**
 * 
 */
package br.com.caelum.leilao.test;

import org.junit.Assert;
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
	
	Avaliador avaliador = new Avaliador();
	
	Usuario ramon = new Usuario("Ramon");
	Usuario maria = new Usuario("Maria");
	Usuario joao = new Usuario("João");
	
	Leilao leilao = new Leilao("Leilão do MotoX Play");
	
	leilao.propoe(new Lance(ramon, 500.0));
	leilao.propoe(new Lance(maria, 50.0));
	leilao.propoe(new Lance(joao, 350.0));
	
	avaliador.avalia(leilao);
	avaliador.mediaDosLances(leilao);
	
	Assert.assertEquals(maiorValorEsperado, avaliador.getMaiorValor(), 0.00001);
	Assert.assertEquals(menorValorEsperado, avaliador.getMenorValor(), 0.00001);
	Assert.assertEquals(mediaEsperada, avaliador.mediaDosLances(leilao), 0.00001);
		
	}
	
}
