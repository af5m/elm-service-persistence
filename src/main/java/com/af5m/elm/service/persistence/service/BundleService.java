package com.af5m.elm.service.persistence.service;

import java.util.UUID;

import com.af5m.elm.model.Bundle;
import com.dell.isg.smi.commons.utilities.model.PagedResult;

/**
 * The Interface BundleService.
 */
public interface BundleService {

	/**
	 * Gets the Bundle by uuid.
	 *
	 * @param uuid the uuid
	 * @return the bundle
	 */
	public abstract Bundle get(UUID uuid);
	
	/**
	 * Gets the Bundle by name.
	 *
	 * @param name the name
	 * @return the Bundle by name
	 */
	public PagedResult<Bundle> getByName(String name);
	
	/**
	 * Gets all of the bundles.
	 *
	 * @param offset the offset
	 * @param limit the limit
	 * @return PagedResult of bundles
	 */
	public abstract PagedResult<Bundle> getAll(int offset, int limit);
	
	/**
	 * Save.
	 *
	 * @param bundle the bundle
	 */
	public abstract void save(Bundle bundle);
	
	
	/**
	 * Delete.
	 *
	 * @param uuid the uuid
	 */
	public abstract void delete(UUID uuid);
}
