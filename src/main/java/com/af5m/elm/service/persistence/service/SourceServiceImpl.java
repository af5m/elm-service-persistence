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

import com.af5m.elm.model.Source;
import com.af5m.elm.service.persistence.entity.SourceEntity;
import com.af5m.elm.service.persistence.exception.BadRequestException;
import com.af5m.elm.service.persistence.repository.SourceRepository;
import com.dell.isg.smi.commons.utilities.PaginationUtils;
import com.dell.isg.smi.commons.utilities.model.PagedResult;

/**
 * The Class SourceServiceImpl.
 */
@Component
public class SourceServiceImpl implements SourceService {
    private static final Logger logger = LoggerFactory.getLogger(SourceServiceImpl.class);
	
    @Autowired
    SourceRepository sourceRepository;
    
    @Autowired
    private ModelMapper modelMapper;
    
	/* (non-Javadoc)
	 * @see com.af5m.elm.service.persistence.service.SourceService#get(java.util.UUID)
	 */
	@Override
	public Source get(UUID uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.af5m.elm.service.persistence.service.SourceService#getByName(java.lang.String)
	 */
	@Override
	public PagedResult<Source> getByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.af5m.elm.service.persistence.service.SourceService#getAll(int, int)
	 */
	@Override
	public PagedResult<Source> getAll(int offset, int limit) {
        logger.trace("Entering SourceServiceImpl getAll");
        List<SourceEntity> soureEntityList;

        if (limit > 0) {
            int page = offset / limit;
            PageRequest pageRequest = new PageRequest(page, limit);
            Page<SourceEntity> sourceEntityPage = sourceRepository.findAll(pageRequest);
            soureEntityList = sourceEntityPage.getContent();
        } else {
            BadRequestException badRequestException = new BadRequestException();
            //badRequestException.setErrorCode(ErrorCodeEnum.ENUM_BAD_REQUEST_ERROR);
            //badRequestException.addAttribute("limit");
            throw badRequestException;
        }

        if (CollectionUtils.isEmpty(soureEntityList)) {
            return PaginationUtils.ZERO_RESULT;
        }

        // transform the entities to model (dto) objects
        List<Source> sourceList = new ArrayList<>();
        for (SourceEntity sourceEntity : soureEntityList) {
        	Source source = convertToModel(sourceEntity);
            sourceList.add(source);
        }
        long total = sourceRepository.count();

        // put the model objects into the paged result
        final PagedResult<Source> pagedResult = PaginationUtils.paginate(sourceList, total, offset, limit);
        logger.trace("Exiting SourceServiceImpl getAll");
        return pagedResult;
	}

	/* (non-Javadoc)
	 * @see com.af5m.elm.service.persistence.service.SourceService#save(com.af5m.elm.model.Source)
	 */
	@Override
	public void save(Source source) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.af5m.elm.service.persistence.service.SourceService#delete(java.util.UUID)
	 */
	@Override
	public void delete(UUID uuid) {
		// TODO Auto-generated method stub

	}
	
	private Source convertToModel(SourceEntity sourceEntity){
		Source source = modelMapper.map(sourceEntity, Source.class);
		return source;
	}
	
	private void convertToEntityByRef(Source sourceModel, SourceEntity sourceEntity){
		modelMapper.map(sourceModel, sourceEntity);
	}

}
