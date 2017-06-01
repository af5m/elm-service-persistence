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
 */
@Entity
@Table(name = TableConstants.T_MESSAGE_ELEMENT_TYPE)
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = TableConstants.C_UUID, referencedColumnName = TableConstants.BASE_ENTITY_ID)
public class MessageElementTypeEntity extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
    @ManyToOne(targetEntity = MessageTypeEntity.class, fetch = FetchType.EAGER)
	private MessageTypeEntity messageTypeEntity;
	
	@Column(name="name")
	private String name;
	
	/**
	 * Gets the message type entity.
	 *
	 * @return the message type entity
	 */
	public MessageTypeEntity getMessageTypeEntity() {
		return this.messageTypeEntity;
	}
	
	/**
	 * Sets the message type entity.
	 *
	 * @param messageType the new message type entity
	 */
	public void setMessageTypeEntity(MessageTypeEntity messageTypeEntity) {
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