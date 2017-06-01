package com.af5m.elm.service.persistence.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.af5m.elm.service.persistence.entity.MessageTypeEntity;

/**
 * The Interface MessageTypeRepository.
 */
@Repository
public interface MessageTypeRepository extends CrudRepository<MessageTypeEntity, UUID> {

}
