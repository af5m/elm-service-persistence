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

import com.af5m.elm.model.Organization;
import com.af5m.elm.service.persistence.exception.BadRequestException;
import com.af5m.elm.service.persistence.model.CreatedResponseDto;
import com.af5m.elm.service.persistence.service.OrganizationService;
import com.dell.isg.smi.commons.utilities.model.PagedResult;

/**
 * The Class OrganizationRestApiImpl.
 */
@Component
public class OrganizationRestApiImpl implements OrganizationRestApi {
	
	private static final Logger logger = LoggerFactory.getLogger(OrganizationRestApiImpl.class);
	
	@Autowired
	private OrganizationService organizationService;
	

	/* (non-Javadoc)
	 * @see com.af5m.elm.service.persistence.controller.OrganizationRestApi#getOrganization(java.util.UUID)
	 */
	@Override
	public Organization getOrganization(UUID uuid) {
        logger.trace("Entered getOrganization() with uuid: {}", uuid);
        Organization organization = organizationService.get(uuid);
        logger.trace("Exiting getNetwork() with networkId: {}", uuid);
        return organization;
	}
	

	/* (non-Javadoc)
	 * @see com.af5m.elm.service.persistence.controller.OrganizationRestApi#getOrganizations(java.lang.String, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public PagedResult<Organization> getOrganizations(@RequestParam(name = PARAMETER_NAME, required = false) String name, @RequestParam(name = OFFSET, defaultValue = DEFAULT_OFFSET) Integer offset, @RequestParam(name = LIMIT, defaultValue = DEFAULT_LIMIT) Integer limit) {
        logger.trace("entered getOrganizations()");
        PagedResult<Organization> pagedResult;
        if (!StringUtils.isEmpty(name)) {
        	pagedResult = organizationService.getByName(name);
        } else {
        	pagedResult = organizationService.getAll(offset, limit);
        }
        logger.trace("exiting getOrganizations()");
        return pagedResult;
	}
	

	/* (non-Javadoc)
	 * @see com.af5m.elm.service.persistence.controller.OrganizationRestApi#createOrganization(com.af5m.elm.model.Organization)
	 */
	@Override
	public CreatedResponseDto createOrganization(@RequestBody Organization organization) {
        logger.trace("entered createNetwork(Network network)");
        if (null == organization ) {
            BadRequestException badRequestException = new BadRequestException();
            throw badRequestException;
        }
		organizationService.save(organization);
        CreatedResponseDto organizationCreatedResponse = new CreatedResponseDto();
        organizationCreatedResponse.setUuid(organization.getUuid());
        logger.trace("exiting createOrganization(Organization organization) with uuid: {} ", organization.getUuid());
        return organizationCreatedResponse;
	}
	

	/* (non-Javadoc)
	 * @see com.af5m.elm.service.persistence.controller.OrganizationRestApi#updateOrganization(com.af5m.elm.model.Organization)
	 */
	@Override
	public void updateOrganization(@RequestBody Organization organization) {
        logger.trace("entered updateOrganization(Network network)");
        if (null == organization ) {
            BadRequestException badRequestException = new BadRequestException();
            throw badRequestException;
        }
		organizationService.save(organization);
        logger.trace("exiting updateOrganization(Organization organization) with uuid: {} ", organization.getUuid());
	}
	

	/* (non-Javadoc)
	 * @see com.af5m.elm.service.persistence.controller.OrganizationRestApi#deleteOrganization(java.util.UUID)
	 */
	@Override
	public void deleteOrganization(@PathVariable(PARAMETER_UUID) UUID uuid) {
        logger.trace("Entered deleteOrganization() with uuid: {}", uuid);
        organizationService.delete(uuid);
        logger.trace("Exiting deleteOrganization() with uuid: {}", uuid);
	}

}
