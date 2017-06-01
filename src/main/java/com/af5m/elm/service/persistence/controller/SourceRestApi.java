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

import com.af5m.elm.model.Source;
import com.af5m.elm.service.persistence.model.CreatedResponseDto;
import com.dell.isg.smi.commons.utilities.model.PagedResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * The Interface SourceRestApi.
 */
@RestController
@Api(value = "/api/1.0/localization/sources")
@RequestMapping("/api/1.0/localization/sources")
public interface SourceRestApi {

    static final String OFFSET = "offset";
    static final String LIMIT = "limit";
    static final String PARAMETER_UUID = "uuid";
    static final String PARAMETER_NAME = "name";
    
    static final String PATH_URI_UUID = "/{uuid}";
    static final String PATH_URI_ALL_SOURCES = "";
    
    static final String ROLE_READ = "ROLE_READ";
    static final String ROLE_WRITE = "ROLE_WRITE";
    static final String ROLE_CONFIGURE_SOURCE = "ROLE_CONFIGURE_SOURCE";
    
    static final String DEFAULT_OFFSET = "0";
    static final String DEFAULT_LIMIT = "10";
    static final String DEFAULT_STATE = "ALL";
    static final String DEFAULT_CONTENT_TYPE = "application/json";
    
    
    /**
     * Gets the source.
     *
     * @param uuid the uuid
     * @return the source
     */
    @ApiOperation(value = "Get source (by uuid)", nickname = "Get source (by uuid)", notes = "Returns the source information for the given " + PARAMETER_UUID + " path variable", response = Source.class)
    @RequestMapping(method = RequestMethod.GET, path = PATH_URI_UUID)
    @RolesAllowed({ ROLE_READ })
	public abstract Source getSource(@PathVariable(PARAMETER_UUID) UUID uuid);
	
	
    /**
     * Gets the sources.
     *
     * @param name the name
     * @param offset the offset
     * @param limit the limit
     * @return the sources
     */
    @ApiOperation(value = "Get sources", nickname = "Get sources", notes = "Gets a paged result of sources using the optional pagination offset and limit provided. " + "The default limit is " + DEFAULT_LIMIT + " records. An optional querystring parameter of '" + PARAMETER_NAME + "' is available to find a source by name.", response = PagedResult.class)
    @RequestMapping(method = RequestMethod.GET, value = PATH_URI_ALL_SOURCES, produces = MediaType.APPLICATION_JSON_VALUE)
    @RolesAllowed({ ROLE_READ })
	public abstract PagedResult<Source> getSources(@RequestParam(name = PARAMETER_NAME, required = false) String name, @RequestParam(name = OFFSET, defaultValue = DEFAULT_OFFSET) Integer offset, @RequestParam(name = LIMIT, defaultValue = DEFAULT_LIMIT) Integer limit); 
	
	
    /**
     * Creates the source.
     *
     * @param source the source
     * @return the created response
     */
    @ApiOperation(value = "Create source", nickname = "Create source", notes = "Creates a source", response = CreatedResponseDto.class)
    @RequestMapping(method = RequestMethod.POST)
    @RolesAllowed({ ROLE_CONFIGURE_SOURCE })
    @ResponseStatus(HttpStatus.CREATED)
    public abstract CreatedResponseDto createSource(@RequestBody Source source);
    
    
    /**
     * Update source.
     *
     * @param source the source
     */
    @ApiOperation(value = "Update source", nickname = "Update source", notes = "Updates the source for the given " + PARAMETER_UUID + " path variable")
    @RequestMapping(method = RequestMethod.PUT, path = PATH_URI_UUID)
    @RolesAllowed({ ROLE_CONFIGURE_SOURCE })
    @ResponseStatus(HttpStatus.NO_CONTENT)
	public abstract void updateSource(@RequestBody Source source);
    
    
    /**
     * Delete source.
     *
     * @param guid the guid
     */
    @ApiOperation(value = "Delete source", nickname = "Delete source", notes = "Deletes the soure for the given " + PARAMETER_UUID + " path variable")
    @RequestMapping(method = RequestMethod.DELETE, path = PATH_URI_UUID)
    @RolesAllowed({ ROLE_CONFIGURE_SOURCE })
    @ResponseStatus(HttpStatus.NO_CONTENT)
	public abstract void deleteSource(@PathVariable(PARAMETER_UUID) UUID guid);

}
