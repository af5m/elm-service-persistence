package com.af5m.elm.service.persistence.controller;

import java.util.UUID;

import javax.annotation.security.RolesAllowed;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.af5m.elm.model.Bundle;
import com.af5m.elm.service.persistence.model.CreatedResponseDto;
import com.dell.isg.smi.commons.utilities.model.PagedResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * The Interface BundleRestApi.
 */
@RestController
@Api(value = "/api/1.0/organizations/{organization_uuid}/localization/bundles")
@RequestMapping("/api/1.0/organizations/{organization_uuid}/localization/bundles")
public interface BundleRestApi {

    static final String OFFSET = "offset";
    static final String LIMIT = "limit";
    static final String PARAMETER_UUID = "uuid";
    static final String PARAMETER_NAME = "name";
    
    static final String PATH_URI_UUID = "/{uuid}";
    static final String PATH_URI_ALL_BUNDLES = "";
    
    static final String ROLE_READ = "ROLE_READ";
    static final String ROLE_WRITE = "ROLE_WRITE";
    static final String ROLE_CONFIGURE_BUNDLE = "ROLE_CONFIGURE_BUNDLE";
    
    static final String DEFAULT_OFFSET = "0";
    static final String DEFAULT_LIMIT = "10";
    static final String DEFAULT_STATE = "ALL";
    static final String DEFAULT_CONTENT_TYPE = "application/json";
    
    
    /**
     * Gets the bundle.
     *
     * @param uuid the uuid
     * @return the bundle
     */
    @ApiOperation(value = "Get bundle (by uuid)", nickname = "Get bundle (by uuid)", notes = "Returns the bundle information for the given " + PARAMETER_UUID + " path variable", response = Bundle.class)
    @RequestMapping(method = RequestMethod.GET, path = PATH_URI_UUID)
    @RolesAllowed({ ROLE_READ })
	public abstract Bundle getBundle(@PathVariable(PARAMETER_UUID) UUID uuid);
	
	
    /**
     * Gets the bundles.
     *
     * @param name the name
     * @param offset the offset
     * @param limit the limit
     * @return the bundles
     */
    @ApiOperation(value = "Get bundles", nickname = "Get bundles", notes = "Gets a paged result of bundles using the optional pagination offset and limit provided. " + "The default limit is " + DEFAULT_LIMIT + " records. An optional querystring parameter of '" + PARAMETER_NAME + "' is available to find a bundle by name.", response = PagedResult.class)
    @RequestMapping(method = RequestMethod.GET, value = PATH_URI_ALL_BUNDLES, produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed({ ROLE_READ })
	public abstract PagedResult<Bundle> getBundles(@RequestParam(name = PARAMETER_NAME, required = false) String name, @RequestParam(name = OFFSET, defaultValue = DEFAULT_OFFSET) Integer offset, @RequestParam(name = LIMIT, defaultValue = DEFAULT_LIMIT) Integer limit); 
	
	
    /**
     * Creates the bundle.
     *
     * @param bundle the bundle
     * @return the created response
     */
    @ApiOperation(value = "Create bundle", nickname = "Create bundle", notes = "Creates a bundle", response = CreatedResponseDto.class)
    @RequestMapping(method = RequestMethod.POST)
    @RolesAllowed({ ROLE_CONFIGURE_BUNDLE })
    @ResponseStatus(HttpStatus.CREATED)
    public abstract CreatedResponseDto createBundle(@RequestBody Bundle bundle);
    
    
    /**
     * Update bundle.
     *
     * @param bundle the bundle
     */
    @ApiOperation(value = "Update bundle", nickname = "Update bundle", notes = "Updates the bundle for the given " + PARAMETER_UUID + " path variable")
    @RequestMapping(method = RequestMethod.PUT, path = PATH_URI_UUID)
    @RolesAllowed({ ROLE_CONFIGURE_BUNDLE })
    @ResponseStatus(HttpStatus.NO_CONTENT)
	public abstract void updateBundle(@RequestBody Bundle bundle);
    
    
    /**
     * Delete bundle.
     *
     * @param guid the uuid
     */
    @ApiOperation(value = "Delete bundle", nickname = "Delete bundle", notes = "Deletes the soure for the given " + PARAMETER_UUID + " path variable")
    @RequestMapping(method = RequestMethod.DELETE, path = PATH_URI_UUID)
    @RolesAllowed({ ROLE_CONFIGURE_BUNDLE })
    @ResponseStatus(HttpStatus.NO_CONTENT)
	public abstract void deleteBundle(@PathVariable(PARAMETER_UUID) UUID uuid);

}
