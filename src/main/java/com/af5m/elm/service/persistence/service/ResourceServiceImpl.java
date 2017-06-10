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

import com.af5m.elm.model.Resource;
import com.af5m.elm.service.persistence.entity.BundleEntity;
import com.af5m.elm.service.persistence.entity.ResourceEntity;
import com.af5m.elm.service.persistence.exception.BadRequestException;
import com.af5m.elm.service.persistence.repository.BundleRepository;
import com.af5m.elm.service.persistence.repository.ResourceRepository;
import com.dell.isg.smi.commons.utilities.PaginationUtils;
import com.dell.isg.smi.commons.utilities.model.PagedResult;

/**
 * The Class ResourceServiceImpl.
 */
@Component
public class ResourceServiceImpl implements ResourceService {
    private static final Logger logger = LoggerFactory.getLogger(ResourceServiceImpl.class);
	
    @Autowired
    ResourceRepository resourceRepository;
    
    @Autowired
    BundleRepository bundleRepository;
    
    @Autowired
    private ModelMapper modelMapper;
    
	/* (non-Javadoc)
	 * @see com.af5m.elm.service.persistence.service.ResourceService#get(java.util.UUID)
	 */
	@Override
	public Resource get(UUID uuid) {
		ResourceEntity resourceEntity = resourceRepository.findByUuid(uuid);
		Resource resource = convertToModel(resourceEntity);
		if(null != resourceEntity.getBundleEntity()){
			resource.setBundleUuid(resourceEntity.getBundleEntity().getUuid());
		}
		return resource;
	}

	/* (non-Javadoc)
	 * @see com.af5m.elm.service.persistence.service.ResourceService#getByName(java.lang.String)
	 */
	@Override
	public PagedResult<Resource> getByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.af5m.elm.service.persistence.service.ResourceService#getAll(int, int)
	 */
	@Override
	public PagedResult<Resource> getAll(int offset, int limit) {
        logger.trace("Entering ResourceServiceImpl getAll");
        List<ResourceEntity> resourceEntityList;

        if (limit > 0) {
            int page = offset / limit;
            PageRequest pageRequest = new PageRequest(page, limit);
            Page<ResourceEntity> resourceEntityPage = resourceRepository.findAll(pageRequest);
            resourceEntityList = resourceEntityPage.getContent();
        } else {
            BadRequestException badRequestException = new BadRequestException();
            //badRequestException.setErrorCode(ErrorCodeEnum.ENUM_BAD_REQUEST_ERROR);
            //badRequestException.addAttribute("limit");
            throw badRequestException;
        }

        if (CollectionUtils.isEmpty(resourceEntityList)) {
            return PaginationUtils.ZERO_RESULT;
        }

        // transform the entities to model (dto) objects
        List<Resource> resourceList = new ArrayList<>();
        for (ResourceEntity resourceEntity : resourceEntityList) {
        	Resource resource = convertToModel(resourceEntity);
            resourceList.add(resource);
        }
        long total = resourceRepository.count();

        // put the model objects into the paged result
        final PagedResult<Resource> pagedResult = PaginationUtils.paginate(resourceList, total, offset, limit);
        logger.trace("Exiting ResourceServiceImpl getAll");
        return pagedResult;
	}

	/* (non-Javadoc)
	 * @see com.af5m.elm.service.persistence.service.ResourceService#save(com.af5m.elm.model.Source)
	 */
	@Override
	public void save(Resource resource) {
		logger.trace("Entering ResourceServiceImpl save");
        if(null == resource ){
        	return;
        }
        
        
        // new up or find the existing entity object
        ResourceEntity resourceEntity = null;
        if(null == resource.getUuid()){
        	resourceEntity = new ResourceEntity();
        }
        else{
        	 resourceEntity = resourceRepository.findByUuid( resource.getUuid() );
        }
        
        // find the organization entity
        BundleEntity bundleEntity = null;
    	if(null != resource.getBundleUuid()){
    		bundleEntity = bundleRepository.findByUuid(resource.getBundleUuid());
    	}
    	resourceEntity.setBundleEntity(bundleEntity);
        
        
        // transform the model to an entity object
        convertToEntityByRef(resource, resourceEntity);
        
        // save the entity
        resourceRepository.save(resourceEntity);
        
        // set the uuid back into the model object byref
        resource.setUuid(resourceEntity.getUuid());
        
        logger.trace("Exiting ResourceServiceImpl save");

	}

	/* (non-Javadoc)
	 * @see com.af5m.elm.service.persistence.service.ResourceService#delete(java.util.UUID)
	 */
	@Override
	public void delete(UUID uuid) {
		resourceRepository.delete(uuid);
	}
	
	private Resource convertToModel(ResourceEntity resourceEntity){
		Resource resource = modelMapper.map(resourceEntity, Resource.class);
		return resource;
	}
	
	private void convertToEntityByRef(Resource resourceModel, ResourceEntity resourceEntity){
		modelMapper.map(resourceModel, resourceEntity);
	}

}
