package com.feature.filme.impl;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.base.controller.AbstractControllerImpl;
import com.base.exception.ServiceException;
import com.feature.filme.Filme;
import com.feature.filme.FilmeController;
import com.feature.filme.FilmeDTO;
import com.feature.filme.FilmeService;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/v1" + "/filme")
public class FilmeControllerImpl extends AbstractControllerImpl<Long, Filme, FilmeDTO> implements FilmeController{

	private static final Logger logger = LogManager.getLogger(FilmeControllerImpl.class);
	
	@Autowired
	private FilmeService service;
	


	@Override
	@RequestMapping(value = "/importar", method = RequestMethod.GET, produces = "application/json")
	@ApiResponses({ @ApiResponse(code = 200, message = "Success", response = Boolean.class),
		@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found") })
	@ResponseBody
	public Boolean importFilmes() throws ServiceException, IOException {
		try {
			return service.importFilmes();
		} catch (com.base.exception.ServiceException e) {
			logger.error(e);
			throw new ServiceException("Falha ao tentar ao tentar importar o arquivo CSV.");
		}
	}
	
}
