package com.base.dto;

import java.io.Serializable;

import com.base.entity.AbstractEntityClass;

public interface AbstractDTO<PK extends Serializable, T extends AbstractEntityClass<PK>> {

}
