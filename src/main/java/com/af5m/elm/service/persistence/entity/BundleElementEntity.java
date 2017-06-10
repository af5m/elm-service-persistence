package com.af5m.elm.service.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * The Class MessageElementTypeEntity.
 * Possible values include 
 */
@Entity
@Table(name = TableConstants.T_BUNDLE_ELEMENT)
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = TableConstants.C_UUID, referencedColumnName = TableConstants.BASE_ENTITY_ID)
public class BundleElementEntity extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
    @ManyToOne(targetEntity = ElementEntity.class, fetch = FetchType.EAGER)
	private ElementEntity messageTypeEntity; 
    
	@Column(name="name")
	private String name;
	
	/**
	 * Gets the message type entity.
	 *
	 * @return the message type entity
	 */
	public ElementEntity getMessageTypeEntity() {
		return this.messageTypeEntity;
	}
	
	/**
	 * Sets the message type entity.
	 *
	 * @param messageType the new message type entity
	 */
	public void setMessageTypeEntity(ElementEntity messageTypeEntity) {
		this.messageTypeEntity = messageTypeEntity;
	}
	
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
}