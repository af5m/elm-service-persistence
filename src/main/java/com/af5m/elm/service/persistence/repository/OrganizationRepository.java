package com.af5m.elm.service.persistence.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.af5m.elm.service.persistence.entity.OrganizationEntity;

/**
 * The Interface OrganizationRepository.
 */
public interface OrganizationRepository extends CrudRepository<OrganizationEntity, UUID> {
	
	/**
	 * Find all.
	 *
	 * @param pagable the pagable
	 * @return the page
	 */
	Page<OrganizationEntity> findAll(Pageable pagable);
	
	
	/**
	 * Find by name.
	 *
	 * @param name the name
	 * @param pageable the pageable
	 * @return the page
	 */
	Page<OrganizationEntity> findByName(String name, Pageable pageable);
	
	
	/**
	 * Find by uuid.
	 *
	 * @param uuid the uuid
	 * @return the organization
	 */
	OrganizationEntity findByUuid(UUID uuid);

}
