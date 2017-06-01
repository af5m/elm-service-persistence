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
 * The Class LocalizedMessageElementEntity.
 */
@Entity
@Table(name = TableConstants.T_LOCALIZED_MESSAGE_ELEMENT)
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = TableConstants.C_UUID, referencedColumnName = TableConstants.BASE_ENTITY_ID)
public class LocalizedMessageElementEntity extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	@Column(name="message")
	private String messageText;
	
    @ManyToOne(targetEntity = LocaleEntity.class, fetch = FetchType.EAGER)
	private LocaleEntity locale;	
    
    @ManyToOne(targetEntity = MessageElementTypeEntity.class, fetch = FetchType.EAGER)
	private MessageElementTypeEntity MessageElementType;
	
	/**
	 * Gets the message text.
	 *
	 * @return the message text
	 */
	public String getMessageText() {
		return messageText;
	}
	
	/**
	 * Sets the message text.
	 *
	 * @param messageText the new message text
	 */
	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}
	
	/**
	 * Gets the locale.
	 *
	 * @return the locale
	 */
	public LocaleEntity getLocale() {
		return locale;
	}
	
	/**
	 * Sets the locale.
	 *
	 * @param locale the new locale
	 */
	public void setLocale(LocaleEntity locale) {
		this.locale = locale;
	}
	
	/**
	 * Gets the message element type.
	 *
	 * @return the message element type
	 */
	public MessageElementTypeEntity getMessageElementType() {
		return MessageElementType;
	}
	
	/**
	 * Sets the message element type.
	 *
	 * @param messageElementType the new message element type
	 */
	public void setMessageElementType(MessageElementTypeEntity messageElementType) {
		MessageElementType = messageElementType;
	}
	

}
