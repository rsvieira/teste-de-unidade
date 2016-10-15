/**
 * 
 */
package br.com.caelum.leilao.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;

/**
 * @author Ramon Vieira
 *
 */

public class Avaliador {

	private Double maiorValor = Double.NEGATIVE_INFINITY;
	private Double menorValor = Double.POSITIVE_INFINITY;
	private List<Lance> maiores;
	
	public void avalia(Leilao leilao){
		
		if(leilao.getLances().size() == 0){
			throw new RuntimeException();
		}
		
		
		for (Lance lance: leilao.getLances()) {
			
			if(lance.getValor() > maiorValor){
				maiorValor = lance.getValor();
			}
			
			if(lance.getValor() < menorValor){
				menorValor = lance.getValor();
			}
			
		}
		
		pegaOsMaioresNo(leilao);
		
	}
	
	private void pegaOsMaioresNo(Leilao leilao) {
        maiores = new ArrayList<Lance>(leilao.getLances());
        Collections.sort(maiores, new Comparator<Lance>() {
            public int compare(Lance o1, Lance o2) {
                if(o1.getValor() < o2.getValor()) return 1;
                if(o1.getValor() > o2.getValor()) return -1;
                return 0;
            }
        });
        maiores = maiores.subList(0, maiores.size() > 3 ? 3 : maiores.size());
    }

	
	public Double mediaDosLances(Leilao leilao){
		
		Double total = Double.MIN_NORMAL;
		
		for (Lance lance: leilao.getLances()) {
			total+=lance.getValor();
		}
		
		return total/(new Double(leilao.getLances().size()));
		
	}
	
    public List<Lance> getTresMaiores() {
        return this.maiores;
    }

	public Double getMaiorValor() {
		return maiorValor;
	}

	public Double getMenorValor() {
		return menorValor;
	}
	
}

