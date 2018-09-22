package com.feature.filme.controller;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.validation.constraints.NotNull;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.feature.filme.model.Filme;
import com.feature.filme.repository.FilmeRepository;

@Controller
public class FilmeController {

	private static final Logger logger = LogManager.getLogger(FilmeController.class);
	
	@Autowired
	private FilmeRepository repository;

	@RequestMapping(value = "/importar", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity  importFilmes() throws IOException {

		final String CSV_FILE_PATH = "movielist.csv";

		Reader reader = Files.newBufferedReader(Paths.get(CSV_FILE_PATH));
		CSVParser csvParser = new CSVParser(reader,
				CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
		for (CSVRecord csvRecord : csvParser) {
			Filme filme = new Filme();
			filme.setYear(Integer.valueOf(csvRecord.get("year")));
			filme.setTitle(csvRecord.get("title"));
			filme.setStudios(csvRecord.get("studios"));
			filme.setProducers(csvRecord.get("producers"));
			filme.setWinner(Boolean.valueOf(csvRecord.get("winner")));

			repository.save(filme);
		}
		return ResponseEntity.ok(null);

	}
	
	@RequestMapping(value = "/winnerByYear", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Filme>> getWinnersByYear(int year) {
		return new ResponseEntity<List<Filme>>(repository.getWinnersByYear(year), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/yearsByMoreWinner", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Filme>> getYearsByMoreWinner() {
		return new ResponseEntity<List<Filme>>(repository.getYearsMoreWinner(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/studioOrderWinner", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Filme>> getStudioOrderWinner() {
		return new ResponseEntity<List<Filme>>(repository.getStudioOrderWinner(), HttpStatus.OK);
	}
	

//	@RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
//	public void deleteFilme(@NotNull Filme filme) {
//		try {
//			if (!filme.isWinner()) {
//				repository.deleteByFilme(filme);
//			} else {
//				StringBuilder mensagemErro = new StringBuilder();
//				mensagemErro.append("Este filme é um vencedor e não pode ser excluído.");
//				throw new Exception(mensagemErro.toString());
//			}
//		} catch (Exception e) {
//			logger.error(e.getMessage());
//		}
//
//	}

}
