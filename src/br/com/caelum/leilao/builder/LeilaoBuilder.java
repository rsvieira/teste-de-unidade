/**
 * 
 */
package br.com.caelum.leilao.builder;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;

/**
 * @author Ramon Vieira
 *
 */

public class LeilaoBuilder {

	private Leilao leilao;
	
	public LeilaoBuilder criaLeilao(String descricao) {
		leilao = new Leilao(descricao);
		return this;
	}

	public LeilaoBuilder crialance(Lance lance) {
		leilao.propoe(lance);
		return this;
	}

	public Leilao builder() {
		return leilao;
	}

}
