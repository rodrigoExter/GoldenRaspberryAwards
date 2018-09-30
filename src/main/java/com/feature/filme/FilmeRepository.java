package com.feature.filme;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long>{

	@Query(value = "select f from Filme f where f.year = :year and f.winner = true")
	public List<Filme> getWinnersByYear(@Param("year") int year);
	
	@Query(value = "select f.year as year, count(f.winner) as winners from Filme f where f.winner = true group by f.year having count(f.winner) > 1")
	List<Object[]> getYearsMoreWinner();
	
	@Query(value = "select f.studios as studios, count(f.winner) as winners from Filme f where f.winner = true group by f.studios having count(f.winner) > 1 order by count(f.winner) DESC")
	List<Object[]> getStudioOrderWinner();
	
//	public void deleteByFilme(Filme filme);
	
}
