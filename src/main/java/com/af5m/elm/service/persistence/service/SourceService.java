package com.af5m.elm.service.persistence.service;

import java.util.UUID;

import com.af5m.elm.model.Source;
import com.dell.isg.smi.commons.utilities.model.PagedResult;

/**
 * The Interface SourceService.
 */
public interface SourceService {

	/**
	 * Gets the Source by uuid.
	 *
	 * @param uuid the uuid
	 * @return the source
	 */
	public abstract Source get(UUID uuid);
	
	/**
	 * Gets the Source by name.
	 *
	 * @param name the name
	 * @return the Source by name
	 */
	public PagedResult<Source> getByName(String name);
	
	/**
	 * Gets all of the sources.
	 *
	 * @param offset the offset
	 * @param limit the limit
	 * @return PagedResult of sources
	 */
	public abstract PagedResult<Source> getAll(int offset, int limit);
	
	/**
	 * Save.
	 *
	 * @param source the source
	 */
	public abstract void save(Source source);
	
	
	/**
	 * Delete.
	 *
	 * @param uuid the uuid
	 */
	public abstract void delete(UUID uuid);
}
