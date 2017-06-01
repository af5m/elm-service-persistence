package com.af5m.elm.service.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * The Class MessageTypeEntity.
 */
@Entity
@Table(name = TableConstants.T_MESSAGE_TYPE)
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = TableConstants.C_UUID, referencedColumnName = TableConstants.BASE_ENTITY_ID)
public class MessageTypeEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	private String name;
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
