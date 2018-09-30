package com.base.test.features.units.filme;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.base.exception.ServiceException;
import com.base.util.pageable.PageableCustom;
import com.feature.filme.Filme;
import com.feature.filme.FilmeDTO;
import com.feature.filme.FilmeRepository;
import com.feature.filme.FilmeService;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ActiveProfiles("test")
public class FilmeServiceTest {

	@Autowired
	private FilmeService service;
	
	@MockBean
	private FilmeRepository repository;
	
	@Test
	public void getWinnersByYearNotNull() throws ServiceException {
		List<Filme> lista =  new ArrayList<>(); 
		Filme filme = new Filme();
		filme.setYear(1985);
		lista.add(filme);
		when(repository.getWinnersByYear(1985)).thenReturn(lista);
		List<FilmeDTO> filmes = service.getWinnersByYear(1985);
		Assert.assertNotNull( filmes );
	}
	
	@Test
	public void getStudioOrderWinnerNotNull() throws ServiceException {
		List<Object[]> lista =  new ArrayList<>(); 
		Filme filme = new Filme();
		filme.setYear(1985);
		filme.setStudios("Teste Studio vencedor");
		filme.setWinner(true);
		lista.addAll((java.util.Collection<? extends Object[]>) filme);
		when(repository.getStudioOrderWinner()).thenReturn(lista);
		List<Object[]> filmes = service.getStudioOrderWinner();
		Assert.assertNotNull( filmes );
	}
	
	@Test
	public void getYearsMoreWinnerNotNull() throws ServiceException {
		List<Object[]> lista =  new ArrayList<>(); 
		Filme filme = new Filme();
		filme.setYear(1985);
		filme.setStudios("Teste Ano Vencedor");
		filme.setWinner(true);
		lista.addAll((java.util.Collection<? extends Object[]>) filme);
		when(repository.getYearsMoreWinner()).thenReturn(lista);
		List<Object[]> filmes = service.getYearsMoreWinner();
		Assert.assertNotNull( filmes );
	}
	
	
	
	@Test
	public void getStudioOrderWinnerNull() throws ServiceException {
		when(repository.getStudioOrderWinner()).thenReturn(null);
		List<Object[]> filmes = service.getStudioOrderWinner();
		Assert.assertNull( filmes );
	}
	
	@Test
	public void getYearsMoreWinnerNull() throws ServiceException {
		when(repository.getYearsMoreWinner()).thenReturn(null);
		List<Object[]> filmes = service.getYearsMoreWinner();
		Assert.assertNull( filmes );
	}
	
	@Test
	@Rollback(false)
	public void getWinnersByYearNull() {
//		List<Filme> lista =  new ArrayList<>();
//		lista.add(null);
//		when(repository.getWinnersByYear(1)).thenReturn(lista);
//		List<FilmeDTO> filmes = service.getWinnersByYear(1);
//		Assert.assertEquals("", filmes);
		
		PageableCustom pageableCustom = new PageableCustom(0, 30, null);
		List<Filme> lista = new ArrayList<>();
		lista.add(null);
		Page<Filme> pagedResponse = new PageImpl<>(lista);
		when(repository.findAll(pageableCustom)).thenReturn(pagedResponse);
		List<FilmeDTO> turmas = service.getWinnersByYear(1);
		Assert.assertNull(turmas);
	}
}
