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
import com.af5m.elm.service.persistence.exception.BadRequestException;
import com.af5m.elm.service.persistence.model.CreatedResponseDto;
import com.af5m.elm.service.persistence.service.BundleService;
import com.dell.isg.smi.commons.utilities.model.PagedResult;

/**
 * The Class BundleRestApiImpl.
 */
@Component
public class BundleRestApiImpl implements BundleRestApi {
	
	private static final Logger logger = LoggerFactory.getLogger(BundleRestApiImpl.class);

	@Autowired
	private BundleService bundleService;
	
	
	/* (non-Javadoc)
	 * @see com.af5m.elm.service.persistence.controller.BundleRestApi#getBundle(java.util.UUID)
	 */
	@Override
	public Bundle getBundle(@PathVariable(PARAMETER_UUID) UUID uuid) {
        logger.trace("entered getBundle(UUID {})", uuid.toString());
        Bundle bundle = bundleService.get(uuid); 
        logger.trace("exiting getBundle(UUID {})", uuid.toString());
		return bundle;
	}

	
	/* (non-Javadoc)
	 * @see com.af5m.elm.service.persistence.controller.BundleRestApi#getBundles(java.lang.String, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public PagedResult<Bundle> getBundles(@RequestParam(name = PARAMETER_NAME, required = false) String name, @RequestParam(name = OFFSET, defaultValue = DEFAULT_OFFSET) Integer offset, @RequestParam(name = LIMIT, defaultValue = DEFAULT_LIMIT) Integer limit) {
        logger.trace("entered getBundles()");
        PagedResult<Bundle> pagedResult;
        if (!StringUtils.isEmpty(name)) {
        	pagedResult = bundleService.getByName(name);
        } else {
        	pagedResult = bundleService.getAll(offset, limit);
        }
        logger.trace("exiting getBundles()");
        return pagedResult;
	}

	
	/* (non-Javadoc)
	 * @see com.af5m.elm.service.persistence.controller.BundleRestApi#createBundle(com.af5m.elm.model.Bundle)
	 */
	@Override
	public CreatedResponseDto createBundle(@RequestBody Bundle bundle) {
        logger.trace("entered createBundle(Bundle bundle)");
        if (null == bundle ) {
            BadRequestException badRequestException = new BadRequestException();
            throw badRequestException;
        }
		bundleService.save(bundle);
        CreatedResponseDto bundleCreatedResponse = new CreatedResponseDto();
        bundleCreatedResponse.setUuid(bundle.getUuid());
        logger.trace("exiting createBundle(Bundle bundle) with uuid: {} ", bundle.getUuid());
        return bundleCreatedResponse;
	}

	
	/* (non-Javadoc)
	 * @see com.af5m.elm.service.persistence.controller.BundleRestApi#updateBundle(com.af5m.elm.model.Bundle)
	 */
	@Override
	public void updateBundle(@RequestBody Bundle bundle) {
        logger.trace("entered updateBundle(Bundle bundle)");
		// TODO Auto-generated method stub
        logger.trace("exiting updateBundle(Bundle bundle) with uuid: {} ", bundle.getUuid().toString());
	}

	
	/* (non-Javadoc)
	 * @see com.af5m.elm.service.persistence.controller.BundleRestApi#deleteBundle(java.util.UUID)
	 */
	@Override
	public void deleteBundle(@PathVariable(PARAMETER_UUID) UUID uuid) {
        logger.trace("entered deleteBundle(UUID {})", uuid.toString());
		bundleService.delete(uuid);
        logger.trace("exiting deleteBundle(UUID {})", uuid.toString());
	}
}
