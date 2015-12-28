package br.com.sicacard.entity;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

/**
 * GenericEntity.java
 *
 * @author nayalison <br />
 *     		nayalison@gmail.com
 *
 * @since 15/04/2012
 * @version 1.0
 */
@MappedSuperclass
public class GenericEntity implements Serializable {

	private static final long serialVersionUID = 6775201243029699651L;
	
	@Version
	private Long version;
	
	

	/**
	 * Get the version
	 * @return <code>Long</code>
	 */
	public final Long getVersion() {
		return version;
	}

	/**
	 * Set the version
	 * @param version the <code>Long</code>
	 */
	public final void setVersion(Long version) {
		this.version = version;
	}

}
