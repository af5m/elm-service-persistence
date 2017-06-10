package com.af5m.elm.service.persistence.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.af5m.elm.service.persistence.entity.BundleElementEntity;

/**
 * The Interface BundleElementRepository.
 */
@Repository
public interface BundleElementRepository extends CrudRepository<BundleElementEntity, UUID> {

}
