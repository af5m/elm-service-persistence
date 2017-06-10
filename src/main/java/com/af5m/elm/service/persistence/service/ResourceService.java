package com.af5m.elm.service.persistence.service;

import java.util.UUID;

import com.af5m.elm.model.Resource;
import com.dell.isg.smi.commons.utilities.model.PagedResult;

public interface ResourceService {
	
	/**
	 * Gets the Resource by uuid.
	 *
	 * @param uuid the uuid
	 * @return the resource
	 */
	public abstract Resource get(UUID uuid);
	
	/**
	 * Gets the Resource by name.
	 *
	 * @param name the name
	 * @return the Resource by name
	 */
	public PagedResult<Resource> getByName(String name);
	
	/**
	 * Gets all of the resources.
	 *
	 * @param offset the offset
	 * @param limit the limit
	 * @return PagedResult of resources
	 */
	public abstract PagedResult<Resource> getAll(int offset, int limit);
	
	/**
	 * Save.
	 *
	 * @param resource the resource
	 */
	public abstract void save(Resource resource);
	
	
	/**
	 * Delete.
	 *
	 * @param uuid the uuid
	 */
	public abstract void delete(UUID uuid);

}
