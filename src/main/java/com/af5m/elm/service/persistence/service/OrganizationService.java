package com.af5m.elm.service.persistence.service;

import java.util.UUID;

import com.af5m.elm.model.Organization;
import com.dell.isg.smi.commons.utilities.model.PagedResult;

/**
 * The Interface OrganizationService.
 */
public interface OrganizationService {
	
	/**
	 * Gets the.
	 *
	 * @param guid the guid
	 * @return the organization
	 */
	public abstract Organization get(UUID guid);
	
	/**
	 * Gets the by name.
	 *
	 * @param name the name
	 * @return the by name
	 */
	public PagedResult<Organization> getByName(String name);
	
	/**
	 * Gets the all.
	 *
	 * @param offset the offset
	 * @param limit the limit
	 * @return the all
	 */
	public abstract PagedResult<Organization> getAll(int offset, int limit);
	
	/**
	 * Save.
	 *
	 * @param organization the organization
	 */
	public abstract void save(Organization organization);
	
	/**
	 * Delete.
	 *
	 * @param guid the guid
	 */
	public abstract void delete(UUID guid);
}
