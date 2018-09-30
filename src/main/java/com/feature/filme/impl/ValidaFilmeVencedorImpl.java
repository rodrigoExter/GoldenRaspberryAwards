package com.feature.filme.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baidu.unbiz.fluentvalidator.ValidationError;
import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.feature.filme.Filme;
import com.feature.filme.FilmeService;

@Component
public class ValidaFilmeVencedorImpl extends ValidatorHandler<Long>{
	
	@Autowired
	private FilmeService service;
	
	public ValidaFilmeVencedorImpl(FilmeService service) {
		this.service = service;
	}
	
	@Override
	public boolean validate(ValidatorContext validatorContext, Long id) {
		
		Optional<Filme> filme = service.get(id);
		
		if(filme != null) {
			if (filme.get().isWinner()) {
				validatorContext.addError(ValidationError.create("Este filme é um vencedor e não pode ser excluído.")
						.setField("cargaHorariaCurso")
						.setErrorCode(1));
				return false;
			} 
		}
		
		return true;
	}
}
