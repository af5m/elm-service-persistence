package com.af5m.elm.service.persistence.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.af5m.elm.service.persistence.entity.ResourceEntity;

/**
 * The Interface ResourceRepository.
 */
@Repository
public interface ResourceRepository extends CrudRepository<ResourceEntity, UUID> {
	
	/**
	 * Find all.
	 *
	 * @param pagable the pagable
	 * @return the page
	 */
	Page<ResourceEntity> findAll(Pageable pagable);
	
	
	/**
	 * Find by name.
	 *
	 * @param name the name
	 * @param pageable the pageable
	 * @return the page
	 */
	Page<ResourceEntity> findByName(String name, Pageable pageable);
	
	
	/**
	 * Find by uuid.
	 *
	 * @param uuid the uuid
	 * @return the ResourceEntity
	 */
	ResourceEntity findByUuid(UUID uuid);

}
