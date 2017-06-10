package com.af5m.elm.service.persistence.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.af5m.elm.service.persistence.entity.ElementEntity;

/**
 * The Interface ElementRepository.
 */
@Repository
public interface ElementRepository extends CrudRepository<ElementEntity, UUID> {

}
