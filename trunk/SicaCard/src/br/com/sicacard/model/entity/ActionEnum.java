/**
 *
 */
package br.com.sicacard.model.entity;

/**
 * ActionEnum.java
 *
 * @author Nayalison <br />
 *     		nayalison@gmail.com
 *
 * @since 18/04/2012
 * @version 1.0
 */
public enum ActionEnum {
	/** Ação para selecionar todos. */
	SELECT_ALL("SELECT_ALL"),
	/** Ação para selecionar um. */
	SELECT("SELECT"),
	/** Ação para inserir. */
	INSERT("INSERT"),
	/** Ação para atualizar. */
	UPDATE("UPDATE"),
	/** Ação para deletar. */
	DELECT("DELETE");

	private String desc;

	/**
	 * Default constructor.
	 */
	private ActionEnum(String desc) {
		this.desc = desc;
	}

	/**
	 * Get the desc.
	 * @return <code>String</code>
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * Set the desc.
	 * @param desc the <code>String</code>
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

}
