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

import com.af5m.elm.model.Source;
import com.af5m.elm.service.persistence.exception.BadRequestException;
import com.af5m.elm.service.persistence.model.CreatedResponseDto;
import com.af5m.elm.service.persistence.service.SourceService;
import com.dell.isg.smi.commons.utilities.model.PagedResult;

/**
 * The Class SourceRestApiImpl.
 */
@Component
public class SourceRestApiImpl implements SourceRestApi {
	
	private static final Logger logger = LoggerFactory.getLogger(SourceRestApiImpl.class);

	@Autowired
	private SourceService sourceService;
	
	
	/* (non-Javadoc)
	 * @see com.af5m.elm.service.persistence.controller.SourceRestApi#getSource(java.util.UUID)
	 */
	@Override
	public Source getSource(@PathVariable(PARAMETER_UUID) UUID uuid) {
        logger.trace("entered getSource(UUID {})", uuid.toString());
        Source source = sourceService.get(uuid); 
        logger.trace("exiting getSource(UUID {})", uuid.toString());
		return source;
		
	}

	
	/* (non-Javadoc)
	 * @see com.af5m.elm.service.persistence.controller.SourceRestApi#getSources(java.lang.String, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public PagedResult<Source> getSources(@RequestParam(name = PARAMETER_NAME, required = false) String name, @RequestParam(name = OFFSET, defaultValue = DEFAULT_OFFSET) Integer offset, @RequestParam(name = LIMIT, defaultValue = DEFAULT_LIMIT) Integer limit) {
        logger.trace("entered getSources()");
        PagedResult<Source> pagedResult;
        if (!StringUtils.isEmpty(name)) {
        	pagedResult = sourceService.getByName(name);
        } else {
        	pagedResult = sourceService.getAll(offset, limit);
        }
        logger.trace("exiting getSources()");
        return pagedResult;
	}

	
	/* (non-Javadoc)
	 * @see com.af5m.elm.service.persistence.controller.SourceRestApi#createSource(com.af5m.elm.model.Source)
	 */
	@Override
	public CreatedResponseDto createSource(@RequestBody Source source) {
        logger.trace("entered createSource(Source source)");
        if (null == source ) {
            BadRequestException badRequestException = new BadRequestException();
            throw badRequestException;
        }
		sourceService.save(source);
        CreatedResponseDto sourceCreatedResponse = new CreatedResponseDto();
        sourceCreatedResponse.setUuid(source.getUuid());
        logger.trace("exiting createSource(Source source) with uuid: {} ", source.getUuid());
        return sourceCreatedResponse;
	}

	
	/* (non-Javadoc)
	 * @see com.af5m.elm.service.persistence.controller.SourceRestApi#updateSource(com.af5m.elm.model.Source)
	 */
	@Override
	public void updateSource(@RequestBody Source source) {
        logger.trace("entered updateSource(Source source)");
		// TODO Auto-generated method stub
        logger.trace("exiting updateSource(Source source) with uuid: {} ", source.getUuid().toString());
	}

	
	/* (non-Javadoc)
	 * @see com.af5m.elm.service.persistence.controller.SourceRestApi#deleteSource(java.util.UUID)
	 */
	@Override
	public void deleteSource(@PathVariable(PARAMETER_UUID) UUID uuid) {
        logger.trace("entered deleteSource(UUID {})", uuid.toString());
		sourceService.delete(uuid);
        logger.trace("exiting deleteSource(UUID {})", uuid.toString());
	}
}
