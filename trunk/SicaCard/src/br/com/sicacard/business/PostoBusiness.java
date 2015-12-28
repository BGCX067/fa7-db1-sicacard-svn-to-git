package br.com.sicacard.business;

import java.util.List;

import br.com.sicacard.model.entity.Posto;
import br.com.sicacard.model.entity.exception.BusinessException;

/**
 * 
 * PostoBusiness.java
 *
 * @author Nayalison <br />
 *     		nayalison@gmail.com
 *
 * @since 06/05/2012
 * @version 1.0
 */
public interface PostoBusiness {
	
	public void salvar(Posto posto) throws BusinessException;
	public void deletar(Posto posto) throws BusinessException;
	public List<Posto> listar() throws BusinessException;
	public Posto recuperar(Posto posto) throws BusinessException;

}
