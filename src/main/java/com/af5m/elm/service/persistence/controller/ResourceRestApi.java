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

import com.af5m.elm.model.Resource;
import com.af5m.elm.service.persistence.model.CreatedResponseDto;
import com.dell.isg.smi.commons.utilities.model.PagedResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "/api/1.0/organizations/{organization_uuid}/localization/bundles/{bundle_uuid}/resourcess")
@RequestMapping("/api/1.0/organizations/{organization_uuid}/localization/bundles/{bundle_uuid}/resources")
public interface ResourceRestApi {

    static final String OFFSET = "offset";
    static final String LIMIT = "limit";
    static final String PARAMETER_UUID = "uuid";
    static final String PARAMETER_BUNDLE_UUID = "bundle_uuid";
    static final String PARAMETER_ORGANIZATION_UUID = "organization_uuid";
    static final String PARAMETER_NAME = "name";
    
    static final String PATH_URI_UUID = "/{uuid}";
    static final String PATH_URI_ALL_RESOURCES= "";
    
    static final String ROLE_READ = "ROLE_READ";
    static final String ROLE_WRITE = "ROLE_WRITE";
    static final String ROLE_CONFIGURE_RESOURCE = "ROLE_CONFIGURE_RESOURCE";
    
    static final String DEFAULT_OFFSET = "0";
    static final String DEFAULT_LIMIT = "10";
    static final String DEFAULT_STATE = "ALL";
    static final String DEFAULT_CONTENT_TYPE = "application/json";
	
    
    /**
     * Gets the resource.
     *
     * @param uuid the uuid
     * @return the resource
     */
    @ApiOperation(value = "Get Resource (by uuid)", nickname = "Get Resource (by uuid)", notes = "Returns the resource information for the given " + PARAMETER_UUID + " path variable", response = Resource.class)
    @RequestMapping(method = RequestMethod.GET, path = PATH_URI_UUID)
    @RolesAllowed({ ROLE_READ })
	public abstract Resource getResource(@PathVariable(PARAMETER_ORGANIZATION_UUID) UUID organization_uuid, @PathVariable(PARAMETER_BUNDLE_UUID) UUID bundle_uuid, @PathVariable(PARAMETER_UUID) UUID uuid);
	
	
    /**
     * Gets the resources.
     *
     * @param name the name
     * @param offset the offset
     * @param limit the limit
     * @return the resources
     */
    @ApiOperation(value = "Get Resources", nickname = "Get resources", notes = "Gets a paged result of resources using the optional pagination offset and limit provided. " + "The default limit is " + DEFAULT_LIMIT + " records. An optional querystring parameter of '" + PARAMETER_NAME + "' is available to find a resource by name.", response = PagedResult.class)
    @RequestMapping(method = RequestMethod.GET, value = PATH_URI_ALL_RESOURCES, produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed({ ROLE_READ })
	public abstract PagedResult<Resource> getResources( @PathVariable(PARAMETER_ORGANIZATION_UUID) UUID organization_uuid, @PathVariable(PARAMETER_BUNDLE_UUID) UUID bundle_uuid, @RequestParam(name = PARAMETER_NAME, required = false) String name, @RequestParam(name = OFFSET, defaultValue = DEFAULT_OFFSET) Integer offset, @RequestParam(name = LIMIT, defaultValue = DEFAULT_LIMIT) Integer limit); 
	
	
    /**
     * Creates the resource.
     *
     * @param resource the resource
     * @return the created response
     */
    @ApiOperation(value = "Create Resource", nickname = "Create resource", notes = "Creates a resource", response = CreatedResponseDto.class)
    @RequestMapping(method = RequestMethod.POST)
    @RolesAllowed({ ROLE_CONFIGURE_RESOURCE })
    @ResponseStatus(HttpStatus.CREATED)
    public abstract CreatedResponseDto createResource(@PathVariable(PARAMETER_ORGANIZATION_UUID) UUID organization_uuid, @PathVariable(PARAMETER_BUNDLE_UUID) UUID bundle_uuid, @RequestBody Resource resource);
    
    
    /**
     * Update resource.
     *
     * @param resource the resource
     */
    @ApiOperation(value = "Update Resource", nickname = "Update resource", notes = "Updates the resource for the given " + PARAMETER_UUID + " path variable")
    @RequestMapping(method = RequestMethod.PUT, path = PATH_URI_UUID)
    @RolesAllowed({ ROLE_CONFIGURE_RESOURCE })
    @ResponseStatus(HttpStatus.NO_CONTENT)
	public abstract void updateResource(@PathVariable(PARAMETER_ORGANIZATION_UUID) UUID organization_uuid, @PathVariable(PARAMETER_BUNDLE_UUID) UUID bundle_uuid, @RequestBody Resource resource);
    
    
    /**
     * Delete resource.
     *
     * @param guid the uuid
     */
    @ApiOperation(value = "Delete Resource", nickname = "Delete Resource", notes = "Deletes the soure for the given " + PARAMETER_UUID + " path variable")
    @RequestMapping(method = RequestMethod.DELETE, path = PATH_URI_UUID)
    @RolesAllowed({ ROLE_CONFIGURE_RESOURCE })
    @ResponseStatus(HttpStatus.NO_CONTENT)
	public abstract void deleteResource(@PathVariable(PARAMETER_ORGANIZATION_UUID) UUID organization_uuid, @PathVariable(PARAMETER_BUNDLE_UUID) UUID bundle_uuid, @PathVariable(PARAMETER_UUID) UUID uuid);
	
}
