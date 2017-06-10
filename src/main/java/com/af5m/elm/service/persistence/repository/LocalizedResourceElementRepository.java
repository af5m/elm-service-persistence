package com.af5m.elm.service.persistence.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.af5m.elm.service.persistence.entity.LocalizedResourceElementEntity;

/**
 * The Interface LocalizedResourceElementRepository.
 */
@Repository
public interface LocalizedResourceElementRepository extends CrudRepository<LocalizedResourceElementEntity, UUID> {

	/**
	 * Find all.
	 *
	 * @param pagable the pagable
	 * @return the page
	 */
	Page<LocalizedResourceElementEntity> findAll(Pageable pagable);
	
	
	/**
	 * Find by uuid.
	 *
	 * @param uuid the uuid
	 * @return the LocalizedResourceElementEntity
	 */
	LocalizedResourceElementEntity findByUuid(UUID uuid);
}
