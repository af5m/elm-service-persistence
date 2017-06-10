package com.af5m.elm.service.persistence.controller;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.af5m.elm.model.Bundle;
import com.af5m.elm.model.Resource;
import com.af5m.elm.service.persistence.exception.BadRequestException;
import com.af5m.elm.service.persistence.model.CreatedResponseDto;
import com.af5m.elm.service.persistence.service.ResourceService;
import com.dell.isg.smi.commons.utilities.model.PagedResult;

@Component
public class ResourceRestApiImpl implements ResourceRestApi {
	
	private static final Logger logger = LoggerFactory.getLogger(ResourceRestApiImpl.class);
	
	@Autowired
	private ResourceService resourceService;
	
	
	/* (non-Javadoc)
	 * @see com.af5m.elm.service.persistence.controller.ResourceRestApi#getResource(java.util.UUID, java.util.UUID, java.util.UUID)
	 */
	@Override
	public Resource getResource(@PathVariable(PARAMETER_ORGANIZATION_UUID) UUID organization_uuid, @PathVariable(PARAMETER_BUNDLE_UUID) UUID bundle_uuid, @PathVariable(PARAMETER_UUID) UUID uuid) {
        logger.trace("entered getResource(UUID {})", uuid.toString());
        Resource resource = resourceService.get(uuid); 
        logger.trace("exiting getResource(UUID {})", uuid.toString());
		return resource;
	}
	
	
	/* (non-Javadoc)
	 * @see com.af5m.elm.service.persistence.controller.ResourceRestApi#getResources(java.util.UUID, java.util.UUID, java.lang.String, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public PagedResult<Resource> getResources( @PathVariable(PARAMETER_ORGANIZATION_UUID) UUID organization_uuid, @PathVariable(PARAMETER_BUNDLE_UUID) UUID bundle_uuid, @RequestParam(name = PARAMETER_NAME, required = false) String name, @RequestParam(name = OFFSET, defaultValue = DEFAULT_OFFSET) Integer offset, @RequestParam(name = LIMIT, defaultValue = DEFAULT_LIMIT) Integer limit) {
        logger.trace("entered getResources()");
        PagedResult<Resource> pagedResult;
        if (!StringUtils.isEmpty(name)) {
        	pagedResult = resourceService.getByName(name);
        } else {
        	pagedResult = resourceService.getAll(offset, limit);
        }
        logger.trace("exiting getResources()");
        return pagedResult;
	}

	
    /* (non-Javadoc)
     * @see com.af5m.elm.service.persistence.controller.ResourceRestApi#createResource(java.util.UUID, java.util.UUID, com.af5m.elm.model.Resource)
     */
    public CreatedResponseDto createResource(@PathVariable(PARAMETER_ORGANIZATION_UUID) UUID organization_uuid, @PathVariable(PARAMETER_BUNDLE_UUID) UUID resource_uuid, @RequestBody Resource resource) {
	    logger.trace("entered createResource(Resource resource)");
	    if (null == resource ) {
	        BadRequestException badRequestException = new BadRequestException();
	        throw badRequestException;
	    }
	    resourceService.save(resource);
	    CreatedResponseDto createdResponse = new CreatedResponseDto();
	    createdResponse.setUuid(resource.getUuid());
	    logger.trace("exiting createResource(Resource resource) with uuid: {} ", resource.getUuid());
	    return createdResponse;
	}
    
	
	/* (non-Javadoc)
	 * @see com.af5m.elm.service.persistence.controller.ResourceRestApi#updateResource(java.util.UUID, java.util.UUID, com.af5m.elm.model.Resource)
	 */
	@Override
	public void updateResource(@PathVariable(PARAMETER_ORGANIZATION_UUID) UUID organization_uuid, @PathVariable(PARAMETER_BUNDLE_UUID) UUID bundle_uuid, @RequestBody Resource resource) {
        logger.trace("entered updateResource(Resource resource)");
		// TODO Auto-generated method stub
        logger.trace("exiting updateResource(Resource resource) with uuid: {} ", resource.getUuid().toString());
	}
	
	
	/* (non-Javadoc)
	 * @see com.af5m.elm.service.persistence.controller.ResourceRestApi#deleteResource(java.util.UUID, java.util.UUID, java.util.UUID)
	 */
	@Override
	public void deleteResource(@PathVariable(PARAMETER_ORGANIZATION_UUID) UUID organization_uuid, @PathVariable(PARAMETER_BUNDLE_UUID) UUID bundle_uuid, @PathVariable(PARAMETER_UUID) UUID uuid) {
        logger.trace("entered deleteResource(UUID {})", uuid.toString());
        resourceService.delete(uuid);
        logger.trace("exiting deleteResource(UUID {})", uuid.toString());
	}
	
    
}
