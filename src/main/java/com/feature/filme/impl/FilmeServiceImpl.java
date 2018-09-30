package com.feature.filme.impl;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.baidu.unbiz.fluentvalidator.FluentValidator;
import com.base.exception.ServiceException;
import com.base.service.AbstractServiceImpl;
import com.feature.filme.Filme;
import com.feature.filme.FilmeDTO;
import com.feature.filme.FilmeRepository;
import com.feature.filme.FilmeService;

@Service
@Validated
public class FilmeServiceImpl extends AbstractServiceImpl<Long, Filme> implements FilmeService{
	
	@Autowired
	private FilmeRepository repository;
	
	private FilmeService filmeService;
	
	private static ModelMapper mapper = new ModelMapper();

	@Override
	public Boolean importFilmes(String diretorio_csv) throws ServiceException, IOException {
		Boolean retorno = false;
		if(diretorio_csv.isEmpty()) {
			diretorio_csv = "/movielist.csv";
		}
		
		CSVParser csvParser = new CSVParser(new FileReader(diretorio_csv), 
				CSVFormat.newFormat(';')
				.withHeader());

		for (CSVRecord csvRecord : csvParser) {
			int year = Integer.valueOf(csvRecord.get("year"));
			String title = csvRecord.get("title");
			String studios = csvRecord.get("studios");
			String producers = csvRecord.get("producers");
			String winner = String.valueOf(csvRecord.get("winner"));

			System.out.println("Year: " + year);
			System.out.println("Title: " + title);
			System.out.println("Studios: " + studios);
			System.out.println("Producers: " + producers);
			System.out.println("Winner: " + winner);
			System.out.println("--------------------------");
			
			Filme filme = new Filme();
			filme.setYear(Integer.valueOf(csvRecord.get("year")));
			filme.setTitle(csvRecord.get("title"));
			filme.setStudios(csvRecord.get("studios"));
			filme.setProducers(csvRecord.get("producers"));
			if (csvRecord.get("winner").equals("yes")) {
				filme.setWinner(true);				
			} else {
				filme.setWinner(false);
			}

			repository.save(filme);
			retorno = true;
		}
		
		csvParser.close();
		
		return retorno;

	}
	
	@Override
	public List<FilmeDTO> getWinnersByYear(int year) {
		List<Filme> winnersRetorno = this.repository.getWinnersByYear(year);
		return this.convertToDto(winnersRetorno);
	}
	
	@Override
	public List<Object[]> getStudioOrderWinner() {
		return repository.getStudioOrderWinner();
	}
	
	@Override
	public List<Object[]> getYearsMoreWinner() {
		return repository.getYearsMoreWinner();
	}
	
	private List<FilmeDTO> convertToDto(List<Filme> lista) {
		if (lista == null)
			return new ArrayList<>();
		if (lista.isEmpty())
			return new ArrayList<>();
		return lista.stream().map(FilmeServiceImpl::toDto).collect(Collectors.toList());
	}
	
	private static FilmeDTO toDto(Filme filme) {
		return filme == null ? null : mapper.map(filme, FilmeDTO.class);
	}
	
	@Override
	protected FluentValidator beforeDeleteValidate(Long id, FluentValidator checkAll) {
		return super.beforeDeleteValidate(id, checkAll).on(id, new ValidaFilmeVencedorImpl(this.filmeService));
	}

}
