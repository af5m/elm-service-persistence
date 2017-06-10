package com.af5m.elm.service.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = TableConstants.T_RESOURCE)
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = TableConstants.C_UUID, referencedColumnName = TableConstants.BASE_ENTITY_ID)
public class ResourceEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;
	
	@Column(name="name")
	private String name;

    @ManyToOne(targetEntity = BundleEntity.class, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
	private BundleEntity bundleEntity;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BundleEntity getBundleEntity() {
		return bundleEntity;
	}

	public void setBundleEntity(BundleEntity bundleEntity) {
		this.bundleEntity = bundleEntity;
	}

}
