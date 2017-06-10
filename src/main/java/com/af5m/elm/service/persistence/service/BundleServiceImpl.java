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

import com.af5m.elm.model.Bundle;
import com.af5m.elm.service.persistence.entity.OrganizationEntity;
import com.af5m.elm.service.persistence.entity.BundleEntity;
import com.af5m.elm.service.persistence.exception.BadRequestException;
import com.af5m.elm.service.persistence.repository.OrganizationRepository;
import com.af5m.elm.service.persistence.repository.BundleRepository;
import com.dell.isg.smi.commons.utilities.PaginationUtils;
import com.dell.isg.smi.commons.utilities.model.PagedResult;

/**
 * The Class BundleServiceImpl.
 */
@Component
public class BundleServiceImpl implements BundleService {
    private static final Logger logger = LoggerFactory.getLogger(BundleServiceImpl.class);
	
    @Autowired
    BundleRepository bundleRepository;
    
    @Autowired
    OrganizationRepository organizationRepository;
    
    @Autowired
    private ModelMapper modelMapper;
    
	/* (non-Javadoc)
	 * @see com.af5m.elm.service.persistence.service.BundleService#get(java.util.UUID)
	 */
	@Override
	public Bundle get(UUID uuid) {
		BundleEntity bundleEntity = bundleRepository.findByUuid(uuid);
		Bundle source = convertToModel(bundleEntity);
		if(null != bundleEntity.getOrganizationEntity()){
			source.setOrganizationUuid(bundleEntity.getOrganizationEntity().getUuid());
		}
		return source;
	}

	/* (non-Javadoc)
	 * @see com.af5m.elm.service.persistence.service.BundleService#getByName(java.lang.String)
	 */
	@Override
	public PagedResult<Bundle> getByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.af5m.elm.service.persistence.service.BundleService#getAll(int, int)
	 */
	@Override
	public PagedResult<Bundle> getAll(int offset, int limit) {
        logger.trace("Entering BundleServiceImpl getAll");
        List<BundleEntity> bundleEntityList;

        if (limit > 0) {
            int page = offset / limit;
            PageRequest pageRequest = new PageRequest(page, limit);
            Page<BundleEntity> bundleEntityPage = bundleRepository.findAll(pageRequest);
            bundleEntityList = bundleEntityPage.getContent();
        } else {
            BadRequestException badRequestException = new BadRequestException();
            //badRequestException.setErrorCode(ErrorCodeEnum.ENUM_BAD_REQUEST_ERROR);
            //badRequestException.addAttribute("limit");
            throw badRequestException;
        }

        if (CollectionUtils.isEmpty(bundleEntityList)) {
            return PaginationUtils.ZERO_RESULT;
        }

        // transform the entities to model (dto) objects
        List<Bundle> bundleList = new ArrayList<>();
        for (BundleEntity bundleEntity : bundleEntityList) {
        	Bundle bundle = convertToModel(bundleEntity);
            bundleList.add(bundle);
        }
        long total = bundleRepository.count();

        // put the model objects into the paged result
        final PagedResult<Bundle> pagedResult = PaginationUtils.paginate(bundleList, total, offset, limit);
        logger.trace("Exiting BundleServiceImpl getAll");
        return pagedResult;
	}

	/* (non-Javadoc)
	 * @see com.af5m.elm.service.persistence.service.BundleService#save(com.af5m.elm.model.Source)
	 */
	@Override
	public void save(Bundle bundle) {
		logger.trace("Entering BundleServiceImpl save");
        if(null == bundle ){
        	return;
        }
        
        
        // new up or find the existing entity object
        BundleEntity bundleEntity = null;
        if(null == bundle.getUuid()){
        	bundleEntity = new BundleEntity();
        }
        else{
        	 bundleEntity = bundleRepository.findByUuid( bundle.getUuid() );
        }
        
        // find the organization entity
        OrganizationEntity organizationEntity = null;
    	if(null != bundle.getOrganizationUuid()){
    		organizationEntity = organizationRepository.findByUuid(bundle.getOrganizationUuid());
    	}
    	bundleEntity.setOrganizationEntity(organizationEntity);
        
        
        // transform the model to an entity object
        convertToEntityByRef(bundle, bundleEntity);
        
        // save the entity
        bundleRepository.save(bundleEntity);
        
        // set the uuid back into the model object byref
        bundle.setUuid(bundleEntity.getUuid());
        
        logger.trace("Exiting BundleServiceImpl save");

	}

	/* (non-Javadoc)
	 * @see com.af5m.elm.service.persistence.service.BundleService#delete(java.util.UUID)
	 */
	@Override
	public void delete(UUID uuid) {
		bundleRepository.delete(uuid);
	}
	
	private Bundle convertToModel(BundleEntity bundleEntity){
		Bundle source = modelMapper.map(bundleEntity, Bundle.class);
		return source;
	}
	
	private void convertToEntityByRef(Bundle bundleModel, BundleEntity bundleEntity){
		modelMapper.map(bundleModel, bundleEntity);
	}

}
