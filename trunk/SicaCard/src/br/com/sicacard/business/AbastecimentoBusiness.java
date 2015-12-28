/**
 *
 */
package br.com.sicacard.business;

import java.util.List;

import br.com.sicacard.model.entity.HistoricoAbastecimento;
import br.com.sicacard.model.entity.exception.BusinessException;

/**
 * AbastecimentoBusiness.java
 *
 * @author Nayalison <br />
 *     		nayalison@gmail.com
 *
 * @since 06/05/2012
 * @version 1.0
 */
public interface AbastecimentoBusiness {
	
	public void realizarAbastecimento(HistoricoAbastecimento historicoAbastecimento) throws BusinessException;
	public List<HistoricoAbastecimento> listarAbastecimentos() throws BusinessException;

}
