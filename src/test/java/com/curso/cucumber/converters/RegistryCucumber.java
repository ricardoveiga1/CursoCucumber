package com.curso.cucumber.converters;

import io.cucumber.core.api.TypeRegistry;
import io.cucumber.core.api.TypeRegistryConfigurer;
import io.cucumber.cucumberexpressions.ParameterType;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class RegistryCucumber implements TypeRegistryConfigurer {
//classe de transformação de data
	@Override
	public void configureTypeRegistry(TypeRegistry registry) {
		registry.defineParameterType(
				//tipo, funçao regex(poderia ser .*, mas estava aceitando tudo e prejudicando a geração do steps) e conversão, método lambda ->
				new ParameterType<>("data", "(\\\\d+)/(\\\\d+)/(\\\\d+)", Date.class, (String s) -> {
					DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
					try {
						Date retorno = format.parse(s);
						return retorno;
					} catch (ParseException e) {
						e.printStackTrace();
						return null;
					}
				}) 
			);

		//Posso add um novo registry aqui para centralizar e utilizar em outros pontos
		/*
		registry.defineParameterType(
				//tipo, funçao regex e conversão, método lambda ->
				new ParameterType<>("data", ".*", Date.class, (String s) -> {
					DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
					try {
						Date retorno = format.parse(s);
						return retorno;
					} catch (ParseException e) {
						e.printStackTrace();
						return null;
					}
				})
		);*/

	}

	@Override
	public Locale locale() {
		return Locale.ENGLISH;
	}

}
