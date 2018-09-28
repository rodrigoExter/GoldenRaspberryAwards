package com.feature.filme.impl;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.base.exception.ServiceException;
import com.base.service.AbstractServiceImpl;
import com.feature.filme.Filme;
import com.feature.filme.FilmeService;

import lombok.extern.java.Log;

@Service
@Validated
public class FilmeServiceImpl extends AbstractServiceImpl<Long, Filme> implements FilmeService{

	@Override
	public Boolean importFilmes() throws ServiceException, IOException {
		Boolean retorno = false;
		final String CSV_FILE_PATH = "movielist.csv";
		final String VIRGULA = ";";
			Reader reader = Files.newBufferedReader(Paths.get(CSV_FILE_PATH));
			CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
			
			for (CSVRecord csvRecord: csvParser) {
				int year = Integer.valueOf(csvRecord.get("year"));
				String title = csvRecord.get("title");
				String studios = csvRecord.get("studios");
				String producers = csvRecord.get("producers");
				boolean winner = Boolean.valueOf(csvRecord.get("winner"));
				
			    System.out.println("Year: " + year);
			    System.out.println("Title: " + title);
			    System.out.println("Studios: " + studios);
			    System.out.println("Producers: " + producers);
			    System.out.println("Winner: " + winner);
			    System.out.println("--------------------------");
			}
	
		
//		BufferedReader reader;
//		try {
//			reader = new BufferedReader(new InputStreamReader(new FileInputStream(CSV_FILE_PATH)));
//
//			String linha = null;
//			try {
//				while((linha = reader.readLine()) != null) {
//					String[] dadosUsuario = linha.split(VIRGULA);
//				    System.out.println(Arrays.toString(dadosUsuario));
//				    System.out.println("Year: " + dadosUsuario[0]);
//				    System.out.println("Title: " + dadosUsuario[1]);
//				    System.out.println("Studios: " + dadosUsuario[2]);
//				    System.out.println("Producers: " + dadosUsuario[3]);
//				    System.out.println("Winner: " + dadosUsuario[4]);
//				    System.out.println("--------------------------");
//				    retorno = true;
//				}
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			try {
//				reader.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		return retorno;

	}
	
}
