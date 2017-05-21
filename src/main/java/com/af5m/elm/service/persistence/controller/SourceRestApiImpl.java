package com.af5m.elm.service.persistence.controller;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import com.af5m.elm.model.Organization;
import com.af5m.elm.model.Source;
import com.af5m.elm.service.persistence.model.CreatedResponse;
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
	
	
	@Override
	public Source getSource(UUID uuid) {
		// TODO Auto-generated method stub
		return null;
	}

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

	@Override
	public CreatedResponse createSource(Source source) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateSource(Source source) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteSource(UUID guid) {
		// TODO Auto-generated method stub

	}

}
