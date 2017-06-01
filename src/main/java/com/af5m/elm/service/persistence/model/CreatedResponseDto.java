package com.af5m.elm.service.persistence.model;

import java.util.UUID;

/**
 * The Class OrganizationCreatedResponse.
 */
public class CreatedResponseDto {
	private UUID uuid;

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

}
