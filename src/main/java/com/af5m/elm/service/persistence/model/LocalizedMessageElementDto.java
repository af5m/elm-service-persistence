package com.af5m.elm.service.persistence.model;

import java.util.UUID;

/**
 * The Class LocalizedMessageElementDto.
 */
public class LocalizedMessageElementDto {
	private UUID uuid;
	private UUID messageUuid;
	private UUID messageElementType;
	private UUID localeUuid;
	private String messageText;
	
	/**
	 * Gets the uuid.
	 *
	 * @return the uuid
	 */
	public UUID getUuid() {
		return uuid;
	}
	
	/**
	 * Sets the uuid.
	 *
	 * @param uuid the new uuid
	 */
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
	
	/**
	 * Gets the message uuid.
	 *
	 * @return the message uuid
	 */
	public UUID getMessageUuid() {
		return messageUuid;
	}
	
	/**
	 * Sets the message uuid.
	 *
	 * @param messageUuid the new message uuid
	 */
	public void setMessageUuid(UUID messageUuid) {
		this.messageUuid = messageUuid;
	}
	
	/**
	 * Gets the message element type.
	 *
	 * @return the message element type
	 */
	public UUID getMessageElementType() {
		return messageElementType;
	}
	
	/**
	 * Sets the message element type.
	 *
	 * @param messageElementType the new message element type
	 */
	public void setMessageElementType(UUID messageElementType) {
		this.messageElementType = messageElementType;
	}
	
	/**
	 * Gets the locale uuid.
	 *
	 * @return the locale uuid
	 */
	public UUID getLocaleUuid() {
		return localeUuid;
	}
	
	/**
	 * Sets the locale uuid.
	 *
	 * @param localeUuid the new locale uuid
	 */
	public void setLocaleUuid(UUID localeUuid) {
		this.localeUuid = localeUuid;
	}
	
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
}
