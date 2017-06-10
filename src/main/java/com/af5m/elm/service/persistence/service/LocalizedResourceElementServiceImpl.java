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

import com.af5m.elm.model.LocalizedResourceElement;
import com.af5m.elm.service.persistence.entity.LocalizedResourceElementEntity;
import com.af5m.elm.service.persistence.exception.BadRequestException;
import com.af5m.elm.service.persistence.repository.BundleRepository;
import com.af5m.elm.service.persistence.repository.LocalizedResourceElementRepository;
import com.dell.isg.smi.commons.utilities.PaginationUtils;
import com.dell.isg.smi.commons.utilities.model.PagedResult;

/**
 * The Class ResourceServiceImpl.
 */
@Component
public class LocalizedResourceElementServiceImpl implements LocalizedResourceElementService{
    private static final Logger logger = LoggerFactory.getLogger(LocalizedResourceElementServiceImpl.class);
	
    @Autowired
    LocalizedResourceElementRepository localizedResourceElementRepository;
    
    @Autowired
    BundleRepository bundleRepository;
    
    @Autowired
    private ModelMapper modelMapper;
    
    
	@Override
	public LocalizedResourceElement get(UUID uuid) {
		LocalizedResourceElementEntity localizedResourceElementEntity = localizedResourceElementRepository.findByUuid(uuid);
		LocalizedResourceElement localizedResourceElement = convertToModel(localizedResourceElementEntity);
		/*
		if(null != localizedResourceElementEntity.getBundleEntity()){
			localizedResourceElement.setBundleUuid(localizedResourceElementEntity.getBundleEntity().getUuid());
		}
		*/
		return localizedResourceElement;
	}


	@Override
	public PagedResult<LocalizedResourceElement> getByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public PagedResult<LocalizedResourceElement> getAll(int offset, int limit) {
        logger.trace("Entering LocalizedResourceElementServiceImpl getAll");
        List<LocalizedResourceElementEntity> localizedResourceElementEntityList;

        if (limit > 0) {
            int page = offset / limit;
            PageRequest pageRequest = new PageRequest(page, limit);
            Page<LocalizedResourceElementEntity> localizedResourceElementEntityPage = localizedResourceElementRepository.findAll(pageRequest);
            localizedResourceElementEntityList = localizedResourceElementEntityPage.getContent();
        } else {
            BadRequestException badRequestException = new BadRequestException();
            //badRequestException.setErrorCode(ErrorCodeEnum.ENUM_BAD_REQUEST_ERROR);
            //badRequestException.addAttribute("limit");
            throw badRequestException;
        }

        if (CollectionUtils.isEmpty(localizedResourceElementEntityList)) {
            return PaginationUtils.ZERO_RESULT;
        }

        // transform the entities to model (dto) objects
        List<LocalizedResourceElement> localizedResourceElementList = new ArrayList<>();
        for (LocalizedResourceElementEntity localizedResourceElementEntity : localizedResourceElementEntityList) {
        	LocalizedResourceElement localizedResourceElement = convertToModel(localizedResourceElementEntity);
        	localizedResourceElementList.add(localizedResourceElement);
        }
        long total = localizedResourceElementRepository.count();

        // put the model objects into the paged result
        final PagedResult<LocalizedResourceElement> pagedResult = PaginationUtils.paginate(localizedResourceElementList, total, offset, limit);
        logger.trace("Exiting LocalizedResourceElementServiceImpl getAll");
        return pagedResult;
	}


	@Override
	public void save(LocalizedResourceElement localizedResourceElement) {
		logger.trace("Entering LocalizedResourceElementServiceImpl save");
        if(null == localizedResourceElement ){
        	return;
        }
        
        
        // new up or find the existing entity object
        LocalizedResourceElementEntity localizedResourceElementEntity = null;
        if(null == localizedResourceElement.getUuid()){
        	localizedResourceElementEntity = new LocalizedResourceElementEntity();
        }
        else{
        	 localizedResourceElementEntity = localizedResourceElementRepository.findByUuid( localizedResourceElement.getUuid() );
        }
        
        // find the organization entity
        /*
        BundleEntity bundleEntity = null;
    	if(null != localizedResourceElement.getBundleUuid()){
    		bundleEntity = bundleRepository.findByUuid(localizedResourceElement.getBundleUuid());
    	}
    	localizedResourceElementEntity.setBundleEntity(bundleEntity);
        */
        
        // transform the model to an entity object
        convertToEntityByRef(localizedResourceElement, localizedResourceElementEntity);
        
        // save the entity
        localizedResourceElementRepository.save(localizedResourceElementEntity);
        
        // set the uuid back into the model object byref
        localizedResourceElement.setUuid(localizedResourceElementEntity.getUuid());
        
        logger.trace("Exiting LocalizedResourceElementServiceImpl save");

	}


	@Override
	public void delete(UUID uuid) {
		localizedResourceElementRepository.delete(uuid);
	}
	
	private LocalizedResourceElement convertToModel(LocalizedResourceElementEntity localizedResourceElementEntity){
		LocalizedResourceElement localizedResourceElementModel = modelMapper.map(localizedResourceElementEntity, LocalizedResourceElement.class);
		return localizedResourceElementModel;
	}
	
	private void convertToEntityByRef(LocalizedResourceElement localizedResourceElementModel, LocalizedResourceElementEntity localizedResourceElementEntity){
		modelMapper.map(localizedResourceElementModel, localizedResourceElementEntity);
	}

}
