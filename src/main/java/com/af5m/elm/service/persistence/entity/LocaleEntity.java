package com.af5m.elm.service.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * The Class LocaleEntity.
 */
@Entity
@Table(name = TableConstants.T_LOCALE)
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = TableConstants.C_UUID, referencedColumnName = TableConstants.BASE_ENTITY_ID)
public class LocaleEntity extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	@Column(name="name")
	private String name;
	
	@Column(name="is_visible")
	private boolean isVisable;
	
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Checks if is visable.
	 *
	 * @return true, if is visable
	 */
	public boolean isVisable() {
		return isVisable;
	}
	
	/**
	 * Sets the visable.
	 *
	 * @param isVisable the new visable
	 */
	public void setVisable(boolean isVisable) {
		this.isVisable = isVisable;
	}
}
