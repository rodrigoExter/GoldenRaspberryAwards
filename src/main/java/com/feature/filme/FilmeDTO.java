package com.feature.filme;

import com.base.dto.AbstractDTO;

public class FilmeDTO implements AbstractDTO<Long, Filme>{
	
	private Long id;
	
	private int year;
		
	private String title;
	
	private String studios;
	
	private String producers;
	
	private boolean winner;
	
	public FilmeDTO() { }
	
	public FilmeDTO(Long id) {
		this.setId(id);
	}
	
	public FilmeDTO(Long id, int year, String title, String studios, String producers, boolean winner) {
		this.setId(id);
		this.setYear(year);
		this.setTitle(title);
		this.setStudios(studios);
		this.setProducers(producers);
		this.setWinner(winner);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStudios() {
		return studios;
	}

	public void setStudios(String studios) {
		this.studios = studios;
	}

	public String getProducers() {
		return producers;
	}

	public void setProducers(String producers) {
		this.producers = producers;
	}

	public boolean isWinner() {
		return winner;
	}

	public void setWinner(boolean winner) {
		this.winner = winner;
	}
		
}
