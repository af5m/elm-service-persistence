package com.af5m.elm.service.persistence.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.af5m.elm.service.persistence.entity.SourceEntity;

/**
 * The Interface SourceRepository.
 */
public interface SourceRepository extends CrudRepository<SourceEntity, UUID> {
	
	/**
	 * Find all.
	 *
	 * @param pagable the pagable
	 * @return the page
	 */
	Page<SourceEntity> findAll(Pageable pagable);
	
	
	/**
	 * Find by name.
	 *
	 * @param name the name
	 * @param pageable the pageable
	 * @return the page
	 */
	Page<SourceEntity> findByName(String name, Pageable pageable);
	
	
	/**
	 * Find by uuid.
	 *
	 * @param uuid the uuid
	 * @return the SourceEntity
	 */
	SourceEntity findByUuid(UUID uuid);

}
