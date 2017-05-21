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

import com.af5m.elm.model.Organization;
import com.af5m.elm.service.persistence.model.CreatedResponse;
import com.dell.isg.smi.commons.utilities.model.PagedResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * The Interface OrganizationsRestApi.
 */
@RestController
@Api(value = "/api/1.0/organizations")
@RequestMapping("/api/1.0/organizations")
public interface OrganizationRestApi {
	
    static final String OFFSET = "offset";
    static final String LIMIT = "limit";
    static final String PARAMETER_UUID = "uuid";
    static final String PARAMETER_NAME = "name";
    
    static final String PATH_URI_UUID = "/{uuid}";
    static final String PATH_URI_ALL_ORGANIZATIONS = "";
    
    static final String ROLE_READ = "ROLE_READ";
    static final String ROLE_WRITE = "ROLE_WRITE";
    static final String ROLE_CONFIGURE_ORGANIZATION = "ROLE_CONFIGURE_ORGANIZATION";

    
    static final String DEFAULT_OFFSET = "0";
    static final String DEFAULT_LIMIT = "10";
    static final String DEFAULT_STATE = "ALL";
    static final String DEFAULT_CONTENT_TYPE = "application/json";

    /**
     * Gets an organization by guid.
     *
     * @param guid the guid
     * @return the organization
     */
    @ApiOperation(value = "Get organization (by uuid)", nickname = "Get organization (by uuid)", notes = "Returns the organization information for the given " + PARAMETER_UUID + " path variable", response = Organization.class)
    @RequestMapping(method = RequestMethod.GET, path = PATH_URI_UUID)
    @RolesAllowed({ ROLE_READ })
	public abstract Organization getOrganization(@PathVariable(PARAMETER_UUID) UUID guid);
	

    /**
     * Gets the organizations.
     *
     * @param name the name
     * @param offset the offset
     * @param limit the limit
     * @return the organizations
     */
    @ApiOperation(value = "Get organizations", nickname = "Get organizations", notes = "Gets a paged result of organizations using the optional pagination offset and limit provided. " + "The default limit is " + DEFAULT_LIMIT + " records. An optional querystring parameter of '" + PARAMETER_NAME + "' is available to find a organization by name.", response = PagedResult.class)
    @RequestMapping(method = RequestMethod.GET, value = PATH_URI_ALL_ORGANIZATIONS, produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed({ ROLE_READ })
	public abstract PagedResult<Organization> getOrganizations(@RequestParam(name = PARAMETER_NAME, required = false) String name, @RequestParam(name = OFFSET, defaultValue = DEFAULT_OFFSET) Integer offset, @RequestParam(name = LIMIT, defaultValue = DEFAULT_LIMIT) Integer limit); 
	
	
    /**
     * Creates the organization.
     *
     * @param organization the organization
     * @return the organization created response
     */
    @ApiOperation(value = "Create organization", nickname = "Create organization", notes = "Creates an organization", response = CreatedResponse.class)
    @RequestMapping(method = RequestMethod.POST)
    @RolesAllowed({ ROLE_CONFIGURE_ORGANIZATION })
    @ResponseStatus(HttpStatus.CREATED)
    public abstract CreatedResponse createOrganization(@RequestBody Organization organization);
    
    
    /**
     * Update organization.
     *
     * @param organization the organization
     */
    @ApiOperation(value = "Update organization", nickname = "Update organization", notes = "Updates the organization for the given " + PARAMETER_UUID + " path variable")
    @RequestMapping(method = RequestMethod.PUT, path = PATH_URI_UUID)
    @RolesAllowed({ ROLE_CONFIGURE_ORGANIZATION })
    @ResponseStatus(HttpStatus.NO_CONTENT)
	public abstract void updateOrganization(@RequestBody Organization organization); 
	
    
    /**
     * Deletes an organization
     *
     * @param guid the guid
     */
    @ApiOperation(value = "Delete organization", nickname = "Delete organization", notes = "Deletes the organization for the given " + PARAMETER_UUID + " path variable")
    @RequestMapping(method = RequestMethod.DELETE, path = PATH_URI_UUID)
    @RolesAllowed({ ROLE_CONFIGURE_ORGANIZATION })
    @ResponseStatus(HttpStatus.NO_CONTENT)
	public abstract void deleteOrganization(@PathVariable(PARAMETER_UUID) UUID guid);

}
