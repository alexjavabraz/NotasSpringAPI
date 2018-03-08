package notas.api.spring.service;

import notas.api.dto.TransacaoDTO;

/**
 * 
 * @author asimas
 *
 */
public class TransacaoService {
	
	private static final int nota100 = 100;
	private static final int nota50 = 50;
	private static final int nota20 = 20;
	private static final int nota10 = 10;
	
	
	/**
	 * S
	 * 
	 * @param valor
	 * @return
	 */
	public TransacaoDTO contarNotas(Double valor) {

		if (valor == null) {
			throw new IllegalArgumentException("Value not allowed");
		}

		if (valor < 0) {
			throw new IllegalArgumentException("Negative value not allowed");
		}

		TransacaoDTO retorno = new TransacaoDTO();

		int valorInteiro = valor.intValue();

		int totalNotas100 = quantidadeNotas(valorInteiro, nota100);

		valorInteiro -= totalNotas100 * nota100;

		int totalNotas50 = quantidadeNotas(valorInteiro, nota50);

		valorInteiro -= totalNotas50 * nota50;

		int totalNotas20 = quantidadeNotas(valorInteiro, nota20);

		valorInteiro -= totalNotas20 * nota20;

		int totalNotas10 = quantidadeNotas(valorInteiro, nota10);

		valorInteiro -= totalNotas10 * nota10;

		retorno.setQtdNotasCem(totalNotas100);
		retorno.setQtdNotasCinquenta(totalNotas50);
		retorno.setQtdNotasVinte(totalNotas20);
		retorno.setQtdNotasDez(totalNotas10);

		return retorno;
	}

	/**
	 * 
	 * @param valor
	 * @param valorNota
	 * @return
	 */
	private int quantidadeNotas(int valor, int valorNota) {
		int retorno = 0;
		retorno = valor / valorNota;

		if (retorno > 0) {
			valor -= valorNota * retorno;
		}

		return retorno;
	}

}
