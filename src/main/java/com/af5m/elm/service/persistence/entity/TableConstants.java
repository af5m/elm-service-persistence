/**
 * Copyright © 2017 DELL Inc. or its subsidiaries.  All Rights Reserved.
 */
package com.af5m.elm.service.persistence.entity;

/**
 * The Interface TableConstants.
 */
public interface TableConstants {

    String C_ID = "id";
    String C_UUID = "uuid";
    String BASE_ENTITY_ID = "uuid";

    // LocalizedMessages table
    String T_LOCALIZED_MESSAGES = "localized_msg_codes";
    String SEQ_LOCALIZED_MESSAGES = "localized_msg_codes_id_seq";
    String LOCALIZED_MESSAGES_ID = "id";
    String LOCALIZED_MESSAGES_CODE = "msg_code";
    String LOCALIZED_MESSAGES_PARAMS = "msg_params";
    String LOCALIZED_MESSAGES_CREATION_TIME = "creation_time";
    String LOCALIZED_MESSAGES_PRIORITY = "priority";
    String LOCALIZED_MESSAGES_GROUP_ORDER = "group_order";
    String LOCALIZED_MESSAGES_GROUP = "message_group";

    // DAO model for RBAC
    String SEQUENCE_ENTITY = "base_entity_seq";
    
    String T_ORGANIZATION = "organization";
    String T_SOURCE = "source";
}
