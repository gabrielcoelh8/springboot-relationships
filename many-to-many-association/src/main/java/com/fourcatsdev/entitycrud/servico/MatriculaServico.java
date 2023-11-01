package com.fourcatsdev.entitycrud.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fourcatsdev.entitycrud.repositorio.MatriculaRepositorio;

@Service
public class MatriculaServico {
	
	@Autowired
	private MatriculaRepositorio matriculaRepositorio;
}
