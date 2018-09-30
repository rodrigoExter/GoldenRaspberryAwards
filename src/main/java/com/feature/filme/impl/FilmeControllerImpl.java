package com.feature.filme.impl;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.base.controller.AbstractControllerImpl;
import com.base.exception.ServiceException;
import com.feature.filme.Filme;
import com.feature.filme.FilmeController;
import com.feature.filme.FilmeDTO;
import com.feature.filme.FilmeService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/v1" + "/filme")
public class FilmeControllerImpl extends AbstractControllerImpl<Long, Filme, FilmeDTO> implements FilmeController{

	private static final Logger logger = LogManager.getLogger(FilmeControllerImpl.class);
	
	@Autowired
	private FilmeService service;
	
	@RequestMapping(value = "/importar", method = RequestMethod.POST, produces = "application/json")
	@ApiResponses({ @ApiResponse(code = 200, message = "Success", response = Boolean.class),
		@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found") })
	@ResponseBody
	public Boolean importFilmes(@RequestParam(value = "diretorio") String diretorio) throws ServiceException, IOException {
		try {
			return service.importFilmes(diretorio);
		} catch (com.base.exception.ServiceException e) {
			logger.error(e);
			throw new ServiceException("Falha ao tentar ao tentar importar o arquivo CSV.");
		}
	}
	
	@GetMapping(value = "/winnerByYear")
	@ApiResponses({ @ApiResponse(code = 200, message = "Success", response = Boolean.class),
		@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found") })
	@ApiOperation(value = "Retorna lista de Filmes Vencedores no Ano")
	public ResponseEntity<List<FilmeDTO>> getWinnersByYear(@RequestParam("year") int year) {
		return ResponseEntity.ok(service.getWinnersByYear(year));
	}
	
	@RequestMapping(value = "/yearsMoreWinner", method = RequestMethod.GET, produces = "application/json")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Success", responseContainer = "List"),
		@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found") })
	@ApiOperation(value = "Retorna lista dos anos que mais tiveram vencedores")
	public List<Object[]> getYearsMoreWinner() {
		return service.getYearsMoreWinner();
	}
	
	
	
	@RequestMapping(value = "/studioOrderWinner", method = RequestMethod.GET, produces = "application/json")
	@ApiResponses({
		@ApiResponse(code = 200, message = "Success", responseContainer = "List"),
		@ApiResponse(code = 400, message = "Bad request"), @ApiResponse(code = 404, message = "Not found") })
	@ApiOperation(value = "Retorna lista dos studios que mais venceram por ordem de maior vencedor")
	public List<Object[]> getStudioOrderWinner() {
		return service.getStudioOrderWinner();
	}
	
//	@RequestMapping(value = "/{id:.+}", produces = MediaType.APPLICATION_JSON_VALUE,  method = RequestMethod.DELETE)
//	@ApiResponses({
//		@ApiResponse(code = 200, message = "Success"),
//		@ApiResponse(code = 400, message = "Bad request"),
//		@ApiResponse(code = 404, message = "Not found")
//	})
//	public void delete(@PathVariable("id") Long id) {
//		try {
//			this.service.delete(id);
//		} catch (Exception e) {
//			logger.error(e.getMessage());
//		}
//	}
	
}
