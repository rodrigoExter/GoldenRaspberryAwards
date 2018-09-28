package com.base.entity;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractEntityClass<PK extends Serializable> implements Serializable {

	
	private static final long serialVersionUID = -4188359917172477657L;

	public abstract PK getId();
}
