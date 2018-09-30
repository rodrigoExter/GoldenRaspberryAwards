package com.feature.filme;

import java.io.IOException;
import java.util.List;

import com.base.exception.ServiceException;
import com.base.service.AbstractService;

public interface FilmeService extends AbstractService<Long, Filme>{

	Boolean importFilmes(String diretorio) throws ServiceException, IOException;

	List<FilmeDTO> getWinnersByYear(int year);

	List<Object[]> getStudioOrderWinner();

	List<Object[]> getYearsMoreWinner();
	
}
