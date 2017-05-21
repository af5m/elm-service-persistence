package com.af5m.elm.service.persistence.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.collections4.CollectionUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.af5m.elm.model.Organization;
import com.af5m.elm.service.persistence.entity.OrganizationEntity;
import com.af5m.elm.service.persistence.exception.BadRequestException;
import com.af5m.elm.service.persistence.repository.OrganizationRepository;
import com.dell.isg.smi.commons.utilities.PaginationUtils;
import com.dell.isg.smi.commons.utilities.model.PagedResult;

/**
 * The Class OrganizationServiceImpl.
 */
@Component
public class OrganizationServiceImpl implements OrganizationService {
    private static final Logger logger = LoggerFactory.getLogger(OrganizationServiceImpl.class);
    
    @Autowired
    OrganizationRepository organizationRepository;
    
    @Autowired
    private ModelMapper modelMapper;

	/* (non-Javadoc)
	 * @see com.af5m.elm.service.persistence.service.OrganizationService#get(java.util.UUID)
	 */
	@Override
	public Organization get(UUID guid) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.af5m.elm.service.persistence.service.OrganizationService#getAll()
	 */
	@Override
	public PagedResult<Organization> getAll(int offset, int limit) {
        logger.trace("Entering OrganizationServiceImpl getAll");
        List<OrganizationEntity> organizationEntityList;

        if (limit > 0) {
            int page = offset / limit;
            PageRequest pageRequest = new PageRequest(page, limit);
            Page<OrganizationEntity> organizationPage = organizationRepository.findAll(pageRequest);
            organizationEntityList = organizationPage.getContent();
        } else {
            BadRequestException badRequestException = new BadRequestException();
            //badRequestException.setErrorCode(ErrorCodeEnum.ENUM_BAD_REQUEST_ERROR);
            //badRequestException.addAttribute("limit");
            throw badRequestException;
        }

        if (CollectionUtils.isEmpty(organizationEntityList)) {
            return PaginationUtils.ZERO_RESULT;
        }

        // transform the entities to model (dto) objects
        List<Organization> networksList = new ArrayList<>();
        for (OrganizationEntity organizationEntity : organizationEntityList) {
        	Organization network = convertToModel(organizationEntity);
            networksList.add(network);
        }
        long total = organizationRepository.count();

        // put the model objects into the paged result
        final PagedResult<Organization> pagedResult = PaginationUtils.paginate(networksList, total, offset, limit);
        logger.trace("Exiting OrganizationServiceImpl getAll");
        return pagedResult;
	}

	/* (non-Javadoc)
	 * @see com.af5m.elm.service.persistence.service.OrganizationService#save(com.af5m.elm.model.Organization)
	 */
	@Override
	public void save(Organization organization) {
        logger.trace("Entering OrganizationServiceImpl save");
        if(null == organization ){
        	return;
        }
        
        // new up or find the existing entity object
        OrganizationEntity organizationEntity = null;
        if(organization.getUuid() == null){
        	organizationEntity = new OrganizationEntity();
        }
        else{
        	 organizationEntity = organizationRepository.findByUuid( organization.getUuid() );
        }
        
        // transform the model to an entity object
        convertToEntityByRef(organization, organizationEntity);
        
        // save the entity
        organizationRepository.save(organizationEntity);
        
        // set the uuid back into the model object byref
        organization.setUuid(organizationEntity.getUuid());
        
        logger.trace("Exiting OrganizationServiceImpl save");
	}

	/* (non-Javadoc)
	 * @see com.af5m.elm.service.persistence.service.OrganizationService#delete(java.util.UUID)
	 */
	@Override
	public void delete(UUID guid) {
        logger.trace("Entering OrganizationServiceImpl delete with uuid {}", guid);
        organizationRepository.delete(guid);
        logger.trace("Exiting OrganizationServiceImpl delete");
	}

	@Override
	public PagedResult<Organization> getByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	private Organization convertToModel(OrganizationEntity organizationEntity){
		Organization organization = modelMapper.map(organizationEntity, Organization.class);
		return organization;
	}
	
	private void convertToEntityByRef(Organization organizationModel, OrganizationEntity organizationEntity){
		modelMapper.map(organizationModel, organizationEntity);
	}
	

}
