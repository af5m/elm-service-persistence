package com.af5m.elm.service.persistence.service;

import java.util.UUID;

import com.af5m.elm.model.LocalizedResourceElement;
import com.dell.isg.smi.commons.utilities.model.PagedResult;

/**
 * The Interface LocalizedResourceElementService.
 */
public interface LocalizedResourceElementService {
	
	
	/**
	 * Gets the LocalizedResourceElement by uuid.
	 *
	 * @param uuid the uuid
	 * @return the LocalizedResourceElement
	 */
	public abstract LocalizedResourceElement get(UUID uuid);
	
	
	/**
	 * Gets the LocalizedResourceElement by name.
	 *
	 * @param name the name
	 * @return the LocalizedResourceElement by name
	 */
	public PagedResult<LocalizedResourceElement> getByName(String name);
	
	
	/**
	 * Gets all of the LocalizedResourceElement.
	 *
	 * @param offset the offset
	 * @param limit the limit
	 * @return PagedResult of LocalizedResourceElement
	 */
	public abstract PagedResult<LocalizedResourceElement> getAll(int offset, int limit);
	
	
	/**
	 * Save.
	 *
	 * @param localizedResourceElement the LocalizedResourceElement
	 */
	public abstract void save(LocalizedResourceElement localizedResourceElement);
	
	
	/**
	 * Delete.
	 *
	 * @param uuid the uuid
	 */
	public abstract void delete(UUID uuid);

}
