package com.af5m.elm.service.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * The Class Organization.
 */
@Entity
@Table(name = TableConstants.T_ORGANIZATION)
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = TableConstants.C_UUID, referencedColumnName = TableConstants.BASE_ENTITY_ID)
public class OrganizationEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	@Column(name="name")
	private String name;
	
	@Column(name="description")
	private String description;
	
	@Column(name="isPrivate")
	private boolean isPrivate;
	
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
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Checks if is private.
	 *
	 * @return true, if is private
	 */
	public boolean isPrivate() {
		return isPrivate;
	}
	
	/**
	 * Sets the private.
	 *
	 * @param isPrivate the new private
	 */
	public void setPrivate(boolean isPrivate) {
		this.isPrivate = isPrivate;
	}
}
