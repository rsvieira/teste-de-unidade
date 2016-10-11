/**
 * 
 */
package br.com.caelum.leilao.service;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;

/**
 * @author Ramon Vieira
 *
 */

public class Avaliador {

	private Double maiorValor = Double.NEGATIVE_INFINITY;
	private Double menorValor = Double.POSITIVE_INFINITY;
	
	public void avalia(Leilao leilao){
		
		for (Lance lance: leilao.getLances()) {
			
			if(lance.getValor() > maiorValor){
				maiorValor = lance.getValor();
			}
			
			if(lance.getValor() < menorValor){
				menorValor = lance.getValor();
			}
			
		}
		
	}
	
	public Double mediaDosLances(Leilao leilao){
		
		Double total = Double.MIN_NORMAL;
		
		for (Lance lance: leilao.getLances()) {
			total+=lance.getValor();
		}
		
		return total/(new Double(leilao.getLances().size()));
		
	}

	public Double getMaiorValor() {
		return maiorValor;
	}

	public Double getMenorValor() {
		return menorValor;
	}
	
}

