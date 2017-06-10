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

import com.af5m.elm.model.LocalizedResourceElement;
import com.af5m.elm.service.persistence.exception.BadRequestException;
import com.af5m.elm.service.persistence.model.CreatedResponseDto;
import com.af5m.elm.service.persistence.service.LocalizedResourceElementService;
import com.dell.isg.smi.commons.utilities.model.PagedResult;

/**
 * The Class LocalizedResourceElementRestApiImpl.
 */
@Component
public class LocalizedResourceElementRestApiImpl implements LocalizedResourceElementRestApi {
	
	private static final Logger logger = LoggerFactory.getLogger(LocalizedResourceElementRestApiImpl.class);
	
	@Autowired
	private LocalizedResourceElementService localizedResourceElementService;
	
	
	/* (non-Javadoc)
	 * @see com.af5m.elm.service.persistence.controller.LocalizedResourceElementRestApi#getLocalizedResourceElement(java.util.UUID, java.util.UUID, java.util.UUID, java.util.UUID)
	 */
	@Override
	public LocalizedResourceElement getLocalizedResourceElement(@PathVariable(PARAMETER_ORGANIZATION_UUID) UUID organization_uuid, @PathVariable(PARAMETER_BUNDLE_UUID) UUID bundle_uuid, @PathVariable(PARAMETER_RESOURCE_UUID) UUID resource_uuid, @PathVariable(PARAMETER_UUID) UUID uuid) {
	    logger.trace("entered getLocalizedResourceElement(UUID {})", uuid.toString());
	    LocalizedResourceElement localizedResourceElement = localizedResourceElementService.get(uuid); 
        logger.trace("exiting getLocalizedResourceElement(UUID {})", uuid.toString());
		return localizedResourceElement;
	}
	
	
	/* (non-Javadoc)
	 * @see com.af5m.elm.service.persistence.controller.LocalizedResourceElementRestApi#getLocalizedResourceElements(java.util.UUID, java.util.UUID, java.util.UUID, java.lang.String, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public PagedResult<LocalizedResourceElement> getLocalizedResourceElements(@PathVariable(PARAMETER_ORGANIZATION_UUID) UUID organization_uuid, @PathVariable(PARAMETER_BUNDLE_UUID) UUID bundle_uuid, @PathVariable(PARAMETER_RESOURCE_UUID) UUID resource_uuid, @RequestParam(name = PARAMETER_NAME, required = false) String name, @RequestParam(name = OFFSET, defaultValue = DEFAULT_OFFSET) Integer offset, @RequestParam(name = LIMIT, defaultValue = DEFAULT_LIMIT) Integer limit) { 
	    logger.trace("entered getLocalizedResourceElements()");
        PagedResult<LocalizedResourceElement> pagedResult;
        if (!StringUtils.isEmpty(name)) {
        	pagedResult = localizedResourceElementService.getByName(name);
        } else {
        	pagedResult = localizedResourceElementService.getAll(offset, limit);
        }
        logger.trace("exiting getLocalizedResourceElements()");
        return pagedResult;
	}

	
	/* (non-Javadoc)
	 * @see com.af5m.elm.service.persistence.controller.LocalizedResourceElementRestApi#createLocalizedResourceElement(java.util.UUID, java.util.UUID, java.util.UUID, com.af5m.elm.model.LocalizedResourceElement)
	 */
	@Override
    public CreatedResponseDto createLocalizedResourceElement(@PathVariable(PARAMETER_ORGANIZATION_UUID) UUID organization_uuid, @PathVariable(PARAMETER_BUNDLE_UUID) UUID bundle_uuid, @PathVariable(PARAMETER_RESOURCE_UUID) UUID resource_uuid, @RequestBody LocalizedResourceElement localizedResourceElement) {
	    logger.trace("entered createLocalizedResourceElement(LocalizedResourceElement localizedResourceElement)");
	    if (null == localizedResourceElement ) {
	        BadRequestException badRequestException = new BadRequestException();
	        throw badRequestException;
	    }
	    localizedResourceElementService.save(localizedResourceElement);
	    CreatedResponseDto createdResponse = new CreatedResponseDto();
	    createdResponse.setUuid(localizedResourceElement.getUuid());
	    logger.trace("exiting createLocalizedResourceElement(LocalizedResourceElement localizedResourceElement) with uuid: {} ", localizedResourceElement.getUuid());
	    return createdResponse;
	}
    
	
	/* (non-Javadoc)
	 * @see com.af5m.elm.service.persistence.controller.LocalizedResourceElementRestApi#updateLocalizedResourceElement(java.util.UUID, java.util.UUID, java.util.UUID, com.af5m.elm.model.LocalizedResourceElement)
	 */
	@Override
	public void updateLocalizedResourceElement(@PathVariable(PARAMETER_ORGANIZATION_UUID) UUID organization_uuid, @PathVariable(PARAMETER_BUNDLE_UUID) UUID bundle_uuid, @PathVariable(PARAMETER_RESOURCE_UUID) UUID resource_uuid, @RequestBody LocalizedResourceElement localizedResourceElement) {
        logger.trace("entered updateLocalizedResourceElement(LocalizedResourceElement localizedResourceElement)");
		// TODO Auto-generated method stub
        logger.trace("exiting updateLocalizedResourceElement(LocalizedResourceElement localizedResourceElement) with uuid: {} ", localizedResourceElement.getUuid().toString());
	}
	

	/* (non-Javadoc)
	 * @see com.af5m.elm.service.persistence.controller.LocalizedResourceElementRestApi#deleteLocalizedResourceElement(java.util.UUID, java.util.UUID, java.util.UUID, java.util.UUID)
	 */
	@Override
	public void deleteLocalizedResourceElement(@PathVariable(PARAMETER_ORGANIZATION_UUID) UUID organization_uuid, @PathVariable(PARAMETER_BUNDLE_UUID) UUID bundle_uuid, @PathVariable(PARAMETER_RESOURCE_UUID) UUID resource_uuid, @PathVariable(PARAMETER_UUID) UUID uuid) {
        logger.trace("entered deleteLocalizedResourceElement(UUID {})", uuid.toString());
        localizedResourceElementService.delete(uuid);
        logger.trace("exiting deleteLocalizedResourceElement(UUID {})", uuid.toString());
	}
	
}
