package br.com.sicacard.business;

import java.util.List;

import br.com.sicacard.model.entity.Cliente;
import br.com.sicacard.model.entity.ClientePf;
import br.com.sicacard.model.entity.ClientePj;
import br.com.sicacard.model.entity.TipoCliente;
import br.com.sicacard.model.entity.exception.BusinessException;

/**
 * 
 * ClienteBusiness.java
 *
 * @author Nayalison <br />
 *     		nayalison@gmail.com
 *
 * @since 06/05/2012
 * @version 1.0
 */
public interface ClienteBusiness {
	
	public void salvar(ClientePj clientePj) throws BusinessException;
	public void deletar(ClientePj clientePj) throws BusinessException;
	public List<ClientePj> listarClientePJ() throws BusinessException;
	public ClientePj recuperar(ClientePj clientePj) throws BusinessException;
	public Cliente recuperarCliente(long numDocumento, TipoCliente tipoCliente) throws BusinessException;
	
	public void salvar(ClientePf clientePf) throws BusinessException;
	public void deletar(ClientePf clientePf) throws BusinessException;
	public List<ClientePf> listarClientePf() throws BusinessException;
	public ClientePf recuperar(ClientePf clientePf) throws BusinessException;

}
