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
 * The Class LocalizedResourceElementEntity.
 */
@Entity
@Table(name = TableConstants.T_LOCALIZED_RESOURCE_ELEMENT)
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = TableConstants.C_UUID, referencedColumnName = TableConstants.BASE_ENTITY_ID)
public class LocalizedResourceElementEntity extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	@Column(name="message")
	private String messageText;
	
    @ManyToOne(targetEntity = LocaleEntity.class, fetch = FetchType.EAGER)
	private LocaleEntity locale;	
    
    @ManyToOne(targetEntity = ResourceEntity.class, fetch = FetchType.EAGER)
	private ResourceEntity resourceEntity;	
    
    @ManyToOne(targetEntity = ElementEntity.class, fetch = FetchType.EAGER)
	private ElementEntity elementEntity;
	
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
	 * Gets the resource entity.
	 *
	 * @return the resource entity
	 */
	public ResourceEntity getResourceEntity() {
		return resourceEntity;
	}

	/**
	 * Sets the resource entity.
	 *
	 * @param resourceEntity the new resource entity
	 */
	public void setResourceEntity(ResourceEntity resourceEntity) {
		this.resourceEntity = resourceEntity;
	}

	/**
	 * Gets the element entity.
	 *
	 * @return the element entity
	 */
	public ElementEntity getElementEntity() {
		return elementEntity;
	}

	/**
	 * Sets the element entity.
	 *
	 * @param elementEntity the new element entity
	 */
	public void setElementEntity(ElementEntity elementEntity) {
		this.elementEntity = elementEntity;
	}
	
}
