package com.feature.filme;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long>{

//	@Query(value = "select f from Filme f where f.year = :year and f.winner = true")
//	List<Filme> getWinnersByYear(@Param("year") int year);
//	
//	@Query(value = "select year, count(winner) from Filme f where f.winner = true group by year having count(winner) > 1 ")
//	List<Filme> getYearsMoreWinner();
	
//	@Query(value = "select studios, count(winner) from Filme f where f.winner = true group by studios having count(winner) > 1 order by count(winner) DESC ")
//	List<Filme> getStudioOrderWinner();
	
//	public void deleteByFilme(Filme filme);
	
}
