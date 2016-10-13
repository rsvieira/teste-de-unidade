package br.com.caelum.leilao.dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Ramon Vieira
 *
 */

public class Leilao {

	private String descricao;
	private List<Lance> lances;
	
	public Leilao(String descricao) {
		this.descricao = descricao;
		this.lances = new ArrayList<Lance>();
	}
	
	public void propoe(Lance lance) {
		if(lances.isEmpty() || ultimoUsuarioRepetido(lance)){
			lances.add(lance);
		}
	}
	
	/**
	 * @param lance
	 * @return
	 */
	private boolean ultimoUsuarioRepetido(Lance lance) {
		return !lance.getUsuario().equals(lances.get(lances.size() - 1).getUsuario()) && totalPorUsuario(lance.getUsuario()) < 5;
	}

	/**
	 * @param lance
	 * @return
	 */
	private int totalPorUsuario(Usuario usuario) {
		int total = 0;
		
		for (Lance l : lances) {
			if(l.getUsuario().equals(usuario)){
				total++;
			}
			
		}
		
		return total;
	}
	
	/**
	 * @param usuario
	 * @return
	 */
	public void dobraLance(Usuario usuario){

		Double valorUltimoLance = Double.MIN_VALUE;
		
		for (Lance lance : lances) {
			if(lance.getUsuario().equals(usuario)){
				valorUltimoLance = lance.getValor();
			}
		}

		if(valorUltimoLance != Double.MIN_VALUE && ultimoUsuarioRepetido(new Lance(usuario, valorUltimoLance * 2))){
			propoe(new Lance(usuario, valorUltimoLance * 2));
		}
	}

	public String getDescricao() {
		return descricao;
	}

	public List<Lance> getLances() {
		return Collections.unmodifiableList(lances);
	}

	
	
}
