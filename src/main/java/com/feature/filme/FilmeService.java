package com.feature.filme;

import java.io.IOException;

import com.base.exception.ServiceException;
import com.base.service.AbstractService;

public interface FilmeService extends AbstractService<Long, Filme>{

	Boolean importFilmes() throws ServiceException, IOException;
	
}
