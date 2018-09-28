package com.feature.filme;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.validator.constraints.NotBlank;

import com.base.entity.AbstractEntityClass;

@Entity
@DynamicUpdate
@Table(name = "filme", uniqueConstraints = @UniqueConstraint(name = "filme_ukey", columnNames = {
		"year","title","studios" }))
@GenericGenerator(
        name = "generator",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
                @Parameter(name = "sequence_name", value = "sq_filme"),
                @Parameter(name = "initial_value", value = "1"),
                @Parameter(name = "increment_size", value = "1")
        }
)
public class Filme extends AbstractEntityClass<Long>{
	
	private static final long serialVersionUID = 6250470230477012320L;

	@Id
	@GeneratedValue(generator = "generator")
	@Column(unique = true, nullable = false)
	private Long id;
	
	@NotNull
	private int year;
	
	@NotBlank
	private String title;
	
	@NotBlank
	private String studios;
	
	@NotBlank
	private String producers;
	
	private boolean winner;
	
	@Override
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((producers == null) ? 0 : producers.hashCode());
		result = prime * result + ((studios == null) ? 0 : studios.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + (winner ? 1231 : 1237);
		result = prime * result + year;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Filme other = (Filme) obj;
		if (producers == null) {
			if (other.producers != null)
				return false;
		} else if (!producers.equals(other.producers))
			return false;
		if (studios == null) {
			if (other.studios != null)
				return false;
		} else if (!studios.equals(other.studios))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (winner != other.winner)
			return false;
		if (year != other.year)
			return false;
		return true;
	}
	
	
	
}
