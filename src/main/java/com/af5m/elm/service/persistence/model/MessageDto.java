package com.af5m.elm.service.persistence.model;

import java.util.List;
import java.util.UUID;

public class MessageDto {
	private UUID uuid;
	private UUID sourceUuid;
	private UUID messageTypeId;
	private UUID severityId;
	private boolean active;
}
