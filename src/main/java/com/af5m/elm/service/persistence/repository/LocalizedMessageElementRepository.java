package com.af5m.elm.service.persistence.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.af5m.elm.service.persistence.entity.LocalizedMessageElementEntity;

/**
 * The Interface LocalizedMessageElementRepository.
 */
@Repository
public interface LocalizedMessageElementRepository extends CrudRepository<LocalizedMessageElementEntity, UUID> {

}
